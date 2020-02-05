package com.help.server.service;

import com.help.server.model.Suggest;

import java.util.List;

public interface ISuggestService {
    public int submit(Suggest suggest);

    public List<Suggest> list(Integer pageSize, Integer pageNo);

    public int count();
}
