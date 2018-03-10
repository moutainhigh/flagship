package com.hzcf.flagship.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.flagship.dao.RiskProductMapper;
import com.hzcf.flagship.dao.RiskProductWarningMapper;
import com.hzcf.flagship.dao.RiskSeparateMapper;
import com.hzcf.flagship.model.RiskProduct;
import com.hzcf.flagship.model.RiskProductWarning;
import com.hzcf.flagship.model.RiskSeparate;
import com.hzcf.flagship.service.RiskParamsService;
/**
 * 
 *<dl>
 *<dt>类名：RiskParamsServiceImpl.java</dt>
 *<dd>描述: ~风控参数service的实现类</dd> 
 *<dd>创建时间： 2017年10月17日 上午11:10:23</dd>
 *<dd>创建人： GuoDong</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年10月17日 上午11:10:23    GuoDong       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Service
public class RiskParamsServiceImpl implements RiskParamsService {
	
	@Autowired
	private RiskSeparateMapper riskSeparateMapper;
	@Autowired
	private RiskProductWarningMapper riskProductWarningMapper;
	@Autowired
	private RiskProductMapper riskProductMapper;
	
	/**
	 * 查找最新的贷后分割点
	 */
	@Override
	public RiskSeparate getSeparate() {
		RiskSeparate riskSeparate = riskSeparateMapper.findSeparate();
		return riskSeparate;
	}

	/**
	 * 新增贷后分割点
	 */
	@Override
	public void insertSeparate(String separate,Integer employeeId) {
		RiskSeparate riskSeparate = new RiskSeparate();
		riskSeparate.setCreator(employeeId);
		riskSeparate.setCreateTime(new Date());
		riskSeparate.setValue(Integer.valueOf(separate));
		riskSeparateMapper.insertSelective(riskSeparate);
	}
	
	/**
	 * 产品预警列表
	 */
	@Override
	public List<Map<String, Object>> getPorductWarList() {
		List<Map<String, Object>> porductWarList = riskProductWarningMapper.selectPorductWarList();
		return porductWarList;
	}
	/**
	 * 新增产品预警
	 */
	@Override
	public void insertWaringVal(RiskProductWarning riskProductWarning) {
		riskProductWarningMapper.insertSelective(riskProductWarning);
	}
	/**
	 * 根据编号查找产品
	 */
	@Override
	public RiskProduct findProductNumByProduct(String product) {
		return riskProductMapper.findProductNumByProduct(product);
	}


}
