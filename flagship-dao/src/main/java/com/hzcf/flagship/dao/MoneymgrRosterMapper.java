package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.MoneymgrRoster;
import com.hzcf.flagship.model.MoneymgrRosterExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
/**
 * 
 * 类名：MoneymgrRosterMapper.java</dt> 
 * 功能描述：理财人力花名册
 * 创建时间： 2017年5月10日 上午11:52:02
 * </dd> 创建人：TieGuoWei</dd>
 */
public interface MoneymgrRosterMapper {
	int countByExample(MoneymgrRosterExample example);

    int deleteByExample(MoneymgrRosterExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MoneymgrRoster record);

    int insertSelective(MoneymgrRoster record);

    List<MoneymgrRoster> selectByExample(MoneymgrRosterExample example);

    MoneymgrRoster selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MoneymgrRoster record, @Param("example") MoneymgrRosterExample example);

    int updateByExample(@Param("record") MoneymgrRoster record, @Param("example") MoneymgrRosterExample example);

    int updateByPrimaryKeySelective(MoneymgrRoster record);

    int updateByPrimaryKey(MoneymgrRoster record);
   
    /**
     * 获得指定机构管理人员人数
     * @param orgSimpleName
     * @return
     */
    long getManagerNum(String orgSimpleName);
    
    /**
     * 获得指定机构咨询师人数
     * @param orgSimpleName
     * @return
     */
    long getCounselorNum(String orgSimpleName);
    /**
   	 * 理财人力花名册 数据导入
   	 */
	void insertMoneymgrRoster(List<?> list2);
	/**
	 * 
	 * Description: 理财人力花名册查询总条数
	 */
	Long findAllByPageCount(Map<String, Object> paramsCondition);
	/**
	 * 
	 * Description: 理财花名册分页查询列表
	 */
	List<Map<String, Object>> findAllRetMapByPage(Map<String, Object> paramsCondition);
	
	/**
	 * 根据机构简称获得机构负责人名字
	 * @param orgName
	 * @return
	 */
	String getOrgPrincipal(String orgName);
}