package cc.tech;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.reduce.LongSumReducer;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.commoncrawl.warc.WARCFileInputFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class WATFileScriptTags extends Configured implements Tool {
    private static final Logger LOG = LoggerFactory.getLogger(WATFileScriptTags.class);

    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new Configuration(), new WATFileScriptTags(), args);
        System.exit(res);
    }

    public class WATFileScriptTagsParams {
        @Parameter(description = "input", names="-input")
        public String input;

        @Parameter(description = "output", names = "-output")
        public String output;

        @Parameter(description = "crunchbase file", names="-cb")
        public String crunchbaseFile;
    }


    @Override
    public int run(String[] args) throws Exception {
        WATFileScriptTagsParams params = new WATFileScriptTagsParams();
        new JCommander(params, args);

        Configuration conf = getConf();

        Job job = new Job(conf);
        job.setJarByClass(WATFileScriptTags.class);

        job.getConfiguration().set("mapred.reduce.tasks", "100");

        String inputPath = params.input;
        LOG.info("inputPath : {}", inputPath);

        FileInputFormat.addInputPath(job, new Path(inputPath));

        String outputPath = params.output;
        FileSystem fs = FileSystem.get(conf);
        if (fs.exists(new Path(outputPath))) {
            fs.delete(new Path(outputPath), true);
        }

        FileOutputFormat.setOutputPath(job, new Path(outputPath));

        DistributedCache.addCacheFile(new URI(params.crunchbaseFile), job.getConfiguration());

        job.setInputFormatClass(WARCFileInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        job.setMapperClass(ScriptTags.ScriptTagsMapper.class);
        job.setReducerClass(LongSumReducer.class);

        if (job.waitForCompletion(true)) {
            return 0;
        } else {
            return 1;
        }
    }
}
