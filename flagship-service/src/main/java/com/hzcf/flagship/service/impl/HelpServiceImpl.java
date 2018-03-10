package com.hzcf.flagship.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.flagship.dao.AppHelpContentMapper;
import com.hzcf.flagship.dao.AppHelpSortMapper;
import com.hzcf.flagship.model.AppHelpContent;
import com.hzcf.flagship.service.HelpService;
import com.hzcf.flagship.util.PageModel;
import com.hzcf.flagship.util.StringUtil;

/**
*
* 类名：HelpService.java</dt>
* 功能描述：app用户帮助 interface
* 创建时间： 2017年8月1日 下午10:51:02</dd>
* 创建人：tieguowei</dd>
*/
@Service
public class HelpServiceImpl implements HelpService {

	private final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private AppHelpSortMapper appHelpSortMapper;
	@Autowired
	private AppHelpContentMapper appHelpContentMapper;
	@Override
	public PageModel findAllByPage(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paramsCondition.get("pageNo"));
		pageModel.setPageSize((Integer) paramsCondition.get("pageSize"));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> data = appHelpContentMapper.findAllRetMapByPage(paramsCondition);
		Long totalRecords = appHelpContentMapper.findAllByPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}

	@Override
	public List<?> getAllSorts() {
		return appHelpSortMapper.selectByExample(null);
	}

	@Override
	public void doAddHelpContent(AppHelpContent appHelpContent) {
		appHelpContentMapper.insert(appHelpContent);
	}

	@Override
	public Map<String, Object> findByContentID(Integer id) {
		return appHelpContentMapper.findByContentID(id);
	}

	@Override
	public void doUpdateHelpContent(AppHelpContent appHelpContent) {
		if(null == appHelpContent.getIsHot()){
			appHelpContent.setIsHot(0);
		}else{
			appHelpContent.setIsHot(1);
		}
		appHelpContentMapper.updateByPrimaryKeySelective(appHelpContent);
	}

	@Override
	public long getCountBySortId(AppHelpContent appHelpContent) {
		return appHelpContentMapper.getCountBySortId(appHelpContent);
	}

	@Override
	public void deleteById(AppHelpContent appHelpContent) {
		Integer id = appHelpContent.getId();
		appHelpContentMapper.deleteByPrimaryKey(id);		
	}

}
