package com.hzcf.flagship.service;

import com.hzcf.flagship.model.AssetPerson;
import com.hzcf.flagship.model.AssetPersonExample;
import com.hzcf.flagship.util.PageModel;

import java.util.List;

/**
 * <dl>
 * <dt>类名：AssetPersonService.java</dt>
 * <dd>描述: 战略运营部需求-花名册导入</dd>
 * <dd>创建时间： 2017年11月16日 上午9:25:00</dd>
 * <dd>创建人：liuxianfa</dd>
 * <dt>版本历史: </dt>
 * <pre>
 * Date Author Version Description
 * ------------------------------------------------------------------
 * 2017年11月16日 上午9:25:00 liuxianfa 1.0 1.0 Version
 * </pre>
 * </dl>
 * @author liuxianfa
 */
public interface AssetPersonService {

    int countByExample(AssetPersonExample example);

    int deleteByExample(AssetPersonExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AssetPerson record);

    int insertSelective(AssetPerson record);

    List<AssetPerson> selectByExample(AssetPersonExample example);

    AssetPerson selectByPrimaryKey(Long id);

    int updateByExampleSelective(AssetPerson record, AssetPersonExample example);

    int updateByExample(AssetPerson record, AssetPersonExample example);

    int updateByPrimaryKeySelective(AssetPerson record);

    int updateByPrimaryKey(AssetPerson record);

    /**
     * 根据参数查询花名册列表
     * @param page 页码
     * @param rows 每页显示条数
     * @param depNo 部门编号
     * @param name 姓名
     * @param mobile 手机号
     * @see AssetPerson
     * @return
     */
    PageModel queryAssetPersonList(int page, int rows, String depNo, String name, String mobile);

    /**
     * 批量导入花名册数据
     * @param list
     * @param creator 创建人id/当前登录人员id
     * @return
     */
    int insertassetPersonBatch(List<List<Object>> list, Integer creator);
}
