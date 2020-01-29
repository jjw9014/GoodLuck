package com.help.server.dao;

import com.help.server.model.Tuser;
import com.help.server.model.TuserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TuserMapper {
    int countByExample(TuserExample example);

    int deleteByExample(TuserExample example);

    int deleteByPrimaryKey(String id);

    int insert(Tuser record);

    int insertSelective(Tuser record);

    List<Tuser> selectByExample(TuserExample example);

    Tuser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Tuser record, @Param("example") TuserExample example);

    int updateByExample(@Param("record") Tuser record, @Param("example") TuserExample example);

    int updateByPrimaryKeySelective(Tuser record);

    int updateByPrimaryKey(Tuser record);
}