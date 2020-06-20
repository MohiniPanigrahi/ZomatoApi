import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;

public class CollectionsTest extends TestBase {
	TestBase base;
	String url;
	HashMap<String, String> InputHeader = new HashMap<String, String>();
	CloseableHttpResponse response;
	
	@BeforeMethod
	public void setup()  throws ClientProtocolException, IOException{
		base = new TestBase();
		url = prop.getProperty("baseUrl")+prop.getProperty("collectionServiceUrl")+prop.getProperty("queryparams");
	}
	
	@Test
	public void getAPI() throws ClientProtocolException, IOException, URISyntaxException {
		RestClient client = new RestClient();
		InputHeader.put("user-key", "2e6e14130513cf65d615a5813b68b230");
		response = client.getResponsewithParams(InputHeader,url);
		Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
	}

}
