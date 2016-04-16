package com.app.naren.myfirstapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Naren on 4/12/2016.
 */
public class SingleContactActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.list_item);

        TextView txtName = (TextView) findViewById(R.id.name);
        TextView txtEmail = (TextView) findViewById(R.id.email);
        TextView txtMobile = (TextView) findViewById(R.id.mobile);

        Intent i = getIntent();

        // getting attached intent data
        String name = i.getStringExtra("name");
        String email = i.getStringExtra("email");
        String mobile = i.getStringExtra("mobile");

        // displaying selected product name
        txtName.setText(name);
        txtEmail.setText(email);
        txtMobile.setText(mobile);

    }
}
