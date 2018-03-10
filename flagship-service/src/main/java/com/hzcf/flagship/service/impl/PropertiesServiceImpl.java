

package com.hzcf.flagship.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.flagship.dao.PropertiesMapper;
import com.hzcf.flagship.model.Properties;
import com.hzcf.flagship.service.PropertiesService;
import com.hzcf.flagship.util.PageModel;


/**
 *<dl>
 *<dt>类名：PropertiesServiceImpl.java</dt>
 *<dd>描述: 系统属性配置</dd> 
 *<dd>创建时间： 2017年7月4日 下午3:44:23</dd>
 *<dd>创建人： TieGuowei </dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年7月4日 下午3:44:23    TieGuowei       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Service
public class PropertiesServiceImpl implements PropertiesService{

	@Autowired
	private  PropertiesMapper propertiesMapper;
	
	private final Log logger = LogFactory.getLog(getClass());
	/**
	 * 
	 * Description: 融资营业部对应表分页查询列表
	 */
	@Override
	public PageModel findAllByPage(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paramsCondition.get("pageNo"));
		pageModel.setPageSize((Integer) paramsCondition.get("pageSize"));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> data = propertiesMapper.findAllRetMapByPage(paramsCondition);
		Long totalRecords = propertiesMapper.findAllByPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}
	@Override
	public Properties selectByPrimaryKey(Integer id) {
		
		return propertiesMapper.selectByPrimaryKey(id);
	}
	@Override
	public void doUpdateProperty(Properties properties, Integer id) {
		Map<String,Object> map = new HashMap<String,Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		map.put("id", properties.getId());
		map.put("propertyStringValue", properties.getPropertyStringValue());
		map.put("propertyFigureValue", properties.getPropertyFigureValue());
		map.put("updateUserId", id);
		map.put("updateTime", sdf.format(new Date()));
		propertiesMapper.doUpdateProperty(map);
	}

}
