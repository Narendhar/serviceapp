package com.app.naren.myfirstapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Naren on 4/12/2016.
 */
public class SingleUserActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.user_item);

        TextView txtName = (TextView) findViewById(R.id.name);
        TextView txtEmail = (TextView) findViewById(R.id.email);
        TextView txtCreatedAt = (TextView) findViewById(R.id.created_at);

        Intent i = getIntent();

        // getting attached intent data
        String name = i.getStringExtra("name");
        String email = i.getStringExtra("email");
        String createdAt = i.getStringExtra("created_at");

        // displaying selected user

        txtName.setText(name);
        txtEmail.setText(email);
        txtCreatedAt.setText(createdAt);

    }
}
