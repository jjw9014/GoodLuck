package com.help.api;

import java.util.List;

public interface SuggestFacade {
    public ResultDTO submit(SuggestParam suggestParam);

    public ResultDTO<List> list(int pageSize, int pageNo);
}
