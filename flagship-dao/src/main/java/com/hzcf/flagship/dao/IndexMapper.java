package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.Index;
import com.hzcf.flagship.model.IndexExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface IndexMapper {
	int countByExample(IndexExample example);

    int deleteByExample(IndexExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Index record);

    int insertSelective(Index record);

    List<Index> selectByExample(IndexExample example);

    Index selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Index record, @Param("example") IndexExample example);

    int updateByExample(@Param("record") Index record, @Param("example") IndexExample example);

    int updateByPrimaryKeySelective(Index record);

    int updateByPrimaryKey(Index record);

	void updateByCode(Index index);

	List<Index> getValueByCode(Map<String, Object> map);

	Index getLineByCode(String totalAvgWaring);
}