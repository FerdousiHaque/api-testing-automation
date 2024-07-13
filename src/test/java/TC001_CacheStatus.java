import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TC001_CacheStatus extends CommonFunction{
    @Test
    public void CacheStatusCheck() throws IOException, ParseException {
        String currentdir = System.getProperty("user.dir");
        BufferedReader brEnvironment = new BufferedReader(new FileReader(currentdir+"/textReport/environment.txt"));
        String flagName = brEnvironment.readLine(), urlFlag = "";

        if (flagName.equals("staging")) {
            urlFlag = "http://stage.com";
        } else if (flagName.equals("production")) {
            urlFlag = "https://prodution.com";
        }

        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = urlFlag;

        // Get the RequestSpecification of the request
        RequestSpecification httpRequest = RestAssured.given();

        // Make a request to the server by specifying the GET method
        Response response = httpRequest.request(Method.GET, "/property-details?is_test=0&language=en");
        String url = urlFlag+"/property-details?is_test=&language=en";
        String statement = "";

        // Header named X-Cache
        String x_cache_value = response.header("X-Cache");

        try {
            Assert.assertEquals(x_cache_value, "Miss from cloudfront");
            statement = "Check X-Cache value status from cloudfront|"+"true|Always shows Miss from cloudfront for 1st hit|"+url;
        }
        catch (java.lang.AssertionError e) {
            statement = "Check X-Cache value status from cloudfront|"+"false|Doesn't show Miss from cloudfront for 1st hit|"+e.getLocalizedMessage()+"|"+url;
        }

        CommonReport.CreateReport("result.txt", statement);

        // check status code and verify required key
        httpStatus(url);
        getData(response.body().asString(), "key", url);

    }
}
