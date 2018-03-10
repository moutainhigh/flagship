package com.hzcf.flagship.service;

import java.util.List;
import java.util.Map;

import com.hzcf.flagship.model.RiskProduct;
import com.hzcf.flagship.model.RiskProductWarning;
import com.hzcf.flagship.model.RiskSeparate;
/**
 * 
 *<dl>
 *<dt>类名：RiskParamsService.java</dt>
 *<dd>描述: ~风控参数的service</dd> 
 *<dd>创建时间： 2017年10月17日 上午11:09:50</dd>
 *<dd>创建人： GuoDong</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年10月17日 上午11:09:50    GuoDong       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
public interface RiskParamsService {

	/**
	 * 查找最新的贷后分割点
	 * @return
	 */
	RiskSeparate getSeparate();

	/**
	 * 新增贷后分割点
	 * @param separate
	 * @param employeeId
	 */
	void insertSeparate(String separate, Integer employeeId);
	/**
	 * 产品预警列表
	 * @return
	 */
	List<Map<String, Object>> getPorductWarList();
	/**
	 * 新增产品预警
	 * @param riskProductWarning
	 */
	void insertWaringVal(RiskProductWarning riskProductWarning);
	/**
	 * 根据编号查找产品
	 * @param product
	 * @return
	 */
	RiskProduct findProductNumByProduct(String product);


}
