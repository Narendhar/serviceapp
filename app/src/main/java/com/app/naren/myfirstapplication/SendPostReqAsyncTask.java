package com.app.naren.myfirstapplication;

import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naren on 4/16/2016.
 */
class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

    public AsyncResponse delegate=null;
    public List<NameValuePair> params = new ArrayList<NameValuePair>();

    @Override
    protected String doInBackground(String... params) {

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(params[0]);// replace with your url
        httpPost.addHeader("Content-type", "application/x-www-form-urlencoded");

        try {
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(this.params);
            httpPost.setEntity(urlEncodedFormEntity);

            try {
                HttpResponse httpResponse = httpClient.execute(httpPost);
                InputStream inputStream = httpResponse.getEntity().getContent();
                InputStreamReader inputStreamReader = new InputStreamReader(
                        inputStream);
                BufferedReader bufferedReader = new BufferedReader(
                        inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();
                String bufferedStrChunk = null;
                while ((bufferedStrChunk = bufferedReader.readLine()) != null) {
                    stringBuilder.append(bufferedStrChunk);
                }

                return stringBuilder.toString();

            } catch (ClientProtocolException cpe) {
                System.out
                        .println("First Exception coz of HttpResponese :"
                                + cpe);
                cpe.printStackTrace();
            } catch (IOException ioe) {
                System.out
                        .println("Second Exception coz of HttpResponse :"
                                + ioe);
                ioe.printStackTrace();
            }

        } catch (UnsupportedEncodingException uee) {
            System.out
                    .println("An Exception given because of UrlEncodedFormEntity argument :"
                            + uee);
            uee.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish(result);
    }
}