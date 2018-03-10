package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.RiskSubcenter;
import com.hzcf.flagship.model.RiskSubcenterExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RiskSubcenterMapper {
    int countByExample(RiskSubcenterExample example);

    int deleteByExample(RiskSubcenterExample example);

    /**
     * 逻辑删除(update status = '0')
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    int insert(RiskSubcenter record);

    int insertSelective(RiskSubcenter record);

    List<RiskSubcenter> selectByExample(RiskSubcenterExample example);

    RiskSubcenter selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RiskSubcenter record, @Param("example") RiskSubcenterExample example);

    int updateByExample(@Param("record") RiskSubcenter record, @Param("example") RiskSubcenterExample example);

    int updateByPrimaryKeySelective(RiskSubcenter record);

    int updateByPrimaryKey(RiskSubcenter record);

    List<RiskSubcenter> subcenterList(Map<String, Object> paramsCondition);

    /**
     * 返回list，使用list.size()既是分中心列表总条数
     * @param paramsCondition
     * @return
     */
    List<Long> subcenterListCount(Map<String, Object> paramsCondition);

    /**
     * 查找分中心表中的新记录
     */
    List<Map<String, String>> findNewSubcente();
    
    /**
     * 查询分中心表中的修改记录(与权限表中的名称不一致)
     */
    List<Map<String, Object>> findUpdateSubcente();
    
    
     /** 查询最新的分中心数据
     * @Name:selectForDistinct
     * @Author:YuanGuoLei
     * @CreateTime:2017年11月27日下午4:30:19
     * @return
     */
    List<RiskSubcenter> selectForDistinct();
    /**
     * 查询分中心cm1List
     * @Name:selectForDistinct
     * @return
     */
	List<Map<String, Object>> findSubcenterCm1List(Map<String, Object> map);
	 /**
     * 查询分中心cm1Sum
     * @Name:selectForDistinct
     * @return
     */
	Map findSubcenterCm1ListSum(Map<String, Object> map);
	 /**
     * 查询分中心plan
     * @Name:selectForDistinct
     * @return
     */
	Map findSubcenterCm1Plan(Map<String, Object> map);
}