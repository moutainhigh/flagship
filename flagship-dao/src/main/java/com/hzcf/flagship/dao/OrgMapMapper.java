package com.hzcf.flagship.dao;

import com.hzcf.flagship.model.OrgMap;
import com.hzcf.flagship.model.OrgMapExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
/**
 * 
 * 类名：OrgMapMapper.java</dt> 
 * 功能描述：理财机构名称对应表
 * 创建时间： 2017年5月10日 上午11:52:02
 * </dd> 创建人：TieGuoWei</dd>
 */
public interface OrgMapMapper {
    int countByExample(OrgMapExample example);

    int deleteByExample(OrgMapExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrgMap record);

    int insertSelective(OrgMap record);

    List<OrgMap> selectByExample(OrgMapExample example);

    OrgMap selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrgMap record, @Param("example") OrgMapExample example);

    int updateByExample(@Param("record") OrgMap record, @Param("example") OrgMapExample example);

    int updateByPrimaryKeySelective(OrgMap record);

    int updateByPrimaryKey(OrgMap record);
    /**
	 * 理财机构名称对应表上传 数据导入
	 */
	void insertOrgMap(List<OrgMap> resultList);
	/**
	 * 理财机构名称对应表分页查询
	 */
	List<Map<String, Object>> findAllRetMapByPage(Map<String, Object> paramsCondition);
	/**
	 * 理财机构名称对应表查询总条数
	 */
	Long findAllByPageCount(Map<String, Object> paramsCondition);
}