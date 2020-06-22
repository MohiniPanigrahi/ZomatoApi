package com.qa.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RequestUtils {

		public static String setqueryparams(String url, JSONArray inputArray) {
			for(int i=0; i<inputArray.size();i++) {
				if (i==0) {
					url.concat("?");
				}
				JSONObject jobj = (JSONObject) inputArray.get(i);
				System.out.println("first query  "+jobj );
				String queryname =  jobj.get("queryparams").toString();
				String queryValue=  jobj.get("value").toString();
				System.out.println(" query  "+queryname );
				System.out.println("value query  "+queryValue );
				String queryparam = queryname +"="+queryValue;
				url.concat(queryname);
				if(!(i==(inputArray.size()-1))) {
					url.concat("&");
				}
				System.out.println("String url "+ i+"  "+ url );
			}
			return url;
			
		}
		
		
}
