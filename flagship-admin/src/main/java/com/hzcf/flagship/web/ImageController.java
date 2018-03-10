package com.hzcf.flagship.web;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hzcf.flagship.baseweb.BaseController;
import com.hzcf.flagship.model.Image;
import com.hzcf.flagship.service.ImageService;
import com.hzcf.flagship.util.PropertyUtil;
import com.hzcf.flagship.util.RandomNumUtil;

/**
 *<dl>
 *<dt>类名：ImageController.java</dt>
 *<dd>描述: 图片controller</dd> 
 *<dd>创建时间： 2017年8月1日 上午10:46:42</dd>
 *<dd>创建人： XuHao</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年8月1日 上午10:46:42    XuHao       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Controller
@RequestMapping(value="/image")
public class ImageController extends BaseController{
	@Autowired
	private ImageService imageService;
	
	@RequestMapping(value="/imageUpload",method=RequestMethod.POST)  
    @ResponseBody
    public Map<String, Object> uploadImage(@RequestParam(value = "pictFile") CommonsMultipartFile pictFile) throws IOException{  
		logger.info("-------------上传图片----------------");
		try {
			//原始文件名称
			String pictureFileName =  pictFile.getOriginalFilename();
			//新文件名称
			String newFileName = System.currentTimeMillis()+""+RandomNumUtil.getRandomNum("6");
			if (pictureFileName.lastIndexOf(".") != -1){
				newFileName = newFileName+pictureFileName.substring(pictureFileName.lastIndexOf("."));
			}
			//上传图片
			Image image = new Image();
			image.setName(newFileName);
			image.setImage(pictFile.getBytes());
			image.setCreateTime(new Date());
			int result = imageService.uploadImage(image);
			if (result==1) {
				logger.info("图片"+pictureFileName+"上传成功");
				Map<String, Object> data = new HashMap<>();
				data.put("error", 0);
				data.put("url", PropertyUtil.getInfo("imgUrl")+newFileName);
				return data;
			}else {
				logger.error("图片"+pictureFileName+"上传数据库失败");
				Map<String, Object> data = new HashMap<>();
				data.put("error", 0);
				data.put("message", "图片上传数据库失败");
				return data;
			}
		} catch (Exception e) {
			logger.error("图片上传异常");
			e.printStackTrace();
			Map<String, Object> data = new HashMap<>();
			data.put("error", 1);
			data.put("message", "图片上传异常");
			return data;
		}
    	
    } 
}
