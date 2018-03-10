package com.hzcf.flagship.dao;

import java.util.List;
import java.util.Map;

import com.hzcf.flagship.model.AppUser;
import com.hzcf.flagship.model.AppUserExample;

public interface AppUserMapper extends BaseMapper<AppUser>{
	public Object findByMobile(String mobile);
	
	public Map<String, Object> findByUID(int id);
	
	public int countByUserName(String username);

	public AppUser selectByTelephoneAndPsw(Map<String, Object> params);

	public List<AppUser> selectByExample(AppUserExample appUserExample);

	public void updateByTelephone(Map<String, Object> params);
	
	public AppUser selectByTelephone(String telephone);
	
	public Object findNotDelByMobile(String mobile);
	
	public void deleteDelUserByMobile(String mobile);

	public List<AppUser> selectAppUserByTelephone(String telephone);

	public List<?> getAllUser(AppUser user);
	/**
	 * 通过id获取个人详情（id,姓名 图片 手机号）
	 * @param telephone
	 * @return
	 */
	public Map<String, Object> getUserInfoById(String userId);
	
	/**
	 * 根据id和所属分类查询用户详情
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> selectAppUserByIdAndSortId(Map<String, Object> map);
	/**
	 * 查找最新添加的用户
	 * @param user 
	 * @param 
	 * @return
	 */
	public AppUser findLastOneUser();
	/**
	 * 修改头像
	 * @param name
	 */
	public void updateImgByImgName(String name);
	/**
	 * 根据分类id查找用户
	 * @param name 
	 */
	public List<AppUser> findUserBySoftId(Map<String, Object> requestMap);

}