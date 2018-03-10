package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.AppHelpContent;
import com.hzcf.flagship.model.AppHelpContentExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface AppHelpContentMapper {
	int countByExample(AppHelpContentExample example);

    int deleteByExample(AppHelpContentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AppHelpContent record);

    int insertSelective(AppHelpContent record);

    List<AppHelpContent> selectByExampleWithBLOBs(AppHelpContentExample example);

    List<AppHelpContent> selectByExample(AppHelpContentExample example);

    AppHelpContent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AppHelpContent record, @Param("example") AppHelpContentExample example);

    int updateByExampleWithBLOBs(@Param("record") AppHelpContent record, @Param("example") AppHelpContentExample example);

    int updateByExample(@Param("record") AppHelpContent record, @Param("example") AppHelpContentExample example);

    int updateByPrimaryKeySelective(AppHelpContent record);

    int updateByPrimaryKeyWithBLOBs(AppHelpContent record);

    int updateByPrimaryKey(AppHelpContent record);

	List<Map<String, Object>> findAllRetMapByPage(Map<String, Object> paramsCondition);

	Long findAllByPageCount(Map<String, Object> paramsCondition);

	Map<String, Object> findByContentID(Integer id);

	long getCountBySortId(AppHelpContent appHelpContent);
    /**
     * 根据问题种类查询
     * @param sortId
     * @return
     */
	List<Map<String, Object>> selectBySortName(String sortName);
	/**
	 * 根据id查找问答
	 * @param id
	 * @return
	 */
	Map<String, Object> selectById(String id);
	/**
	 * 自增1
	 * @param id
	 */
	void addTimes(String id);

}