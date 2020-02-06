package com.help.server.service;

import com.help.api.PictureParam;

import java.util.List;

public interface PictureService {
    public int submit(PictureParam param);

    public List<PictureParam> list(List<String> md5List);
}
