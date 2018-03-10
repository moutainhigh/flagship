package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.RiskLoanM1Overdue;
import com.hzcf.flagship.model.RiskLoanM1OverdueExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface RiskLoanM1OverdueMapper {
    int countByExample(RiskLoanM1OverdueExample example);

    int deleteByExample(RiskLoanM1OverdueExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RiskLoanM1Overdue record);

    int insertSelective(RiskLoanM1Overdue record);

    List<RiskLoanM1Overdue> selectByExample(RiskLoanM1OverdueExample example);

    RiskLoanM1Overdue selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RiskLoanM1Overdue record, @Param("example") RiskLoanM1OverdueExample example);

    int updateByExample(@Param("record") RiskLoanM1Overdue record, @Param("example") RiskLoanM1OverdueExample example);

    int updateByPrimaryKeySelective(RiskLoanM1Overdue record);

    int updateByPrimaryKey(RiskLoanM1Overdue record);
    
    /**
     * 根获得某天某个机构的某个产品的m1逾期率和连续六个月放款笔数 
     */
    Map<String, Object> getM1ValueByOrgAndProduct(Map<String, Object> params);
    
    /**
     * 获得总记录数和重复数
     * @return
     */
    Map<String, Object> getTotalCountAndDiffByDate(String date);
    
    /**
	 * 将所有精英类(1.69,1.89等)产品聚合成精英类(product_no=991)
	 */
	void updateEliteSum(String date);
	
	/**
	 * 将所有公积金类(2.39)产品聚合成公积金类(product_no=11)
	 */
	void updateProvidentFundSum(String date);
	
	/**
	 * 获得一个营业部的编号
	 * @param date
	 * @return
	 */
	Map<String, Object> getOneOrgAndProduct(String date);
}