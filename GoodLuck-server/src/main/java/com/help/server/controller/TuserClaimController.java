package com.help.server.controller;

import com.help.api.ResultDTO;
import com.help.server.service.TuserClaimService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户认领控制层接口
 * @Author LY
 * @Date 2020/1/30 11:18
 * @Version 1.0
 **/
@RestController
@RequestMapping(path = "/claim/")
public class TuserClaimController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TuserClaimController.class);

    @Autowired
    private TuserClaimService tuserClaimService;

    /**
     * 通过问题id获取问题联系信息
     * @param questionId 问题id
     * @return
     */
    @RequestMapping(value = "/get")
    public ResultDTO getInfoByQuestionId(String questionId) {
        return tuserClaimService.getInfoByQuestionId(questionId);
    }

}
