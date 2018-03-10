package com.hzcf.flagship.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.flagship.dao.MoneymgrMonthHistoryMapper;
import com.hzcf.flagship.model.MoneymgrMonthHistory;
import com.hzcf.flagship.service.MoneyMonthSumService;
import com.hzcf.flagship.util.ImportUtil;
import com.hzcf.flagship.util.PageModel;
import com.hzcf.flagship.util.RegExpUtil;
import com.hzcf.flagship.util.StringUtil;
/**
 * 
 * 类名：MonthSumServiceImpl.java</dt> 
 * 功能描述：理财月度累计表
 * 创建时间： 2017年5月10日 上午11:52:02
 * </dd> 创建人：TieGuoWei</dd>
 */
@Service
public class MonthSumServiceImpl implements MoneyMonthSumService {

	@Autowired
	private MoneymgrMonthHistoryMapper moneymgrMonthHistoryMapper;
	
	private final Log logger = LogFactory.getLog(getClass());

	/**
	 * 理财月度累计表分页查询
	 */
	@Override
	public PageModel findAllByPage(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paramsCondition.get("pageNo"));
		pageModel.setPageSize((Integer) paramsCondition.get("pageSize"));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> data = moneymgrMonthHistoryMapper.findAllRetMapByPage(paramsCondition);
		Long totalRecords = moneymgrMonthHistoryMapper.findAllByPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}

	/**
	 * 理财月度累计表上传 数据导入
	 * @throws Exception 
	 */

	@Override
	public int insertMoneymgrMonthHistory(List<List<Object>> list,Integer id) throws Exception {
		List<MoneymgrMonthHistory> resultList = new ArrayList<MoneymgrMonthHistory>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 1; i < list.size(); i++) {
			try {
				MoneymgrMonthHistory moneymgrMonthHistory = new MoneymgrMonthHistory();
				if(!StringUtil.isEmpty(String.valueOf(list.get(i).get(0))) && RegExpUtil.checkIsDate(String.valueOf(list.get(i).get(0)))){
					Date date = sdf.parse(String.valueOf(list.get(i).get(0)));
					moneymgrMonthHistory.setRecordDate(date);// 记录日期(月份)
				}else{
					throw new Exception();
				}
				
				if(String.valueOf(list.get(i).get(1)).length() <= 50){
					moneymgrMonthHistory.setRecordName(String.valueOf(list.get(i).get(1)));// 记录名称
				}else{
					throw new Exception();
				}
				
				if(String.valueOf(list.get(i).get(2)).length() <= 50 && !StringUtil.isEmpty(String.valueOf(list.get(i).get(2)))){
					moneymgrMonthHistory.setIndexName(String.valueOf(list.get(i).get(2)));// 指标名称
				}else{
					throw new Exception();
				}
				
				
				if(String.valueOf(list.get(i).get(3)).contains("%")){
					String[] split = String.valueOf(list.get(i).get(3)).split("%");
					if(RegExpUtil.checkIsDecimalsOrInt(split[0])){
						double parseDouble = Double.parseDouble(split[0]);
					     moneymgrMonthHistory.setIndexValue(new BigDecimal(parseDouble));// 指标业绩
					}else{
						throw new Exception();
					}
				}else{
					if(RegExpUtil.checkIsDecimalsOrInt(String.valueOf(list.get(i).get(3)))){
						moneymgrMonthHistory.setIndexValue(new BigDecimal(String.valueOf(list.get(i).get(3))));// 指标业绩
					}else{
						throw new Exception();
					}
				}
				moneymgrMonthHistory.setCreatorId(id);
				moneymgrMonthHistory.setCreateTime(new Date());
				resultList.add(moneymgrMonthHistory);
			} catch (Exception e) {
				logger.error(e.getMessage()); ;
				e.printStackTrace();
				if (0 != i) {
					return i+1;
				}
			}
		}
		
		if (0 != resultList.size()) {
			moneymgrMonthHistoryMapper.deleteByExample(null);
			List<List<?>> splitList = ImportUtil.splitList(resultList, 100);
			for (List<?> list2 : splitList) {
				moneymgrMonthHistoryMapper.insertMoneymgrMonthHistory(list2);
			}
		}
		return 0;
	}
}
