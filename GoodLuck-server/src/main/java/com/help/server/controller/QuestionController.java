package com.help.server.controller;

import com.alibaba.fastjson.JSON;
import com.help.api.*;
import com.help.server.common.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public String newest(Integer pageSize, Integer pageNo) {
        Map<String, Object> paramMap = GetParamsUtils.getParamsMap();
        pageSize = (Integer) GetParamsUtils.getValueIfNull(pageSize, paramMap.get("pageSize"), 10);
        pageNo = (Integer) GetParamsUtils.getValueIfNull(pageNo, paramMap.get("pageNo"), 1);

        QuestionPageParam pageParam = new QuestionPageParam();
        pageParam.setPageSize(pageSize);
        pageParam.setPageNo(pageNo);
        pageParam.setOrderBy("pub_time desc");

        return JSON.toJSONString(questionFacade.list(pageParam));
    }

    // http://127.0.0.1:9090/question/concerned?pageSize=10&pageNo=1
    @RequestMapping(value="/concerned")
    @ResponseBody
    public String concerned(Integer pageSize, Integer pageNo) {
        Map<String, Object> paramMap = GetParamsUtils.getParamsMap();
        pageSize = (Integer) GetParamsUtils.getValueIfNull(pageSize, paramMap.get("pageSize"), 10);
        pageNo = (Integer) GetParamsUtils.getValueIfNull(pageNo, paramMap.get("pageNo"), 1);

        QuestionPageParam pageParam = new QuestionPageParam();
        pageParam.setPageSize(pageSize);
        pageParam.setPageNo(pageNo);
        pageParam.setOrderBy("stars desc");

        return JSON.toJSONString(questionFacade.list(pageParam));
    }

    // http://127.0.0.1:9090/question/auditing?pageSize=10&pageNo=1&pubUserId=chuanqirensheng
    @RequestMapping(value="/auditing")
    @ResponseBody
    public String auditing(Integer pageSize, Integer pageNo, String pubUserId) {
        Map<String, Object> paramMap = GetParamsUtils.getParamsMap();
        pageSize = (Integer) GetParamsUtils.getValueIfNull(pageSize, paramMap.get("pageSize"), 10);
        pageNo = (Integer) GetParamsUtils.getValueIfNull(pageNo, paramMap.get("pageNo"), 1);
        pubUserId = (String) GetParamsUtils.getValueIfNull(pubUserId, paramMap.get("pubUserId"));

        QuestionPageParam pageParam = new QuestionPageParam();
        pageParam.setPageSize(pageSize);
        pageParam.setPageNo(pageNo);
        pageParam.setAuditState(AuditStateEnum.AUDITING.getCode());
        pageParam.setPubUserId(pubUserId);

        return JSON.toJSONString(questionFacade.list(pageParam));
    }

    // http://127.0.0.1:9090/question/auditedFailure?pageSize=10&pageNo=1&pubUserId=chuanqirensheng
    @RequestMapping(value="/auditedFailure")
    @ResponseBody
    public String auditedFailure(Integer pageSize, Integer pageNo, String pubUserId) {
        Map<String, Object> paramMap = GetParamsUtils.getParamsMap();
        pageSize = (Integer) GetParamsUtils.getValueIfNull(pageSize, paramMap.get("pageSize"), 10);
        pageNo = (Integer) GetParamsUtils.getValueIfNull(pageNo, paramMap.get("pageNo"), 1);
        pubUserId = (String) GetParamsUtils.getValueIfNull(pubUserId, paramMap.get("pubUserId"));

        QuestionPageParam pageParam = new QuestionPageParam();
        pageParam.setPageSize(pageSize);
        pageParam.setPageNo(pageNo);
        pageParam.setAuditState(AuditStateEnum.AUDITED_FAILURE.getCode());
        pageParam.setPubUserId(pubUserId);

        return JSON.toJSONString(questionFacade.list(pageParam));
    }

    // http://127.0.0.1:9090/question/auditedSuccess?pageSize=10&pageNo=1&pubUserId=chuanqirensheng
    @RequestMapping(value="/auditedSuccess")
    @ResponseBody
    public String auditedSuccess(Integer pageSize, Integer pageNo, String pubUserId) {
        Map<String, Object> paramMap = GetParamsUtils.getParamsMap();
        pageSize = (Integer) GetParamsUtils.getValueIfNull(pageSize, paramMap.get("pageSize"), 10);
        pageNo = (Integer) GetParamsUtils.getValueIfNull(pageNo, paramMap.get("pageNo"), 1);
        pubUserId = (String) GetParamsUtils.getValueIfNull(pubUserId, paramMap.get("pubUserId"));

        QuestionPageParam pageParam = new QuestionPageParam();
        pageParam.setPageSize(pageSize);
        pageParam.setPageNo(pageNo);
        pageParam.setAuditState(AuditStateEnum.AUIDTED.getCode());
        pageParam.setPubUserId(pubUserId);

        return JSON.toJSONString(questionFacade.list(pageParam));
    }

    // http://127.0.0.1:9090/question/verifiedFailure?pageSize=10&pageNo=1&pubUserId=chuanqirensheng
    @RequestMapping(value="/verifiedFailure")
    @ResponseBody
    public String verifiedFailure(Integer pageSize, Integer pageNo, String pubUserId) {
        Map<String, Object> paramMap = GetParamsUtils.getParamsMap();
        pageSize = (Integer) GetParamsUtils.getValueIfNull(pageSize, paramMap.get("pageSize"), 10);
        pageNo = (Integer) GetParamsUtils.getValueIfNull(pageNo, paramMap.get("pageNo"), 1);
        pubUserId = (String) GetParamsUtils.getValueIfNull(pubUserId, paramMap.get("pubUserId"));

        QuestionPageParam pageParam = new QuestionPageParam();
        pageParam.setPageSize(pageSize);
        pageParam.setPageNo(pageNo);
        pageParam.setAuditState(AuditStateEnum.VERIFIED_FAILURE.getCode());
        pageParam.setPubUserId(pubUserId);

        return JSON.toJSONString(questionFacade.list(pageParam));
    }

    // http://127.0.0.1:9090/question/verifiedSuccess?pageSize=10&pageNo=1&pubUserId=chuanqirensheng
    @RequestMapping(value="/verifiedSuccess")
    @ResponseBody
    public String verifiedSuccess(Integer pageSize, Integer pageNo, String pubUserId) {
        Map<String, Object> paramMap = GetParamsUtils.getParamsMap();
        pageSize = (Integer) GetParamsUtils.getValueIfNull(pageSize, paramMap.get("pageSize"), 10);
        pageNo = (Integer) GetParamsUtils.getValueIfNull(pageNo, paramMap.get("pageNo"), 1);
        pubUserId = (String) GetParamsUtils.getValueIfNull(pubUserId, paramMap.get("pubUserId"));

        QuestionPageParam pageParam = new QuestionPageParam();
        pageParam.setPageSize(pageSize);
        pageParam.setPageNo(pageNo);
        pageParam.setAuditState(AuditStateEnum.VERIFIED.getCode());
        pageParam.setPubUserId(pubUserId);

        return JSON.toJSONString(questionFacade.list(pageParam));
    }

    // http://127.0.0.1:9090/question/newAdd?pageSize=10&pageNo=1
    @RequestMapping(value="/newAdd")
    @ResponseBody
    public String newAdd(Integer pageSize, Integer pageNo) {
        Map<String, Object> paramMap = GetParamsUtils.getParamsMap();
        pageSize = (Integer) GetParamsUtils.getValueIfNull(pageSize, paramMap.get("pageSize"), 10);
        pageNo = (Integer) GetParamsUtils.getValueIfNull(pageNo, paramMap.get("pageNo"), 1);

        QuestionPageParam pageParam = new QuestionPageParam();
        pageParam.setPageSize(pageSize);
        pageParam.setPageNo(pageNo);
        pageParam.setState(StateEnum.UNRESOLVED.getCode());
        pageParam.setAuditStates(Arrays.asList(
                AuditStateEnum.AUDITING.getCode(),
                AuditStateEnum.AUDITED_FAILURE.getCode()));

        return JSON.toJSONString(questionFacade.list(pageParam));
    }

    // http://127.0.0.1:9090/question/resolved?pageSize=10&pageNo=1
    @RequestMapping(value="/resolved")
    @ResponseBody
    public String resolved(Integer pageSize, Integer pageNo) {
        Map<String, Object> paramMap = GetParamsUtils.getParamsMap();
        pageSize = (Integer) GetParamsUtils.getValueIfNull(pageSize, paramMap.get("pageSize"), 10);
        pageNo = (Integer) GetParamsUtils.getValueIfNull(pageNo, paramMap.get("pageNo"), 1);

        QuestionPageParam pageParam = new QuestionPageParam();
        pageParam.setPageSize(pageSize);
        pageParam.setPageNo(pageNo);
        pageParam.setState(StateEnum.RESOLVED.getCode());

        return JSON.toJSONString(questionFacade.list(pageParam));
    }

    // http://127.0.0.1:9090/question/unresolved?pageSize=10&pageNo=1
    @RequestMapping(value="/unresolved")
    @ResponseBody
    public String unresolved(Integer pageSize, Integer pageNo) {
        Map<String, Object> paramMap = GetParamsUtils.getParamsMap();
        pageSize = (Integer) GetParamsUtils.getValueIfNull(pageSize, paramMap.get("pageSize"), 10);
        pageNo = (Integer) GetParamsUtils.getValueIfNull(pageNo, paramMap.get("pageNo"), 1);

        QuestionPageParam pageParam = new QuestionPageParam();
        pageParam.setPageSize(pageSize);
        pageParam.setPageNo(pageNo);
        pageParam.setState(StateEnum.UNRESOLVED.getCode());
        pageParam.setAuditStates(Arrays.asList(
                AuditStateEnum.AUIDTED.getCode(),
                AuditStateEnum.VERIFIED.getCode(),
                AuditStateEnum.VERIFIED_FAILURE.getCode()));

        return JSON.toJSONString(questionFacade.list(pageParam));
    }

    // http://127.0.0.1:9090/question/focus?number=2020-01-28-000001&userId=chuanqirensheng
    @RequestMapping(value="/focus")
    @ResponseBody
    public String focus(String number, String userId) {
        Map<String, Object> paramMap = GetParamsUtils.getParamsMap();
        number = (String) GetParamsUtils.getValueIfNull(number, paramMap.get("number"));
        userId = (String) GetParamsUtils.getValueIfNull(userId, paramMap.get("userId"));

        return JSON.toJSONString(questionFacade.focus(number, userId));
    }

    // http://127.0.0.1:9090/question/detail?number=2020-01-28-000001
    @RequestMapping(value="/detail")
    @ResponseBody
    public String detail(String number) {
        Map<String, Object> paramMap = GetParamsUtils.getParamsMap();
        number = (String) GetParamsUtils.getValueIfNull(number, paramMap.get("number"));

        return JSON.toJSONString(questionFacade.info(number));
    }

    // http://127.0.0.1:9090/question/edit?number=2020-01-28-000001&remark=修改
    @RequestMapping(value="/edit")
    @ResponseBody
    public String edit(QuestionUpdateParam updateParam) {
        QuestionUpdateParam postParam = GetParamsUtils.getParamsObject(QuestionUpdateParam.class);

        updateParam = postParam==null?updateParam:postParam;

        return JSON.toJSONString(questionFacade.updateInfo(updateParam));
    }

    // http://127.0.0.1:9090/question/pub?remark=买不到医用口罩&tag=A&pubUserId=chuanqirensheng&province=360000&city=361000&district=361025&street=招携镇&mobile=13265479740
    @RequestMapping(value="/pub")
    @ResponseBody
    public String pub(QuestionParam param) {
        QuestionParam postParam = GetParamsUtils.getParamsObject(QuestionParam.class);

        param = postParam==null?param:postParam;

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

    // http://127.0.0.1:9090/question/audit?number=2020-01-28-000001&userId=chuanqirensheng&pass=1
    @RequestMapping(value="/audit")
    @ResponseBody
    public String audit(String number, String userId, Integer pass) {
        Map<String, Object> paramMap = GetParamsUtils.getParamsMap();
        number = (String) GetParamsUtils.getValueIfNull(number, paramMap.get("number"));
        userId = (String) GetParamsUtils.getValueIfNull(userId, paramMap.get("userId"));
        pass = (Integer) GetParamsUtils.getValueIfNull(pass, paramMap.get("pass"));



        return JSON.toJSONString(questionFacade.audit(number, userId,
                (pass !=null && pass == 1) ? AuditStateEnum.AUIDTED.getCode() : AuditStateEnum.AUDITED_FAILURE.getCode()));
    }

    // http://127.0.0.1:9090/question/verify?number=2020-01-28-000001&userId=chuanqirensheng&pass=1
    @RequestMapping(value="/verify")
    @ResponseBody
    public String verify(String number, String userId, Integer pass) {
        Map<String, Object> paramMap = GetParamsUtils.getParamsMap();
        number = (String) GetParamsUtils.getValueIfNull(number, paramMap.get("number"));
        userId = (String) GetParamsUtils.getValueIfNull(userId, paramMap.get("userId"));
        pass = (Integer) GetParamsUtils.getValueIfNull(pass, paramMap.get("pass"));

        return JSON.toJSONString(questionFacade.audit(number, userId,
                (pass !=null && pass == 1) ? AuditStateEnum.VERIFIED.getCode() : AuditStateEnum.VERIFIED_FAILURE.getCode()));
    }

    // http://127.0.0.1:9090/question/resolve?number=2020-01-28-000001&userId=chuanqirensheng
    @RequestMapping(value="/resolve")
    @ResponseBody
    public String resolve(String number, String userId) {
        Map<String, Object> paramMap = GetParamsUtils.getParamsMap();
        number = (String) GetParamsUtils.getValueIfNull(number, paramMap.get("number"));
        userId = (String) GetParamsUtils.getValueIfNull(userId, paramMap.get("userId"));

        return JSON.toJSONString(questionFacade.resolve(number, userId));
    }

    // http://127.0.0.1:9090/question/list?pageSize=10&pageNo=1&starsMin=3&starsMax=5&province=360000&city=361000&district=361025&street=%E6%9C%9B%E4%BB%99%E9%95%87&mobile=13265479740
    @RequestMapping(value="/list")
    @ResponseBody
    public String list(QuestionPageParam param) {
        QuestionPageParam postParam = GetParamsUtils.getParamsObject(QuestionPageParam.class);

        param = postParam==null?param:postParam;

        return JSON.toJSONString(questionFacade.list(param));
    }

}
