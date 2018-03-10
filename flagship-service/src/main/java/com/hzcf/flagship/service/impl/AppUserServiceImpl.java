package com.hzcf.flagship.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.flagship.dao.AppPermissionMapper;
import com.hzcf.flagship.dao.AppUserMapper;
import com.hzcf.flagship.dao.AppUserPermissionRelationMapper;
import com.hzcf.flagship.dao.AppUserRelationshipsMapper;
import com.hzcf.flagship.dao.AppUserSortMapper;
import com.hzcf.flagship.dao.ImageMapper;
import com.hzcf.flagship.enmu.UserConstants;
import com.hzcf.flagship.im.api.impl.EasemobIMUsers;
import com.hzcf.flagship.model.AppUser;
import com.hzcf.flagship.model.AppUserExample;
import com.hzcf.flagship.model.AppUserExample.Criteria;
import com.hzcf.flagship.model.AppUserPermissionRelation;
import com.hzcf.flagship.model.AppUserRelationships;
import com.hzcf.flagship.model.Image;
import com.hzcf.flagship.service.AppUserPermissionRelationService;
import com.hzcf.flagship.service.AppUserRelationService;
import com.hzcf.flagship.service.AppUserService;
import com.hzcf.flagship.util.JedisClientUtil;
import com.hzcf.flagship.util.PageModel;

import io.swagger.client.model.Nickname;
import io.swagger.client.model.RegisterUsers;
import io.swagger.client.model.User;

/**
 * 
 *<dl>
 *<dt>类名：AppUserServiceImpl.java</dt>
 *<dd>描述: APP用户Service实现类 </dd> 
 *<dd>创建时间： 2017年5月13日 下午3:07:48</dd>
 *<dd>创建人： Caigen</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年5月13日 下午3:07:48    Caigen       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Service("appUserService")
public class AppUserServiceImpl implements AppUserService {
	@Autowired
	private AppUserMapper appUserMapper;
	@Autowired
	private AppPermissionMapper appPermissionMapper;
	@Autowired
	private AppUserPermissionRelationMapper appUserPermissionRelationMapper;
	@Autowired
	JedisClientUtil jedisClientUtil;
	@Autowired
	private AppUserSortMapper appUserSortMapper;
	@Autowired
	private AppUserRelationshipsMapper appUserRelationshipsMapper;
	@Autowired
	private EasemobIMUsers easemobIMUsers;
	@Autowired
	private ImageMapper imageMapper;
	@Autowired
	private AppUserRelationService appUserRelationService;
	@Autowired
	private AppUserPermissionRelationService appUserPermissionRelationService;
	@Override
	public PageModel findAllByPage(Map<String, Object> paramsCondition) {
		PageModel pageModel = new PageModel();
		pageModel.setPageNo((Integer) paramsCondition.get("pageNo"));
		pageModel.setPageSize((Integer) paramsCondition.get("pageSize"));
		paramsCondition.put("startIndex", pageModel.getStartIndex());
		paramsCondition.put("endIndex", pageModel.getEndIndex());
		List<Map<String, Object>> data = appUserMapper.findAllRetMapByPage(paramsCondition);
		Long totalRecords = appUserMapper.findAllByPageCount(paramsCondition);
		pageModel.setList(data);
		pageModel.setTotalRecords(totalRecords);
		return pageModel;
	}

	@Override
	public int updateByPrimaryKeySelective(AppUser appUser, String oldLetter, String[] letter, Integer employeeId,String oldauth, String[] oauth) {
		if(null !=appUser.getStatus() && appUser.getStatus().equals("2")){
			return appUserMapper.updateByPrimaryKeySelective(appUser);

		};
		if(null !=appUser.getStatus() && appUser.getStatus().equals("1")){
			return appUserMapper.updateByPrimaryKeySelective(appUser);
		}
		Map<String, Object> findByUID = appUserMapper.findByUID(appUser.getId());
		if(null != findByUID){
			//判断修改后的姓名是否和原来的一致，不一致调用环信修改昵称接口
			if(null !=findByUID.get("name") && !findByUID.get("name").equals(appUser.getName())){
				//修改环信昵称
				String userName = String.valueOf(appUser.getId());
				Nickname nickname = new Nickname();
				nickname.setNickname(appUser.getName());
				easemobIMUsers.modifyIMUserNickNameWithAdminToken(userName,nickname);
			}
			
			if(null !=findByUID.get("mobile") && !findByUID.get("mobile").equals(appUser.getMobile())){
				//删除redis中的token记录
				jedisClientUtil.delByKeyprefixAndValue(UserConstants.USER_TOKEN, appUser.getId()+"");
			}
		}
		
		int result = appUserMapper.updateByPrimaryKeySelective(appUser);
		
		//修改联系人
		StringBuffer bufLetter =new StringBuffer();
		if(null != letter){
			for (int i = 0; i < letter.length; i++) {
				String str = letter[i];
				bufLetter.append(str).append(",");
			}
			bufLetter.deleteCharAt(bufLetter.lastIndexOf(","));
			// 联系人变更
			if (!oldLetter.equals(bufLetter.toString())) {
				appUserRelationService.updateUserRelation(appUser, letter);
			}
		}else{
			// delete fromId
			appUserRelationshipsMapper.deleteFromUID(appUser.getId());
		}
		
		//修改权限
		StringBuffer buf =new StringBuffer();
		if(null != oauth){
			for (int i = 0; i < oauth.length; i++) {
				String str = oauth[i];
				buf.append(str).append(",");
			}
			buf.deleteCharAt(buf.lastIndexOf(","));
			// 权限变更
			if (!oldauth.equals(buf.toString())) {
				appUserPermissionRelationService.updatePermission(appUser, oauth);
			}
		}
		
		
		if(null !=appUser.getStatus() && appUser.getStatus().equals("3")){
			//调用环信删除接口
			easemobIMUsers.deleteIMUserByUserName(String.valueOf(appUser.getId()));
			
			//删除用户关系表相应数据
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("toUserId", appUser.getId());
			map.put("fromUserId", appUser.getId());
			appUserRelationshipsMapper.deleteRelationsByToUserId(map);
		}
		return result;
	}


	@Override
	public List<?> getRoles() {
		return appPermissionMapper.findAllRetMapNoPage(null);
	}

	@Override
	public void insertSelective(AppUser appUser,Integer employeeId,String password,String[] oauth, String[] letter) {
		//查询出最新的默认头像
		Image image = imageMapper.findDefaultImage();
		appUser.setHeadImageName(image.getName());
		//执行添加操作
		appUserMapper.insertSelective(appUser);
		//得到最新添加的数据
		AppUser lastAppUser = appUserMapper.findLastOneUser();
	
		Map<String,Object> requestMap = new HashMap<String,Object>();
		//正常用户
		if(appUser.getSortId() == 1){
			requestMap.put("sortId", 1);
		}else if(appUser.getSortId() == 2){
			requestMap.put("sortId", 2);
		}else if(appUser.getSortId() == 3){
			requestMap.put("sortId", 3);
		}else{
			requestMap.put("sortId", 1);
		}
		//得到用户类别后  给用户关系表添加数据
		List<AppUser> users= appUserMapper.findUserBySoftId(requestMap);
		for (AppUser appUser2 : users) {
			if (appUser2.getId() != lastAppUser.getId()) {
				AppUserRelationships record = new AppUserRelationships();
				record.setFromUserId(appUser2.getId());
				record.setToUserId(lastAppUser.getId());
				record.setCreateTime(new Date());
				record.setCreator(employeeId);
				record.setStatus("1");
				appUserRelationshipsMapper.insertSelective(record);
			}
		}
		//调用环信 创建单个用户接口
		RegisterUsers users2 = new RegisterUsers();
		User hxUser = new User();
		hxUser.setUsername(String.valueOf(lastAppUser.getId()));
		hxUser.setPassword(password);
		users2.add(hxUser);
		Object result = easemobIMUsers.createNewIMUserSingle(users2);
		//调用环信 修改昵称接口
		if(null != result){
			String userName = String.valueOf(lastAppUser.getId());
			Nickname nickname = new Nickname();
			nickname.setNickname(lastAppUser.getName());
			easemobIMUsers.modifyIMUserNickNameWithAdminToken(userName,nickname);
		}
		
		Object obj = appUserMapper.findByMobile(appUser.getMobile());
		if(null != obj){
			appUser.setId((int)obj);
		}
		
		//添加 用户权限关系表
		AppUserPermissionRelation record = new AppUserPermissionRelation();
		record.setUserId(appUser.getId());
		record.setCreateTime(appUser.getCreateTime());
		record.setCreatorId(appUser.getCreatorId());
		
		for (int i = 0; i < oauth.length; i++) {
			String str = oauth[i];
			//如果没有选中 fk 默认添加一条
				record.setPermissionId(Integer.valueOf( str ) );
				appUserPermissionRelationMapper.insertSelective(record);
			
		}
		//添加 用户  私信联系人 关系表
		AppUserRelationships appUserRelationships = new AppUserRelationships();
		appUserRelationships.setFromUserId(appUser.getId());
		appUserRelationships.setCreateTime(appUser.getCreateTime());
		appUserRelationships.setCreator(appUser.getCreatorId());
		appUserRelationships.setStatus("1");
		if(letter != null){
			for (String string : letter) {
				appUserRelationships.setToUserId(Integer.valueOf(string));
				appUserRelationshipsMapper.insertSelective(appUserRelationships);
			}
		}
		
	}

	
	/**
	 * 根据手机号查找未删除用户
	 * @param mobile
	 * @return
	 */
	@Override
	public int findNotDelByMobile(String mobile){
		Object obj= appUserMapper.findNotDelByMobile(mobile);
		if(obj==null)return 0;
		else return (int)obj;
	}
	
	@Override
	public void deleteDelUserByMobile(String mobile){
		//删除用户表数据
		appUserMapper.deleteDelUserByMobile(mobile);
		//删除用户权限关联表数据
		Object findByMobile = appUserMapper.findByMobile(mobile);
		if (findByMobile!=null) {
			int userId = (int)findByMobile;
			appUserPermissionRelationMapper.deleteByUID(userId);
		}
		
	}

	@Override
	public AppUser selectByPrimaryKey(Integer id) {
		return appUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public Map<String, Object> findByUID(Integer id) {
		return appUserMapper.findByUID(id);
	}

	@Override
	public int findCountByUserName(String username) {
		return appUserMapper.countByUserName(username);
	}

	/**
	 * 查找所有的用户
	 */
	@Override
	public List<AppUser> findAllUsers() {
		AppUserExample appUserExample = new AppUserExample();
		List<AppUser> users = appUserMapper.selectByExample(appUserExample);
		return users;
	}

	/**
	 * 查找所有状态为1(正常)的用户
	 */
	@Override
	public List<AppUser> findAllNormalUsers() {
		AppUserExample appUserExample = new AppUserExample();
		Criteria appUserCriteria = appUserExample.createCriteria();
		appUserCriteria.andStatusEqualTo("1");
		List<AppUser> users = appUserMapper.selectByExample(appUserExample);
		return users;
	}
	
	/**
	 * 查找所有状态为1(正常)和2(禁用)的用户
	 */
	@Override
	public List<AppUser> findAllNormalAndForbiddenUsers() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		AppUserExample appUserExample = new AppUserExample();
		Criteria appUserCriteria = appUserExample.createCriteria();
		appUserCriteria.andStatusIn(list);
		List<AppUser> users = appUserMapper.selectByExample(appUserExample);
		return users;
	}

	@Override
	public void updateImPassword(Integer userId,String imPassword) {
		AppUser appUser = new AppUser();
		appUser.setId(userId);
		appUser.setImPassword(imPassword);
		appUserMapper.updateByPrimaryKeySelective(appUser);
	}
	@Override
	public List<?> getSorts() {
		return appUserSortMapper.getSorts();
	}

	@Override
	public List<?> getAllUser(AppUser user) {
		
		return appUserMapper.getAllUser(user);
	}
	
	@Override
	public int findByMobile(String mobile) {
		Object obj= appUserMapper.findByMobile(mobile);
		if(obj==null)return 0;
		else return (int)obj;
	}

	@Override
	public void updateToUserIdStatus(Map<String, Object> map) {
		appUserRelationshipsMapper.updateToUserIdStatus(map);
	}

	@Override
	public void updateToUserIdStatusOpen(Map<String, Object> map) {
		appUserRelationshipsMapper.updateToUserIdStatusOpen(map);
		
	}

	@Override
	public List<Map<String, Object>> findPermissionList() {
		return appPermissionMapper.findPermissionList();
	}

}
