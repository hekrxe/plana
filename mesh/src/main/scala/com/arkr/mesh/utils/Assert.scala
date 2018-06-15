package com.arkr.mesh.utils

/**
  * User: tanhuayou  
  * Date: 2018/6/7
  */
object Assert {

  @inline
  def invalid(f: => Boolean, code: Int = 3000, msg: String = "expression invalid"): Unit = if (f) MeshException.throws(code, msg)
}
