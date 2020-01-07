package com.ego.order.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016092800614468";

	// 商户私钥，您的PKCS8格式RSA2私钥
	public static String merchant_private_key =
			"MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCSVCeZMeVDPpaPTaDOuP3cU4851yR/eH6NYXi0cdFcUbv2" +
					"+/aMiS6aRVUMJZUx4LKxjmFe7vAPR5K3vH4q104ZdkpEaihjoQOHQLVDzuKvhXjDI54BuQ4GMJcNxlYQKyiyNlWk7d21aoHDiGe2WatttSnh6fl0FXxPXGDU32OycyeIzTf+Ax3KNT5ID7OdXjHK/Hvavt8VGYQ/ahC7EIDIwG0L8br051oV7e+0f2jbUAcsE6QT/veX/sq+gidaf75ywGQ0N7wkJoJE0HEw8F1zOAvXQLyYld1Z6qK+zqScf9NGANrQU3Lx7m3iOr9G+BmcEwnB0eflq22LGQ0tMo4hAgMBAAECggEAcaJktfUng1cBePvh22eRzR4e5d4Le+pI7OY65HZXDYhzndQBnRWTWT5qU2qEOB2052oYd666QkNkzsbUe9SD1AYmmoaOIwCRSuGXYrcxFvlh1Li+JbxeBbSKOapCf3/ktLC5RcFrIU06xGwTjmqIVEQR6FzY2astxDuK/wUT2Smi3PjHcJ4iDfaMbGdNL9Z3QL2lDPpJrB9r/VTILDZxACoN9TwlJSb0GUP5KMiokRo+DZ6Hc23Arsy2YXDJF0GgvMtEMPTXl1+1r1G4GZrirJ7M5Q21aae6Ga/HAUdSGrmtEviospORR6G4VWDUIrSVa/57Tap1frpLjtP5sreuGQKBgQDOPC1EZ2iwCAkbVC7sKlNL1S8dKIY2v+X4hEdcysfADscQyNbmebEJKw0cd0uBjONDCDHpQlf9a3c6evogGCO2/GZgV6k6gkpoKkqyr8joj1Sx+mBF7yr1YPlICRhd8kKwTphEh8daOU/cPJI2h2PBF4PCNX6ke0EnFvosJ2BEKwKBgQC1o11xJEWmEISDRgfIxYb8BBVFSo4YTMkb8Gj8RBJm5YTfpBBTSFuVIlRLCpIwt142opieETx67kKqdae0e/ry8AI5uOsjIuy0tmWimeMTl1VLYIAdiEOFoKpydNpN8DAequHsgzdw+5CD3ohN0Yh+iswlkwlbF1wb7xFYcWhU4wKBgQCiD0GXDheCtF3LnuRcaooX/gbOx+hnn3YsSm31add7gWFGPDw60gee57ie1NN9kuOG6iqmXJ7eIhOH3dktXSq7gS1FqbzuoDGuFwWp0l5y+SaZ0ODUM7OzLKfWxmbidBuZhbk7qOKrcHvj2akftlZaBotgEj9ZKw2wlOqzxaFtDwKBgGoO8NHOpyh3PpjUPFlcFRx0WeMyzFOeClPZBcQ/Dyur2vEnAC1Q88DZ4bRLgN8XQtjOCiVJ1THGi4Cy4HC/b+GIiRQhSJMkomhGlIPkLTZUSAaSW1xjI2IUVVItrwaRN9fAWdjbKXU8Pr7xIk+mCHgzHU7XU0AwHKVm647j8VKXAoGAUQG1zF38KEySI/DADhf1RVbqnsPQ04uehW+vCnlE+bxcPQYSoSDHIVR8YgpHAB2mo040wPb/gU8QbPrZlUa/DCzhsvmxkbsmgcCrlHwy/QNLZSJ2OWIbOf5gS8QyGQuHqTkZ80nTu1tp22GrnjRqy8rSL/6zeaTSAqbl/CneiUQ=";

	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
	public static String alipay_public_key =
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxYJbnlZc8GaNZPs96suFWyfcIjZBugJvMOLaPIBlQ/I2t2MD/X26Lye" +
					"+6emawehyh/axiD0gGsr1Y4y60S5AwVUxKTkg1NRZG8JSt6Wb0/uepZ5mjnoJJBYOCBf7oVXHTl3ZO1Wdk" +
					"+0nxQEEssokM3eCL7Y/LKssCW8fBk9XCXK2f5yAcSQapGp1XvJi9lUAP1ytXV" +
					"/AjO4mwRpgdM60aGnbcxB8bWaQkZFTsjMukpXSBi1+lCZqkgWQ7P92EfqRtu" +
					"+weFadbINfGz8Wqifaj3BUkO8IQ68Afy1YQpICYapQqZ/hq1nDAhegHkfF/1JP3ItE3IPlNCy+SXFyO90FJQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://vvqce5.natappfree.cc/ego-portal/order/callback";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:9092/ego-order/order/myOrder";

	// 签名方式
	public static String sign_type = "RSA2";

	// 字符编码格式
	public static String charset = "utf-8";

	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	/**
	 * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
	 *
	 * @param sWord 要写入日志里的文本内容
	 */
	public static void logResult(String sWord) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
			writer.write(sWord);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

