package day5

import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

object StateFullWordCount {


  val updataFunc = (iter: Iterator[(String, Seq[Int], Option[Int])]) => {

    iter.flatMap{case(x,y,z)=>Some(y.sum + z.getOrElse(0)).map(m =>(x,m))}
    //iter.map{case(word, current_count, history_count) => (word,current_count.sum + history_count.gerOrElse(0))}
  }

  def main(args: Array[String]): Unit = {
    //设置控制台显示信息
    LoggerLevels.setStreamingLogLevels()
    //StreamingContext
    val conf = new SparkConf().setAppName("StateFullWordCount").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val ssc = new StreamingContext(sc, Seconds(6))
    //updateStateByKey必须要设置checkpoint
    sc.setCheckpointDir("/Users/zhangjian/Desktop/ck")
    //接收数据
    val ds = ssc.socketTextStream("192.168.252.130", 8888)
    //DStream是一个特殊的RDD
    val result = ds.flatMap(_.split(" ")).map((_, 1)).updateStateByKey(updataFunc, new HashPartitioner(sc.defaultParallelism), true)

    result.print()
    ssc.start()
    ssc.awaitTermination()
  }

}
