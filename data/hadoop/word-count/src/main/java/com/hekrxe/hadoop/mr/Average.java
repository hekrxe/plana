package com.hekrxe.hadoop.mr;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
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
public class Average {

    public static class MyMapper extends Mapper<Object, Text, Text, LongWritable> {
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer stringTokenizer = new StringTokenizer(value.toString());
            while (stringTokenizer.hasMoreTokens()) {
                String name = stringTokenizer.nextToken();
                String score = stringTokenizer.nextToken();
                System.out.println(name + " <=> " + score);
                context.write(new Text(name), new LongWritable(Long.valueOf(score)));
            }
        }
    }

    public static class MyReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
        @Override
        protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
            long sum = 0;
            int count = 0;
            for (LongWritable v : values) {
                sum += v.get();
                count++;
            }
            context.write(key, new LongWritable(sum / count));
        }
    }

    public static void main(String[] args) throws Exception {
        if (null == args || args.length < 2) {
            System.out.println("in out");
            return;
        }
        Job job = Job.getInstance();
        job.setJobName("Average");
        job.setJarByClass(Average.class);

        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        String rootPath = "hdfs://localhost:9000/";
        Path in1 = new Path(rootPath + args[0]);
        Path in2 = new Path(rootPath + args[1]);
        Path in3 = new Path(rootPath + args[2]);

        Path out = new Path(rootPath + args[3]);

        FileInputFormat.addInputPath(job, in1);
        FileInputFormat.addInputPath(job, in2);
        FileInputFormat.addInputPath(job, in3);
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
