package com.help.api;

import java.util.List;

public interface QuestionFacade {
    public ResultDTO pub(QuestionParam param);

    public ResultDTO focus(String number, String userId);

    public ResultDTO updateInfo(QuestionUpdateParam updateParam);

    public ResultDTO resolve(String number, String userId);

    public ResultDTO audit(String number, String userId, String auditState);

    public ResultDTO<List> list(QuestionPageParam pageParam);

    public ResultDTO<QuestionParam> info(String number);

    public ResultDTO<QuestionCountDTO> count();

    public ResultDTO depreate(String number, String userId);
}
