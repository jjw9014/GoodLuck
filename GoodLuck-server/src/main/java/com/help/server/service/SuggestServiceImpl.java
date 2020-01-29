package com.help.server.service;

import com.github.pagehelper.PageHelper;
import com.help.server.dao.SuggestMapper;
import com.help.server.model.Suggest;
import com.help.server.model.SuggestExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class SuggestServiceImpl implements ISuggestService {
    @Autowired
    private SuggestMapper suggestMapper;

    @Override
    public int submit(Suggest suggest) {
        suggest.setId(null);
        suggest.setCreateTime(Calendar.getInstance().getTime());

        return suggestMapper.insert(suggest);
    }

    @Override
    public List<Suggest> list(int pageSize, int pageNo) {
        //注意此方法后面紧跟着mybatis查询方法
        PageHelper.startPage(pageNo, pageSize);
        SuggestExample example = new SuggestExample();
        example.createCriteria();
        List<Suggest> list = suggestMapper.selectByExample(example);

        return list;
    }

    @Override
    public int count() {
        SuggestExample example = new SuggestExample();
        example.createCriteria();

        return suggestMapper.countByExample(example);
    }
}
