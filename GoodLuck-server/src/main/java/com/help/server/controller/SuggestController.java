package com.help.server.controller;

import com.alibaba.fastjson.JSON;
import com.help.api.SuggestFacade;
import com.help.api.SuggestParam;
import com.help.server.common.GetParamsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/suggest")
public class SuggestController {
    private static Logger logger = LoggerFactory.getLogger(SuggestController.class);

    @Autowired
    private SuggestFacade suggestFacade;

    // http://127.0.0.1:9090/suggest/submit?userId=qijirensheng&remark=%E5%95%8A%E5%95%8A%E5%95%8A&mobile=13265479740
    @RequestMapping(value="/submit")
    @ResponseBody
    public String submit(SuggestParam param) {
        SuggestParam postParam = GetParamsUtils.getParamsObject(SuggestParam.class);

        param = postParam==null?param:postParam;

        return JSON.toJSONString(suggestFacade.submit(param));
    }

    // http://127.0.0.1:9090/suggest/list?pageSize=10&pageNo=1
    @RequestMapping(value="/list")
    @ResponseBody
    public String list(Integer pageSize, Integer pageNo) {
        Map<String, Object> paramMap = GetParamsUtils.getParamsMap();
        pageSize = (Integer) GetParamsUtils.getValueIfNull(pageSize, paramMap.get("pageSize"), 10);
        pageNo = (Integer) GetParamsUtils.getValueIfNull(pageNo, paramMap.get("pageNo"), 1);

        return JSON.toJSONString(suggestFacade.list(pageSize, pageNo));
    }

}
