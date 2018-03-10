package com.hzcf.flagship.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hzcf.flagship.dao.RiskOrgStructMapper;
import com.hzcf.flagship.dao.RiskOverdueMapper;
import com.hzcf.flagship.service.PublishService;
import com.hzcf.flagship.util.DateTimeUtil;
import com.hzcf.flagship.util.PropertyUtil;

/**
 * 
 *<dl>
 *<dt>类名：PublishServiceImpl.java</dt>
 *<dd>描述: ~节点业务逻辑实现</dd> 
 *<dd>创建时间： 2017年12月20日 下午4:02:18</dd>
 *<dd>创建人： GuoDong</dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2017年12月20日 下午4:02:18    GuoDong       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Service
public class PublishServiceImpl implements PublishService {

	 @Autowired  
	 private AmqpTemplate amqpTemplate; 
	 @Autowired
	 private RiskOverdueMapper riskOverdueMapper;
	 @Autowired
	 private RiskOrgStructMapper riskOrgStructMapper;
	 //营业部逾期数据上传到FastDFS队列名
	 private String rabbitmq_risk_queueName = PropertyUtil.getInfo("rabbitmq.risk.queueName");
	 //交换机
	 private String rabbitmq_risk_exchange = PropertyUtil.getInfo("rabbitmq.risk.exchange");
	 
	 private final Log logger = LogFactory.getLog(getClass());
	 @Override
		public void send() {
			Map<String, Object> map = new HashMap<String, Object>();
			String yesterdayDateString = DateTimeUtil.getYesterdayDateString();
			map.put("recordDate", yesterdayDateString);
			List<Map<String, Object>> resultList = riskOverdueMapper.findOverdueOrgNOByRecordDate(map);
			if(null != resultList && resultList.size()>0){
				for (Map<String, Object> map2 : resultList) {
					//根据机构编号查询 f_risk_org_data表中的逾期账龄
					Map<String, Object> requestMap = new HashMap<String,Object>();
					requestMap.put("org_no", map2.get("org_no"));
					List<Map<String, Object>> findDetailByOrgNo = riskOrgStructMapper.findDetailByOrgNo(requestMap);
					if(null != findDetailByOrgNo && findDetailByOrgNo.size()>0){
						if(null != findDetailByOrgNo.get(0).get("principal_send_aging")){
							Integer principalSendAging =  Integer.valueOf(String.valueOf(findDetailByOrgNo.get(0).get("principal_send_aging")));
							map2.put("principalSendAging", principalSendAging);
						}else{
							map2.put("principalSendAging", 0);
						}
						map2.put("recordDate", yesterdayDateString);
						String jsonString = JSON.toJSONString(map2);
						amqpTemplate.convertAndSend(rabbitmq_risk_exchange, rabbitmq_risk_queueName, jsonString);
						logger.info("发送上传文件消息:"+jsonString);
					}else{
						logger.info("f_risk_org_data表中没有:"+ map2.get("org_no")+"记录");
					}
				}
			}else {
				logger.info(yesterdayDateString + "没有逾期营业部");
			}
		}

}
