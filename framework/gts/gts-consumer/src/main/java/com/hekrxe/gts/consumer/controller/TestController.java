package com.hekrxe.gts.consumer.controller;

import com.hekrxe.gts.consumer.service.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanhuayou on 2019/02/18
 */
@RestController
public class TestController {
    @Autowired
    private EchoService echoService;

    @GetMapping("/echo")
    public Object echo(@RequestParam(value = "msg") String msg) {
        return echoService.echo(msg);
    }
}
