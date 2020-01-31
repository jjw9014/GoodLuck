package com.help.server.service;

import com.help.api.AreaParam;
import com.help.server.model.Area;

import java.util.List;

public interface IAreaService {
    public List<Area> list(AreaParam param);

    public Area get(AreaParam param);
}
