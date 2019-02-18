package com.hekrxe.gts.consumer.service;

import com.hekrxe.gts.api.DemoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * @author tanhuayou on 2019/02/18
 */
@Service
public class EchoService {
    @Reference(version = "${service.version.demo}")
    private DemoService demoService;


    public String echo(String msg) {
        return demoService.echo(msg);
    }
}
