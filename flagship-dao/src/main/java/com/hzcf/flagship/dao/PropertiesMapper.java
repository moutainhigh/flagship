package com.hzcf.flagship.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hzcf.flagship.model.Properties;
import com.hzcf.flagship.model.PropertiesExample;

public interface PropertiesMapper {
    int countByExample(PropertiesExample example);

    int deleteByExample(PropertiesExample example);

    int insert(Properties record);

    int insertSelective(Properties record);

    List<Properties> selectByExample(PropertiesExample example);

    int updateByExampleSelective(@Param("record") Properties record, @Param("example") PropertiesExample example);

    int updateByExample(@Param("record") Properties record, @Param("example") PropertiesExample example);

	List<Map<String, Object>> findAllRetMapByPage(Map<String, Object> paramsCondition);

	Long findAllByPageCount(Map<String, Object> paramsCondition);

	Properties selectByPrimaryKey(Integer id);

	void doUpdateProperty(Map<String, Object> map);

	Properties findValueByPropertyName(Map<String, Object> map);
}