package com.sud.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;




public class HomeActivity extends AppCompatActivity {

    private TextView tc1;
    private TextView td1;
    private TextView tr1;
    private TextView nc1;
    private TextView se1;
    private TextView dt1;

    private TextView tc2;
    private TextView td2;
    private TextView tr2;
    private TextView nc2;
    private TextView se2;
    private TextView dt2;
    private TextView help;

    private ImageView sy;
    private ImageView pr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tc1 = findViewById(R.id.tc1);
        td1 = findViewById(R.id.td1);
        tr1 = findViewById(R.id.tr1);
        nc1 = findViewById(R.id.nc1);
        se1 = findViewById(R.id.se1);
        dt1 = findViewById(R.id.dt1);

        tc2 = findViewById(R.id.tc2);
        td2 = findViewById(R.id.td2);
        tr2 = findViewById(R.id.tr2);
        nc2 = findViewById(R.id.nc2);
        se2 = findViewById(R.id.se2);
        dt2 = findViewById(R.id.dt2);

        sy=findViewById(R.id.imageView5);
        pr=findViewById(R.id.imageView6);
        help=findViewById(R.id.help);

        DownloadTask task = new DownloadTask();
        task.execute("https://thevirustracker.com/free-api?global=stats");

        DownloadTask1 task1 = new DownloadTask1();
        task1.execute("https://thevirustracker.com/free-api?countryTotal=IN");

        sy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, symptoms.class);
                startActivity(intent);

            }
        });

        pr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, pre.class);
                startActivity(intent);

            }
        });
    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {

                    char current = (char) data;

                    result += current;

                    data = reader.read();

                }

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);


           try {

                JSONObject jsonObject = new JSONObject(result);

                String result1 = jsonObject.getString("results");

                Log.i("result", result1);

                JSONArray arr = new JSONArray(result1);

                for (int i = 0; i < arr.length(); i++) {

                    JSONObject jsonPart = arr.getJSONObject(i);

                    //Log.i("total_cases", jsonPart.getString("total_cases"));
                    //Log.i("total_recovered", jsonPart.getString("total_recovered"));
                    tc1.setText(jsonPart.getString("total_cases"));
                    tr1.setText(jsonPart.getString("total_recovered"));
                    td1.setText(jsonPart.getString("total_deaths"));
                    nc1.setText(jsonPart.getString("total_new_cases_today"));
                    se1.setText(jsonPart.getString("total_serious_cases"));
                    dt1.setText(jsonPart.getString("total_new_deaths_today"));


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }



        }
    }

    public class DownloadTask1 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {

                    char current = (char) data;

                    result += current;

                    data = reader.read();

                }

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);


            try {

                JSONObject jsonObject = new JSONObject(result);

                String result1 = jsonObject.getString("countrydata");

                Log.i("result", result1);

                JSONArray arr = new JSONArray(result1);

                for (int i = 0; i < arr.length(); i++) {

                    JSONObject jsonPart = arr.getJSONObject(i);

                    Log.i("total_cases", jsonPart.getString("total_cases"));
                    Log.i("total_recovered", jsonPart.getString("total_recovered"));


                    tc2.setText(jsonPart.getString("total_cases"));
                    tr2.setText(jsonPart.getString("total_recovered"));
                    td2.setText(jsonPart.getString("total_deaths"));
                    nc2.setText(jsonPart.getString("total_new_cases_today"));
                    se2.setText(jsonPart.getString("total_serious_cases"));
                    dt2.setText(jsonPart.getString("total_new_deaths_today"));


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
}
