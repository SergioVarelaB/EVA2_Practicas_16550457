package com.example.eva2_7_sqlite2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class InfoAdapter extends ArrayAdapter<Info> {
    Context context;
    int resource;
    Info[] people;

    public InfoAdapter(Context context, int resource, ArrayList<Info> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.people = objects.toArray(new Info[0]);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView txtName, txtPhone;

        if(convertView == null){
            //Crear nuestro layout que representa una fila en la lista
            //INFLATER
            LayoutInflater lInflator = ((Activity) context).getLayoutInflater();
            convertView = lInflator.inflate(resource, parent, false);
        }

        txtName = convertView.findViewById(R.id.txtName);
        txtPhone = convertView.findViewById(R.id.txtPhone);

        txtName.setText(people[position].getName());
        txtPhone.setText(people[position].getPhone());

        return convertView;
    }
}
