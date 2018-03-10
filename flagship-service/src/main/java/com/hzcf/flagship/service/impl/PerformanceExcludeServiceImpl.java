package com.hzcf.flagship.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.hzcf.flagship.dao.MoneymgrPerformExclusionMapper;
import com.hzcf.flagship.enmu.TemplateConstants;
import com.hzcf.flagship.model.MoneymgrPerformExclusion;
import com.hzcf.flagship.service.MoneyPerformanceExcludeService;
import com.hzcf.flagship.util.PageModel;
/**
 * 
 * 类名：PerformanceExcludeServiceImpl.java</dt>
 * 功能描述：理财业绩排除表
 * 创建时间： 2017年5月12日 下午2:36:16</dd>
*  创建人：TieGuoWei</dd>
 */
@Service
public class PerformanceExcludeServiceImpl implements MoneyPerformanceExcludeService {

	@Autowired
	private MoneymgrPerformExclusionMapper moneymgrPerformExclusionMapper;

    private  final Log logger = LogFactory.getLog(getClass());

	/**
	 * 理财业绩排除表分页查询
	 */
	@Override
	public PageModel findAllByPage(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paramsCondition.get("pageNo"));
		pageModel.setPageSize((Integer) paramsCondition.get("pageSize"));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> data = moneymgrPerformExclusionMapper.findAllRetMapByPage(paramsCondition);
		Long totalRecords = moneymgrPerformExclusionMapper.findAllByPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}

	/**
	 * 理财业绩排除表上传 数据导入
	 */
	@Override
	public int insertPerformanceExclude(List<List<Object>> list, Integer id) {
		List<MoneymgrPerformExclusion> resultList = new ArrayList<MoneymgrPerformExclusion>();
		for (int i = 1; i < list.size(); i++) {
			try {
				MoneymgrPerformExclusion performExclusionser = new MoneymgrPerformExclusion();
				if(!StringUtils.isEmpty((String) list.get(i).get(0))){
					if (((String)list.get(i).get(0)).contains(TemplateConstants.SALESDEPARTMENT)
							&& ((String)list.get(i).get(0)).length() <= 50) {
						performExclusionser.setType(TemplateConstants.SALESDEPARTMENTONE);// 排除类别:客户经理营业部
					} else if (((String) list.get(i).get(0)).contains(TemplateConstants.INVESTMENTPRODUCT)
							&& ((String)list.get(i).get(0)).length() <= 50 ) {
						performExclusionser.setType(TemplateConstants.INVESTMENTPRODUCTTWO);// 排除类别:投资产品
					} else if (((String) list.get(i).get(0)).contains(TemplateConstants.INVESTMENTSTATE)
							&& ((String)list.get(i).get(0)).length() <= 50) {
						performExclusionser.setType(TemplateConstants.INVESTMENTSTATETHREE);// 排除类别:投资状态
					}else{
						throw new RuntimeException();
					}
				}else{
					throw new RuntimeException();
				}
				
				if(!StringUtils.isEmpty((String) list.get(i).get(1)) && ((String) list.get(i).get(1)).length() <= 50){
					performExclusionser.setContent((String) list.get(i).get(1));// 排除内容
				}else{
					throw new RuntimeException();
				}
				performExclusionser.setCreatorId(id);// 创建人id
				performExclusionser.setCreateTime(new Date());// 创建时间
				resultList.add(performExclusionser);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage()); 
				if (0 != i) {
					return i + 1;
				}
			}
		}
		if (0 != resultList.size()) {
			moneymgrPerformExclusionMapper.deleteByExample(null);
			moneymgrPerformExclusionMapper.insertPerformanceExclude(resultList);
		}
		return 0;
	}

}
