import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.utils.RequestUtils;

public class CollectionsTest extends TestBase {
	TestBase base;
	String url;
	HashMap<String, String> InputHeader = new HashMap<String, String>();
	CloseableHttpResponse response;
	JSONParser jsonparse = new JSONParser();
	FileReader fr;
	JSONObject JObj;
	
	
	@BeforeMethod
	public void setup()  throws ClientProtocolException, IOException, URISyntaxException, ParseException{
		base = new TestBase();
		url = prop.getProperty("baseUrl")+prop.getProperty("collectionServiceUrl");
		fr = new FileReader(System.getProperty("user.dir")+"/src/main/java/com/qa/testdata/categoriesTestData.json");
		JObj = (JSONObject) jsonparse.parse(fr);
	}
	
	@Test 
	public void getAPI() throws ClientProtocolException, IOException, URISyntaxException{
		RestClient client = new RestClient();
		InputHeader.put("user-key", "2e6e14130513cf65d615a5813b68b230");
		JSONArray queryset = (JSONArray) JObj.get("queryset1");
		System.out.println("query Array "+queryset);
		url = RequestUtils.setqueryparams(url, queryset);
		response = client.getResponsewithParams(InputHeader,url);
		Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
	}

}
