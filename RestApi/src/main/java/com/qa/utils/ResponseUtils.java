package com.qa.utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class ResponseUtils {
	
	public static JSONObject getJSONObject(CloseableHttpResponse response) throws ParseException, IOException {
		HttpEntity entity = response.getEntity();
		String resp = EntityUtils.toString(entity,"UTF-8");
		JSONObject jObj = new JSONObject(resp);
		return jObj;
	}

}
