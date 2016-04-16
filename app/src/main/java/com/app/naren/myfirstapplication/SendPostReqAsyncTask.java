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

    @Override
    protected String doInBackground(String... params) {
        String paramUsername = params[0];
        String paramPassword = params[1];
        String paramEmail = params[2];

        //System.out.println("*** doInBackground ** paramUsername " + paramUsername + " paramPassword :" + paramPassword+ " paramEmail :" + paramEmail);

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(params[3]);// replace with your url
        httpPost.addHeader("Content-type", "application/x-www-form-urlencoded");

        BasicNameValuePair usernameBasicNameValuePair = new BasicNameValuePair("name", paramUsername);  // Make your own key value pair
        BasicNameValuePair passwordBasicNameValuePAir = new BasicNameValuePair("password", paramPassword);// make your own key value pair
        BasicNameValuePair emailBasicNameValuePAir = new BasicNameValuePair("email", paramEmail);// make your own key value pair

        // You can add more parameters like above

        List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
        nameValuePairList.add(usernameBasicNameValuePair);
        nameValuePairList.add(passwordBasicNameValuePAir);
        nameValuePairList.add(emailBasicNameValuePAir);

        try {
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairList);
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