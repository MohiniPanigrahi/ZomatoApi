
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.utils.RequestUtils;
import com.qa.utils.ResponseUtils;

public class CollectionsTest extends TestBase {
	TestBase base;
	String serviceurl;
	HashMap<String, String> InputHeader = new HashMap<String, String>();
	CloseableHttpResponse response;
	JSONParser jsonparse = new JSONParser();
	FileReader fr;
	JSONObject JObj;
	
	
	@BeforeMethod
	public void setup()  throws ClientProtocolException, IOException, URISyntaxException, ParseException{
		base = new TestBase();
		serviceurl = prop.getProperty("baseUrl")+prop.getProperty("collectionServiceUrl");
		fr = new FileReader(System.getProperty("user.dir")+"/src/main/java/com/qa/testdata/CollectionsTestData.json");
		JObj = (JSONObject) jsonparse.parse(fr);
	}
	
	@Test //check get call with params combination of 2 cities
	public void getAPIwithsameParam() throws ClientProtocolException, IOException, URISyntaxException{
		RestClient client = new RestClient();
		InputHeader.put("user-key", "2e6e14130513cf65d615a5813b68b230");
		
		JSONArray queryset = (JSONArray) JObj.get("queryset1");
		String url= serviceurl + RequestUtils.setqueryparams(queryset);
		
		response = client.getResponsewithParams(InputHeader,url);
		Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
	}
	
	@Test // check get call with 2 different params
	public void getAPIwithDifferentParam() throws ClientProtocolException, IOException, URISyntaxException{
		RestClient client = new RestClient();
		InputHeader.put("user-key", "2e6e14130513cf65d615a5813b68b230");
		
		JSONArray queryset = (JSONArray) JObj.get("queryset2");
		String url= serviceurl + RequestUtils.setqueryparams(queryset);
		
		response = client.getResponsewithParams(InputHeader,url);
		Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
		
		org.json.JSONObject jObj = ResponseUtils.getJSONObject(response);
		org.json.JSONArray jArray = jObj.getJSONArray("collections");
		
		String getCount = jObj.get("has_total").toString();
		int count = jArray.length();
		Assert.assertEquals(getCount, count , "total count failed");
		
	}
	
	@Test //Get data from cities and put it in param value 
	public void getAPIdatafromOtherAPI() throws ClientProtocolException, IOException, URISyntaxException, ParseException{
		RestClient client = new RestClient();
		InputHeader.put("user-key", "2e6e14130513cf65d615a5813b68b230");
		
		CitiesTest city = new CitiesTest();
		String Cityid= city.getCityId();
		
		String url= serviceurl + RequestUtils.setparamvalue("city_id", Cityid);
		
		response = client.getResponsewithParams(InputHeader,url);
		Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
		
		org.json.JSONObject jObj = ResponseUtils.getJSONObject(response);
		org.json.JSONArray jArray = jObj.getJSONArray("collections");
		
		String responseCityurl = jObj.get("share_url").toString();
		System.out.println("share_url "+responseCityurl);
		int count = jArray.length();
		responseCityurl.contains("http://www.zoma.to/c-5/");
		Assert.assertTrue(responseCityurl.contains("http://www.zoma.to/c-5"), "City id present");
	}
	
	@Test // check with incorrect param values
	public void getAPIwithIIncorrectParamValues() throws ClientProtocolException, IOException, URISyntaxException{
		RestClient client = new RestClient();
		InputHeader.put("user-key", "2e6e14130513cf65d615a5813b68b230");
		
		JSONArray queryset = (JSONArray) JObj.get("queryset3");
		String url= serviceurl + RequestUtils.setqueryparams(queryset);
		
		response = client.getResponsewithParams(InputHeader,url);
		Assert.assertEquals(response.getStatusLine().getStatusCode(),500);
	}

}
