package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.AssetBusiness;
import com.hzcf.flagship.model.AssetBusinessExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface AssetBusinessMapper {
    int countByExample(AssetBusinessExample example);

    int deleteByExample(AssetBusinessExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AssetBusiness record);

    int insertSelective(AssetBusiness record);

    List<AssetBusiness> selectByExample(AssetBusinessExample example);

    AssetBusiness selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AssetBusiness record, @Param("example") AssetBusinessExample example);

    int updateByExample(@Param("record") AssetBusiness record, @Param("example") AssetBusinessExample example);

    int updateByPrimaryKeySelective(AssetBusiness record);

    int updateByPrimaryKey(AssetBusiness record);

    List<?> selectAssetBusiness();

	List<Map<String, Object>> findAllRetMapByPage(Map<String, Object> paramsCondition);

	Long findAllByPageCount(Map<String, Object> paramsCondition);
}