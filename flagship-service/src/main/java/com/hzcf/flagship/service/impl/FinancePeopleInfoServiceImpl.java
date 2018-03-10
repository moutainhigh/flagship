

package com.hzcf.flagship.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.hzcf.flagship.dao.FinancePersonalInfoMapper;
import com.hzcf.flagship.model.FinancePersonalInfo;
import com.hzcf.flagship.service.FinancePeopleInfoService;
import com.hzcf.flagship.util.ImportUtil;
import com.hzcf.flagship.util.PageModel;
import com.hzcf.flagship.util.RegExpUtil;
import com.hzcf.flagship.util.StringUtil;

/**
 * 
 * 类名：FinancePeopleInfoServiceImpl.java</dt> 
 * 功能描述：融资人员信息表
 * 创建时间： 2017年5月10日 上午11:52:02
 * </dd> 创建人：TieGuoWei</dd>
 */
@Service
public class FinancePeopleInfoServiceImpl implements FinancePeopleInfoService{

	@Autowired
	private  FinancePersonalInfoMapper financePersonalInfoMapper;
	
	private final Log logger = LogFactory.getLog(getClass());
	/**
	 * 
	 * Description: 融资风险表分页查询列表
	 */
	@Override
	public PageModel findAllByPage(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paramsCondition.get("pageNo"));
		pageModel.setPageSize((Integer) paramsCondition.get("pageSize"));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> data = financePersonalInfoMapper.findAllRetMapByPage(paramsCondition);
		Long totalRecords = financePersonalInfoMapper.findAllByPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}


	/**
	 * 融资人员信息表 数据导入
	 * @throws Exception 
	 */
	@Override
	public int insertFinancePeopleInfo(List<List<Object>> list, Integer id) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<FinancePersonalInfo> resultList = new ArrayList<FinancePersonalInfo>();
		for (int i = 1; i < list.size(); i++) {
			try {
				FinancePersonalInfo personalInfo = new FinancePersonalInfo();
				if(String.valueOf(list.get(i).get(0)).length() <= 20
						&& !StringUtil.isEmpty(String.valueOf(list.get(i).get(0)))){
					personalInfo.setManageOffice(String.valueOf(list.get(i).get(0)));//管办
				}else{
					throw new Exception();
				}
				
				if(!"年计划".equals(String.valueOf(list.get(i).get(0)))){

					if(String.valueOf(list.get(i).get(1)).length() <= 50
							&& !StringUtil.isEmpty(String.valueOf(list.get(i).get(1)))){
						personalInfo.setOrgName(String.valueOf(list.get(i).get(1)));//营业部名称
					}else{
						throw new Exception();
					}
					
					
					if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(2)))
							&& RegExpUtil.checkIsInt(String.valueOf(list.get(i).get(2)))){
						personalInfo.setSalesNum(Integer.valueOf(String.valueOf(list.get(i).get(2))));//销售人数	
					}else{
						throw new Exception();
					}
					
					
					if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(3)))
							&& RegExpUtil.checkIsInt(String.valueOf(list.get(i).get(3)))){
						personalInfo.setTotalEmpNum(Integer.valueOf(String.valueOf(list.get(i).get(3))));//总人数
					}else{
						throw new Exception();
					}
					
					if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(4)))
							&& RegExpUtil.checkIsInt(String.valueOf(list.get(i).get(4)))){
						personalInfo.setTeamNum((Integer.valueOf(String.valueOf(list.get(i).get(4)))));//团队个数
					}else{
						throw new Exception();
					}
				}
				Date date =null;
				if(!StringUtils.isEmpty((String) list.get(i).get(5))
						&& RegExpUtil.checkIsDate((String) list.get(i).get(5))){
					date = sdf.parse(String.valueOf(list.get(i).get(5)));
					personalInfo.setRecordDate(date);//记录时间
				}else{
					throw new Exception();
				}
				
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(6)))
						&& RegExpUtil.checkIsInt(String.valueOf(list.get(i).get(6)))){
					personalInfo.setMonthPlan(Integer.valueOf(String.valueOf(list.get(i).get(6))));//月计划目标
				}else{
					throw new Exception();
				}
				
				personalInfo.setCreator(id);//创建人id
				personalInfo.setCreateTime(new Date());//创建时间
				resultList.add(personalInfo);
			} catch (Exception e) {
				e.printStackTrace();
				if(0 != i ){
					return i+1;
				}
			}
		}
		if( 0 != resultList.size()){
			financePersonalInfoMapper.deleteByExample(null);
			List<List<?>> splitList = ImportUtil.splitList(resultList,100);
			for (List<?> list2 : splitList) {
				financePersonalInfoMapper.insertFinancePeopleInfo(list2);
		 }
		}
		return 0;
	}
		
}
