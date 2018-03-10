package com.hzcf.flagship.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.flagship.dao.PropertiesMapper;
import com.hzcf.flagship.model.Properties;
import com.hzcf.flagship.model.PropertiesExample;
import com.hzcf.flagship.model.PropertiesExample.Criteria;
import com.hzcf.flagship.service.SystemService;

@Service("systemService")
public class SystemServiceImpl implements SystemService {
	@Autowired
	PropertiesMapper propertiesMapper;
	
	
	/**
	 * 属性配置表中根据属性名称查找String类型的属性值
	 */
	@Override
	public String getPropertyStringValueByName(String propertyName) {
		PropertiesExample propertiesExample = new PropertiesExample();
		Criteria propertiesCriteria = propertiesExample.createCriteria();
		propertiesCriteria.andPropertyNameEqualTo(propertyName);
		List<Properties> list = propertiesMapper.selectByExample(propertiesExample);
		if (list!=null && list.size()!=0) {
			return list.get(0).getPropertyStringValue();
		}
		return null;
	}
	
}
