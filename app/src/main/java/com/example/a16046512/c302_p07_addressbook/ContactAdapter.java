package com.example.a16046512.c302_p07_addressbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

public class ContactAdapter extends ArrayAdapter<Contact> {
    private ArrayList<Contact> contact;
    private Context context;
    private TextView tvfirstname,tvlastname,tvmobilenumber,tvhomenumber;

    public ContactAdapter(Context context, int resource, ArrayList<Contact> objects){
        super(context, resource, objects);
        // Store the food that is passed to this adapter
        contact = objects;
        // Store Context object as we would need to use it later
        this.context = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // The usual way to get the LayoutInflater object to
        //  "inflate" the XML file into a View object
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // "Inflate" the row.xml as the layout for the View object
        View rowView = inflater.inflate(R.layout.row, parent, false);

        // Get the TextView object
        tvfirstname = (TextView) rowView.findViewById(R.id.tvfirstname);
        tvlastname = (TextView) rowView.findViewById(R.id.tvlastname);
        tvmobilenumber = (TextView) rowView.findViewById(R.id.tvmobilenum);
        tvhomenumber = (TextView) rowView.findViewById(R.id.tvhomenum);


        // The parameter "position" is the index of the
        //  row ListView is requesting.
        //  We get back the food at the same index.
        Contact currentContact = contact.get(position);
        // Set the TextView to show the food

        tvfirstname.setText("First Name: "+currentContact.getFirstname());
        tvlastname.setText("Last Name: "+currentContact.getLastname());
        tvmobilenumber.setText("Mobile Number: "+currentContact.getMobile());
        tvhomenumber.setText("Home Number: "+currentContact.getHome());

        // Return the nicely done up View to the ListView
        return rowView;
    }

}
