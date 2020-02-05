package com.help.server.service;

import com.help.api.AreaParam;
import com.help.server.dao.AreaMapper;
import com.help.server.model.Area;
import com.help.server.model.AreaExample;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class AreaServiceImpl implements IAreaService {
    @Autowired
    private AreaMapper areaMapper;

    @Override
    public List<AreaParam> list(AreaParam param) {
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

        List<Area> list = areaMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(list)) {
            List<AreaParam> paramList = new ArrayList<>();
            for (Area area : list) {
                paramList.add(area2Param(area));
            }

            return paramList;
        }

        return new ArrayList<>();
    }

    private AreaParam area2Param(Area area) {
        AreaParam param = new AreaParam();
        BeanUtils.copyProperties(area, param);
        return param;
    }

    @Override
    public AreaParam get(AreaParam param) {
        List<AreaParam> areaList = list(param);

        return CollectionUtils.isEmpty(areaList) ? null : areaList.get(0);

    }
}
