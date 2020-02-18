package com.help.server.service;

import com.help.server.cache.StaticDataCache;
import com.help.server.dao.BaseDicMapper;
import com.help.server.model.BaseDic;
import com.help.server.model.BaseDicExample;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 字典基础服务
 */
@Service
public class BaseDicServiceImpl implements BaseDicService, ApplicationRunner {

    @Resource
    private BaseDicMapper baseDicMapper;

    @Override
    public void cacheIndentityTypes() {
        BaseDicExample example = new BaseDicExample();
        BaseDicExample.Criteria criteria= example.createCriteria();
        criteria.andCodeEqualTo("identity_type");
        example.setOrderByClause(" CAST(item_value AS SIGNED)  ASC ");
        List<BaseDic> list = baseDicMapper.selectByExample(example);
        if(!list.isEmpty()){
            for(int i= 0;i<list.size();i++){
                String key =  list.get(i).getItemValue();
                String value =  list.get(i).getItemName();
                StaticDataCache.IDENTITY_TYPE_MAP.put(key,value);
                if(!value.contains("黑名单")){
                    StaticDataCache.IDENTITY_TYPE_NO_BLACK_MAP.put(key,value);
                }
            }
        }
    }

    @Override
    public Map<String, String> getIndentityTypes() {
        if(StaticDataCache.IDENTITY_TYPE_MAP.isEmpty()){
            cacheIndentityTypes();
        }
        return StaticDataCache.IDENTITY_TYPE_MAP;
    }

    @Override
    public Map<String, String> getIndentityNoBlackTypes() {
        if(StaticDataCache.IDENTITY_TYPE_NO_BLACK_MAP.isEmpty()){
            cacheIndentityTypes();
        }
        return StaticDataCache.IDENTITY_TYPE_NO_BLACK_MAP;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        cacheIndentityTypes();
    }
}
