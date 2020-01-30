package com.help.server.service;

import com.help.api.ResultDTO;
import com.help.server.common.ResultHandler;
import com.help.server.dao.QuestionMapper;
import com.help.server.model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户认领实现类
 * @Author LY
 * @Date 2020/1/30 17:18
 * @Version 1.0
 **/
@Service
public class TuserClaimServiceImpl implements TuserClaimService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TuserServiceImpl.class);

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public ResultDTO getInfoByQuestionId(String questionId) {
        Question question = questionMapper.selectByPrimaryKey(questionId);
        if(question != null){
            Map<String,String> map = new HashMap<String,String>();
            map.put("mobile",question.getMobile());
            map.put("wxNumber",question.getWxNumber() != null ? question.getWxNumber() : "");
            return ResultHandler.handleSuccess(map);
        }else{
            return ResultHandler.handleError("该问题不存在",null);
        }
    }
}
