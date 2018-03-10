package com.hzcf.flagship.service;

import com.hzcf.flagship.form.PerformanceParm;
import com.hzcf.flagship.vo.ReturnMsgData;

import java.util.Map;

/**
 *
 * 类名：ApiPerformanceService.java</dt>
 * 功能描述：APP接口 业绩 interface
 * 创建时间： 2017年5月16日 下午3:12:02</dd>
 * 创建人：zhangmx</dd>
 */
public interface ApiPerformanceService {

    /**
     * 理财 日业绩页面结果数据
     * @param performanceParm
     * @return
     */
    public ReturnMsgData findPerformanceByDate(PerformanceParm performanceParm);

    /**
     * 理财 月业绩
     * @param performanceParm
     * @return
     */
    public ReturnMsgData findPerformanceByMonths(PerformanceParm performanceParm);

    /**
     * 理财H5接口 各机构当月累计业绩
     * @param parm
     * @return
     */
    public ReturnMsgData findPerformanceOrgMapByDays(Map<String, Object> parm);

    /**
     * 理财 日人员人效
     * @param performanceParm
     * @return
     */
    public ReturnMsgData findEfficiencyForDays(PerformanceParm performanceParm);
    /**
     * 查询理财区域页数据
     * @param token 
     * @param performanceParm
     * @return
     */
	public ReturnMsgData financeForMonthData(String days, String token);
	/**
	 * 查询业务监控室首页数据
	 * @param days
	 * @return
	 */
	public ReturnMsgData findIndexPageInfo(String days);

    /**
     * 理财 月人员人效
     * @param performanceParm
     * @return
     */
    public ReturnMsgData findEfficiencyForMonths(PerformanceParm performanceParm);

    /**
     * 理财H5接口 查询所有区
     * @return
     */
    public ReturnMsgData findAllAreaName(PerformanceParm performanceParm);

    /**
     * 理财 各机构当月累计达成率
     * @param performanceParm
     * @return
     */
    public ReturnMsgData findAreaPerformanceByOrgName(PerformanceParm performanceParm);

    /**
     * 理财H5接口 各机构当月累计达成率
     * @param parmMap
     * @return
     */
    public ReturnMsgData findAreaPerformanceByOrgName(Map<String, Object> parmMap);

    /**
     * 理财H5接口 查询所有区域下的机构
     * param parm
     * @return
     */
    public ReturnMsgData findOrgNameByArea(Map<String, Object> parmMap);

    /**
     * 模糊查询机构名称
     * @param parmMap
     * @return
     */
    public ReturnMsgData findLikeOrgName(Map<String ,Object> parmMap);
    
    /**
     * 查询首页融资的数据
     * @param days
     * @return
     */
    public Map<String, Object> getFinancePerformanceData(String days);

    /**
     * 理财H5接口 查询全部区域和机构
     * @return
     */
    public ReturnMsgData findAllAreaOrgName(PerformanceParm performanceParm);

}
