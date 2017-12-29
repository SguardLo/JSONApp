package com.example.user.jsonapp;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by User on 29/12/2017.
 */

public class FetchData extends AsyncTask {

    String data = "";
    String data2 = "";
    @Override
    protected Object doInBackground(Object[] params) {
        //establish URL
        //create HTTPUrlConnection
        //read bytes from json file
        try {
            URL url = new URL("https://api.myjson.com/bins/6c2mj");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            InputStream inputStream = http.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }

            //remove {}, []
            JSONArray JA = new JSONArray(data);
            for(int i=0; i < JA.length();i++){
                JSONObject JO = (JSONObject) JA.get(i);
                String data1 = "Name: " + JO.get("name") +
                                "\nAge: " + JO.get("age") +
                                "\nDOB: " + JO.get("dob") + "\n\n";
                data2 = data2 + data1;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        //display the result in textView
        MainActivity.textView1.setText(this.data2);
    }


}
