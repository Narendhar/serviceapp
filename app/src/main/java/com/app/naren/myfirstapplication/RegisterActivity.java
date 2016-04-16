package com.app.naren.myfirstapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Naren on 4/12/2016.
 */
public class RegisterActivity extends Activity implements AsyncResponse {

    EditText password,userName,email;

    // URL to get contacts JSON
    //private static String url = "http://dev.apiservice.com:8080/user";
    private static String urlRegistration = "http://4aebc177.ngrok.io/user";

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

        String sName=userName.getText().toString();
        String sEmail=email.getText().toString();
        String sPassword=password.getText().toString();

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.delegate = this;

        sendPostReqAsyncTask.params.put("name", sName);
        sendPostReqAsyncTask.params.put("email", sEmail);
        sendPostReqAsyncTask.params.put("password", sPassword);

        sendPostReqAsyncTask.execute(urlRegistration);
    }
}