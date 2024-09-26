object ScalaBase {

    /* 函数 */
    def addOneTest(i: Int) = i + 1

    /* 集合 */
    def seqTest(): Unit = {
        val a = Seq(1, 2, 3, 4, 5)
        println(a(0), a(1), a)
        val b = Seq.tabulate(9)(i => i * i)
        println(b)
        val c = b.reverse
        println(c)
    }

    /* for循环 */
    def forTest(): Unit = {
        for(i <- 0 to 5){
            println(i)
        }
    }

    /* 对象 */
    /* 定义一个trait Accelerator，表示加速器模块 */
    trait Accelerator {
        def accelerate(): Unit = {
            println("the car is accelerating...")
        }
    }

    /* 定义一个trait Brake，表示刹车模块 */
    trait Brake {
        def brake(): Unit = {
            println("the car is braking...")
        }
    }

    /* 定义一个trait Steering，表示方向盘模块 */
    trait Steering {
        /* 不做具体实现，混用traits的类来实现 */
        def steer(direction: String): Unit
    }


    abstract class Car {
        /* 定义属性 */
        var color: String
        var year: Int

        /* 定义一个抽象方法 */
        def drive(): Unit
    }

    class Taxi extends Car with Accelerator with Brake with Steering {
        var color: String = _
        var year: Int = _
        
        var driverName: String = _
    
        def drive(): Unit = {
            println("the taxi is driving...")
        }

        def steer(direction: String): Unit = {
            println(s"the taxi is steering to the $direction")
        }
    }

    def classTest(): Unit = {

        // val myCar = new Car
        // myCar.color = "rice"
        // myCar.year = 2024
        // println(myCar.color, myCar.year)

        // val myTaxi = new Taxi
        // myTaxi.color = "blue"
        // myTaxi.year = 2022
        // myTaxi.driverName = "Mary"
        // println(myTaxi.color, myTaxi.year, myTaxi.driverName)
        // println(myTaxi.getClass)

        val dd: Taxi = new Taxi
        dd.color = "red"
        dd.year = 2021
        dd.asInstanceOf[Taxi].driverName = "John"
        println(dd.color, dd.year)
        println(dd.getClass)

        dd.drive()
        dd.accelerate()
        dd.brake()
        dd.steer("left")


    }

    /* 单例对象 */
    object MySingleton {
        def apply = {
            println("creating MySingleton object...")
        }
    }

    def singletonTest(): Unit = {
        MySingleton()
    }

    def main(args: Array[String]): Unit = {
        // println("Hello, World!")

        // println(addOneTest(10))
        
        // seqTest()

        // forTest()

        // classTest()

        singletonTest()
        
    }

}
