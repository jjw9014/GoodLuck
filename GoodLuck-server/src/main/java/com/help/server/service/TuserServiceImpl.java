package com.help.server.service;

import com.help.server.dao.TuserMapper;
import com.help.server.model.Tuser;
import com.help.server.model.TuserExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;


/**
 * 用户管理接口服务
 * @Author LY
 * @Date 2020/1/30 11:18
 * @Version 1.0
 **/
@Service
@Slf4j
public class TuserServiceImpl implements TuserService{


    @Autowired
    private TuserMapper tuserMapper;


    @Override
    public Tuser getUserInfoByOpenId(String openId) {
        return tuserMapper.selectByPrimaryKey(openId);
    }

    @Override
    public boolean addWxUserInfo(Tuser tuser) {
        tuser.setCreateTime(new Date());
        String openId = tuser.getId();
        if(openId == null){
            return false;
        }else{
            Tuser user = tuserMapper.selectByPrimaryKey(openId);
            if(user != null){
                log.info("用户名已存在，不可重复添加");
                return false;
            }else{
                return tuserMapper.insertSelective(tuser) > 0 ? true : false;
            }
        }
    }

    @Override
    public boolean editUserInfo(Tuser tuser) {
        tuser.setLastUpdateTime(new Date());
        int updateNum= tuserMapper.updateByPrimaryKeySelective(tuser);
        if(updateNum > 0){
            return true;
        }else{
            log.info("没有该用户");
            return false;
        }
    }

    @Override
    public Map<String,Tuser> list(Set<String> set) {
        TuserExample example = new TuserExample();
        example.createCriteria().andIdIn(new ArrayList<>(set));
        List<Tuser> list = tuserMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return new HashMap<>();
        }
        Map<String,Tuser> map = new HashMap<>();
        for (Tuser user : list) {
            map.put(user.getId(),user);
        }
        return map;
    }

    @Override
    public Map<String, Tuser> getUserInfoToMap(String openId) {
        Tuser user = this.getUserInfoByOpenId(openId);
        Map<String,Tuser> map = new HashMap<>();
        if(user != null){
            map.put(user.getId(),user);
        }
        return map;
    }

    @Override
    public void register(Tuser tuser) {

        String openId = tuser.getId();
        if(openId != null){
            Tuser user = tuserMapper.selectByPrimaryKey(openId);
            if(user != null){
                log.info("用户名已存在，进行修改操作");
                this.editUserInfo(tuser);
            }else{
                tuser.setCreateTime(new Date());
                int insertNum = tuserMapper.insertSelective(tuser);
                if(insertNum > 0){
                    log.info("用户添加成功");
                }
            }
        }else{
            tuser = null;
        }
    }
}
