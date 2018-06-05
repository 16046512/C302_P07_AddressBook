package com.example.a16046512.c302_p07_addressbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ListView lv;
    ArrayAdapter aa;
    ArrayList<Contact> contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lv = (ListView)findViewById(R.id.lv);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contact selectedContact = contact.get(i);
                //toast
                Toast.makeText(MainActivity.this,selectedContact.getId()+"",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this,UpdateDeleteActivity.class);
                intent.putExtra("id",selectedContact.getId());
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.createcontact){
            Intent i = new Intent(MainActivity.this,CreateActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();


                // Code for step 1 start
                String url = "http://10.0.2.2/C302_CloudAddressBook/getContactDetails.php";

                HttpRequest request = new HttpRequest(url);
                request.setOnHttpResponseListener(mHttpResponseListener);
                request.setMethod("GET");
                request.execute();
                // Code for step 1 end
    }



    // Code for step 2 start
    private HttpRequest.OnHttpResponseListener mHttpResponseListener =
            new HttpRequest.OnHttpResponseListener() {
                @Override
                public void onResponse(String response) {
                    lv = (ListView)findViewById(R.id.lv);
                    contact = new ArrayList<Contact>();


                    // process response here
                    try {
                        Log.i("JSON Results: ", response);

                        JSONArray jsonArr = new JSONArray(response);

                        for (int i = 0 ; i < jsonArr.length();i++){
                            JSONObject jsonObj = jsonArr.getJSONObject(i);
                            int id = jsonObj.getInt("id");
                            String firstname = jsonObj.getString("firstname");
                            String lastname = jsonObj.getString("lastname");
                            String home = jsonObj.getString("home");
                            String mobile = jsonObj.getString("mobile");
                            String address = jsonObj.getString("address");
                            contact.add(new Contact(id,firstname,lastname,home,mobile));
                        }
                        aa = new ContactAdapter(MainActivity.this, R.layout.row, contact);
                        lv.setAdapter(aa);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }







                }

            };
}

