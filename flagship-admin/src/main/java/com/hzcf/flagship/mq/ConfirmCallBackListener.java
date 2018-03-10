package com.hzcf.flagship.mq;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Service;

import com.hzcf.flagship.service.impl.MoneymgrJobServiceImpl;

@Service("confirmCallBackListener")
public class ConfirmCallBackListener implements ConfirmCallback{

	private final static Logger logger = Logger.getLogger(MoneymgrJobServiceImpl.class);

	/**
	 * Description: 监听消息确认 当ack 为 true时消息成功消费
	 * @param correlationData
	 * @param ack
	 * @param cause
	 * @author guodong
	 */
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		if(!ack){
			logger.info("=========消息进入交换机失败。cause："+cause);
		}
	}

}
