package cn.itcast.scala.day1


object Hello {

  val func0 = (x: Int) => x * 10
  val func1: Int => Int = {
    x => x * 10
  }


  val func2: Int => String = {
    x => x.toString
  }
  val func3 = (x: Int) => x.toString


  val func4 = (x: Int, y: Double) => (y, x)
  val func5:(Int, Double) => (Double, Int) = {
    (a, b) => (b, a)
  }

  def m1(f: Int => Int) : Int = {
    //在方法体里面调用函数
    f(3)
  }

  def main(args: Array[String]){
//    for (i <- 1 to 3; j <- 1 to 3 if i != j)
//      print((10*i + j) + " ")
//      println()
    val r = m1(func0)
    val q = func4(3, 1.0)
    print(r)
    print(q)

  }

}
