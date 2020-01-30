package com.help.server.service;

import com.help.api.ResultDTO;

/**
 * 用户认领接口
 * @Author LY
 * @Date 2020/1/30 17:18
 * @Version 1.0
 **/
public interface TuserClaimService {
    /**
     * 通过问题id获取认领的问题
     * @param questionId 问题id
     * @return ResultDTO
     */
    ResultDTO getInfoByQuestionId(String questionId);
}
