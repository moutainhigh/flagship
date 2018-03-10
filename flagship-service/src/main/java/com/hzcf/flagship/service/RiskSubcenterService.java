package com.hzcf.flagship.service;

import com.hzcf.flagship.model.AppPageModel;
import com.hzcf.flagship.model.RiskSubcenter;
import com.hzcf.flagship.model.RiskSubcenterExample;
import com.hzcf.flagship.util.PageModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 *<dl>
 *<dt>类名：RiskSubcenterService.java</dt>
 *<dd>描述: 分中心</dd>
 *<dd>创建时间： 2017年11月27日 下午15:02:00</dd>
 *<dd>创建人： liuxianfa</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2017年11月27日 下午15:02:00    liuxianfa       1.0        1.0 Version
 * </pre>
 *</dl>
 */
public interface RiskSubcenterService {

    int countByExample(RiskSubcenterExample example);

    int deleteByExample(RiskSubcenterExample example);

    /**
     * 通过主键(id)删除数据
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    int insert(RiskSubcenter record);

    int insertSelective(RiskSubcenter record, Integer employeeId);

    List<RiskSubcenter> selectByExample(RiskSubcenterExample example);

    RiskSubcenter selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RiskSubcenter record, @Param("example") RiskSubcenterExample example);

    int updateByExample(@Param("record") RiskSubcenter record, @Param("example") RiskSubcenterExample example);

    int updateByPrimaryKeySelective(RiskSubcenter record);

    int updateByPrimaryKey(RiskSubcenter record);

    PageModel subcenterList(int pageNo, int pageSize);
    
    /**
     * 查询最新的分中心数据
     * @Name:selectForDistinct
     * @Author:YuanGuoLei
     * @CreateTime:2017年11月27日下午4:30:19
     * @return
     */
    List<RiskSubcenter> selectForDistinct();

    /**
     * 通过SubcenterNo修改数据
     * @param riskSubcenter
     * @param employeeId
     * @return
     */
    int updateBySubcenterNoSelective(RiskSubcenter riskSubcenter, Integer employeeId);

    /**
     * 根据subcenterNo查询此分中心管理的营业部名称
     * @param subcenterNo
     * @return
     */
    public String selectManagementOrgDataBySubcenterNo(String subcenterNo);

    /**
     * 删除分中心
     * @param subcenterNo 分中心编号
     * @param name 分中心名称
     * @param creator 创建人id/当前登录用户id
     * @return
     */
    int delete(String subcenterNo, String name, Integer creator);
    
    /**
     * 根据page查询模块
     * @param zwPpage
     * @return
     */
	List<AppPageModel> findModelByPage(String page);
	 /**
     * 修改page对应模块的状态和顺序
	 * @param id 
     * @param zwPpage
     * @return
     */
	void updatePage(String data, Integer id);
}
