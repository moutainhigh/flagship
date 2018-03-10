

package com.hzcf.flagship.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.flagship.dao.RiskOldOverdueMapper;
import com.hzcf.flagship.model.RiskOldOverdue;
import com.hzcf.flagship.service.RiskOldOverdueService;
import com.hzcf.flagship.util.ImportUtil;
import com.hzcf.flagship.util.PageModel;
import com.hzcf.flagship.util.StringUtil;

@Service
public class RiskOldOverdueServiceImpl implements RiskOldOverdueService{

	@Autowired
	private RiskOldOverdueMapper riskOldOverdueMapper;

	/**
	 * 老系统逾期表 数据导入
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Override
	public int insertOldOverdue(List<List<Object>> list, Integer id) {
			SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");

			//判断是否有当天数据
			List<Map<String,Object>> result = riskOldOverdueMapper.findOldOverdueByCreateTime(sdf2.format(new Date()));
			if(null != result && list.size()>0){
				riskOldOverdueMapper.deleteOldOverdueByCreateTime(sdf2.format(new Date()));
			}
			List<RiskOldOverdue> resultList = new ArrayList<RiskOldOverdue>();
			for (int i = 1; i < list.size(); i++) {
			  try {
				RiskOldOverdue overdue = new RiskOldOverdue();
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(0)))){
					overdue.setApplyNo(String.valueOf(list.get(i).get(0)));//申请编号
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(1)))){
					overdue.setSaleDepartment(String.valueOf(list.get(i).get(1)));//销售部门
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(2)))){
					overdue.setClientName(String.valueOf(list.get(i).get(2)));//客户名称
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(3)))){
					overdue.setContractNo(String.valueOf(list.get(i).get(3)));//合同编号
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(4)))){
					overdue.setProductName(String.valueOf(list.get(i).get(4)));//产品名称
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(5)))){
					overdue.setProductNo(String.valueOf(list.get(i).get(5)));//产品编号
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(6)))){
					overdue.setCardNo(String.valueOf(list.get(i).get(6)));//卡号
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(7)))){
					overdue.setBank(String.valueOf(list.get(i).get(7)));//银行
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(8)))){
					overdue.setHandAmount(new BigDecimal(String.valueOf(list.get(i).get(8))));//到手额
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(9)))){
					overdue.setPeriods(Integer.valueOf(String.valueOf(list.get(i).get(9))));//期数
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(10)))){
					overdue.setInitialRepaymentTime(sdf.parse(String.valueOf(list.get(i).get(10))));//首次还款日期
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(11)))){
					overdue.setPrincipalAndInterest(new BigDecimal(String.valueOf(list.get(i).get(11))));//还款本息
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(12)))){
					overdue.setPenalty(new BigDecimal(String.valueOf(list.get(i).get(12))));//违约金总额
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(13)))){
					overdue.setPenaltyInterest(new BigDecimal(String.valueOf(list.get(i).get(13))));//罚息总额
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(14)))){
					overdue.setBreachPenalty(new BigDecimal(String.valueOf(list.get(i).get(14))));//罚息违约金总额
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(15)))){
					overdue.setTotalRepaymentAmount(new BigDecimal(String.valueOf(list.get(i).get(15))));//应还款总额
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(16)))){
					overdue.setRepaymentDate(String.valueOf(list.get(i).get(16)));//还款日期
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(17)))){
					overdue.setTeamManager(String.valueOf(list.get(i).get(17)));//团队经理
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(18)))){
					overdue.setClientManager(String.valueOf(list.get(i).get(18)));//客户经理
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(19)))){
					overdue.setOldOrgName(String.valueOf(list.get(i).get(19)));//老系统营业部名称
				}
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(20)))){
					overdue.setOrgNo(String.valueOf(list.get(i).get(20)));//营业部编号
				}
				overdue.setCreator(id);//创建人
				overdue.setCreateTime(new Date());
				resultList.add(overdue);
			  } catch (Exception e) {
					e.printStackTrace();
					if(0 != i ){
						return i+1;
					}
			}
		}
		if(0 != resultList.size()){
			List<List<?>> splitList = ImportUtil.splitList(resultList,100);
			for (List<?> list2 : splitList) {
				riskOldOverdueMapper.insertOldOverdue(list2);
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
		List<Map<String, Object>> data = riskOldOverdueMapper.findAllRetMapByPage(paramsCondition);
		Long totalRecords = riskOldOverdueMapper.findAllByPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}

		
}
