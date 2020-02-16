package com.help.server.dao;

import com.help.server.model.BaseDic;
import com.help.server.model.BaseDicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseDicMapper {
    int countByExample(BaseDicExample example);

    int deleteByExample(BaseDicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseDic record);

    int insertSelective(BaseDic record);

    List<BaseDic> selectByExample(BaseDicExample example);

    BaseDic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseDic record, @Param("example") BaseDicExample example);

    int updateByExample(@Param("record") BaseDic record, @Param("example") BaseDicExample example);

    int updateByPrimaryKeySelective(BaseDic record);

    int updateByPrimaryKey(BaseDic record);
}