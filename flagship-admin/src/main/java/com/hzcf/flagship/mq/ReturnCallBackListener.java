package com.hzcf.flagship.mq;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.stereotype.Service;

import com.hzcf.flagship.service.impl.MoneymgrJobServiceImpl;

@Service("returnCallBackListener")
public class ReturnCallBackListener implements ReturnCallback {

	private final static Logger logger = Logger.getLogger(MoneymgrJobServiceImpl.class);

	@Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
		logger.info("消息进入队列："+routingKey+"失败。cause："+replyText);
	}

}
