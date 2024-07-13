import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class CommonFunction {

    public int httpStatus(String url) throws IOException {
        String statement = "";
        URL imageURL = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) imageURL.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int code = connection.getResponseCode();

        if (code == 200) {
            statement = "Check property details page http status is 200(ok)|"+"true|http status is "+code+ "|URL : "+url+"|";
            CommonReport.CreateReport("result.txt", statement);
        }
        else  {
            statement = "Check property details page http status is 200(ok)|"+"false|http status is "+code+ "|URL : "+url+"|";
            CommonReport.CreateReport("result.txt", statement);
        }
        return code;
    }


    public String getData(String jsonData,String data,String url) throws ParseException, IOException {
        String value = "", statement = "";
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(jsonData);

        try {
            value = (json.get(data)).toString();
            statement = "Check property details page if all required field has data |true|"+data+" field has data for property id |"+url;
        }
        catch (NullPointerException p) {
            statement = "Check property details page if all required field has data|false|Data field is empty for property id |"+data+" field is empty|"+url;
        }
        CommonReport.CreateReport("result.txt", statement);
        return value;
    }


}
