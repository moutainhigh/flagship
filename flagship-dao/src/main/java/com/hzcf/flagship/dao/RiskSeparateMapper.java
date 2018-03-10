package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.RiskSeparate;
import com.hzcf.flagship.model.RiskSeparateExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface RiskSeparateMapper {
    int countByExample(RiskSeparateExample example);

    int deleteByExample(RiskSeparateExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RiskSeparate record);

    int insertSelective(RiskSeparate record);

    List<RiskSeparate> selectByExample(RiskSeparateExample example);

    RiskSeparate selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RiskSeparate record, @Param("example") RiskSeparateExample example);

    int updateByExample(@Param("record") RiskSeparate record, @Param("example") RiskSeparateExample example);

    int updateByPrimaryKeySelective(RiskSeparate record);

    int updateByPrimaryKey(RiskSeparate record);

    /**
     * 查找最新的贷后分割点（后台）
     * @return
     */
	RiskSeparate findSeparate();
	/**
	 * 查找最新的贷后分割点（前台app）
	 * @param days
	 * @return
	 * @author guodong
	 */
	Map<String, Object> selectSeparate(String days);
}