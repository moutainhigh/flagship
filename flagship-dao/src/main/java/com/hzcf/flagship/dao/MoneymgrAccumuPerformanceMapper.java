package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.MoneymgrAccumuPerformance;
import com.hzcf.flagship.model.MoneymgrAccumuPerformanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MoneymgrAccumuPerformanceMapper {
    int countByExample(MoneymgrAccumuPerformanceExample example);

    int deleteByExample(MoneymgrAccumuPerformanceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MoneymgrAccumuPerformance record);

    int insertSelective(MoneymgrAccumuPerformance record);

    List<MoneymgrAccumuPerformance> selectByExample(MoneymgrAccumuPerformanceExample example);

    MoneymgrAccumuPerformance selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MoneymgrAccumuPerformance record, @Param("example") MoneymgrAccumuPerformanceExample example);

    int updateByExample(@Param("record") MoneymgrAccumuPerformance record, @Param("example") MoneymgrAccumuPerformanceExample example);

    int updateByPrimaryKeySelective(MoneymgrAccumuPerformance record);

    int updateByPrimaryKey(MoneymgrAccumuPerformance record);

	int selectByDate(String days);
}