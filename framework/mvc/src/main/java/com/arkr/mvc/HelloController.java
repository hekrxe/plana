package com.arkr.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: tanhuayou
 * Date: 2018/7/27
 */
@Controller
public class HelloController {

    @RequestMapping(value = "/", method = RequestMethod.HEAD)
    public String head() {
        System.out.println("head");
        return "head.jsp";
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        System.out.println("index");
        model.addAttribute("msg", "message");
        return "head.jsp";
    }

}
