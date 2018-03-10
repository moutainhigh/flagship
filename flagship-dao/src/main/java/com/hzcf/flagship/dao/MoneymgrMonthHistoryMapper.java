package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.MoneymgrMonthHistory;
import com.hzcf.flagship.model.MoneymgrMonthHistoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * 
 * 类名：MoneymgrMonthHistoryMapper.java</dt> 
 * 功能描述：理财月度累计表
 * 创建时间： 2017年5月10日 上午11:52:02
 * </dd> 创建人：TieGuoWei</dd>
 */
public interface MoneymgrMonthHistoryMapper {
    int countByExample(MoneymgrMonthHistoryExample example);
    /**
	 * 理财月度累计表数据清除
	 */
    int deleteByExample(MoneymgrMonthHistoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MoneymgrMonthHistory record);

    int insertSelective(MoneymgrMonthHistory record);

    List<MoneymgrMonthHistory> selectByExample(MoneymgrMonthHistoryExample example);

    MoneymgrMonthHistory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MoneymgrMonthHistory record, @Param("example") MoneymgrMonthHistoryExample example);

    int updateByExample(@Param("record") MoneymgrMonthHistory record, @Param("example") MoneymgrMonthHistoryExample example);

    int updateByPrimaryKeySelective(MoneymgrMonthHistory record);

    int updateByPrimaryKey(MoneymgrMonthHistory record);
    /**
	 * 理财月度累计表上传 数据导入
	 */

	void insertMoneymgrMonthHistory(List<?> list2);
	/**
	 * 理财月度累计表分页查询
	 */

	List<Map<String, Object>> findAllRetMapByPage(Map<String, Object> paramsCondition);
	/**
	 * 理财月度累计表查询总条数
	 */

	Long findAllByPageCount(Map<String, Object> paramsCondition);

    /**
     * 理财历史月度累计 统计
     * @param paramsCondition
     * @return
     */
    List<Map<String, Object>> findPerformanceByYear(Map<String, Object> paramsCondition);

    /**
     * 理财历史月度达成率 统计
     * @param paramsCondition
     * @return
     */
    List<Map<String, Object>> findPerformanceRateByYear(Map<String, Object> paramsCondition);

    /**
     * 理财历史月度 机构数目
     * @param paramsCondition
     * @return
     */
    List<Map<String, Object>> findOrgNumByYear(Map<String, Object> paramsCondition);

    /**
     * 理财历史月度 新客户数
     * @param paramsCondition
     * @return
     */
    List<Map<String, Object>> findNewClientNumByYear(Map<String, Object> paramsCondition);


    /**
     * 理财人员人效 月度 人均新客户数
     * @param paramsCondition
     * @return
     */
    List<Map<String, Object>> findPerCapitaNewClientByYear(Map<String, Object> paramsCondition);


    /**
     * 理财人员人效 月度  人均产能
     * @param paramsCondition
     * @return
     */
    List<Map<String, Object>> findPerCapitaCapacityByYear(Map<String, Object> paramsCondition);
    
    /**
	 * 根据日期查询记录数(count *)和指标数(count DISTINCT record_name,index_name)之间的差值
	 * @param recordDate
	 * @return
	 */
	Map<String, Object> getDiffOfCountAndIndexNumByDate(Date date);
}