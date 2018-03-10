package com.hzcf.flagship.service;

import java.util.List;

import com.hzcf.flagship.model.Image;


/**
 *<dl>
 *<dt>类名：ImageService.java</dt>
 *<dd>描述: 图片服务类接口</dd> 
 *<dd>创建时间： 2017年7月25日 上午10:31:06</dd>
 *<dd>创建人： XuHao</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年7月25日 上午10:31:06    XuHao       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
public interface ImageService {
	/**
	 * 根据图片名称查找图片
	 * @return
	 */
	public byte[] getImageByName(String picName);

	/**
	 * 查找默认图片
	 * @param string
	 * @return
	 */
	List<Image> selectDefaultImageByName(String name);
	
	/**
	 * 上传图片
	 */
	int uploadImage(Image image);
	/**
	 * 上传头像
	 * @param image
	 */
	public void updateImg(Image image);

}
