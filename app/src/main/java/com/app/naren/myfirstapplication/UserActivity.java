package com.app.naren.myfirstapplication;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Naren on 4/18/2016.
 */
public class UserActivity extends ListActivity implements AsyncResponse {

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> userList;

    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_USERS = "users";
    private static final String TAG_CREATED_AT = "created_at";


    // contacts JSONArray
    JSONArray users = null;

    private ProgressDialog pDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        userList = new ArrayList<HashMap<String, String>>();

        ListView lv = getListView();

        // Listview on item click listener
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                String name = ((TextView) view.findViewById(R.id.name))
                        .getText().toString();
                String email = ((TextView) view.findViewById(R.id.email))
                        .getText().toString();
                String createdAt = ((TextView) view.findViewById(R.id.created_at))
                        .getText().toString();

                // Starting single contact activity
                Intent in = new Intent(getApplicationContext(),
                        SingleUserActivity.class);
                in.putExtra(TAG_NAME, name);
                in.putExtra(TAG_EMAIL, email);
                in.putExtra(TAG_CREATED_AT, createdAt);

                startActivity(in);

            }
        });


        // Showing progress dialog
        pDialog = new ProgressDialog(UserActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.delegate = this;
        sendPostReqAsyncTask.execute(Constants.service_baseUrl + Constants.service_url_user_list, "GET");

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
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("ServiceHandler", "Couldn't get any data from the url");
        }

        if (pDialog.isShowing())
            pDialog.dismiss();

        /**
         * Updating parsed JSON data into ListView
         * */
        ListAdapter adapter = new SimpleAdapter(
                UserActivity.this, userList,
                R.layout.user_item, new String[] { TAG_NAME, TAG_EMAIL }, new int[] { R.id.name,
                R.id.email});

        setListAdapter(adapter);
    }





}
