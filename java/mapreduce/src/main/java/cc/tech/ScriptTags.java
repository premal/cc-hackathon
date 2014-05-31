package cc.tech;

import cc.tech.utils.HtmlMetadataUtils;
import org.apache.commons.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.archive.io.ArchiveReader;
import org.archive.io.ArchiveRecord;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class ScriptTags {
    private static final Logger LOG = LoggerFactory.getLogger(ScriptTags.class);

    protected static class ScriptTagsMapper extends Mapper<Text, ArchiveReader, Text,
                LongWritable> {

        private Text outKey = new Text();
        private LongWritable outVal = new LongWritable(1);

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

                    List<String> domains = HtmlMetadataUtils.getURLDomainFromScriptTags(json,
                            "text/javascript");
                    for (String domain : domains) {
                        outKey.set(String.format("%s\t%s", pageDomain, domain));
                        context.write(outKey, outVal);
                    }
                } catch (Exception ex) {
                    LOG.error("caught exception", ex);
                    context.getCounter("MAP", "EXCEPTIONS").increment(1);
                }
            }
        }


    }
}
