package com.app.naren.myfirstapplication;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Created by Naren on 4/22/2016.
 */
public class UserDetailActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        setContentView(R.layout.activity_detail);
        UserDetailFragment detailFragment = (UserDetailFragment)getSupportFragmentManager().findFragmentById(R.id.user_detail_fragment);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Back");

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            detailFragment.setTextName(bundle.getString(UserMainActivity.USER_NAME));
            detailFragment.setTextEmail(bundle.getString(UserMainActivity.USER_EMAIL));
            detailFragment.setTextCreated(bundle.getString(UserMainActivity.USER_CREATED_AT));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
