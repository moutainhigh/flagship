

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
import com.hzcf.flagship.dao.FinanceOrgMapMapper;
import com.hzcf.flagship.model.FinanceOrgMap;
import com.hzcf.flagship.service.FinanceOrgMapService;
import com.hzcf.flagship.util.ImportUtil;
import com.hzcf.flagship.util.PageModel;
import com.hzcf.flagship.util.RegExpUtil;
import com.hzcf.flagship.util.StringUtil;

/**
 * 
 * 类名：FinanceOrgMapServiceImpl.java</dt> 
 * 功能描述：融资营业部对应表
 * 创建时间： 2017年5月10日 上午11:52:02
 * </dd> 创建人：TieGuoWei</dd>
 */
@Service
public class FinanceOrgMapServiceImpl implements FinanceOrgMapService{

	@Autowired
	private  FinanceOrgMapMapper financeOrgMapMapper;
	
	private final Log logger = LogFactory.getLog(getClass());
	/**
	 * 
	 * Description: 融资营业部对应表分页查询列表
	 */
	@Override
	public PageModel findAllByPage(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paramsCondition.get("pageNo"));
		pageModel.setPageSize((Integer) paramsCondition.get("pageSize"));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> data = financeOrgMapMapper.findAllRetMapByPage(paramsCondition);
		Long totalRecords = financeOrgMapMapper.findAllByPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}


	/**
	 * 融资营业部对应表 数据导入
	 * @throws Exception 
	 */
	@Override
	public int insertFinanceOrgMap(List<List<Object>> list, Integer id) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<FinanceOrgMap> resultList = new ArrayList<FinanceOrgMap>();
		for (int i = 1; i < list.size(); i++) {
			try {
				FinanceOrgMap orgMap = new FinanceOrgMap();
				if(String.valueOf(list.get(i).get(0)).length() <= 50
					&& !StringUtil.isEmpty(String.valueOf(list.get(i).get(0)))){
					orgMap.setBiOrgName(String.valueOf(list.get(i).get(0)));//营业部
				}else{
					throw new Exception();
				}
				
				if(String.valueOf(list.get(i).get(1)).length() <= 20
						&& !StringUtil.isEmpty(String.valueOf(list.get(i).get(1))) ){
					orgMap.setOrgShortName(String.valueOf(list.get(i).get(1)));//营业部简称
				}else{
					throw new Exception();
				}
				
				if(String.valueOf(list.get(i).get(2)).length() <= 20){
					orgMap.setPrincipal(String.valueOf(list.get(i).get(2)));//负责人
				}else{
					throw new Exception();
				}
				
				if(String.valueOf(list.get(i).get(3)).length() <= 20
						&& !StringUtil.isEmpty(String.valueOf(list.get(i).get(3)))){
					orgMap.setManageOffice(String.valueOf(list.get(i).get(3)));//管办
				}else{
					throw new Exception();
				}
				
				if(String.valueOf(list.get(i).get(4)).length() <= 50
						&& !StringUtil.isEmpty(String.valueOf(list.get(i).get(4)))){
					orgMap.setProvince(String.valueOf(list.get(i).get(4)));//省份
				}else{
					throw new Exception();
				}
				
				Date date =null;
				if(StringUtils.isEmpty((String) list.get(i).get(5)) 
						|| RegExpUtil.checkIsDate((String) list.get(i).get(5))){
					if(!StringUtils.isEmpty((String) list.get(i).get(5))){
						date = sdf.parse(String.valueOf(list.get(i).get(5)));
						orgMap.setOpeningDate(date);//开业时间
					}else{
						orgMap.setOpeningDate(date);//开业时间
					}
				}else{
					throw new Exception();
				}
				orgMap.setAbscissa(String.valueOf(list.get(i).get(6)));//经度
				orgMap.setOrdinate(String.valueOf(list.get(i).get(7)));//纬度
				orgMap.setCreator(id);
				orgMap.setCreateTime(new Date());
				resultList.add(orgMap);
			} catch (Exception e) {
				logger.error(e.getMessage());
				if(0 != i ){
					return i+1;
				}
			}
		}
		if (0 != resultList.size()) {
			financeOrgMapMapper.deleteByExample(null);
			List<List<?>> splitList = ImportUtil.splitList(resultList, 100);
			for (List<?> list2 : splitList) {
				financeOrgMapMapper.insertFinaceOrgMap(list2);
			}
		}
		return 0;
	}
}
