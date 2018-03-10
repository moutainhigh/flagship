package com.hzcf.flagship.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hzcf.flagship.model.FinanceDailyPage;
import com.hzcf.flagship.model.FinanceDailyPageExample;

public interface FinanceDailyPageMapper {
    int countByExample(FinanceDailyPageExample example);

    int deleteByExample(FinanceDailyPageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FinanceDailyPage record);

    int insertSelective(FinanceDailyPage record);

    List<FinanceDailyPage> selectByExample(FinanceDailyPageExample example);

    FinanceDailyPage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FinanceDailyPage record, @Param("example") FinanceDailyPageExample example);

    int updateByExample(@Param("record") FinanceDailyPage record, @Param("example") FinanceDailyPageExample example);

    int updateByPrimaryKeySelective(FinanceDailyPage record);

    int updateByPrimaryKey(FinanceDailyPage record);
    
    /**
     * 批量插入融资日历史基本数据
     */
    void insertFinanceDailyPages(List<FinanceDailyPage> pages);

	List<FinanceDailyPage> getValueByCodeAndDate(Map<String, Object> map);

	BigDecimal selectByDataName(Map<String, Object> codeMap);

	BigDecimal selectIndex(Map<String, Object> wellMap);
}