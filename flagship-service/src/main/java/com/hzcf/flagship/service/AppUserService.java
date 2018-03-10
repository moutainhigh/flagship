package com.hzcf.flagship.service;

import java.util.List;
import java.util.Map;

import com.hzcf.flagship.model.AppUser;
import com.hzcf.flagship.util.PageModel;

/**
 * 
 *<dl>
 *<dt>类名：AppUserService.java</dt>
 *<dd>描述: APP用户Service </dd> 
 *<dd>创建时间： 2017年5月13日 下午2:25:29</dd>
 *<dd>创建人： Caigen</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年5月13日 下午2:25:29    Caigen       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
public interface AppUserService {
	/**
	 * 分页查询
	 * @param condition
	 * @return
	 */
	public PageModel findAllByPage(Map<String, Object> condition);
	
	/**
	 * 更新
	 * @param appUser
	 * @param employeeId 
	 * @param letter 
	 * @param oldLetter 
	 * @param oauth 
	 * @param oldauth 
	 * @return
	 */
	public int updateByPrimaryKeySelective(AppUser appUser, String oldLetter, String[] letter, Integer employeeId, String oldauth, String[] oauth);
	
	/**
	 * 获取权限
	 * @return
	 */
	public List<?> getRoles();
	
	/**
	 * 根据手机号查找
	 * @param mobile
	 * @return
	 */
	public int findByMobile(String mobile);
	/**
	 * 添加
	 * @param employeeId 
	 * @param password 
	 * @param letter 
	 * @param oauth 
	 * @param employee
	 * @return
	 */
	public void insertSelective(AppUser appUser, Integer employeeId, String password, String[] oauth, String[] letter);
	
	
	/**
	 * 根据手机号查找未删除用户
	 * @param mobile
	 * @return
	 */
	public int findNotDelByMobile(String mobile);
	
	/**
	 * 删除用户状态为已删除(state=3,6)手机号为mobile的用户
	 * @param mobile
	 * @return
	 */
	public void deleteDelUserByMobile(String mobile);
	
	/**
	 * 根据主键查找
	 * @param id
	 * @return
	 */
	public AppUser selectByPrimaryKey(Integer id);
	
	/**
	 * 查找包含权限
	 * @param id
	 * @return
	 */
	public Map<String, Object> findByUID(Integer id);
	
	/**
	 * 根据用户名查找用户数
	 * @param username
	 * @return
	 */
	public int findCountByUserName(String username);
	
	/**
	 * 查找所有的用户
	 * @return
	 */
	public List<AppUser> findAllUsers();
	
	/**
	 * 查找所有状态为1(正常)的用户
	 * @return
	 */
	public List<AppUser> findAllNormalUsers();
	
	/**
	 * 查找所有状态为1(正常)和2(禁用)的用户
	 * @return
	 */
	public List<AppUser> findAllNormalAndForbiddenUsers();
	
	/**
	 * 修改用户环信密码
	 * @return
	 */
	public void updateImPassword(Integer userId,String imPassword);

	/**
	 * 查找包含分类
	 * @param 
	 * @return
	 */
	public List<?> getSorts();
	/**
	 * 查找所有用户
	 * @param user 
	 * @param 
	 * @return
	 */
	public List<?> getAllUser(AppUser user);
	/**
	 * 禁用 修改 用户 联系人关系表
	 * @param map 
	 * @param 
	 * @return
	 */
	public void updateToUserIdStatus(Map<String, Object> map);
	/**
	 * 启用 修改 用户 联系人关系表
	 * @param map 
	 * @param 
	 * @return
	 */
	public void updateToUserIdStatusOpen(Map<String, Object> map);

	public List<Map<String, Object>> findPermissionList();
}
