package com.help.server.service;

import com.help.api.AreaParam;
import com.help.server.dao.AreaMapper;
import com.help.server.model.Area;
import com.help.server.model.AreaExample;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class AreaServiceImpl implements IAreaService {
    @Autowired
    private AreaMapper areaMapper;

    @Override
    public List<Area> list(AreaParam param) {
        AreaExample example = new AreaExample();
        AreaExample.Criteria criteria = example.createCriteria();

        if (StringUtils.isNotEmpty(param.getAreaCode())) {
            criteria.andAreaCodeEqualTo(param.getAreaCode());
        }
        if (StringUtils.isNotEmpty(param.getAreaName())) {
            criteria.andAreaNameLike("%" +param.getAreaName() +"%");
        }
        if (StringUtils.isNotEmpty(param.getAreaParentCode())) {
            criteria.andAreaParentCodeEqualTo(param.getAreaParentCode());
        }
        if (param.getAreaType() != null) {
            criteria.andAreaTypeEqualTo(param.getAreaType());
        }

        return areaMapper.selectByExample(example);
    }

    @Override
    public Area get(AreaParam param) {
        List<Area> areaList = list(param);

        return CollectionUtils.isEmpty(areaList) ? null : areaList.get(0);
    }
}
