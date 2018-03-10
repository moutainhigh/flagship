package com.hzcf.flagship.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hzcf.flagship.dao.MqErrorLogMapper;
import com.hzcf.flagship.dao.RiskEmailMapper;
import com.hzcf.flagship.dao.RiskOrgDataMapper;
import com.hzcf.flagship.dao.RiskOrgStructMapper;
import com.hzcf.flagship.model.RiskEmail;
import com.hzcf.flagship.model.RiskOrgData;
import com.hzcf.flagship.service.RiskEmailService;
import com.hzcf.flagship.util.HttpUtil;
import com.hzcf.flagship.util.PageModel;
import com.hzcf.flagship.util.PropertyUtil;
import com.hzcf.flagship.util.StringUtil;

import net.sf.json.JSONObject;

/**
*<dl>
*<dt>类名：RiskEmailServiceImpl.java</dt>
*<dd>描述: 风控业务逻辑实现</dd>
*<dd>创建时间： 2017年12月12日 上午8:36:52</dd>
*<dd>创建人：Tieguowei</dd>
*<dt>版本历史: </dt>
* <pre>
* Date Author Version Description
* ------------------------------------------------------------------
* 2017年12月12日 上午8:36:52 Tieguowei 1.0 1.0 Version
* </pre>
*</dl>
*/
@Service
public class RiskEmailServiceImpl implements RiskEmailService {

	@Autowired
	private RiskEmailMapper riskEmailMapper;
	@Autowired
	private RiskOrgStructMapper riskOrgStructMapper;
	@Autowired
	private RiskOrgDataMapper riskOrgDataMapper;
	@Autowired
	private MqErrorLogMapper mqErrorLogMapper;
	private String oa_md5Key = PropertyUtil.getInfo("oa_md5Key");
	//根据员工姓名模糊查询员工详细信息接口
	private String oa_findPersionInfo_url = PropertyUtil.getInfo("oa_findPersionInfo_url");
	//根据员工编号查询员工详细信息接口
	private String oa_findPersionDetailInfo_url = PropertyUtil.getInfo("oa_findPersionDetailInfo_url");
	@Override
	public PageModel findEmailList(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paramsCondition.get("pageNo"));
		pageModel.setPageSize((Integer) paramsCondition.get("pageSize"));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> data = riskEmailMapper.findAllRetMapByPage(paramsCondition);
		Long totalRecords = riskEmailMapper.findAllByPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}

	@Override
	public List<Map<String, Object>> findOrgStructTree() {
		return riskOrgStructMapper.findOrgStructTree();
	}
	
	@Override
	public PageModel childListByOrgNo(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paramsCondition.get("pageNo"));
		pageModel.setPageSize((Integer) paramsCondition.get("pageSize"));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex()); 
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		String rank = (String) paramsCondition.get("rank");
		//营业部 直接展示
			if(!StringUtil.isEmpty(rank)  && Integer.valueOf(rank) == 5){
				resultList =  riskOrgStructMapper.findDetailByOrgNo(paramsCondition);
				pageModel.setTotalRecords(Long.valueOf(resultList.size()));
			}else{
				//非营业部 通过函数查询所有子节点，将rank为5的返回
				resultList = riskOrgStructMapper.findChildListByOrgNo(paramsCondition);
				Map<String, Object> orgMap = new HashMap<String, Object>();
				orgMap.put("orgNo", paramsCondition.get("org_no"));
				orgMap.put("rank", 5);
				//查询条数
				List<String> orgNoList = riskOrgStructMapper.selectNewOrgNos(orgMap);
				Integer size = orgNoList.size();
				pageModel.setTotalRecords(size.longValue());
		}
		pageModel.setList(resultList);
		 return pageModel;
	}

	@Override
	public void updateOrgDataByOrgNo(RiskOrgData orgData) {
		Map<String,Object> map = riskOrgDataMapper.selectByOrgShortNameAndPrincipalNo(orgData);
		if(null == map){
			//简称 和 负责人 改变,执行添加操作
			orgData.setCreateTime(new Date());
			riskOrgDataMapper.insertSelective(orgData);		
		}else{
			//简称 、负责人、分中心 无 改变,修改创建时间
			map.put("create_time", new Date());
			riskOrgDataMapper.updateCreateTime(map);
		}
	}

	@Override
	public PageModel findPrincipalName(String name) {
		try {
			PageModel pageModel = new PageModel();
			Map<String, String> map = new HashMap<>();
			Map<String, Object> params = new HashMap<>();
			params.put("name", name);
			Map<String, Object> pageInfo = new HashMap<>();
			pageInfo.put("page", 1);
			pageInfo.put("pageSize", 30);
			params.put("pageInfo", pageInfo);
			String sign = MD5(JSON.toJSONString(params) + "&key=" + oa_md5Key);
			String encode = URLEncoder.encode(JSON.toJSONString(params),"UTF-8");
		    map.put("params", encode);
			map.put("sign", sign);
			String doPost = HttpUtil.doPost(oa_findPersionInfo_url, map);
			JSONObject  jasonObject = JSONObject.fromObject(doPost);
			Map<String,Object> result = (Map<String,Object>)jasonObject;
			List<Map<String,Object>> dataResult = (List<Map<String, Object>>) result.get("data");
			 pageModel.setList(dataResult);
			 Integer size = dataResult.size();
			 pageModel.setTotalRecords(size.longValue());
			 return pageModel;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}


	/**
	 * md5 加密
	 * @param sourceStr
	 * @return
	 */
	public static String MD5(String sourceStr) {
        String result = "";
        try {
            result = DigestUtils.md5Hex(sourceStr);
        } catch (Exception e) {
            System.out.println(e);
        }
        result = result.toUpperCase();
        return result;
    }

	@Override
	public void insertRecord(RiskEmail riskEmail) {
		riskEmail.setCreateTime(new Date());
		riskEmailMapper.insert(riskEmail);
		
	}



    @Override
	public PageModel findEmailAddressByList(List<RiskOrgData> list) {
		try{
			List<Map<String, Object>> responseList = new ArrayList<Map<String, Object>>();
			PageModel pageModel = new PageModel();
			for (RiskOrgData riskOrgData : list) {
				//风控经理编号
				String creditManagerNo = riskOrgData.getCreditManagerNo();
				//String isCreditManager = riskOrgData.getIsCreditManagerDetail();
				//负责人编号
				String principalNo =  riskOrgData.getPrincipalNo();
				findEmailByNos(responseList, riskOrgData, principalNo,"principal");
				//风控经理 不为空
				if(StringUtil.isNotBlank(creditManagerNo)){
					findEmailByNos(responseList, riskOrgData, creditManagerNo,"creditManager");
				}
				}
		 pageModel.setList(responseList);
		 Integer size = list.size();
		 pageModel.setTotalRecords(size.longValue());
		 return pageModel;
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	 }
	}
	/**
	 * 
	* @Description: 
	* @param @param responseList
	* @param @param riskOrgData
	* @param @param Nos
	* @param @param flag 标识 负责人还是风控经理   
	* @throws
	 */
	private void findEmailByNos(List<Map<String, Object>> responseList, RiskOrgData riskOrgData, String Nos,String flag) {
		try {
			 String[] no = Nos.split(",");
			for (String principal : no) {
				//组装请求参数
			    Map<String, String> map = new HashMap<>();
				Map<String, Object> params = new HashMap<>();
				params.put("name", principal);
				String sign = MD5(JSON.toJSONString(params) + "&key=" + oa_md5Key);
				String encode = URLEncoder.encode(JSON.toJSONString(params),"UTF-8");
			    map.put("params", encode);
				map.put("sign", sign);
				String doPost = HttpUtil.doPost(oa_findPersionDetailInfo_url, map);
				JSONObject  jasonObject = JSONObject.fromObject(doPost);
				Map<String,Object> data = (Map<String,Object>)jasonObject;
				List<Map<String,Object>> dataResult = (List<Map<String, Object>>) data.get("data");
				//组装返回数据
				Map<String, Object> responseMap = new HashMap<String, Object>();
			    for (Iterator iterator = dataResult.iterator(); iterator.hasNext();) {
			    	responseMap = (Map<String, Object>) iterator.next();
			    	responseMap.put("orgNo", riskOrgData.getOrgNo());
			    	responseMap.put("orgShortName", riskOrgData.getOrgShortName());
			    	responseMap.put("principalNo", principal);
			    	if(flag.equals("principal")){
			    		responseMap.put("isPrincipalDetail", riskOrgData.getIsPrincipalDetail());
			    		responseMap.put("principal","yes");
			    	}else{
			    		responseMap.put("isCreditManagerDetail", riskOrgData.getIsCreditManagerDetail());
			    	}
			    	responseList.add(responseMap);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public PageModel findMQExceptionList(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paramsCondition.get("pageNo"));
		pageModel.setPageSize((Integer) paramsCondition.get("pageSize"));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> data = mqErrorLogMapper.findAllRetMapByPage(paramsCondition);
		Long totalRecords = mqErrorLogMapper.findAllByPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}
    
}
