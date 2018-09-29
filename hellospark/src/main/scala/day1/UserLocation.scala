package day1

import org.apache.spark.{SparkConf, SparkContext}


object UserLocation {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("").setMaster("")
    val sc = new SparkContext(sc)
    val rdd1 = sc.textFile("/User/Documents/").map(x => {
      var arr = x.split(",")
      val mb = (arr(0), arr(2))
      val flag = arr(3)
      val time = arr(1).toLong

    })

  }

}
