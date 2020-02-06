package com.help.server.service;

import com.help.api.PictureParam;
import com.help.server.dao.PictureMapper;
import com.help.server.model.Picture;
import com.help.server.model.PictureExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    private PictureMapper pictureMapper;

    @Override
    public int submit(PictureParam param) {
        Picture picture = new Picture();

        BeanUtils.copyProperties(param, picture);
        picture.setCreateTime(Calendar.getInstance().getTime());

        return pictureMapper.insert(picture);
    }

    @Override
    public List<PictureParam> list(List<String> md5List) {
        PictureExample example = new PictureExample();
        example.createCriteria().andPicMd5In(md5List);

        List<Picture> list = pictureMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }

        List<PictureParam> paramList = new ArrayList<>();
        for (Picture picture : list) {
            PictureParam param = new PictureParam();
            BeanUtils.copyProperties(picture, param);
            paramList.add(param);
        }
        return paramList;
    }
}
