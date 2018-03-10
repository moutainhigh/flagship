package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.RiskOldLoan;
import com.hzcf.flagship.model.RiskOldLoanExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface RiskOldLoanMapper {
    int countByExample(RiskOldLoanExample example);

    int deleteByExample(RiskOldLoanExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RiskOldLoan record);

    int insertSelective(RiskOldLoan record);

    List<RiskOldLoan> selectByExample(RiskOldLoanExample example);

    RiskOldLoan selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RiskOldLoan record, @Param("example") RiskOldLoanExample example);

    int updateByExample(@Param("record") RiskOldLoan record, @Param("example") RiskOldLoanExample example);

    int updateByPrimaryKeySelective(RiskOldLoan record);

    int updateByPrimaryKey(RiskOldLoan record);

	void insertOldLoan(List<?> list2);

	List<Map<String, Object>> findAllRetMapByPage(Map<String, Object> paramsCondition);

	Long findAllByPageCount(Map<String, Object> paramsCondition);
}