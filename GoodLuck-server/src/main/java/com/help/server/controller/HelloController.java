package com.help.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HelloController {
    // http://127.0.0.1:8080/hello
    @RequestMapping(value="/hello")
    @ResponseBody
    public String hello() {

        return "Hello, 祝大家新年快乐，身体健康";
    }

}
