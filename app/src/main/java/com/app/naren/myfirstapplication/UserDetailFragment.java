package com.app.naren.myfirstapplication;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Naren on 4/20/2016.
 */
public class UserDetailFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup ViewGroup = (ViewGroup)inflater.inflate(R.layout.user_detailfragment, container, false);
        return ViewGroup;
    }
}
