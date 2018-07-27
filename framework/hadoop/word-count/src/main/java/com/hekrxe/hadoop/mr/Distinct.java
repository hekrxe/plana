package com.hekrxe.hadoop.mr;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 去重
 * 格式:
 * date user
 * <p>
 *
 * @author hztanhuayou
 * @date 2017/11/14
 */
public class Distinct {

    /*
    #! /bin/bash

    rand_num=0
    function rand(){
        min=$1
        max=$(($2-min+1))
        rand_num=$(cat /dev/urandom|head -n 10 | cksum | awk -F ' ' '{print $1}')
        rand_num=$(($rand_num%$max+$min))
    }

    one_day=86400
    today=$(date +%s)

    for i in {1..1000}
    do
        rand today $((today+i*4*one_day/3))
        ymd=`date -d"@$rand_num" "+%Y-%m-%d"`
        user=$((rand_num%10+10))
        echo "$ymd 0${user}0"
    done
    */

    public static class MyMapper extends Mapper<Object, Text, Text, Text> {
        /**
         * @param key   偏移量
         * @param value 具体每一行的数据
         */
        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            context.write(value, new Text(""));
        }
    }

    public static class MyReducer extends Reducer<Text, Text, Text, Text> {
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            context.write(key, new Text(""));
        }
    }

    public static void main(String[] args) throws Exception {
        if (null == args || args.length < 2) {
            System.out.println("in out");
            return;
        }
        Job job = Job.getInstance();
        job.setJobName("Distinct");
        job.setJarByClass(Distinct.class);

        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

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
