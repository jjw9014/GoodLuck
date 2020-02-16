package com.help.server.controller;

import com.help.api.ResultDTO;
import com.help.server.common.ResultHandler;
import com.help.server.service.BaseDicService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 字典控制类
 */
@RestController
@RequestMapping("/dic/")
public class BaseDicController {

    @Resource
    private BaseDicService baseDicService;

    /**
     * 获取身份集合
     * @return
     */
    @RequestMapping(value = "/getIdentityMap")
    public ResultDTO getIdentityMap() {

        return ResultHandler.handleSuccess(baseDicService.getIndentityTypes());
    }

    /**
     * 获取无黑名单的身份集合
     * @return
     */
    @RequestMapping(value = "/getIdentityNoBlackMap")
    public ResultDTO getIdentityNoBlackMap() {

        return ResultHandler.handleSuccess(baseDicService.getIndentityNoBlackTypes());
    }


}
