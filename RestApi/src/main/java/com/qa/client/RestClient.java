package com.qa.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpMessage;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.HttpConnectionParams;

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
	
	@SuppressWarnings("deprecation")
	public CloseableHttpResponse getResponsewithParams(HashMap<String, String> header,String url) throws ClientProtocolException, IOException, URISyntaxException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
//		HashMap<String,String> params = new HashMap<String,String>();
//		params.put("city_id","1");
		
		//URIBuilder builder = new URIBuilder(url);
	//	builder.setQuery("city_id=1");
	//	for (String name : params.keySet()) {
	  //      builder.addParameter("city_id","1");
	//    }
	   // url = builder.build().toString();
		
		 
	    for(Map.Entry<String,String> entry:header.entrySet()) {
			get.addHeader(entry.getKey(),entry.getValue());
		}
		
		System.out.print("Url" +url);
		CloseableHttpResponse ResponseObj = (CloseableHttpResponse) client.execute(get);
		return ResponseObj;
		
	}
}
