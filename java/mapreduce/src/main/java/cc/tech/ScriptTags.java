package cc.tech;

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

                    String server = json.getJSONObject("Envelope").getJSONObject("Payload-Metadata").getJSONObject("HTTP-Response-Metadata").getJSONObject("Headers").getString("Server");
                    outKey.set(server);
                    context.write(outKey, outVal);

                } catch (Exception ex) {
                    LOG.error("caught exception", ex);
                    context.getCounter("MAP", "EXCEPTIONS").increment(1);
                }
            }
        }
    }
}
