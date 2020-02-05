package com.help.server.service;

import com.help.api.AreaParam;
import com.help.server.model.Area;

import java.util.List;

public interface IAreaService {
    public List<AreaParam> list(AreaParam param);

    public AreaParam get(AreaParam param);
}
