package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {

	//Get method
	public CloseableHttpResponse getResponse(HashMap<String, String> header, String url) throws ClientProtocolException, IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		for(Map.Entry<String,String> entry:header.entrySet()) {
		get.addHeader(entry.getKey(),entry.getValue());
		}
		CloseableHttpResponse ResponseObj = (CloseableHttpResponse) client.execute(get);
		return ResponseObj;
	}
}
