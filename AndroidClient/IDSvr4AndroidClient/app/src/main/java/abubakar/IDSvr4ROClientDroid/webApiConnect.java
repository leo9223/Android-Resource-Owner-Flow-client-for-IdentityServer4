package abubakar.IDSvr4ROClientDroid;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Abubakar on 24-Feb-16.
 */
public class webApiConnect {
    String url = constants.AspNetWebApiSampleApi;


    public String callService(String access_token) {
        String result = "Exception Occured";
        try {

            URL obj = new URL(url + "identity");

//            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.104.0.46", 8888));
//
//            HttpURLConnection con = (HttpURLConnection) obj.openConnection(proxy);

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();


            con.setRequestMethod("GET");
//            con.setRequestProperty("User-Agent", "Oauth2Android");
//            con.setRequestProperty("Host", "10.0.2.2:3860");
            con.setRequestProperty("Authorization", "Bearer " + access_token);

            int responseCode = con.getResponseCode();

            if (responseCode < 400) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                result = response.toString();

            }
            else{
                result = con.getResponseMessage();
            }
        } catch (Exception e) {
            return "Error: " + result + "\n\nException: " + result;
        }
        return result;
    }
}


