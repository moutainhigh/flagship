package com.hzcf.flagship.service;

import java.util.Map;

import com.hzcf.flagship.vo.ReturnMsgData;
/**
 * 
 *<dl>
 *<dt>类名：AppHelpService.java</dt>
 *<dd>描述: ~app帮助与反馈service</dd> 
 *<dd>创建时间： 2017年8月1日 下午5:30:22</dd>
 *<dd>创建人： GuoDong</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年8月1日 下午5:30:22    GuoDong       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
public interface AppHelpService {
	/**
	 * 根据问题种类查找问答列表
	 * @param sortId
	 * @return
	 */
	ReturnMsgData findAnswerListBySortName(String sortName);
	/**
	 * 根据id查找问题
	 * @param parameter
	 * @return
	 */
	Map<String, Object> helpInfo(String id);

}
