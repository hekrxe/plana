package com.hekrxe.hadoop.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;
import org.apache.hadoop.io.*;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.Date;

/**
 * Java API操作文件
 *
 * @author hztanhuayou
 * @date 2017/11/13
 */
public class FileOperation {
    private static String HDFS_ROOT = "hdfs://localhost:9000";
    private static Configuration CONFIGURATION = new Configuration();
    private static FileSystem HDFS = null;

    static {
        try {
            HDFS = FileSystem.get(URI.create(HDFS_ROOT), CONFIGURATION);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void uploadFileToHdfs(String srcFile, String descFile) throws IOException {
        String desHDFS = HDFS_ROOT + "/" + descFile;
        HDFS.copyFromLocalFile(new Path(srcFile), new Path(desHDFS));

        FileStatus[] files = HDFS.listStatus(new Path(desHDFS));
        for (FileStatus file : files) {
            System.out.println(file.getPath());
        }
    }

    private static void downloadFileToLocal(String srcFileNameOfHdfs, String desFileOfLocal) throws Exception {
        HDFS.copyToLocalFile(false, new Path(HDFS_ROOT + "/" + srcFileNameOfHdfs), new Path(desFileOfLocal));
        System.out.println("Done");
    }

    private static void catFile(String fileName) throws Exception {
        FSDataInputStream in = HDFS.open(new Path(HDFS_ROOT + "/" + fileName));
        IOUtils.copyBytes(in, System.out, CONFIGURATION, true);
    }

    private static void renameFile(String fileName, String newName) throws IOException {
        Path src = new Path(HDFS_ROOT + "/" + fileName);
        Path des = new Path(HDFS_ROOT + "/" + newName);
        boolean rename = HDFS.rename(src, des);
        if (rename) {
            System.out.println("Succeed");
        } else {
            System.out.println("Failed");
        }
    }

    private static void mkdir(String dir) throws IOException {
        boolean mkdirs = HDFS.mkdirs(new Path(HDFS_ROOT + "/" + dir));
        if (mkdirs) {
            System.out.println("Succeed");
        } else {
            System.out.println("Failed");
        }
    }

    private static void fileExist(String file) throws IOException {
        boolean exists = HDFS.exists(new Path(HDFS_ROOT + "/" + file));
        if (exists) {
            System.out.println("Exist");
        } else {
            System.out.println("Empty");
        }
    }

    private static void deleteFile(String file) throws IOException {
        boolean delete = HDFS.delete(new Path(HDFS_ROOT + "/" + file), false);
        if (delete) {
            System.out.println("Succeed");
        } else {
            System.out.println("Failed");
        }
    }

    private static void listFile(String dir) throws IOException {
        FileStatus[] fileStatuses = HDFS.listStatus(new Path(HDFS_ROOT + dir));
        for (FileStatus fileStatus : fileStatuses) {
            System.out.println("path:\t" + fileStatus.getPath()
                    + "\nsize:\t" + fileStatus.getBlockSize()
                    + "\nlen:\t" + fileStatus.getLen()
                    + "\nmodifyTime:\t" + new Date(fileStatus.getModificationTime())
                    + "\nisFile:\t" + fileStatus.isFile());
        }
    }

    private static void locateFile(String file) throws IOException {
        FileStatus fileStatus = HDFS.getFileStatus(new Path(HDFS_ROOT + "/" + file));
        BlockLocation[] locations = HDFS.getFileBlockLocations(fileStatus, 0, fileStatus.getLen());
        for (BlockLocation location : locations) {
            System.out.println("names:\t" + Arrays.toString(location.getNames()));
            System.out.println("length:\t" + location.getLength());
            String[] hosts = location.getHosts();
            for (String host : hosts) {
                System.out.println("host:\t" + host);
            }
        }
    }

    private static void nodeList() throws IOException {
        DatanodeInfo[] dataNodeStats = ((DistributedFileSystem) HDFS).getDataNodeStats();
        for (DatanodeInfo nodeInfo : dataNodeStats) {
            System.out.println("host: " + nodeInfo.getHostName() + "\tblockPoolUsed: " + nodeInfo.getBlockPoolUsed());
        }
    }

    private static void zip(String file) throws Exception {
        Class<?> klassOfGzipCodec = Class.forName("org.apache.hadoop.io.compress.GzipCodec");
        Path src = new Path(HDFS_ROOT + "/" + file);
        Path des = new Path(HDFS_ROOT + "/" + file + ".gz");
        CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(klassOfGzipCodec, CONFIGURATION);
        FSDataInputStream in = HDFS.open(src);
        FSDataOutputStream out = HDFS.create(des);

        System.out.println("Compress Start");
        CompressionOutputStream compressionOut = codec.createOutputStream(out);
        IOUtils.copyBytes(in, compressionOut, CONFIGURATION);

        IOUtils.closeStream(compressionOut);
        IOUtils.closeStream(out);
        IOUtils.closeStream(in);

        System.out.println("Compress Finished");

        locateFile(file);
        System.out.println();
        locateFile(file + ".gz");
    }

    private static void unzip(String file) throws Exception {
        Class<?> klassOfGzipCodec = Class.forName("org.apache.hadoop.io.compress.GzipCodec");
        Path src = new Path(HDFS_ROOT + "/" + file);
        CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(klassOfGzipCodec, CONFIGURATION);
        FSDataInputStream in = HDFS.open(src);
        CompressionInputStream comIn = codec.createInputStream(in);

        IOUtils.copyBytes(comIn, System.out, CONFIGURATION);
        IOUtils.closeStream(comIn);
        IOUtils.closeStream(in);
    }

    /**
     * 序列化文件
     * `sequence`主要用于小文件的序列化
     *
     * @param file 目标
     */
    private static void sequenceFileRead(String file) throws Exception {
        Path src = new Path(HDFS_ROOT + "/" + file);
        SequenceFile.Reader reader = new SequenceFile.Reader(HDFS, src, CONFIGURATION);
        Writable key = (Writable) ReflectionUtils.newInstance(reader.getKeyClass(), CONFIGURATION);
        Writable value = (Writable) ReflectionUtils.newInstance(reader.getValueClass(), CONFIGURATION);

        long position = reader.getPosition();

        while (reader.next(key, value)) {
            String syncSeen = reader.syncSeen() ? "*" : "";
            System.out.println("position:" + position + "\tsync:" + syncSeen + "\tkey" + key + "\tvalue:" + value);
            position = reader.getPosition();
        }
        IOUtils.closeStream(reader);
    }

    private static void sequenceFileWrite(String file) throws Exception {
        String[] data = {
                "one,two,buckle my shoe", "two,four,shut the door",
                "five,six,pick up sticks", "seven,eight,lay then straight",
                "nine,ten,a big fat then"
        };

        Path src = new Path(HDFS_ROOT + "/" + file);

        IntWritable key = new IntWritable();
        Text value = new Text();

        SequenceFile.Writer writer = SequenceFile.createWriter(HDFS, CONFIGURATION, src, IntWritable.class, Text.class);
        int i = 0;
        for (String row : data) {
            ++i;
            key.set(i);
            value.set(row);
            System.out.println("length:" + writer.getLength() + "\tkey:" + key + "\tvalue:" + value);
            writer.append(key, value);
        }
        IOUtils.closeStream(writer);

    }

    /**
     * 目录形式
     * data+index
     *
     * @param file toFile
     */
    private static void mapFileWrite(String file) throws Exception {
        String[] data = {
                "one,two,buckle my shoe", "two,four,shut the door",
                "five,six,pick up sticks", "seven,eight,lay then straight",
                "nine,ten,a big fat then"
        };

        IntWritable key = new IntWritable();
        Text value = new Text();

        MapFile.Writer writer = new MapFile.Writer(CONFIGURATION, HDFS, HDFS_ROOT + "/" + file, IntWritable.class, Text.class);
        writer.setIndexInterval(1);
        int i = 0;
        for (String row : data) {
            ++i;
            key.set(i);
            value.set(row);
            System.out.println("IndexInterval:" + writer.getIndexInterval() + "\tkey:" + key + "\tvalue:" + value);
            writer.append(key, value);
        }
        IOUtils.closeStream(writer);
    }

    private static void mapFileRead(String file) throws Exception {
        MapFile.Reader reader = new MapFile.Reader(HDFS, HDFS_ROOT + "/" + file, CONFIGURATION);
        int key = 1;
        Text value = new Text();
        while (reader.next(new IntWritable(key), value)) {
            System.out.println("key:" + key + "\tvalue:" + value);
            ++key;
        }
        IOUtils.closeStream(reader);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(Arrays.toString(args));
        if (null == args || args.length < 1) {
            System.out.println("Param Miss");
        } else {
            Integer type = Integer.valueOf(args[0]);
            switch (type) {
                case 1:
                    uploadFileToHdfs(args[1], args[2]);
                    break;
                case 2:
                    downloadFileToLocal(args[1], args[2]);
                    break;
                case 3:
                    catFile(args[1]);
                    break;
                case 4:
                    renameFile(args[1], args[2]);
                    break;
                case 5:
                    mkdir(args[1]);
                    break;
                case 6:
                    fileExist(args[1]);
                    break;
                case 7:
                    deleteFile(args[1]);
                    break;
                case 8:
                    listFile(args[1]);
                    break;
                case 9:
                    locateFile(args[1]);
                    break;
                case 10:
                    nodeList();
                    break;
                case 11:
                    zip(args[1]);
                    break;
                case 12:
                    unzip(args[1]);
                    break;
                case 13:
                    sequenceFileRead(args[1]);
                    break;
                case 14:
                    sequenceFileWrite(args[1]);
                    break;
                case 15:
                    mapFileWrite(args[1]);
                    break;
                case 16:
                    mapFileRead(args[1]);
                    break;
                default:
                    System.out.println("Not Support!");
            }
        }
    }


}
