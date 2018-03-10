

package com.hzcf.flagship.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.flagship.dao.RiskManualAdjustmentMapper;
import com.hzcf.flagship.model.RiskManualAdjustment;
import com.hzcf.flagship.service.ManualAdjustmentService;
import com.hzcf.flagship.util.ImportUtil;
import com.hzcf.flagship.util.PageModel;
import com.hzcf.flagship.util.StringUtil;


@Service
public class ManualAdjustmentServiceImpl implements ManualAdjustmentService{

	@Autowired
	private   RiskManualAdjustmentMapper riskManualAdjustmentMapper;

	@Override
	public int insertManualAdjustment(List<List<Object>> list, Integer id) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<RiskManualAdjustment> resultList = new ArrayList<RiskManualAdjustment>();
		for (int i = 1; i < list.size(); i++) {
			try {
				RiskManualAdjustment manual = new RiskManualAdjustment();
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(0)))){
					manual.setContractNo(String.valueOf(list.get(i).get(0)));
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(1)))){
					manual.setBatch(String.valueOf(list.get(i).get(1)));
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(2)))){
					manual.setAdjustment((Integer.valueOf(String.valueOf(list.get(i).get(2)))));
				}
				//得到当前登录用户id
				manual.setCreator(id);//创建人id
				manual.setCreateTime(new Date());
				resultList.add(manual);
			} catch (Exception e) {
				e.printStackTrace();
				if(0 != i ){
					return i+1;
				}
			}
		}
		if( 0 != resultList.size()){
			List<List<?>> splitList = ImportUtil.splitList(resultList,100);
			for (List<?> list2 : splitList) {
				riskManualAdjustmentMapper.insertManualAdjustment(list2);
			}
		}
		return 0;
	}

	@Override
	public PageModel findAllByPage(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paramsCondition.get("pageNo"));
		pageModel.setPageSize((Integer) paramsCondition.get("pageSize"));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> data = riskManualAdjustmentMapper.findAllRetMapByPage(paramsCondition);
		Long totalRecords = riskManualAdjustmentMapper.findAllByPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}

	
		
}
