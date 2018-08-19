package com.adtec.mr.wc;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by admin on 2017/10/31.
 */
public class WCMap extends Mapper<LongWritable, Text, Text, IntWritable>{

    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String[] strs = value.toString().split(" ");
        for(String str : strs){
            context.write(new Text(str), new IntWritable(1));
        }

    }
}
