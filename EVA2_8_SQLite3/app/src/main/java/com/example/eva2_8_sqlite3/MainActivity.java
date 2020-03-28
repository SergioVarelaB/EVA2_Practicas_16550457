package com.example.eva2_8_sqlite3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText name, phone;
    Button add, select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.etNombre);
        phone = findViewById(R.id.etTelefono);
        add = findViewById(R.id.btnAdd);
        select = findViewById(R.id.btnSelect);
        final SQLiteDatabase db = this.openOrCreateDatabase(
                "myfriendsDB",
                MODE_ENABLE_WRITE_AHEAD_LOGGING,
                null);
        try {
            //perform your database operations here ...
            db.execSQL("create table tblAMIGO ("
                    + " recID integer PRIMARY KEY autoincrement, "
                    + " name text, "
                    + " phone text ); " );
            db.setTransactionSuccessful(); //commit your changes
        }
        catch (SQLiteException e) {
            Log.getStackTraceString(e);
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String  n = name.getText().toString();
                    String telefono = phone.getText().toString();
                    db.execSQL("insert into tblAMIGO(name , phone) values ('"+n+"', '"+telefono+"');");
                    db.setTransactionSuccessful(); //commit your changes
                }catch (Exception e) {
                    Log.getStackTraceString(e);
                }
            }
        });
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(getApplicationContext(),Lista.class);
                startActivity(intento);
            }
        });

    }
}