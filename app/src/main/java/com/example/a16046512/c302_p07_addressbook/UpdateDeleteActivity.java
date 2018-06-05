package com.example.a16046512.c302_p07_addressbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class UpdateDeleteActivity extends AppCompatActivity {
    private EditText etFirstName, etLastName, etMobile;
    private Button btnUpdate, btnDelete;
    int theid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        etFirstName = findViewById(R.id.etUDFirstName);
        etLastName = findViewById(R.id.etUDLastName);
        etMobile = findViewById(R.id.etUDMobile);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        Intent i = getIntent();
        theid = i.getIntExtra("id",0);
        Log.i("id",theid+"");



        // Code for step 1 start
        String url = "http://10.0.2.2/C302_CloudAddressBook/getContactById.php?id=" + theid;
        HttpRequest request = new HttpRequest(url);
        request.setOnHttpResponseListener(mHttpResponseListener);
        request.setMethod("GET");
        request.execute();
        // Code for step 1 end


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code for step 1 start
                String url = "http://10.0.2.2/C302_CloudAddressBook/UpdateContactById.php";
                HttpRequest request = new HttpRequest(url);
                request.setOnHttpResponseListener(mHttpResponseListener2);
                request.setMethod("POST");
                request.addData("id",theid+"");
                request.addData("firstname", etFirstName.getText().toString());
                request.addData("lastname", etLastName.getText().toString());
                request.addData("mobile", etMobile.getText().toString());
                request.execute();
                // Code for step 1 end
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://10.0.2.2/C302_CloudAddressBook/deleteContact.php";
                HttpRequest request = new HttpRequest(url);
                request.setOnHttpResponseListener(mHttpResponseListener3);
                request.setMethod("POST");
                request.addData("id",theid+"");
                request.execute();
                // Code for step 1 end
            }
        });

    }


    // Code for step 2 start
    private HttpRequest.OnHttpResponseListener mHttpResponseListener =
            new HttpRequest.OnHttpResponseListener() {
                @Override
                public void onResponse(String response) {

//                    Log.i("JSON Results: ", response);
                    // process response here
                    // process response here
                    try {
//                        Log.i("JSON Results: ", response);

                        JSONObject jsonObj = new JSONObject(response);

                        int id = jsonObj.getInt("id");
                        String firstname = jsonObj.getString("firstname");
                        String lastname = jsonObj.getString("lastname");
                        String mobile = jsonObj.getString("mobile");
                        etFirstName.setText(firstname);
                        etLastName.setText(lastname);
                        etMobile.setText(mobile);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            };

    // Code for step 2 start
    private HttpRequest.OnHttpResponseListener mHttpResponseListener2 =
            new HttpRequest.OnHttpResponseListener() {
                @Override
                public void onResponse(String response) {

                    Log.i("JSON Results: ", response);
                    // process response here
                    try {
                        Log.i("JSON Results: ", response);

                        JSONObject jsonObj = new JSONObject(response);
                        if(jsonObj.getString("status") == "true"){
                            Toast.makeText(getApplicationContext(), "Contact record is updated successfully", Toast.LENGTH_SHORT).show();
                            Intent i  = new Intent(UpdateDeleteActivity.this,MainActivity.class);
                            startActivity(i);
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            };

    // Code for step 2 start
    private HttpRequest.OnHttpResponseListener mHttpResponseListener3 =
            new HttpRequest.OnHttpResponseListener() {
                @Override
                public void onResponse(String response) {

                    // process response here
                    try {
                        Log.i("JSON Results: ", response);

                        JSONObject jsonObj = new JSONObject(response);
                        if (jsonObj.getString("success") == "true"){

                            Toast.makeText(getApplicationContext(), "Contact record is deleted successfully", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(UpdateDeleteActivity.this,MainActivity.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(getApplicationContext(), "Deleted Fail", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            };
}
