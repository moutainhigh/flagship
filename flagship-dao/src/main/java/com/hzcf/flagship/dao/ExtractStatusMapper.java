package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.ExtractStatus;
import com.hzcf.flagship.model.ExtractStatusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExtractStatusMapper {
    int countByExample(ExtractStatusExample example);

    int deleteByExample(ExtractStatusExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ExtractStatus record);

    int insertSelective(ExtractStatus record);

    List<ExtractStatus> selectByExample(ExtractStatusExample example);

    ExtractStatus selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ExtractStatus record, @Param("example") ExtractStatusExample example);

    int updateByExample(@Param("record") ExtractStatus record, @Param("example") ExtractStatusExample example);

    int updateByPrimaryKeySelective(ExtractStatus record);

    int updateByPrimaryKey(ExtractStatus record);

	long findCountByDate(String days);
}