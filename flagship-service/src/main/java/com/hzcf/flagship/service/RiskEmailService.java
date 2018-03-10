package com.hzcf.flagship.service;

import java.util.List;
import java.util.Map;

import com.hzcf.flagship.model.RiskEmail;
import com.hzcf.flagship.model.RiskOrgData;
import com.hzcf.flagship.util.PageModel;


/**
*<dl>
*<dt>类名：RiskEmailService.java</dt>
*<dd>描述: 邮件发送管理</dd>
*<dd>创建时间： 2017年10月16日 下午3:48:54</dd>
*<dd>创建人：Tieguowei</dd>
*<dt>版本历史: </dt>
* <pre>
* Date Author Version Description
* ------------------------------------------------------------------
* 2017年10月16日 下午3:48:54 Tieguowei 1.0 1.0 Version
* </pre>
*</dl>
*/
public interface RiskEmailService {

	PageModel findEmailList(Map<String, Object> paramsCondition);

	List<Map<String, Object>> findOrgStructTree();

	PageModel childListByOrgNo(Map<String, Object> paramsCondition);

	void updateOrgDataByOrgNo(RiskOrgData orgData);

	PageModel findPrincipalName(String name);

	void insertRecord(RiskEmail riskEmail);

	PageModel findEmailAddressByList(List<RiskOrgData> list);

	PageModel findMQExceptionList(Map<String, Object> paramsCondition);

}
