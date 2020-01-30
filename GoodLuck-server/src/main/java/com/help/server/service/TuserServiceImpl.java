package com.help.server.service;

import com.help.api.ResultDTO;
import com.help.server.common.ResultHandler;
import com.help.server.dao.TuserMapper;
import com.help.server.model.Tuser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 用户管理接口服务
 * @Author LY
 * @Date 2020/1/30 11:18
 * @Version 1.0
 **/
@Service
public class TuserServiceImpl implements TuserService{

    private static final Logger LOGGER = LoggerFactory.getLogger(TuserServiceImpl.class);

    @Autowired
    private TuserMapper tuserMapper;

    @Override
    public ResultDTO login(String wxId) {
        return ResultHandler.handleSuccess(null);
    }

    @Override
    public ResultDTO editUserInfo(String wxId, String mobile, String name) {
        Tuser record = new Tuser();
        record.setId(wxId);
        record.setMobile(mobile);
        record.setName(name);
        record.setLastUpdateTime(new Date());
        Tuser tuser = tuserMapper.selectByPrimaryKey(wxId);
        if(tuser != null){
            return ResultHandler.handleSuccess(tuserMapper.updateByPrimaryKeySelective(record));
        }else{
            return ResultHandler.handleError("该用户不存在",null);
        }
    }
}
