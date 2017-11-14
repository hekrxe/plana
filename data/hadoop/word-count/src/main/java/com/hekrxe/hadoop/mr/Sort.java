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

/**
 * 排序
 *
 * @author hztanhuayou
 * @date 2017/11/14
 */
public class Sort {
    /*
    #! /bin/bash

    for i in {1..1000}
    do
        num=$(date +%s%N)
        echo $(($num%10000+5000)) >> ./number
    done
     */

    public static class MyMapper extends Mapper<Object, Text, LongWritable, LongWritable> {
        private static LongWritable val = new LongWritable();

        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            System.out.println(key + " " + value);
            val.set(Long.valueOf(value.toString()));
            context.write(val, new LongWritable(1));
        }
    }

    public static class MyReducer extends Reducer<LongWritable, LongWritable, LongWritable, LongWritable> {
        private LongWritable num = new LongWritable(1);

        @Override
        protected void reduce(LongWritable key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
            for (LongWritable val : values) {
                System.out.println("key: " + key + " val:" + val);
                context.write(num, key);
                num = new LongWritable(num.get() + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        if (null == args || args.length < 2) {
            System.out.println("in out");
            return;
        }
        Job job = Job.getInstance();
        job.setJobName("Sort");
        job.setJarByClass(Sort.class);

        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);

        job.setOutputKeyClass(LongWritable.class);
        job.setOutputValueClass(LongWritable.class);

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
