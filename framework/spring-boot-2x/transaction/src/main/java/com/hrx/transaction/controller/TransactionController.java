package com.hrx.transaction.controller;

import com.hrx.transaction.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanhuayou on 2019/05/07
 */
@RestController
public class TransactionController {
    @Autowired
    private ResourceService resourceService;


    @GetMapping("/required")
    public Object required() {
        try {
            return resourceService.required();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
