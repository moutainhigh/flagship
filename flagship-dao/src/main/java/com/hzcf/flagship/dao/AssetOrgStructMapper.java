package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.AssetOrgStruct;
import com.hzcf.flagship.model.AssetOrgStructExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface AssetOrgStructMapper {
    int countByExample(AssetOrgStructExample example);

    int deleteByExample(AssetOrgStructExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AssetOrgStruct record);

    int insertSelective(AssetOrgStruct record);

    List<AssetOrgStruct> selectByExample(AssetOrgStructExample example);

    AssetOrgStruct selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AssetOrgStruct record, @Param("example") AssetOrgStructExample example);

    int updateByExample(@Param("record") AssetOrgStruct record, @Param("example") AssetOrgStructExample example);

    int updateByPrimaryKeySelective(AssetOrgStruct record);

    int updateByPrimaryKey(AssetOrgStruct record);

	List<?> getBusinessNameWithResultsView();

	List<Map<String, Object>> findAllRetMapByPage(Map<String, Object> paramsCondition);

	Long findAllByPageCount(Map<String, Object> paramsCondition);
}