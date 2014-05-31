package cc.tech;

import cc.tech.utils.HtmlMetadataUtils;
import com.beust.jcommander.internal.Sets;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.apache.commons.io.IOUtils;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.archive.io.ArchiveReader;
import org.archive.io.ArchiveRecord;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class ScriptTags {
    private static final Logger LOG = LoggerFactory.getLogger(ScriptTags.class);

    protected static class ScriptTagsMapper extends Mapper<Text, ArchiveReader, Text,
                LongWritable> {

        private Text outKey = new Text();
        private LongWritable outVal = new LongWritable(1);

        private Set<String> crunchbaseDomains = Sets.newHashSet();

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            super.setup(context);
            Path[] paths = DistributedCache.getLocalCacheFiles(context.getConfiguration());
            File crunchbaseFile = new File(paths[0].toString());

            BufferedReader br = Files.newReader(crunchbaseFile, Charsets.UTF_8);
            String line;
            while ((line = br.readLine()) != null) {
                if (!crunchbaseDomains.contains(line)) {
                    crunchbaseDomains.add(line);
                }
            }

            LOG.info("crunchbaseDomains : {}", crunchbaseDomains.size());
        }

        @Override
        protected void map(Text key, ArchiveReader archiveReader, Context context)
                throws IOException, InterruptedException {
            for (ArchiveRecord archiveRecord : archiveReader) {
                // Skip any records that are not JSON
                if (!archiveRecord.getHeader().getMimetype().equals("application/json")) {
                    continue;
                }

                try {
                    context.getCounter("MAP", "COUNTER").increment(1);

                    // Convenience function that reads the full message into a raw byte array
                    byte[] rawData = IOUtils.toByteArray(archiveRecord, archiveRecord.available());
                    String content = new String(rawData);
                    JSONObject json = new JSONObject(content);

                    String pageDomain = HtmlMetadataUtils.getPageDomain(json);

                    //skip domains not in crunchbase
                    if (!crunchbaseDomains.contains(pageDomain)) {
                        context.getCounter("MAP", "NOT-CB").increment(1);
                        continue;
                    }

                    context.getCounter("MAP", "CB").increment(1);

                    List<String> domains = HtmlMetadataUtils.getURLDomainFromScriptTags(json,
                            "text/javascript");
                    for (String domain : domains) {
                        outKey.set(String.format("%s\t%s", pageDomain, domain));
                        context.write(outKey, outVal);
                    }
                } catch (Exception ex) {
                    //LOG.error("caught exception", ex);
                    context.getCounter("MAP", "EXCEPTIONS").increment(1);
                }
            }
        }


    }
}
