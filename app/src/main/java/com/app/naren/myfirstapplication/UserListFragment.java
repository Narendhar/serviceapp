package com.app.naren.myfirstapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Naren on 4/20/2016.
 */
public class UserListFragment extends Fragment implements AsyncResponse, AdapterView.OnItemClickListener {

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> userList = new  ArrayList<HashMap<String, String>>();
    ArrayList<String> userNameList = new ArrayList<String>();

    ArrayAdapter<String> adapter;
    ListView userListView;
    ViewGroup viewGroup;

    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_USERS = "users";
    private static final String TAG_CREATED_AT = "created_at";

    private HandleCallBack handleCallBack;

    // contacts JSONArray
    JSONArray users = null;

    private ProgressDialog pDialog;

    private String[] userNames = new String[] {"User 1", "User 2", "User 3"};

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HandleCallBack) {
            handleCallBack = (HandleCallBack)context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Showing progress dialog
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.delegate = this;
        sendPostReqAsyncTask.execute(Constants.service_baseUrl + Constants.service_url_user_list, "GET");

        viewGroup = (ViewGroup)inflater.inflate(R.layout.user_listfragment, container, false);
        userListView = (ListView) viewGroup.findViewById(R.id.userlist);

        return viewGroup;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        handleCallBack.loadUserDetail(userList.get(position));
    }

    public void processFinish(String output){
        if (output != null) {
            try {
                JSONObject jsonObj = new JSONObject(output);

                // Getting JSON Array node
                users = jsonObj.getJSONArray(TAG_USERS);

                // looping through All Contacts
                for (int i = 0; i < users.length(); i++) {
                    JSONObject c = users.getJSONObject(i);

                    String id = c.getString(TAG_ID);
                    String name = c.getString(TAG_NAME);
                    String email = c.getString(TAG_EMAIL);
                    String created_at = c.getString(TAG_CREATED_AT);


                    // tmp hashmap for single contact
                    HashMap<String, String> user = new HashMap<String, String>();

                    // adding each child node to HashMap key => value
                    user.put(TAG_ID, id);
                    user.put(TAG_NAME, name);
                    user.put(TAG_EMAIL, email);
                    user.put(TAG_CREATED_AT, created_at);

                    // adding contact to contact list
                    userList.add(user);

                    userNameList.add(name);
                }

                if (pDialog.isShowing())
                    pDialog.dismiss();

                adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, userNameList);
                userListView.setOnItemClickListener(this);
                userListView.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {
            if (pDialog.isShowing())
                pDialog.dismiss();
            Log.e("ServiceHandler", "Couldn't get any data from the url");
        }
    }
}