package com.hzcf.flagship.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.flagship.dao.AppUserMapper;
import com.hzcf.flagship.dao.AppUserRelationshipsMapper;
import com.hzcf.flagship.model.AppUser;
import com.hzcf.flagship.model.AppUserExample;
import com.hzcf.flagship.model.AppUserRelationships;
import com.hzcf.flagship.service.IMService;

@Service
public class IMServiceImpl implements IMService {
	@Autowired
	private AppUserMapper appUserMapper;
	@Autowired
	private AppUserRelationshipsMapper  appUserRelationshipsMapper;

	@Override
	public void insertInitUserRelation() {
		//查询所有状态为1的用户
		
		//查找除了当前用户外的其他所有状态为1(正常)的用户
		AppUserExample appUserExample = new AppUserExample();
		com.hzcf.flagship.model.AppUserExample.Criteria appUserCriteria = appUserExample.createCriteria();
		//appUserCriteria.andIdNotEqualTo(appUser.getId());
		appUserCriteria.andStatusEqualTo("1");
		List<AppUser> users = appUserMapper.selectByExample(appUserExample);
		
		for (AppUser fromUser : users) {
			//2.判断用户类别
			//如果用户是执委,则可以看到所有的用户
			if (fromUser.getSortId()==1) {
				for (AppUser toUser : users) {
					if (toUser.getId()!=fromUser.getId()) {
						AppUserRelationships appUserRelationship = new AppUserRelationships();
						appUserRelationship.setFromUserId(fromUser.getId());
						appUserRelationship.setToUserId(toUser.getId());
						appUserRelationship.setStatus("1");
						appUserRelationship.setCreateTime(new Date());
						appUserRelationship.setCreator(1);
						appUserRelationshipsMapper.insert(appUserRelationship);
					}
				}
			}else if (fromUser.getSortId()==4) {//如果用户类别是职能,则不做任何处理
				
			}else {//如果是其他情况,则用户是什么类别就将其他也是这个类别的用户加入到关系表中
				for (AppUser toUser : users) {
					if (toUser.getSortId()==fromUser.getSortId() && fromUser.getId()!=toUser.getId()) {
						AppUserRelationships appUserRelationship = new AppUserRelationships();
						appUserRelationship.setFromUserId(fromUser.getId());
						appUserRelationship.setToUserId(toUser.getId());
						appUserRelationship.setStatus("1");
						appUserRelationship.setCreateTime(new Date());
						appUserRelationship.setCreator(1);
						appUserRelationshipsMapper.insert(appUserRelationship);
					}
				}
			}
		}
		
	}

}
