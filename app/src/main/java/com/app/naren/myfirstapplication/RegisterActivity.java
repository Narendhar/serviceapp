package com.app.naren.myfirstapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Naren on 4/12/2016.
 */
public class RegisterActivity extends Activity implements AsyncResponse {

    EditText password,userName,email;

    // URL to get contacts JSON
    //private static String url = "http://dev.apiservice.com:8080/user";
    private static String url = "http://4aebc177.ngrok.io/user";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set View to register.xml
        setContentView(R.layout.register);

        TextView loginScreen = (TextView) findViewById(R.id.link_to_login);

        // Listening to Login Screen link
        loginScreen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // Closing registration screen
                // Switching to Login Screen/closing register screen
                finish();
            }
        });

        password=(EditText) findViewById(R.id.reg_password);
        userName=(EditText) findViewById(R.id.reg_fullname);
        email=(EditText) findViewById(R.id.reg_email);

    }

    public void processFinish(String output){
        Toast.makeText(getApplicationContext(), output,
                Toast.LENGTH_SHORT).show();
    }

    public void registerUser(View view) {

        String s1=userName.getText().toString();
        String s2=email.getText().toString();
        String s3=password.getText().toString();

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.delegate = this;
        sendPostReqAsyncTask.execute(s1,s2,s3,url);

        /* Working code */

        //sendPostRequest(s1,s2,s3);

        /*ServiceHandler sh = new ServiceHandler();

        List<NameValuePair> params = new ArrayList<NameValuePair>();

        EditText editName = (EditText) findViewById(R.id.reg_fullname);
        String name = editName.getText().toString();

        EditText editEmail = (EditText) findViewById(R.id.reg_email);
        String email = editEmail.getText().toString();

        EditText editPassword = (EditText) findViewById(R.id.reg_password);
        String password = editPassword.getText().toString();

        params.add(new BasicNameValuePair("name",
                name));
        params.add(new BasicNameValuePair("email",
                email));
        params.add(new BasicNameValuePair("password",
                password));

        // Making a request to url and getting response
        String jsonStr = sh.makeServiceCall(url, ServiceHandler.POST, params);

        Toast.makeText(getApplicationContext(), jsonStr,
                Toast.LENGTH_SHORT).show();*/
    }

    /*private void sendPostRequest(String givenUsername, String givenPassword, String givenEmail) {

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                String paramUsername = params[0];
                String paramPassword = params[1];
                String paramEmail = params[2];

                //System.out.println("*** doInBackground ** paramUsername " + paramUsername + " paramPassword :" + paramPassword+ " paramEmail :" + paramEmail);

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);// replace with your url
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
                    System.out.println(urlEncodedFormEntity);
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
                super.onPostExecute(result);
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(givenUsername, givenPassword, givenEmail);

    }*/

}