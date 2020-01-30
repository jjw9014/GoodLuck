package com.help.server.service;

import com.help.api.QuestionPageParam;
import com.help.api.QuestionParam;
import com.help.server.model.Question;

import java.util.List;

public interface IQuestionService {
    public int submit(QuestionParam param);

    public List<Question> list(QuestionPageParam pageParam);

    public int count(QuestionPageParam pageParam);

    public Question info(String number);
}
