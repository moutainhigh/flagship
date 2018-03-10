package com.hzcf.flagship.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.flagship.dao.MoneymgrOrgDataMapper;
import com.hzcf.flagship.model.MoneymgrOrgData;
import com.hzcf.flagship.service.MoneyCompareService;
import com.hzcf.flagship.util.DateUtil;
import com.hzcf.flagship.util.PageModel;
/**
 * 
 * 类名：MoneyCompareServiceImpl.java</dt> 
 * 功能描述：理财对比表
 * 创建时间： 2017年5月10日 上午11:52:02
 * </dd> 创建人：TieGuoWei</dd>
 */

@Service
public class MoneyCompareServiceImpl implements MoneyCompareService {

	@Autowired
	private MoneymgrOrgDataMapper moneymgrOrgDataMapper;
	
	/**
	 * 理财对比表分页查询
	 * 
	 */
	@Override
	public PageModel findAllByPage(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paramsCondition.get("pageNo"));
		pageModel.setPageSize((Integer) paramsCondition.get("pageSize"));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> data = moneymgrOrgDataMapper.findAllRetMapByPage(paramsCondition);
		Long totalRecords = moneymgrOrgDataMapper.findAllByPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}

	/**
	 * 理财对比表数据批量插入
	 * 
	 */
	@Override
	public void insertMoneyCompare(List<MoneymgrOrgData> resultList) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 得到理财对比表中第一个对象 的月份
		if (0 != resultList.size()) {
			MoneymgrOrgData moneymgrOrgData = resultList.get(0);
			if (null != moneymgrOrgData) {
				Map<String, Object> moneymgrOrgDataMap = new HashMap<String, Object>();
				String str = sdf.format(moneymgrOrgData.getRecordDate());
				Map<String, Object> map = DateUtil.getMonthFirstDayAndLastDay(str);
				moneymgrOrgDataMap.put("firstDay", map.get("firstDay"));
				moneymgrOrgDataMap.put("lastDay", map.get("lastDay"));
				moneymgrOrgDataMapper.deleteOrgDataByFirstDayAndLastDay(moneymgrOrgDataMap);
				moneymgrOrgDataMapper.insertMoneyCompare(resultList);
			}
		}
	}

}
