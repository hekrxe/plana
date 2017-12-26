package com.hekrxe.hadoop.mr;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * @author hztanhuayou
 * @date 2017/11/13
 */
public class MyWordCount {

    /**
     * MapReduceBase类: 实现了Mapper和Reducer接口的基类
     * Mapper: 实现Map功能
     */
    public static class MyMap extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
        private final static IntWritable ONE = new IntWritable(1);
        private Text word = new Text();

        /**
         * Mapper接口中的map方法:
         * 映射一个单个的输入K/V对到一个中间的K/V对
         */
        @Override
        public void map(LongWritable longWritable, Text text, OutputCollector<Text, IntWritable> outputCollector, Reporter reporter) throws IOException {
            String line = text.toString();
            StringTokenizer tokenizer = new StringTokenizer(line);
            while (tokenizer.hasMoreTokens()) {
                word.set(tokenizer.nextToken());
                outputCollector.collect(word, ONE);
            }
        }
    }

    public static class MyReduce extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {

        @Override
        public void reduce(Text text, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> outputCollector, Reporter reporter) throws IOException {
            int sum = 0;
            while (values.hasNext()) {
                sum += values.next().get();
            }
            outputCollector.collect(text, new IntWritable(sum));


        }
    }

    // hadoop jar xxx.jar [paramList]
    public static void main(String[] args) {
        JobConf conf = new JobConf(MyWordCount.class);
        conf.setJobName("Word Count");
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);
        conf.setMapperClass(MyMap.class);
        conf.setCombinerClass(MyReduce.class);
        conf.setReducerClass(MyReduce.class);
        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);

        FileInputFormat.setInputPaths(conf, new Path("hdfs://localhost:9000/user/hadoop/input"));
        FileOutputFormat.setOutputPath(conf, new Path("hdfs://localhost:9000/user/hadoop/output"));

        try {
            JobClient.runJob(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Job succeed!");
    }
}
