package day4

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

object SQLDemo {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SQLDemo")//.setMaster("local")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    System.setProperty("user.name", "hadoop")

    val personRDD = sc.textFile("hdfs://itcast01:9000/person.txt").map(line => {
      val fields = line.split(",")
      Person(fields(0).toLong, fields(1), fields(2).toInt)
    })

    import sqlContext.implicits._
    val personDF = personRDD.toDF

    personDF.registerTempTable("person")

    sqlContext.sql("select * from person order by age desc").show()

    sc.stop()


  }

}

case class Person(id: Long, name: String, age: Int)
