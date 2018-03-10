package com.hzcf.flagship.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hzcf.flagship.service.PublishService;
import com.hzcf.flagship.service.RiskJobService;

@Controller
@RequestMapping("/mq")
public class MQTestController {

	 @Autowired  
	private PublishService publishService;  
	@Autowired
	private RiskJobService riskJobService;
	 /**
	  * 
	 * @Description: 手动执行 营业部 逾期明细数据上传 FastDfs
	 * @param     
	 * @return void
	 * @throws
	  */
	@RequestMapping("/sendF")
    public void sendF() {
    	publishService.send();    
    }
	
	 /**
	  * 
	 * @Description: 手动执行 营业部负责人发送风控数据
	 * @param     
	 * @return void
	 * @throws
	  */
	@RequestMapping("/sendR")
	   public void sendRiskDataEmail() {
		riskJobService.sendRiskDataEmail();    
	   }
	
	
	 /**
	  * 
	 * @Description: 手动执行 检查营业部是否分配分中心
	 * @param     
	 * @return void
	 * @throws
	  */
		@RequestMapping("/sendE")
	   public void sendEmailOfSubcenterIsNull() {
			riskJobService.sendEmailOfSubcenterIsNull();    
	   }
	
		/**
		  * 
		 * @Description: 手动执行 营业部 月邮件上传 FastDfs
		 * @param     
		 * @return void
		 * @throws
		  */
		@RequestMapping("/sendM")
	    public void sendM() {
			riskJobService.riskMonthEmailDataUpload();
	    }
}
