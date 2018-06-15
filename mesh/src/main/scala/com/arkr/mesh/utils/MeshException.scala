package com.arkr.mesh.utils

/**
  * User: tanhuayou  
  * Date: 2018/6/7
  */
class MeshException(val code: Int, val msg: String) extends RuntimeException(msg) {
}

object MeshException {
  @inline
  def apply(code: Int, msg: String = null): MeshException = new MeshException(code, msg)

  @inline
  def throws(code: Int, msg: String = null) = throw MeshException(code, msg)
}