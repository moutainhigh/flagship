package com.hzcf.flagship.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.flagship.dao.OrgMapLogMapper;
import com.hzcf.flagship.service.OrgMapLogService;
import com.hzcf.flagship.util.PageModel;
/**
 * 
 * 类名：OrgMapLogServiceImpl.java</dt> 
 * 功能描述：理财机构日志信息表
 * 创建时间： 2017年7月3日 上午11:52:02
 * </dd> 创建人：TieGuoWei</dd>
 */

@Service
public class OrgMapLogServiceImpl implements OrgMapLogService {
	
	@Autowired
	private OrgMapLogMapper orgMapLogMapper;

	@Override
	public PageModel findAllByPage(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paramsCondition.get("pageNo"));
		pageModel.setPageSize((Integer) paramsCondition.get("pageSize"));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		paramsCondition.put("type", paramsCondition.get("type"));
		List<Map<String, Object>> data = orgMapLogMapper.findAllRetMapByPage(paramsCondition);
		Long totalRecords = orgMapLogMapper.findAllByPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}


}
