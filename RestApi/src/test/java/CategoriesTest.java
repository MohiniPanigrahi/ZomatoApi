import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
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
	@Test   //Get and Verify headers
	public void getHeaders() {
		Header[] headerArray= response.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String,String>();
		for(Header header:headerArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
	}
	
	@Test
	public void getResponse() throws ParseException, IOException{
		JSONObject jObj = ResponseUtils.getJSONObject(response);
		JSONArray jArray = jObj.getJSONArray("categories");
		System.out.print("Array of Objects =" +jArray);
		for(int i =0; i<jArray.length(); i++) {
			String S = jArray.getJSONObject(i).getString("categories");
			System.out.println("Child entries"+ S);
		}
	}
}
