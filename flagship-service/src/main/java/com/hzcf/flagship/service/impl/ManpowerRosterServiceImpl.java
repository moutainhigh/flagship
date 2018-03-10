

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
import com.hzcf.flagship.dao.MoneymgrRosterMapper;
import com.hzcf.flagship.enmu.TemplateConstants;
import com.hzcf.flagship.model.MoneymgrRoster;
import com.hzcf.flagship.service.MoneyManpowerRosterService;
import com.hzcf.flagship.util.ImportUtil;
import com.hzcf.flagship.util.PageModel;
import com.hzcf.flagship.util.RegExpUtil;

/**
 * 
 * 类名：ManpowerRosterServiceImpl.java</dt> 
 * 功能描述：理财人力花名册
 * 创建时间： 2017年5月10日 上午11:52:02
 * </dd> 创建人：TieGuoWei</dd>
 */
@Service
public class ManpowerRosterServiceImpl implements MoneyManpowerRosterService{

	@Autowired
	private MoneymgrRosterMapper moneymgrRosterMapper;
	
	private final Log logger = LogFactory.getLog(getClass());
	/**
	 * 
	 * Description: 理财花名册分页查询列表
	 */
	@Override
	public PageModel findAllByPage(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paramsCondition.get("pageNo"));
		pageModel.setPageSize((Integer) paramsCondition.get("pageSize"));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> data = moneymgrRosterMapper.findAllRetMapByPage(paramsCondition);
		Long totalRecords = moneymgrRosterMapper.findAllByPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}


	/**
	 * 理财人力花名册 数据导入
	 * @throws Exception 
	 */
	@Override
	public int insertMoneymgrRoster(List<List<Object>> list, Integer id) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<MoneymgrRoster> resultList = new ArrayList<MoneymgrRoster>();
		for (int i = 1; i < list.size(); i++) {
			try {
				MoneymgrRoster moneymgrRoster = new MoneymgrRoster();
				
				if((String.valueOf(list.get(i).get(0))).length() <= 32 && !StringUtils.isEmpty(String.valueOf(list.get(i).get(0)))){
					moneymgrRoster.setEmployeeNo(String.valueOf(list.get(i).get(0)));//人员编号
				}else{
					throw new  RuntimeException();
				}
				
				if((String.valueOf(list.get(i).get(1))).length() <= 32){
					moneymgrRoster.setReferralCode(String.valueOf(list.get(i).get(1)));//推荐码
				}else{
					throw new  RuntimeException();
				}
				
				if(StringUtils.isEmpty(String.valueOf(list.get(i).get(2))) || RegExpUtil.checkIdCard(String.valueOf(list.get(i).get(2)))){
					moneymgrRoster.setIdCard(String.valueOf(list.get(i).get(2)));//身份证号
				}else{
					throw new  RuntimeException();
				}
				
				if(!StringUtils.isEmpty(String.valueOf(list.get(i).get(3))) && String.valueOf(list.get(i).get(3)).length() <=20 ){
					moneymgrRoster.setEmployeeName(String.valueOf(list.get(i).get(3)));//员工姓名
				}else{
					throw new  RuntimeException();
				}
				
				if(String.valueOf(list.get(i).get(4)).length() <= 20){
					moneymgrRoster.setDistrictName(String.valueOf(list.get(i).get(4)));//区域
				}else{
					throw new  RuntimeException();
				}
				
				if(String.valueOf(list.get(i).get(5)).length() <= 50 && !StringUtils.isEmpty(String.valueOf(list.get(i).get(5)))){
					moneymgrRoster.setOrgName(String.valueOf(list.get(i).get(5)));//花名册机构名称
				}else{
					throw new  RuntimeException();
				}
				
				if(String.valueOf(list.get(i).get(6)).length() <= 20){
					moneymgrRoster.setOrgPrincipal(String.valueOf(list.get(i).get(6)));//机构负责人
				}else{
					throw new  RuntimeException();
				}
				
				if(String.valueOf(list.get(i).get(7)).length() <= 50){
					moneymgrRoster.setLevel4Department(String.valueOf(list.get(i).get(7)));//四级部门/团
				}else{
					throw new  RuntimeException();
				}
				
				if(String.valueOf(list.get(i).get(8)).length() <= 20){
					moneymgrRoster.setLevel4Principal(String.valueOf(list.get(i).get(8)));//四级部门/团负责人
				}else{
					throw new  RuntimeException();
				}
				
				if(String.valueOf(list.get(i).get(9)).length() <= 50){
					moneymgrRoster.setLevel5Department(String.valueOf(list.get(i).get(9)));//五级部门/组
				}else{
					throw new  RuntimeException();
				}
				
				if(String.valueOf(list.get(i).get(10)).length() <= 20){
					moneymgrRoster.setLevel5Principal(String.valueOf(list.get(i).get(10)));//五级部门/组负责人
				}else{
					throw new  RuntimeException();
				}
				
				if(String.valueOf(list.get(i).get(11)).length() <= 50){
					moneymgrRoster.setPosition(String.valueOf(list.get(i).get(11)));//职位
				}else{
					throw new  RuntimeException();
				}
				
				if(!StringUtils.isEmpty(String.valueOf(list.get(i).get(12))) && String.valueOf(list.get(i).get(12)).length() <= 20){
					moneymgrRoster.setPositionType(String.valueOf(list.get(i).get(12)));//职类
				}else{
					throw new  RuntimeException();
				}
				Date date =null;
				if(!StringUtils.isEmpty((String) list.get(i).get(13)) && RegExpUtil.checkIsDate((String) list.get(i).get(13))){
					date = sdf.parse(String.valueOf(list.get(i).get(13)));
					moneymgrRoster.setEntryDate(date);//入职日期
				}else{
					throw new  RuntimeException();
				}
				//1:已转正,0:未转正
				
				if(!StringUtils.isEmpty(String.valueOf(list.get(i).get(14)))){
					if(String.valueOf(list.get(i).get(14)).contains(TemplateConstants.YES)
							&& String.valueOf(list.get(i).get(14)).length() <= 10){
						moneymgrRoster.setIsPositive(TemplateConstants.YESISONE);//是
					}else if((String.valueOf(list.get(i).get(14)).contains(TemplateConstants.NO))
							&& String.valueOf(list.get(i).get(14)).length() <= 10){
						moneymgrRoster.setIsPositive(TemplateConstants.NOISZERO);//否
					}else{
						throw new  RuntimeException();
					}
				}else{
					throw new  RuntimeException();
				}
				
				Date positiveDate = null;
				if(StringUtils.isEmpty((String)list.get(i).get(15)) || RegExpUtil.checkIsDate((String)list.get(i).get(15))){
					if(!StringUtils.isEmpty((String)list.get(i).get(15))){
						positiveDate =sdf.parse(String.valueOf(list.get(i).get(15)));
						moneymgrRoster.setPositiveDate(positiveDate);
					}else{
						moneymgrRoster.setPositiveDate(positiveDate);//实际转正日期
					}
				}else{
					throw new  RuntimeException();
				}
				
				Date dimissionDate =null;
				if(StringUtils.isEmpty((String)list.get(i).get(16)) || RegExpUtil.checkIsDate((String)list.get(i).get(16))){
					if(!StringUtils.isEmpty((String)list.get(i).get(16))){
						dimissionDate = sdf.parse(String.valueOf(list.get(i).get(16)));
						moneymgrRoster.setDimissionDate(dimissionDate);
					}else{
						moneymgrRoster.setDimissionDate(dimissionDate);//离职日期
					}
				}else{
					throw new  RuntimeException();
				}
				//得到当前登录用户id
				moneymgrRoster.setCreatorId(id);//创建人id
				moneymgrRoster.setCreateTime(new Date());
				resultList.add(moneymgrRoster);
			} catch (Exception e) {
				e.printStackTrace();
				if(0 != i ){
					return i+1;
				}
			}
			
		}
		if( 0 != resultList.size()){
			moneymgrRosterMapper.deleteByExample(null);
			List<List<?>> splitList = ImportUtil.splitList(resultList,100);
			for (List<?> list2 : splitList) {
				moneymgrRosterMapper.insertMoneymgrRoster(list2);
			}
		}
		return 0;
	}
}
