package com.ego.rpc.service;

import com.ego.common.result.BaseResult;

/**
 * 短信Service
 */
public interface SmsService {
	/**
	 * 发送短信
	 * @param phoneNum
	 * @return
	 */
	BaseResult sendSms(String phoneNum);
}
