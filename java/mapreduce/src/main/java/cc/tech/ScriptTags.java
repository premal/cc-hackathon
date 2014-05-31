package cc.tech;

import cc.tech.utils.HtmlMetadataUtils;
import com.beust.jcommander.internal.Sets;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
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
import java.net.URL;
import java.util.List;
import java.util.Set;

public class ScriptTags {
    private static final Logger LOG = LoggerFactory.getLogger(ScriptTags.class);

    protected static class ScriptTagsMapper extends Mapper<Text, ArchiveReader, Text,
                LongWritable> {

        private Text outKey = new Text();
        private LongWritable outVal = new LongWritable(1);

        private Set<String> crunchbaseDomains = Sets.newHashSet();

        private Splitter tsvSplitter = Splitter.on("\t").omitEmptyStrings();

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            super.setup(context);
            Path[] paths = DistributedCache.getLocalCacheFiles(context.getConfiguration());
            File crunchbaseFile = new File(paths[0].toString());

            BufferedReader br = Files.newReader(crunchbaseFile, Charsets.UTF_8);
            String line;
            while ((line = br.readLine()) != null) {
                List<String> splitLine = Lists.newArrayList(Iterables.toArray(tsvSplitter.split
                        (line), String.class));

                if (splitLine.size() == 0) {
                    continue;
                }

                //hack hack: we have 2 types of input file... 1 is single column and 2nd is not
                //in multi column we take column 2 as our domain
                String domain = "";
                if (splitLine.size() > 1) {
                    domain = splitLine.get(1);
                } else {
                    domain = splitLine.get(0);
                }

                domain = HtmlMetadataUtils.canonicalizeURL(domain).replace("www.", "");

                URL url = new URL(domain);

                LOG.info("domain : {}", url.getHost());

                if (!crunchbaseDomains.contains(url.getHost())) {
                    crunchbaseDomains.add(url.getHost());
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
