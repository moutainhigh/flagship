package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.RiskManualAdjustment;
import com.hzcf.flagship.model.RiskManualAdjustmentExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface RiskManualAdjustmentMapper {
    int countByExample(RiskManualAdjustmentExample example);

    int deleteByExample(RiskManualAdjustmentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RiskManualAdjustment record);

    int insertSelective(RiskManualAdjustment record);

    List<RiskManualAdjustment> selectByExample(RiskManualAdjustmentExample example);

    RiskManualAdjustment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RiskManualAdjustment record, @Param("example") RiskManualAdjustmentExample example);

    int updateByExample(@Param("record") RiskManualAdjustment record, @Param("example") RiskManualAdjustmentExample example);

    int updateByPrimaryKeySelective(RiskManualAdjustment record);

    int updateByPrimaryKey(RiskManualAdjustment record);

	void insertManualAdjustment(List<?> list2);

	List<Map<String, Object>> findAllRetMapByPage(Map<String, Object> paramsCondition);

	Long findAllByPageCount(Map<String, Object> paramsCondition);
}