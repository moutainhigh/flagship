package com.hzcf.flagship.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.flagship.dao.MoneymgrTotalMonthPlanMapper;
import com.hzcf.flagship.model.MoneymgrOrgData;
import com.hzcf.flagship.model.MoneymgrTotalMonthPlan;
import com.hzcf.flagship.service.TotalMonthPlanService;
import com.hzcf.flagship.util.DateUtil;
@Service
public class TotalMonthPlanServiceImpl implements TotalMonthPlanService {

	@Autowired
	private MoneymgrTotalMonthPlanMapper moneymgrTotalMonthPlanMapper;
	
    private  final Log logger = LogFactory.getLog(getClass());

	@Override
	public void insertTotalMonthPlan(List<MoneymgrOrgData> resultList,Integer employeeId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//得到理财对比表最后一行的值  更新 理财月总计划表
		Map<String,Object> monthPlanMap = new HashMap<String,Object>();
		if( 0 != resultList.size()){
			MoneymgrOrgData LastmoneymgrOrgData = resultList.get(resultList.size()-1);//集合取对象从0开始
			if(null != LastmoneymgrOrgData){
				String str = sdf.format(LastmoneymgrOrgData.getRecordDate());
			    Map<String, Object> map = DateUtil.getMonthFirstDayAndLastDay(str);
			    monthPlanMap.put("firstDay", map.get("firstDay"));
			    monthPlanMap.put("lastDay", map.get("lastDay"));
			    moneymgrTotalMonthPlanMapper.deleteMonthPlanByFirstDayAndLastDay(monthPlanMap);
			    MoneymgrTotalMonthPlan record = new MoneymgrTotalMonthPlan();
			    record.setValue(LastmoneymgrOrgData.getMonthPlan());//总计划
				try {
					record.setRecordDate(sdf.parse((String)map.get("firstDay")));
				} catch (ParseException e) {
				logger.error(e.getMessage()); 
				}//月总计划表 每次插入 取月份第一天
				record.setUpdatorId(employeeId);
				record.setUpdateTime(new Date());
				moneymgrTotalMonthPlanMapper.insert(record);
		}
	 }
	}
}
