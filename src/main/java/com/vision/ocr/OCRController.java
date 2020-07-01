package com.vision.ocr;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: xuruihan@zhicall.cn
 * @time: 2020/6/29 16:46
 */
@Controller
@RequestMapping(value = "/OCR")
public class OCRController {

	@Value("${OCRappcode}")
	private String ocrAppCode;

	@PostMapping(value = "/wordOCR")
	@ResponseBody
	public String wordOCR(MultipartFile image){
		String bodys = "{\"image\":\""+image+",\"configure\":{\"min_size\":16,\"output_prob\":true,\"output_keypoints\":" +
				"false,\"skip_detection\":false,\"without_predicting_direction\":false}}";
		String host = "https://tysbgpu.market.alicloudapi.com";
		String path = "/api/predict/ocr_general";
		String method = "POST";
		Map<String, String> headers = new HashMap<>();
		//最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + ocrAppCode);
		//根据API的要求，定义相对应的Content-Type
		headers.put("Content-Type", "application/json; charset=UTF-8");
		Map<String, String> querys = new HashMap<>();

		try {
			HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
			return EntityUtils.toString(response.getEntity());
			//获取response的body
			//System.out.println(EntityUtils.toString(response.getEntity()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
