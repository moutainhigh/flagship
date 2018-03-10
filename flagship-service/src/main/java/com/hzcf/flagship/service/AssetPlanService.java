package com.hzcf.flagship.service;

import com.hzcf.flagship.model.AssetPlan;
import com.hzcf.flagship.util.PageModel;

import java.util.List;

/**
 * <dl>
 * <dt>类名：AssetPlanService.java</dt>
 * <dd>描述: 战略运营部 业绩目标</dd>
 * <dd>创建时间： 2017年11月14日 下午2:50:00</dd>
 * <dd>创建人：liuxianfa</dd>
 * <dt>版本历史: </dt>
 * <pre>
 * Date Author Version Description
 * ------------------------------------------------------------------
 * 2017年11月14日 下午2:50:00 liuxianfa 1.0 1.0 Version
 * </pre>
 * </dl>
 */
public interface AssetPlanService {

    /**
     * @param id 主键id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @param assetPlan 实体类 
     * @return
     */
    int insert(AssetPlan assetPlan);

    /**
     * @param assetPlan 实体类
     * @return
     */
    int insertSelective(AssetPlan assetPlan);

    /**
     * @param id
     * @return
     */
    AssetPlan selectByPrimaryKey(Long id);

    /**
     * @param assetPlan 实体类
     * @return
     */
    int updateByPrimaryKeySelective(AssetPlan assetPlan);

    /**
     * @param assetPlan 实体类
     * @return
     */
    int updateByPrimaryKey(AssetPlan assetPlan);

    /**
     * 根据参数查询业绩目标列表
     * @param pageNo
     * @param pageSize
     * @param orgNo
     * @param businessNo
     * @param year
     * @see AssetPlan
     * @return
     */
    PageModel queryAssetPlanList(int pageNo,int pageSize, String orgNo, String businessNo, String year);

    /**
     * 查询业务表f_asset_business
     * @return
     */
    List<?> selectAssetBusiness();

    /**
     * 获取有效的年份
     * @return
     */
    List<?> selectUsefulYear();


    /**
     * 批量导入业绩目标数据
     * @param list
     * @param creator 创建人id/当前登录人员id
     * @return
     */
    int insertAssetPlanBatch(List<List<Object>> list, Integer creator);
}
