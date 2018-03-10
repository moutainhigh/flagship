package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.MoneymgrEfficiencyPage;
import com.hzcf.flagship.model.MoneymgrEfficiencyPageExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MoneymgrEfficiencyPageMapper {
    int countByExample(MoneymgrEfficiencyPageExample example);

    int deleteByExample(MoneymgrEfficiencyPageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MoneymgrEfficiencyPage record);

    int insertSelective(MoneymgrEfficiencyPage record);

    List<MoneymgrEfficiencyPage> selectByExample(MoneymgrEfficiencyPageExample example);

    MoneymgrEfficiencyPage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MoneymgrEfficiencyPage record, @Param("example") MoneymgrEfficiencyPageExample example);

    int updateByExample(@Param("record") MoneymgrEfficiencyPage record, @Param("example") MoneymgrEfficiencyPageExample example);

    int updateByPrimaryKeySelective(MoneymgrEfficiencyPage record);

    int updateByPrimaryKey(MoneymgrEfficiencyPage record);
    /**
     * 理财 日人员人效
     * @param parm
     * @return
     */
    Map<String, Object> findEfficiencyForDays(Map<String, Object> parm);
    
    /**
     * 批量插入当月所有人员人效结果页
     * @param pages
     */
    void insertEfficiencyForMonth(List<MoneymgrEfficiencyPage> pages);
    /**
     * 查询全国机构数
     * @param days
     * @return
     */
	MoneymgrEfficiencyPage selectByDate(String days);
	/**
     * 查询人均新客户数 咨询师人数 管理员人数 咨询师/管理员人数 人均产能
     * @param days
     * @return
     */
	Map<String, Object> selectInfoByDate(String dateString);

}