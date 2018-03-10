package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.MoneymgrOrgData;
import com.hzcf.flagship.model.MoneymgrOrgDataExample;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * 
 * 类名：MoneymgrOrgDataMapper.java</dt> 
 * 功能描述：理财对比表
 * 创建时间： 2017年5月10日 上午11:52:02
 * </dd> 创建人：TieGuoWei</dd>
 */
public interface MoneymgrOrgDataMapper {
	int countByExample(MoneymgrOrgDataExample example);

    int deleteByExample(MoneymgrOrgDataExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MoneymgrOrgData record);

    int insertSelective(MoneymgrOrgData record);

    List<MoneymgrOrgData> selectByExample(MoneymgrOrgDataExample example);

    MoneymgrOrgData selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MoneymgrOrgData record, @Param("example") MoneymgrOrgDataExample example);

    int updateByExample(@Param("record") MoneymgrOrgData record, @Param("example") MoneymgrOrgDataExample example);

    int updateByPrimaryKeySelective(MoneymgrOrgData record);

    int updateByPrimaryKey(MoneymgrOrgData record);
    /**
	 * 理财对比表分页查询
	 * 
	 */
	List<Map<String, Object>> findAllRetMapByPage(Map<String, Object> paramsCondition);
	/**
	 * 理财对比表查询总条数
	 * 
	 */
	Long findAllByPageCount(Map<String, Object> paramsCondition);
	/**
	 * 理财对比表数据批量插入
	 * 
	 */
	void insertMoneyCompare(List<MoneymgrOrgData> resultList);
	
	/**
	 * 查询营业部数量
	 * @param dateString
	 * @return
	 */
	long getOrgNum(String dateString);
	
	//String getDistrictPrincipal()
	/**
	 * 根据指定月份的第一天 和最后一天 删除指定月的数据
	 * @param requestMap
	 */
	void deleteOrgDataByFirstDayAndLastDay(Map<String, Object> requestMap);
	
	/**
	 * 查询指定日期上个月的咨询师人数
	 * @param dateString 格式:yyyy-MM-dd
	 * @return
	 */

	long getCounselorNumOfLastMonth(String dateString);
	
	/**
	 * 得到指定月所有的区域数据(区域名称,区域负责人,区域月计划,区域上月咨询师人数)
	 * @param dateString 格式:yyyy-MM-01
	 * @return
	 */
	List<Map<String, Object>> getAllDistrictData(String dateString);

	/**
	 * 查询所有区域
	 * @param parmMap
	 * @return
	 */
	List<Map<String, Object>> findAllAreaName (Map<String, Object> parmMap);

	/**
	 * 查询所有区域下的机构
	 * @param parmMap
	 * @return
	 */
	List<Map<String, Object>> findOrgNameByArea (Map<String, Object> parmMap);

	/**
	 * 查询所有区域和机构名称
	 * @return
	 */
	List<Map<String, Object>> findAllAreaOrgName ();
	
	/**
	 * 根据日期查询记录数count(*)与营业部count(DISTINCT org_name)
	 * 记录的差值(用于判断数据是否重复),以及标记为整体的数据和大区个数的差值(用于判断是否有漏掉大区整体的情况)
	 */
	Map<String, Object> getDiffOfCountAndDistinctOrgNumByDate(Date date);

}