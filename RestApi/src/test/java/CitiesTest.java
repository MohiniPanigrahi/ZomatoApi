import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.utils.RequestUtils;
import com.qa.utils.ResponseUtils;

public class CitiesTest extends TestBase{
	TestBase baseclass;
	String url,serviceurl;
	CloseableHttpResponse response;
	HashMap<String, String> InputHeader = new HashMap<String, String>();
	FileReader fr;
	org.json.simple.JSONObject JObj;
	JSONParser jsonparse = new JSONParser();
	
	@BeforeMethod
	public void setup()  throws ClientProtocolException, IOException, ParseException{
		baseclass = new TestBase();
		serviceurl = prop.getProperty("baseUrl")+"/cities";
		fr = new FileReader(System.getProperty("user.dir")+"/src/main/java/com/qa/testdata/CitiesTestData.json");
		JObj = (org.json.simple.JSONObject) jsonparse.parse(fr);
	}
	
	public String getCityId() throws ClientProtocolException, IOException, ParseException {
		this.setup();
		RestClient client = new RestClient();
		InputHeader.put("user-key", "2e6e14130513cf65d615a5813b68b230");
		
		JSONArray queryset = (JSONArray) JObj.get("queryset1");
		String url= serviceurl + RequestUtils.setqueryparams(queryset);
		
		response = client.getResponse(InputHeader,url);
		JSONObject jObj = ResponseUtils.getJSONObject(response);
		
		org.json.JSONArray jArray = jObj.getJSONArray("location_suggestions");
		JSONObject subObj = (JSONObject) jArray.getJSONObject(0);
		String s1 = subObj.get("id").toString();
		return s1;
	}
}
