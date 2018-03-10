package com.hzcf.flagship.dao;

import java.util.List;
import java.util.Map;

import com.hzcf.flagship.model.CheckLog;

public interface CheckLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CheckLog record);

    int insertSelective(CheckLog record);

    CheckLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CheckLog record);

    int updateByPrimaryKeyWithBLOBs(CheckLog record);

    int updateByPrimaryKey(CheckLog record);

	CheckLog getSort();

	List<Map<String, Object>> findLogList();
	/**
	 * 分页查询
	 */
	List<Map<String, Object>> findAllRetMapByPage(Map<String, Object> paramsCondition);
	/**
	 * 查总数
	 */
	Long findAllByPageCount(Map<String, Object> paramsCondition);
	/**
	 * 查询接口检测类型
	 */
	List<?> findAllType();
	/**
	 * 查询接口检测结果类型
	 * @return
	 */
	List<?> findAllResult();
	/**
	 * 查询接口检测结果次数
	 * @return
	 */
	List<?> findAllSort(Map<String, Object> paramsCondition);
	
}