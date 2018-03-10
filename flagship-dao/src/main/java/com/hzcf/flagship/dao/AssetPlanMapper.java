package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.AssetPlan;
import com.hzcf.flagship.model.AssetPlanExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface AssetPlanMapper {
    int countByExample(AssetPlanExample example);

    int deleteByExample(AssetPlanExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AssetPlan record);

    int insertSelective(AssetPlan record);

    List<AssetPlan> selectByExample(AssetPlanExample example);

    AssetPlan selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AssetPlan record, @Param("example") AssetPlanExample example);

    int updateByExample(@Param("record") AssetPlan record, @Param("example") AssetPlanExample example);

    int updateByPrimaryKeySelective(AssetPlan record);

    int updateByPrimaryKey(AssetPlan record);

    List<Map<String,Object>> selectAssetPlanList(Map<String,Object> params);

    Long selectAssetPlanListCount(Map<String, Object> param);

    List<?> selectUsefulYear();

    int insertAssetPlanBatch(List list);

    /**
     * 导入excel之前，根据主键把原数据删掉。防止数据冗余
     * @param list2
     * @return
     */
    int deleteAssetPlanBatch(List<?> list2);
}