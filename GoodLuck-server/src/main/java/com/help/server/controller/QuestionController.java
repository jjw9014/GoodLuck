package com.help.server.controller;

import com.alibaba.fastjson.JSON;
import com.help.api.*;
import com.help.server.common.AuditStateEnum;
import com.help.server.common.ResultHandler;
import com.help.server.common.StateEnum;
import com.help.server.common.TagEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

@Controller
@RequestMapping("/question")
public class QuestionController {
    private static Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private QuestionFacade questionFacade;

    // http://127.0.0.1:9090/question/sumByState
    @RequestMapping(value="/sumByState")
    @ResponseBody
    public String sumByState() {

        return JSON.toJSONString(questionFacade.count());
    }

    // http://127.0.0.1:9090/question/newest?pageSize=10&pageNo=1
    @RequestMapping(value="/newest")
    @ResponseBody
    public String newest(int pageSize, int pageNo) {
        QuestionPageParam pageParam = new QuestionPageParam();
        pageParam.setPageSize(pageSize);
        pageParam.setPageNo(pageNo);
        pageParam.setOrderBy("pub_time desc");

        return JSON.toJSONString(questionFacade.list(pageParam));
    }

    // http://127.0.0.1:9090/question/concerned?pageSize=10&pageNo=1
    @RequestMapping(value="/concerned")
    @ResponseBody
    public String concerned(int pageSize, int pageNo) {
        QuestionPageParam pageParam = new QuestionPageParam();
        pageParam.setPageSize(pageSize);
        pageParam.setPageNo(pageNo);
        pageParam.setOrderBy("stars desc");

        return JSON.toJSONString(questionFacade.list(pageParam));
    }

    // http://127.0.0.1:9090/question/auditing?pageSize=10&pageNo=1
    @RequestMapping(value="/auditing")
    @ResponseBody
    public String auditing(int pageSize, int pageNo) {
        QuestionPageParam pageParam = new QuestionPageParam();
        pageParam.setPageSize(pageSize);
        pageParam.setPageNo(pageNo);
        pageParam.setAuditState(AuditStateEnum.AUDITING.getCode());

        return JSON.toJSONString(questionFacade.list(pageParam));
    }

    // http://127.0.0.1:9090/question/resolved?pageSize=10&pageNo=1
    @RequestMapping(value="/resolved")
    @ResponseBody
    public String resolved(int pageSize, int pageNo) {
        QuestionPageParam pageParam = new QuestionPageParam();
        pageParam.setPageSize(pageSize);
        pageParam.setPageNo(pageNo);
        pageParam.setState(StateEnum.RESOLVED.getCode());

        return JSON.toJSONString(questionFacade.list(pageParam));
    }

    // http://127.0.0.1:9090/question/unresolved?pageSize=10&pageNo=1
    @RequestMapping(value="/unresolved")
    @ResponseBody
    public String unresolved(int pageSize, int pageNo) {
        QuestionPageParam pageParam = new QuestionPageParam();
        pageParam.setPageSize(pageSize);
        pageParam.setPageNo(pageNo);
        pageParam.setState(StateEnum.UNRESOLVED.getCode());
        pageParam.setAuditStates(Arrays.asList(AuditStateEnum.AUIDTED.getCode(), AuditStateEnum.VERIFIED.getCode()));

        return JSON.toJSONString(questionFacade.list(pageParam));
    }

    // http://127.0.0.1:9090/question/focus?number=2020-01-28-000001&userId=chuanqirensheng
    @RequestMapping(value="/focus")
    @ResponseBody
    public String focus(String number, String userId) {

        return JSON.toJSONString(questionFacade.focus(number, userId));
    }

    // http://127.0.0.1:9090/question/detail?number=2020-01-28-000001
    @RequestMapping(value="/detail")
    @ResponseBody
    public String detail(String number) {

        return JSON.toJSONString(questionFacade.info(number));
    }

    // http://127.0.0.1:9090/question/edit?number=2020-01-28-000001&remark=修改
    @RequestMapping(value="/edit")
    @ResponseBody
    public String edit(@RequestBody QuestionUpdateParam updateParam) {

        return JSON.toJSONString(questionFacade.updateInfo(updateParam));
    }

    // http://127.0.0.1:9090/question/pub?remark=买不到医用口罩&tag=A&pubUserId=chuanqirensheng&province=江西省&city=抚州市&district=乐安县&street=招携镇&mobile=13265479740
    @RequestMapping(value="/pub")
    @ResponseBody
    public String pub(@RequestBody QuestionParam param) {

        return JSON.toJSONString(questionFacade.pub(param));
    }
    // http://127.0.0.1:9090/question/edit?number=2020-01-28-000001&remark=修改
    @RequestMapping(value="/editByGet")
    @ResponseBody
    public String editByGet(QuestionUpdateParam updateParam) {

        return JSON.toJSONString(questionFacade.updateInfo(updateParam));
    }

    // http://127.0.0.1:9090/question/pub?remark=买不到医用口罩&tag=A&pubUserId=chuanqirensheng&province=江西省&city=抚州市&district=乐安县&street=招携镇&mobile=13265479740
    @RequestMapping(value="/pubByGet")
    @ResponseBody
    public String pubByGet(QuestionParam param) {

        return JSON.toJSONString(questionFacade.pub(param));
    }

    // http://127.0.0.1:9090/question/typelist
    @RequestMapping(value="/typelist")
    @ResponseBody
    public String typelist() {
        Map<String, Object> map = new TreeMap<>();
        for (TagEnum tag : TagEnum.values()) {
            map.put(tag.getCode(), tag.getDesc());
        }

        return JSON.toJSONString(ResultHandler.handleSuccess(map));
    }

    // http://127.0.0.1:9090/question/audit?number=2020-01-28-000001&userId=chuanqirensheng
    @RequestMapping(value="/audit")
    @ResponseBody
    public String audit(String number, String userId) {

        return JSON.toJSONString(questionFacade.audit(number, userId, AuditStateEnum.AUIDTED.getCode()));
    }

    // http://127.0.0.1:9090/question/verify?number=2020-01-28-000001&userId=chuanqirensheng
    @RequestMapping(value="/verify")
    @ResponseBody
    public String verify(String number, String userId) {

        return JSON.toJSONString(questionFacade.audit(number, userId, AuditStateEnum.VERIFIED.getCode()));
    }

    // http://127.0.0.1:9090/question/resolve?number=2020-01-28-000001&userId=chuanqirensheng
    @RequestMapping(value="/resolve")
    @ResponseBody
    public String resolve(String number, String userId) {

        return JSON.toJSONString(questionFacade.resolve(number, userId));
    }

    // http://127.0.0.1:9090/question/list?pageSize=10&pageNo=1&starsMin=3&starsMax=5&province=%E6%B1%9F%E8%A5%BF%E7%9C%81&city=%E6%8A%9A%E5%B7%9E%E5%B8%82&district=%E4%B9%90%E5%AE%89%E5%8E%BF&street=%E6%9C%9B%E4%BB%99%E9%95%87&mobile=13265479740
    @RequestMapping(value="/listByGet")
    @ResponseBody
    public String listByGet(QuestionPageParam param) {

        return JSON.toJSONString(questionFacade.list(param));
    }

    // http://127.0.0.1:9090/question/list?pageSize=10&pageNo=1&starsMin=3&starsMax=5&province=%E6%B1%9F%E8%A5%BF%E7%9C%81&city=%E6%8A%9A%E5%B7%9E%E5%B8%82&district=%E4%B9%90%E5%AE%89%E5%8E%BF&street=%E6%9C%9B%E4%BB%99%E9%95%87&mobile=13265479740
    @RequestMapping(value="/list")
    @ResponseBody
    public String list(@RequestBody QuestionPageParam param) {

        return JSON.toJSONString(questionFacade.list(param));
    }

}
