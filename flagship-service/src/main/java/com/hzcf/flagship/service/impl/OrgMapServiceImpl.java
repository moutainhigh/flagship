package com.hzcf.flagship.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.flagship.dao.OrgLocationMapper;
import com.hzcf.flagship.dao.OrgMapMapper;
import com.hzcf.flagship.model.OrgLocation;
import com.hzcf.flagship.model.OrgMap;
import com.hzcf.flagship.service.MoneyOrgMapService;
import com.hzcf.flagship.util.PageModel;
import com.hzcf.flagship.util.RegExpUtil;
import com.hzcf.flagship.util.StringUtil;
/**
 * 
 * 类名：OrgMapServiceImpl.java</dt> 
 * 功能描述：理财机构名称对应表
 * 创建时间： 2017年5月10日 上午11:52:02
 * </dd> 创建人：TieGuoWei</dd>
 */

@Service
public class OrgMapServiceImpl implements MoneyOrgMapService {

	@Autowired
	private OrgMapMapper orgMapMapper;
    @Autowired
    private OrgLocationMapper orgLocationMapper;
    
    private  final Log logger = LogFactory.getLog(getClass());
	/**
	 * 理财机构名称对应表分页查询
	 */
	@Override
	public PageModel findAllByPage(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paramsCondition.get("pageNo"));
		pageModel.setPageSize((Integer) paramsCondition.get("pageSize"));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> data = orgMapMapper.findAllRetMapByPage(paramsCondition);
		Long totalRecords = orgMapMapper.findAllByPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}
	/**
	 * 理财机构名称对应表上传 数据导入
	 */
	@Override
	public int insertOrgMap(List<List<Object>> list, Integer id) {
		List<OrgMap> orgMapList = new ArrayList<OrgMap>();
		List<OrgLocation> orgLocationList = new ArrayList<OrgLocation>();
		for (int i = 1; i < list.size(); i++) {
			try {
				OrgMap orgMap = new OrgMap();
				OrgLocation orgLocation = new OrgLocation();
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(0))) && String.valueOf(list.get(i).get(0)).length() <= 50){
					orgMap.setBiOrgName(String.valueOf(list.get(i).get(0)));//BI机构名称
				}else{
					throw new Exception();
				}
				
				if(String.valueOf(list.get(i).get(1)).length() <= 50){
					orgMap.setRosterOrgName(String.valueOf(list.get(i).get(1)));//花名册机构名称
				}else{
					throw new Exception();
				}
				
				if(String.valueOf(list.get(i).get(2)).length() <= 20 && !StringUtil.isEmpty(String.valueOf(list.get(i).get(2)))){
					orgMap.setMoneymgrOrgName(String.valueOf(list.get(i).get(2)));//理财机构名称
					orgLocation.setOrgName(String.valueOf(list.get(i).get(2)));//将此名称添加到地图表中
				}else{
					throw new Exception();
				}
				
				if(((String)list.get(i).get(4)).length() <= 200){
					orgLocation.setAddress((String)list.get(i).get(4));//地址
				}else{
					throw new Exception();
				}
				
				if(!StringUtil.isEmpty((String)list.get(i).get(5))){
					String str = (String)list.get(i).get(5);
					String[] split = str.split(",");
					if(RegExpUtil.checkIsDecimalsOrInt(split[0])){
						orgLocation.setAbscissa(split[0]); //横坐标
					}else{
						throw new Exception();	
					}
					if(RegExpUtil.checkIsDecimalsOrInt(split[1])){
						orgLocation.setOrdinate(split[1]); //纵坐标
					}else{
						throw new Exception();	
					}
				}else{
					throw new Exception();	
				}
				orgMap.setCreatorId(id);//创建人id
				orgMap.setCreateTime(new Date());
				orgMapList.add(orgMap);
				orgLocationList.add(orgLocation);
			} catch (Exception e) {
				e.printStackTrace();
				if (0 != i) {
					return i+1;
				}
			}
		}
		if(0 != orgMapList.size()){
			orgMapMapper.deleteByExample(null);
			orgMapMapper.insertOrgMap(orgMapList);
		}
		if(0 != orgLocationList.size()){
			orgLocationMapper.deleteByExample(null);
			//横纵坐标入库
			orgLocationMapper.insertOrgLocationMapper(orgLocationList);
		}
		return 0;
	}

}
