

package com.hzcf.flagship.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.flagship.dao.RiskOldLoanMapper;
import com.hzcf.flagship.model.RiskOldLoan;
import com.hzcf.flagship.service.RiskOldLoanService;
import com.hzcf.flagship.util.ImportUtil;
import com.hzcf.flagship.util.PageModel;
import com.hzcf.flagship.util.StringUtil;


@Service
public class RiskOldLoanServiceImpl implements RiskOldLoanService{

	@Autowired
	private   RiskOldLoanMapper riskOldLoanMapper;

	@Override
	public int insertOldLoan(List<List<Object>> list, Integer id) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		List<RiskOldLoan> resultList = new ArrayList<RiskOldLoan>();
		for (int i = 1; i < list.size(); i++) {
			try {
				RiskOldLoan oldLoan = new RiskOldLoan();
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(0)))){
					oldLoan.setApplyNo(String.valueOf(list.get(i).get(0)));//申请编号
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(1)))){
					oldLoan.setClientName(String.valueOf(list.get(i).get(1)));
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(2)))){
					oldLoan.setContractNo(String.valueOf(list.get(i).get(2)));
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(3)))){
					oldLoan.setProductName(String.valueOf(list.get(i).get(3)));
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(4)))){
					oldLoan.setProductNo(String.valueOf(list.get(i).get(4)));
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(5)))){
					oldLoan.setPurpose(String.valueOf(list.get(i).get(5)));
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(6)))){
					oldLoan.setInitialRepaymentTime(sdf.parse(String.valueOf(list.get(i).get(6))));
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(7)))){
					oldLoan.setPeriods(Integer.valueOf(String.valueOf(list.get(i).get(7))));
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(8)))){
					oldLoan.setContractAmount(new BigDecimal(String.valueOf(list.get(i).get(8))));
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(9)))){
					oldLoan.setLoanAmount(new BigDecimal(String.valueOf(list.get(i).get(9))));
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(10)))){
					oldLoan.setMonthRepayment(new BigDecimal(String.valueOf(list.get(i).get(10))));
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(11)))){
					oldLoan.setPayDate(sdf.parse(String.valueOf(list.get(i).get(11))));
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(12)))){
					oldLoan.setOldOrgName(String.valueOf(list.get(i).get(12)));
				}
				//新营业部编码

				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(13)))){
					oldLoan.setOrgNo((String.valueOf(list.get(i).get(13))));
				}
				
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(14)))){
					oldLoan.setCounselor(String.valueOf(list.get(i).get(14)));
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(15)))){
					oldLoan.setSalesman(String.valueOf(list.get(i).get(15)));
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(16)))){
					oldLoan.setContractEndDate(sdf.parse(String.valueOf(list.get(i).get(16))));
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(17)))){
					oldLoan.setDistrict((String.valueOf(list.get(i).get(17))));
				}
				//得到当前登录用户id
				oldLoan.setCreator(id);//创建人id
				oldLoan.setCreateTime(new Date());
				resultList.add(oldLoan);
			} catch (Exception e) {
				e.printStackTrace();
				if(0 != i ){
					return i+1;
				}
			}
		}
		if( 0 != resultList.size()){
			//全量删除
			riskOldLoanMapper.deleteByExample(null);
			List<List<?>> splitList = ImportUtil.splitList(resultList,100);
			for (List<?> list2 : splitList) {
				riskOldLoanMapper.insertOldLoan(list2);
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
		List<Map<String, Object>> data = riskOldLoanMapper.findAllRetMapByPage(paramsCondition);
		Long totalRecords = riskOldLoanMapper.findAllByPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}
	
		
}
