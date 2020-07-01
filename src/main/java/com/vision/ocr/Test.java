package com.vision.ocr;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: xuruihan@zhicall.cn
 * @time: 2020/6/29 16:01
 */
public class Test {
	public static void main(String[] args) {
		String host = "https://tysbgpu.market.alicloudapi.com";
		String path = "/api/predict/ocr_general";
		String method = "POST";
		String appcode = "9059a29ca55d41ebb6913e2387c40774";
		Map<String, String> headers = new HashMap<>();
		//最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + appcode);
		//根据API的要求，定义相对应的Content-Type
		headers.put("Content-Type", "application/json; charset=UTF-8");
		Map<String, String> querys = new HashMap<>();
		String bodys = "{\"image\":\"https://www.helloimg.com/images/2020/06/29/D0EEAB40-B684-4db9-B3DA-B460DC21ED1244f41da95475c956.png\",\"configure\":{\"min_size\":16,\"output_prob\":true,\"output_keypoints\":false,\"skip_detection\":false,\"without_predicting_direction\":false}}";


		try {
			/**
			 * 重要提示如下:
			 * HttpUtils请从
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
			 * 下载
			 *
			 * 相应的依赖请参照
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
			 */
			HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
			System.out.println(response.toString());
			HttpEntity entity = response.getEntity();
			System.out.println(EntityUtils.toString(entity));

			//获取response的body
           //System.out.println(EntityUtils.toString(response.getEntity()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
