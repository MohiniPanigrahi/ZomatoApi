import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.utils.ResponseUtils;

public class CategoriesTest extends TestBase {
	TestBase baseclass;
	String url;
	CloseableHttpResponse response;
	HashMap<String, String> InputHeader = new HashMap<String, String>();
	
	@BeforeMethod
	public void setup()  throws ClientProtocolException, IOException{
		baseclass = new TestBase();
		url = prop.getProperty("baseUrl")+prop.getProperty("categoriesServiceurl");
		//url = "https://developers.zomato.com/api/v2.1/categories";
	}
	
	@Test  // Verify get call, service url and status code
	public void getAPI() throws ClientProtocolException, IOException {
		RestClient client = new RestClient();
		InputHeader.put("user-key", "2e6e14130513cf65d615a5813b68b230");
		response = client.getResponse(InputHeader,url);
		//Check for status code
		int Statuscode =response.getStatusLine().getStatusCode();
		Assert.assertEquals(Statuscode, 200);
	}
	
	@Test //Verify getcall with incorrect service url
	public void getAPIwithIncorrectURL() throws ClientProtocolException, IOException {
		RestClient client = new RestClient();
		String url1 = "https://developers.zomato.com/api/v2/categorie";
		InputHeader.put("user-key", "2e6e14130513cf65d615a5813b68b230");
		response = client.getResponse(InputHeader,url1);
		//Check for status code
		int Statuscode =response.getStatusLine().getStatusCode();
		Assert.assertEquals(Statuscode, 404);
	}
	
	@Test //Verify getcall with incorrect headers user-key
	public void getAPIwithIncorrectHeaders() throws ClientProtocolException, IOException {
		RestClient client = new RestClient();
		InputHeader.put("user-key", "2e6e14130513cf65d615a5813b");
		response = client.getResponse(InputHeader,url);
		//Check for status code
		int Statuscode =response.getStatusLine().getStatusCode();
		Assert.assertEquals(Statuscode, 403);
		JSONObject jObj = ResponseUtils.getJSONObject(response);
		String Msg = jObj.get("message").toString();
		Assert.assertEquals(Msg, "Invalid API Key");
	}
	
	@Test //Verify getcall without headers
	public void getAPIwithoutHeaders() throws ClientProtocolException, IOException {
		RestClient client = new RestClient();
		response = client.getResponse(InputHeader,url);
		//Check for status code
		int Statuscode =response.getStatusLine().getStatusCode();
		Assert.assertEquals(Statuscode, 403);
		JSONObject jObj = ResponseUtils.getJSONObject(response);
		String Msg = jObj.get("message").toString();
		Assert.assertEquals(Msg, "Invalid API Key");
	}
	
	@Test   //Get and Verify headers
	public void getHeaders() {
		Header[] headerArray= response.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String,String>();
		for(Header header:headerArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		String S = allHeaders.get("Access-Control-Allow-Headers").toString();
		Assert.assertEquals(S, "X-Zomato-API-Key");
	}
	
	@Test
	public void getResponse() throws ParseException, IOException{
		JSONObject jObj = ResponseUtils.getJSONObject(response);
		JSONArray jArray = jObj.getJSONArray("categories");
//		System.out.println("Array of Objects =" +jArray);
//		for(int i =0; i<jArray.length(); i++) {
//			JSONObject subObj = jArray.getJSONObject(i);
//			String S = subObj.get("categories").toString();
//			System.out.println("Child entries"+ S);
//		}
		JSONObject subObj = jArray.getJSONObject(0);
		JSONObject s1= (JSONObject) subObj.get("categories");
		String name = s1.getString("name");
		Assert.assertEquals(name, "Delivery");
		int id = s1.getInt("id");
		Assert.assertEquals(id, 1);
		
		//Get number odf rows
		int count = jArray.length();
		Assert.assertEquals(13, count);
	}
}
