package com.hzcf.flagship.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hzcf.flagship.model.RiskProduct;
import com.hzcf.flagship.model.RiskProductExample;

public interface RiskProductMapper {
    int countByExample(RiskProductExample example);

    int deleteByExample(RiskProductExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RiskProduct record);

    int insertSelective(RiskProduct record);

    List<RiskProduct> selectByExample(RiskProductExample example);

    RiskProduct selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RiskProduct record, @Param("example") RiskProductExample example);

    int updateByExample(@Param("record") RiskProduct record, @Param("example") RiskProductExample example);

    int updateByPrimaryKeySelective(RiskProduct record);

    int updateByPrimaryKey(RiskProduct record);

	RiskProduct findProductNumByProduct(String product);
	
	/**
	 * 查询CM1回款率表中有但是产品表中没有的产品
	 */
	List<Map<String, Object>> findCm1NotexistProducts();
	
	/**
	 * 查询M1逾期率表中有但是产品表中没有的产品
	 */
	List<Map<String, Object>> findM1OverdueNotexistProducts();
}