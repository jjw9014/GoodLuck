package com.help.server.controller;

import com.alibaba.fastjson.JSON;
import com.help.api.ResultDTO;
import com.help.server.model.Suggest;
import com.help.server.service.ISuggestService;
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
    private ISuggestService suggestService;

    // http://127.0.0.1:8080/suggest/submit?userId=qijirensheng&remark=%E5%95%8A%E5%95%8A%E5%95%8A&mobile=13265479740
    @RequestMapping(value="/submit")
    @ResponseBody
    public String submit(Suggest suggest) {
        int res = suggestService.submit(suggest);
        ResultDTO resultDTO = res>0 ? new ResultDTO("200", "成功", null) : new ResultDTO("101", "error", null);

        return JSON.toJSONString(resultDTO);
    }

    // http://127.0.0.1:8080/suggest/list?pageSize=10&pageNo=1
    @RequestMapping(value="/list")
    @ResponseBody
    public String list(int pageSize, int pageNo) {
        pageSize = pageSize<10 ? 10 : pageSize;
        pageNo = pageNo<0 ? 1 : pageNo;

        return JSON.toJSONString(new ResultDTO("200", "成功", suggestService.list(pageSize, pageNo)));
    }

}
