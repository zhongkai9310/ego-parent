package com.ego.rpc.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.ego.common.result.BaseResult;
import com.ego.rpc.service.SmsService;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import org.springframework.stereotype.Component;

/**
 * 短信service实现类
 *
 * @author zhoubin
 * @create 2020/1/6
 * @since 1.0.0
 */
@Service(interfaceClass = SmsService.class)
@Component
public class SmsServiceImpl implements SmsService {

	/**
	 * 发送短信
	 * @param phoneNum
	 * @return
	 */
	@Override
	public BaseResult sendSms(String phoneNum) {
		try{
			//身份认证
			Credential cred = new Credential("AKIDD69Ynx96AGRkCU8aSUThaSDqU6bm2TKW", "IPJBD7tWyzulPxX8Zpur9wZ4RemjbNwL");
			//创建HttpProfile对象
			HttpProfile httpProfile = new HttpProfile();
			httpProfile.setEndpoint("sms.tencentcloudapi.com");
			//创建ClientProfile对象
			ClientProfile clientProfile = new ClientProfile();
			clientProfile.setHttpProfile(httpProfile);
			//选择指定区域
			SmsClient client = new SmsClient(cred, "ap-shanghai", clientProfile);
			//请求参数
			String params = "{\"PhoneNumberSet\":[\"86"+phoneNum+"\"],\"TemplateID\":\"490150\",\"Sign\":\"程序猿学习栈\"," +
					"\"SmsSdkAppid\":\"1400291704\"}";
			//执行请求
			SendSmsRequest req = SendSmsRequest.fromJsonString(params, SendSmsRequest.class);
			SendSmsResponse resp = client.SendSms(req);

			System.out.println(SendSmsRequest.toJsonString(resp));
			if ("Ok".equalsIgnoreCase(resp.getSendStatusSet()[0].getCode())){
				return BaseResult.success();
			}else {
				BaseResult.error();
			}
		} catch (TencentCloudSDKException e) {
			System.out.println(e.toString());
		}
		return BaseResult.error();
	}
}