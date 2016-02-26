package com.example.abubakar.idsvr4androidclient;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Abubakar on 22-Feb-16.
 */


public class PostRequestTest extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params){

        try{

//            URL url = new URL("http://10.0.2.2:3860/api/values");
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.setRequestMethod("GET");
//            //urlConnection.setDoOutput(true);
//            urlConnection.connect();
//
//            int status = urlConnection.getResponseCode();
//
//
//            InputStream inputStream = urlConnection.getErrorStream();
//
//            String abc = "";


            String url = "http://10.0.2.2:22530/connect/token";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String charset = "UTF-8";
            String s = "grant_type=" + URLEncoder.encode("password", charset);
            s += "&client_id=" + URLEncoder.encode("roclient", charset);
            s += "&client_secret=" + URLEncoder.encode("secret", charset);
            s += "&username=" + URLEncoder.encode("bob", charset);
            s += "&password=" + URLEncoder.encode("bob", charset);
            s += "&scope=" + URLEncoder.encode("api1 offline_access", charset);

            String urlParameters = s;

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString());

        }
        catch (Exception e){
            String ex = e.getMessage();
        }
        return "";




    }
}
