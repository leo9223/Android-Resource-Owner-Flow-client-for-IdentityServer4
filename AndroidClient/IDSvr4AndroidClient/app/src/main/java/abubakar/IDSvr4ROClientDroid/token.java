package abubakar.IDSvr4ROClientDroid;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Abubakar on 23-Feb-16.
 */
public class token {

    private String accessToken;
    private String refreshToken;

    public token() {
        accessToken = "";
        refreshToken = "";
    }

    public void setAccessToken(String _accessToken){
        accessToken = _accessToken;
    }
    public void setRefreshToken(String _refreshToken){
        refreshToken = _refreshToken;
    }

    public String getAccessToken(){
        return accessToken;
    }
    public String getRefreshToken(){
        return refreshToken;
    }


    public String getAccessToken(String _clientId, String _clientSecret, String _userName, String _userPassword, String _scope) {
        String result = "";
        try {
            String url = constants.TokenEndpoint;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            //add parameters
            String charset = "UTF-8";
            String urlParameters = "grant_type=" + URLEncoder.encode("password", charset);
            urlParameters += "&client_id=" + URLEncoder.encode(_clientId, charset);
            urlParameters += "&client_secret=" + URLEncoder.encode(_clientSecret, charset);
            urlParameters += "&username=" + URLEncoder.encode(_userName, charset);
            urlParameters += "&password=" + URLEncoder.encode(_userPassword, charset);
            urlParameters += "&scope=" + URLEncoder.encode(_scope, charset);

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            // Get response code
            int responseCode = con.getResponseCode();

            if(responseCode < 400){
                // Response to String
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();


                setAccessToken((new JSONObject(response.toString())).getString("access_token"));

                result = response.toString();
            }
            else {
                result = con.getResponseMessage();
            }


            // Return response (token)
            return result;

        } catch (Exception e) {
            return "Error: " + result + "\n\nException: " +e.getMessage();
        }
    }

    public String getRefreshToken(String _clientId, String _clientSecret, String _refreshToken) {
        String result = "";
        try {
            String url = constants.TokenEndpoint;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");


            //add parameters
            String charset = "UTF-8";
            String urlParameters = "refresh_token=" + URLEncoder.encode(_refreshToken, charset);
            urlParameters += "&client_id=" + URLEncoder.encode(_clientId, charset);
            urlParameters += "&client_secret=" + URLEncoder.encode(_clientSecret, charset);
            urlParameters += "&grant_type=" + URLEncoder.encode("refresh_token", charset);
            urlParameters += "&scope=" + URLEncoder.encode("api1 offline_access", charset);

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            // Get response code
            int responseCode = con.getResponseCode();



            if(responseCode < 400){
                // Response to String
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
            else {
                result = con.getResponseMessage();
            }


            // Return response (token)
            return result;

        } catch (Exception e) {
            return "Error: " + result + "\n\nException: " +e.getMessage();
        }
    }

}
