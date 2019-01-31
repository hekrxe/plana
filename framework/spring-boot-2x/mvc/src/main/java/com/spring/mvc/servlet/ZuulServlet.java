package com.spring.mvc.servlet;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author tanhuayou on 2019/01/30
 */
@Component
public class ZuulServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        // 业务
        String uri = request.getRequestURI();
        System.out.println(uri);
        try (PrintWriter writer = resp.getWriter()) {
            writer.println(uri);
            writer.flush();
        }
    }
}
