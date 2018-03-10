package com.hzcf.flagship.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.flagship.dao.AppHelpContentMapper;
import com.hzcf.flagship.service.AppHelpService;
import com.hzcf.flagship.util.PropertyUtil;
import com.hzcf.flagship.vo.ReturnMsgData;
/**
 * 
 *<dl>
 *<dt>类名：AppHelpServiceImpl.java</dt>
 *<dd>描述: ~app帮助与反馈的实现类</dd> 
 *<dd>创建时间： 2017年8月1日 下午5:31:06</dd>
 *<dd>创建人： GuoDong</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年8月1日 下午5:31:06    GuoDong       1.0        1.0 Version 
 * </pre>
 *</dl>
 */

@Service
public class AppHelpServiceImpl implements AppHelpService {
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private AppHelpContentMapper appHelpContentMapper; 
	/**
	 * 根据问题种类查找问答列表
	 */
	@Override
	public ReturnMsgData findAnswerListBySortName(String sortName) {
		Map<String, Object> data = new HashMap<String, Object>();
		List<Map<String, Object>>  appHelpContents = appHelpContentMapper.selectBySortName(sortName);
		if(null != appHelpContents && appHelpContents.size()>0){
			for (Map<String, Object> map : appHelpContents) {
				map.put("url", PropertyUtil.getInfo("helpUrl")+map.get("id"));
				map.remove("id");
			}
		}else{
			logger.info("sortName为：" + sortName + " 的没有数据");
		}
		data.put("appHelpContents", appHelpContents);
		return new ReturnMsgData("0000", "调用成功", data);
	}
	/**
	 * 根据id查找问答
	 */
	@Override
	public Map<String, Object> helpInfo(String id) {
		appHelpContentMapper.addTimes(id);
		return appHelpContentMapper.selectById(id);
	}

}
