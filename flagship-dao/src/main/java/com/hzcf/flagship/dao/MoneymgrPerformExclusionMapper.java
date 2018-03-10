package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.MoneymgrPerformExclusion;
import com.hzcf.flagship.model.MoneymgrPerformExclusionExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
/**
 * 
 * 类名：MoneymgrPerformExclusionMapper.java</dt>
 * 功能描述：理财业绩排除表
 * 创建时间： 2017年5月12日 下午2:36:16</dd>
*  创建人：TieGuoWei</dd>
 */
public interface MoneymgrPerformExclusionMapper {
    int countByExample(MoneymgrPerformExclusionExample example);

    int deleteByExample(MoneymgrPerformExclusionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MoneymgrPerformExclusion record);

    int insertSelective(MoneymgrPerformExclusion record);

    List<MoneymgrPerformExclusion> selectByExample(MoneymgrPerformExclusionExample example);

    MoneymgrPerformExclusion selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MoneymgrPerformExclusion record, @Param("example") MoneymgrPerformExclusionExample example);

    int updateByExample(@Param("record") MoneymgrPerformExclusion record, @Param("example") MoneymgrPerformExclusionExample example);

    int updateByPrimaryKeySelective(MoneymgrPerformExclusion record);

    int updateByPrimaryKey(MoneymgrPerformExclusion record);
    /**
	 * 理财业绩排除表批量插入
	 */
	void insertPerformanceExclude(List<MoneymgrPerformExclusion> resultList);
	/**
	 * 理财业绩排除表查询总条数
	 */
	Long findAllByPageCount(Map<String, Object> paramsCondition);
	/**
	 * 理财业绩排除表分页查询
	 */
	List<Map<String, Object>> findAllRetMapByPage(Map<String, Object> paramsCondition);
}