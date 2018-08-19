package com.adtec.mr.wc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by admin on 2017/10/31.
 */
public class WCJob {

    public static void main(String[] args) {

        Configuration conf = new Configuration();
        try {
            Job job = Job.getInstance(conf);
            job.setJarByClass(WCJob.class);
            job.setJobName("WC");
            job.setMapOutputKeyClass(Text.class);
            job.setMapOutputValueClass(IntWritable.class);
            job.setMapperClass(WCMap.class);
            job.setReducerClass(WCReduce.class);

            Path path = new Path("/usr/output/wc");
            FileSystem fs = FileSystem.get(conf);
            if(fs.exists(path)){
                fs.delete(path, true);
            }
            FileOutputFormat.setOutputPath(job, path);
            FileInputFormat.setInputPaths(job, new Path("/usr/input"));

            boolean result = job.waitForCompletion(true);
            if(result){
                System.out.println("作业执行成功");
            } else{
                System.out.println("作业执行失败");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
