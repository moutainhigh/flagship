package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.AssetPerson;
import com.hzcf.flagship.model.AssetPersonExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface AssetPersonMapper {
    int countByExample(AssetPersonExample example);

    int deleteByExample(AssetPersonExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AssetPerson record);

    int insertSelective(AssetPerson record);

    List<AssetPerson> selectByExample(AssetPersonExample example);

    AssetPerson selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AssetPerson record, @Param("example") AssetPersonExample example);

    int updateByExample(@Param("record") AssetPerson record, @Param("example") AssetPersonExample example);

    int updateByPrimaryKeySelective(AssetPerson record);

    int updateByPrimaryKey(AssetPerson record);

    List<AssetPerson> queryAssetPersonList(Map<String, Object> param);

    Long queryAssetPersonListCount(Map<String, Object> param);

    int insertAssetPersonBatch(List<?> list);

    int deleteAssetPersonBatch(List<?> deleteList);
}