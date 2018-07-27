package com.arkr.akka.fsm

/**
  * User: tanhuayou  
  * Date: 2018/6/8
  */
sealed trait State

case object Idle extends State

case object Active extends State
