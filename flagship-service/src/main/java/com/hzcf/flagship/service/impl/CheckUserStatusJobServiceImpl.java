package com.hzcf.flagship.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hzcf.flagship.dao.AppUserMapper;
import com.hzcf.flagship.dao.EmployeeMapper;
import com.hzcf.flagship.model.AppUser;
import com.hzcf.flagship.model.AppUserExample;
import com.hzcf.flagship.model.AppUserExample.Criteria;
import com.hzcf.flagship.model.Employee;
import com.hzcf.flagship.service.CheckUserStatusJobService;
import com.hzcf.flagship.util.HttpUtil;
import com.hzcf.flagship.util.PropertyUtil;
import com.hzcf.flagship.util.log.LogDefault;

import net.sf.json.JSONObject;

@Service("checkUserStatusJobService")
public class CheckUserStatusJobServiceImpl implements CheckUserStatusJobService {

	@Autowired
	private AppUserMapper appUserMapper;
	@Autowired
	private EmployeeMapper employeeMapper;
	
	//根据员工编号查询员工详细信息接口
	private String oa_findPersionDetailInfo_url = PropertyUtil.getInfo("oa_findPersionDetailInfo_url");
	private String oa_md5Key = PropertyUtil.getInfo("oa_md5Key");
    private Logger logger = LogDefault.getLogger(getClass());

    /**
     * 检查前台app用户是否离职并更新app用户表中的状态
     */
	@Override
	public void checkAppUser() {
		try {
			//1.查询所有的app用户
			AppUserExample appUserExample = new AppUserExample();
			Criteria appUserCriteria = appUserExample.createCriteria();
			appUserCriteria.andStatusEqualTo("1");
			List<AppUser> list = appUserMapper.selectByExample(appUserExample);
			
			if (list==null || list.size()==0) {
				logger.error("查询app用户结果为空");
				return;
			}
			
			for (AppUser appUser : list) {
				String employeeNo = appUser.getEmployeeNo();
				if (employeeNo!=null) {
					String oaUserStatus = getOAUserStatus(employeeNo);
					if (oaUserStatus!=null && "0".equals(oaUserStatus)) {
						appUser.setStatus("2");
						appUserMapper.updateByPrimaryKeySelective(appUser);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("检查app用户是否离职出现异常",e);
		}
	}

	/**
	 * 检查后台用户是否离职并更新用户表中的状态
	 */
	@Override
	public void checkAdminUser() {
		try {
			//1.查询所有的普通后台用户
			/*AppUserExample appUserExample = new AppUserExample();
			List<AppUser> list = appUserMapper.selectByExample(appUserExample);*/
			List<Employee> list = employeeMapper.findAllNormalEmployee();
			
			if (list==null || list.size()==0) {
				logger.error("查询后台用户结果为空");
				return;
			}
			
			for (Employee employee : list) {
				//String employeeNo = appUser.getEmployeeNo();
				String employeeNo = employee.getEmployeeNo();
				if (employeeNo!=null) {
					String oaUserStatus = getOAUserStatus(employeeNo);
					if (oaUserStatus==null || "0".equals(oaUserStatus)) {
						//appUser.setStatus("2");
						employee.setActivatedState("0");
						employeeMapper.updateByPrimaryKeySelective(employee);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("检查后台员工是否离职出现异常",e);
		}
		
	}
	
	private String getOAUserStatus(String employeeNo) throws UnsupportedEncodingException{
		Map<String, String> map = new HashMap<>();
		Map<String, Object> params = new HashMap<>();
		params.put("name", employeeNo);
		String sign = MD5(JSON.toJSONString(params) + "&key=" + oa_md5Key);
		String encode = URLEncoder.encode(JSON.toJSONString(params),"UTF-8");
	    map.put("params", encode);
		map.put("sign", sign);
		String doPost = HttpUtil.doPost(oa_findPersionDetailInfo_url, map);
		JSONObject  jasonObject = JSONObject.fromObject(doPost);
		Map<String,Object> result = (Map<String,Object>)jasonObject;
		List<Map<String,Object>> dataResult = (List<Map<String, Object>>) result.get("data");
		if (dataResult!=null && dataResult.size()>0) {
			return (String)dataResult.get(0).get("status");
		}
		return null;
		
	}
	
	/**
	 * md5 加密
	 * @param sourceStr
	 * @return
	 */
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
}