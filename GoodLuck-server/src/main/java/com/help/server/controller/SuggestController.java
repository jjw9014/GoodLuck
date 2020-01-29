package com.help.server.controller;

import com.alibaba.fastjson.JSON;
import com.help.api.SuggestFacade;
import com.help.api.SuggestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/suggest")
public class SuggestController {
    private static Logger logger = LoggerFactory.getLogger(SuggestController.class);

    @Autowired
    private SuggestFacade suggestFacade;

    // http://127.0.0.1:8080/suggest/submit?userId=qijirensheng&remark=%E5%95%8A%E5%95%8A%E5%95%8A&mobile=13265479740
    @RequestMapping(value="/submit")
    @ResponseBody
    public String submit(SuggestParam param) {

        return JSON.toJSONString(suggestFacade.submit(param));
    }

    // http://127.0.0.1:8080/suggest/list?pageSize=10&pageNo=1
    @RequestMapping(value="/list")
    @ResponseBody
    public String list(int pageSize, int pageNo) {

        return JSON.toJSONString(suggestFacade.list(pageSize, pageNo));
    }

}
