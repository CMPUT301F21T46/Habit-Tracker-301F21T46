package com.example.habit_tracker_301f21t46;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AllUsersAdapter extends ArrayAdapter<User> {

    private Context allUsersAdaptercontex;
    private int allUsersAdapterResource;

    public AllUsersAdapter(Context context, int resource, ArrayList<User> objects) {
        super(context, resource, objects);
        allUsersAdaptercontex = context;
        allUsersAdapterResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the User details
        User currentUser = getItem(position);
        String name = currentUser.getName();
        String email = currentUser.getEmail();

        //Create Layout Inflater
        LayoutInflater inflater = LayoutInflater.from(allUsersAdaptercontex);
        convertView = inflater.inflate(allUsersAdapterResource, parent, false);

        //Get views and set Data to display
        TextView nameView = (TextView) convertView.findViewById(R.id.user_name_view);
        TextView emailView = (TextView) convertView.findViewById(R.id.user_email_view);

        nameView.setText(name);
        emailView.setText(email);

        return convertView;
    }
}
