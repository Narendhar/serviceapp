package com.app.naren.myfirstapplication;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Naren on 4/20/2016.
 */
public class UserDetailFragment extends Fragment {
    private  TextView textName,textEmail, textCreated;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup)inflater.inflate(R.layout.user_detailfragment, container, false);
        textName = (TextView) viewGroup.findViewById(R.id.user_name);
        textEmail = (TextView) viewGroup.findViewById(R.id.user_email);
        textCreated = (TextView) viewGroup.findViewById(R.id.user_created_at);
        return viewGroup;
    }

    public void setTextEmail(String userEmail) {
        textEmail.setText(userEmail);
    }

    public void setTextCreated(String userCreatedAt) {
        textCreated.setText(userCreatedAt);
    }

    public void setTextName(String userName) {
        textName.setText(userName);
    }
}
