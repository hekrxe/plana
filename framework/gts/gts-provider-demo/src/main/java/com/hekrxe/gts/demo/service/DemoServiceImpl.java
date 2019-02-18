package com.hekrxe.gts.demo.service;

import com.hekrxe.gts.api.DemoService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author tanhuayou on 2019/02/15
 */
@Service(version = "${service.version.demo}")
public class DemoServiceImpl implements DemoService {
    @Override
    public String echo(String msg) {
        return "<=" + msg + " .from demo";
    }
}
