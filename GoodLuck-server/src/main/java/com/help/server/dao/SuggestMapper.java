package com.help.server.dao;

import com.help.server.model.Suggest;
import com.help.server.model.SuggestExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SuggestMapper {
    int countByExample(SuggestExample example);

    int deleteByExample(SuggestExample example);

    int deleteByPrimaryKey(String id);

    int insert(Suggest record);

    int insertSelective(Suggest record);

    List<Suggest> selectByExample(SuggestExample example);

    Suggest selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Suggest record, @Param("example") SuggestExample example);

    int updateByExample(@Param("record") Suggest record, @Param("example") SuggestExample example);

    int updateByPrimaryKeySelective(Suggest record);

    int updateByPrimaryKey(Suggest record);
}