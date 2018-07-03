package base

import scala.reflect.ClassTag

/**
  * User: tanhuayou  
  * Date: 2018/7/3
  */
class ManifestTest {

}

class A[T]

object ManifestTest extends App {

  arrMk(1, 2) foreach println

  arrMk("ni", 12) foreach println

  //通过Manifest这种上下文界定可以创建一个泛型数组
  //Mainifest在运行时存储数组的具体类型,Mainifest实际上作为一个隐式的参数运行在上下文中
  def arrMk[T: Manifest](f: T, s: T) = {
    val ts = new Array[T](2)
    ts(0) = f
    ts(1) = s
    ts
  }

  // 这种写法报错
  //  def arrMk1[T](f: T, s: T) = {
  //    val ts = new Array[T](2)
  //    ts(0) = f
  //    ts(1) = s
  //    ts
  //  }

  //最最常用的是ClassTag,用来代替manifest
  //运行时确定在编译时无法确定的类型
  def mkArray[T: ClassTag](elems: T*) = Array[T](elems: _*)

  mkArray(2, 5).foreach(println)
  mkArray("hello", "world").foreach(println)
  mkArray(1, 2, "11", 3D) foreach println

  println("#" * 10)

  //A =:= B //表示A类型等同于B类型
  //A <:< B //表示A类型是B类型的子类
  //本函数包含一个隐式参数,限定T是指定类型
  def rocky[T](i: T)(implicit env: T <:< java.io.Serializable): Unit = {
    println(i)
  }

  rocky("world")

}

/**
  * 多重界定
  */
//T是A或B的子类
//T <: A with B

//A或B是T的子类
//T >: A with B

//T是A的上界,B的下界
//T >: A <: B

//T必须同时满足存在A[T]和B[T]这样的隐式值
//T: A: B

//T必须同时满足能隐式转换成A和B
//T <% A <% B