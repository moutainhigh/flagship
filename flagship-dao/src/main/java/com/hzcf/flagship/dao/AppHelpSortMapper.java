package com.hzcf.flagship.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hzcf.flagship.model.AppHelpSort;
import com.hzcf.flagship.model.AppHelpSortExample;

public interface AppHelpSortMapper {
    int countByExample(AppHelpSortExample example);

    int deleteByExample(AppHelpSortExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppHelpSort record);

    int insertSelective(AppHelpSort record);

    List<AppHelpSort> selectByExample(AppHelpSortExample example);

    AppHelpSort selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppHelpSort record, @Param("example") AppHelpSortExample example);

    int updateByExample(@Param("record") AppHelpSort record, @Param("example") AppHelpSortExample example);

    int updateByPrimaryKeySelective(AppHelpSort record);

    int updateByPrimaryKey(AppHelpSort record);

}