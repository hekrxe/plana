package com.hekrxe.hadoop.mr;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * @author hztanhuayou
 * @date 2017/11/14
 */
public class NewApiWordCount {

    public static class MyMapper extends Mapper<
            Object /*KEY_IN*/,
            Text /*VALUE_IN*/,
            Text /*KEY_OUT*/,
            IntWritable/*VALUE_OUT*/
            > {

        public MyMapper() {
            super();
            System.out.println("MyMapper()");
        }

        private Text word = new Text();
        private final IntWritable ONE = new IntWritable(1);

        /**
         * @param key   偏移量
         * @param value 当前内容
         */
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer tokenizer = new StringTokenizer(value.toString(), " ");
            while (tokenizer.hasMoreTokens()) {
                word.set(tokenizer.nextToken());
                context.write(word, ONE);
            }
        }
    }

    public static class MyReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        private IntWritable result = new IntWritable();

        public MyReducer() {
            super();
            System.out.println("MyReducer()");
        }

        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable value : values) {
                sum += value.get();
            }
            result.set(sum);
            context.write(key, result);
        }
    }

    public static void main(String[] args) throws Exception {
        if (null == args || args.length < 2) {
            System.out.println("in out");
            return;
        }
        Job job = Job.getInstance();
        job.setJobName("WordCount");
        job.setJarByClass(NewApiWordCount.class);

        job.setMapperClass(MyMapper.class);
        // 减少网络负载
        job.setCombinerClass(MyReducer.class);
        job.setReducerClass(MyReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        String rootPath = "hdfs://localhost:9000/";
        Path in = new Path(rootPath + args[0]);
        Path out = new Path(rootPath + args[1]);

        FileInputFormat.addInputPath(job, in);
        FileOutputFormat.setOutputPath(job, out);

        boolean exit = job.waitForCompletion(true);
        if (exit) {
            System.out.println("Succeed");
        } else {
            System.out.println("Failed");
        }

        System.exit(exit ? 0 : 1);
    }
}
