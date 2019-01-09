package com.provider.test

import org.springframework.stereotype.Service

/**
  *
  * Created by tanhuayou on 2019/01/09
  */
@Service
class DoAction extends Action with HiAction with EchoAction {
}
