package day1

import org.apache.spark.{SparkConf, SparkContext}



object WordCount {

  def main(args: Array[String]): Unit = {
    //
    val conf = new SparkConf().setAppName("wc")
        .setJars(Array("target/original-hello-spark-1.0-SNAPSHOT.jar"))
        .setMaster("spark://itcast01:7077")
    val sc = new SparkContext(conf)

    //textFile会产生两个RDD：HadoopRDD -> MapPartitionRDD
    sc.textFile(args(0)).cache()
    //产生一个RDD:MapPartitionsRDD
      .flatMap(_.split(" "))
    //产生一个RDD:MapPartitionsRDD
      .map((_, 1))
    //产生一个RDD:ShuffledRDD
      .reduceByKey(_+_)
    //产生一个RDD:MapPartitionsRDD
      .saveAsTextFile(args(1))
    sc.stop()
  }

}
