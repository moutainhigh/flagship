package com.hzcf.flagship.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.flagship.dao.CheckLogMapper;
import com.hzcf.flagship.service.CheckDataService;
import com.hzcf.flagship.util.ExportExcel;
import com.hzcf.flagship.util.PageModel;

/**
 * 
 *<dl>
 *<dt>类名：CheckDataServiceImpl.java</dt>
 *<dd>描述: ~数据校验列表的实现类</dd> 
 *<dd>创建时间： 2017年9月15日 上午10:26:39</dd>
 *<dd>创建人： GuoDong</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年9月15日 上午10:26:39    GuoDong       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Service("checkDataService")
public class CheckDataServiceImpl implements CheckDataService {
	
	@Autowired
	private CheckLogMapper checkLogMapper;
	
	/**
	 * 分页查询
	 */
	@Override
	public PageModel findAllByPage(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paramsCondition.get("pageNo"));
		pageModel.setPageSize((Integer) paramsCondition.get("pageSize"));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> data = checkLogMapper.findAllRetMapByPage(paramsCondition);
		Long totalRecords = checkLogMapper.findAllByPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}
	
	/**
	 * 查询接口检测类型
	 */
	@Override
	public List<?> getTypes() {
		return checkLogMapper.findAllType();
	}
	
	/**
	 * 查询接口检测结果类型
	 */
	@Override
	public List<?> getResult() {
		return checkLogMapper.findAllResult();
	}
	
	/**
	 * 查询接口检测结果类型
	 */
	@Override
	public List<?> getSort(Map<String, Object> paramsCondition) {
		return checkLogMapper.findAllSort(paramsCondition);
	}

	/**
	 * 导出Excel
	 */
	@Override
	public void exportExcel(Map<String, Object> paramsCondition, HttpServletResponse response) {
		List<Object[]>  dataList = new ArrayList<Object[]>();
		List<Map<String, Object>> result = checkLogMapper.findAllRetMapByPage(paramsCondition);
		String title = "接口信息表";
		String[] rowsName = new String[]{"序号","日期","访问类型","次数","接口名","详情","状态"};
		Object[] objs = null;
		Map<String, Object> map = null;
		  for (int i = 0; i < result.size(); i++) {
			  	map = result.get(i);
			  	objs = new Object[rowsName.length];
				objs[0] = i+1;
				objs[1] = map.get("date");
				objs[2] = map.get("type");
				objs[3] = map.get("sort");
				objs[4] = map.get("name");
				objs[6] = map.get("result");
				objs[5] = map.get("detail");
				dataList.add(objs);
		  }
		ExportExcel ex = new ExportExcel(title, rowsName, dataList);
		ex.export(response);
	}
}
