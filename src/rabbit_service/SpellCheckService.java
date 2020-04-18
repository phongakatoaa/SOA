package rabbit_service;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpellCheckService {
    private static final String API_KEY_1 = "f28182994bab48c0b8f17d88938cb865";
    //private static final String API_KEY_2 = "c27dbe612070440ca7005edd4b0b3a06";
    private static final String API_HEADER = "Ocp-Apim-Subscription-Key";
    private static final String ENDPOINT = "https://api.cognitive.microsoft.com/bing/v7.0/spellcheck";
    private static final String MODE = "proof";
    private static final String COUNTRY = "en-US";

    public static String checkSpelling(String text) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost request = new HttpPost(ENDPOINT + "?mkt=" + COUNTRY + "&mode=" + MODE);
            request.setHeader(API_HEADER, API_KEY_1);
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/x-www-form-urlencoded");

            List<NameValuePair> nameValuePairs = new ArrayList<>();
            nameValuePairs.add(new BasicNameValuePair("text", text));

            request.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            CloseableHttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    public static void main(String[] args) {
        String response = checkSpelling("Coffeee");
        System.out.println(response);
    }
}
