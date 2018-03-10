package com.hzcf.flagship.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.flagship.dao.AppUserMapper;
import com.hzcf.flagship.dao.ImageMapper;
import com.hzcf.flagship.model.Image;
import com.hzcf.flagship.model.ImageExample;
import com.hzcf.flagship.model.ImageExample.Criteria;
import com.hzcf.flagship.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {
	@Autowired
	private ImageMapper imageMapper;
	@Autowired
	private AppUserMapper appUserMapper;
	protected final Log logger = LogFactory.getLog(getClass());
	
	/**
	 * 根据图片名称查找图片
	 */
	@Override
	public byte[] getImageByName(String picName) {
		ImageExample imageExample = new ImageExample();
		Criteria imageCriteria = imageExample.createCriteria();
		imageCriteria.andNameEqualTo(picName);
		List<Image> list = imageMapper.selectByExampleWithBLOBs(imageExample);
		
		if (list==null || list.size()==0) {
			return null;
		}
		return list.get(0).getImage();
	}
	
	/**
	 * 查找默认图片
	 */
	@Override
	public List<Image> selectDefaultImageByName(String name) {
		ImageExample imageExample = new ImageExample();
		Criteria createCriteria = imageExample.createCriteria();
		@SuppressWarnings("unused")
		Criteria andNameEqualTo = createCriteria.andNameEqualTo(name);
		return imageMapper.selectByExample(imageExample);
		
	}

	/**
	 * 上传图片
	 */
	@Override
	public int uploadImage(Image image) {
		int result = imageMapper.insert(image);
		return result;
	}
	/**
	 * 修改头像
	 */
	@Override
	public void updateImg(Image image) {
		Image defaultImage = imageMapper.findDefaultImage();
		if(null != defaultImage && null != defaultImage.getImage()){	//有头像
			int time = Integer.parseInt(defaultImage.getName().substring(12));
			String newImgName = "defaultImage" + (time+=1);
			image.setName(newImgName);
			imageMapper.updateDefaultimage(image);
			appUserMapper.updateImgByImgName(newImgName);
		}else{	//无头像
			image.setName("defaultImage1");
			imageMapper.insertSelective(image);
			appUserMapper.updateImgByImgName("defaultImage1");
		}
		
	}

}
