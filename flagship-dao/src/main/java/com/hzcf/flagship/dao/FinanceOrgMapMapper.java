package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.FinanceOrgMap;
import com.hzcf.flagship.model.FinanceOrgMapExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FinanceOrgMapMapper {
    int countByExample(FinanceOrgMapExample example);

    int deleteByExample(FinanceOrgMapExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FinanceOrgMap record);

    int insertSelective(FinanceOrgMap record);

    List<FinanceOrgMap> selectByExample(FinanceOrgMapExample example);

    FinanceOrgMap selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FinanceOrgMap record, @Param("example") FinanceOrgMapExample example);

    int updateByExample(@Param("record") FinanceOrgMap record, @Param("example") FinanceOrgMapExample example);

    int updateByPrimaryKeySelective(FinanceOrgMap record);

    int updateByPrimaryKey(FinanceOrgMap record);

	void insertFinaceOrgMap(List<?> list2);

	List<Map<String, Object>> findAllRetMapByPage(Map<String, Object> paramsCondition);

	Long findAllByPageCount(Map<String, Object> paramsCondition);

	List<Map<String, Object>> findAllDistrict();
}