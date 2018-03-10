package com.hzcf.flagship.service;

import java.util.List;

import com.hzcf.flagship.model.MoneymgrOrgData;

public interface TotalMonthPlanService {

	void insertTotalMonthPlan(List<MoneymgrOrgData> resultList, Integer employeeId);

}
