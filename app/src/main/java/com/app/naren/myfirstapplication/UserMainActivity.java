package com.app.naren.myfirstapplication;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Naren on 4/20/2016.
 */
public class UserMainActivity extends FragmentActivity implements HandleCallBack{

    final static String USER_NAME = "name";
    final static String USER_EMAIL = "email";
    final static String USER_CREATED_AT = "created_at";

    @Override
    public void loadUserDetail(HashMap<String, String> userInfo) {

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            UserDetailFragment userDetailFragment = (UserDetailFragment)getSupportFragmentManager().findFragmentById(R.id.user_detail_fragment);

            userDetailFragment.setTextName(userInfo.get("name"));
            userDetailFragment.setTextEmail(userInfo.get("email"));
            userDetailFragment.setTextCreated(userInfo.get("created_at"));
        }
        else {
            Intent intent = new Intent(this, UserDetailActivity.class);
            intent.putExtra(USER_NAME, userInfo.get("name"));
            intent.putExtra(USER_EMAIL, userInfo.get("email"));
            intent.putExtra(USER_CREATED_AT, userInfo.get("created_at"));
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_main);
    }
}
