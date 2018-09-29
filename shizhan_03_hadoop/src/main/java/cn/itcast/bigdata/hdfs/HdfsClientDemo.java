package cn.itcast.bigdata.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class HdfsClientDemo {
    FileSystem fs = null;
    @Before
    public void init() throws IOException {


        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://itcast01:9000");
        //拿到一个文件系统操作的客户端实例对象
        fs = FileSystem.get(conf);

    }

    @Test
    public void testUpload() throws IOException {
        fs.copyFromLocalFile(new Path("/Users/zhangjian/Desktop/Demo1.py"), new Path("/wordcount/output"));
        fs.close();
    }







}
