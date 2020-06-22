package com.qa.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RequestUtils {

		public static String setqueryparams(JSONArray inputArray) {
			StringBuilder uriBuilder = new StringBuilder();
	        uriBuilder.append("?");
	        for (Object jsonObject : inputArray) {
	            JSONObject queryParam = (JSONObject) jsonObject;
	            uriBuilder
	                    .append(queryParam.get("param"))
	                    .append("=")
	                    .append(queryParam.get("value"))
	                    .append("&");
	        }
	        uriBuilder.setLength(uriBuilder.length() - 1); //removes the last & character
	        return uriBuilder.toString();
	    }
		
		public static String setparamvalue(String param, String value) {
			StringBuilder uriBuilder = new StringBuilder();
			uriBuilder.append("?").append(param).append("=").append(value);
			return uriBuilder.toString();
			
		}
			
}
		
