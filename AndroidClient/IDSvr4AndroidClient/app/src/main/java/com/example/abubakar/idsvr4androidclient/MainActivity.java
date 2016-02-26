package com.example.abubakar.idsvr4androidclient;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;

import abubakar.IDSvr4ROClientDroid.jsonManipulation;
import abubakar.IDSvr4ROClientDroid.token;
import abubakar.IDSvr4ROClientDroid.webApiConnect;

public class MainActivity extends AppCompatActivity {
    //ProgressBar pb;
    ProgressDialog pd;
    token objTkn;
    AlertDialog.Builder alertDialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // ALERT DIALOG INIT
        //alertDialogBuilder = new AlertDialog.Builder(this);

        // TOKEN OBJECT INIT
        objTkn = new token();


        // GET PROGRESS BAR
        //pb = (ProgressBar) findViewById(R.id.pbar);
        pd = new ProgressDialog(MainActivity.this);

        // TOKEN ACCESS BUTTON EVENT HANDLER
        Button _atbtn = (Button) findViewById(R.id.atbtn);
        _atbtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        new MainActivityAsyncTask().execute("getAccessToken");
                    }
                }
        );

        // TOKEN REFRESH BUTTON EVENT HANDLER
        Button _rtbtn = (Button) findViewById(R.id.rtbtn);
        _rtbtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        new MainActivityAsyncTask().execute("getRefreshToken");
                    }
                }
        );

        // CALL API BUTTON EVENT HANDLER
        Button _cabtn = (Button) findViewById(R.id.cabtn);
        _cabtn.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        new MainActivityAsyncTask().execute("CallUnSecureApi");
                    }
                }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    class MainActivityAsyncTask extends AsyncTask<String, String, String[]> {

        @Override
        protected void onPreExecute() {
            //pb.setVisibility(ProgressBar.VISIBLE);
            pd.show();
        }


        @Override
        protected String[] doInBackground(String... params) { //params[0] FOR METHOD NAME
            String result[] = new String[2];
            result[0] = params[0];

            switch (result[0]) {
                case "getAccessToken":
                    publishProgress("Getting access token...");
                    result[1] = objTkn.getAccessToken("roclient", "secret", "bob", "bob", "api1 api2 offline_access");

                    break;
                case "getRefreshToken":
                    if (objTkn.getRefreshToken() != "") {
                        publishProgress("Getting refresh token...");
                        result[1] = objTkn.getRefreshToken("roclient", "secret", objTkn.getRefreshToken());
                    }
                    break;
                case "CallUnSecureApi":
                    publishProgress("Calling Service...");
                    webApiConnect objApi = new webApiConnect();

                    result[1] = objApi.callService(objTkn.getAccessToken());


                    break;
                default:
                    result[1] = "Invalid method call";
                    break;
            }

            return result;
        }

        @Override
        protected void onProgressUpdate(String... progress) {
            pd.setMessage(progress[0]);
        }

        @Override
        protected void onPostExecute(String result[]) {
            //pb.setVisibility(ProgressBar.INVISIBLE);
            if (result[0] == "getAccessToken" || result[0] == "getRefreshToken") {
                objTkn.setRefreshToken(jsonManipulation.getAttrFromJson(result[1], "refresh_token"));

            }



            TextView _resulttxt = (TextView) findViewById(R.id.resulttxt);
            _resulttxt.setText(result[1]);
            pd.hide();
        }
    }
}

