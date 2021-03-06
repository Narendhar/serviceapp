package com.app.naren.myfirstapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Naren on 4/16/2016.
 */
class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

    public AsyncResponse delegate=null;
    HashMap<String, String> params = new HashMap<String,String>();

    @Override
    protected String doInBackground(String... params) {
        String response = "";
        try{
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            if(params[1] == "POST") {
                connection.setDoOutput(true);
                connection.setRequestMethod(params[1]);
                OutputStream os = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getQuery(this.params));
                writer.flush();
                writer.close();
                os.close();
            }
            else if(params[1] == "GET") {
                InputStream in = connection.getInputStream();
                InputStreamReader isw = new InputStreamReader(in);

                int data = isw.read();
                while (data != -1) {
                    char current = (char) data;
                    response+= current;
                    data = isw.read();
                }
            }
            int responseCode=connection.getResponseCode();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response+=line;
                }
            } else {
                response="";
                throw new Exception(String.valueOf(responseCode));
            }
        } catch (MalformedURLException e) {
            // ...
        } catch (IOException e) {
            // writing exception to log
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private String getQuery(HashMap<String, String> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish(result);
    }
}