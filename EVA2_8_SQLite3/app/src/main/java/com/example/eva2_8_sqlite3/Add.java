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

public class Add extends AppCompatActivity {
    EditText name, phone;
    Button add, select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        name = findViewById(R.id.etNombre);
        phone = findViewById(R.id.etTelefono);
        add = findViewById(R.id.btnAdd);
        //select = findViewById(R.id.btnSelect);
        final SQLiteDatabase db = this.openOrCreateDatabase(
                "myfriendsDB",
                MODE_ENABLE_WRITE_AHEAD_LOGGING,
                null);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String  n = name.getText().toString();
                    String telefono = phone.getText().toString();
                    if(!n.equals("")  || !telefono.equals("")){
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        db.execSQL("insert into tblAMIGO(name , phone) values ('"+n+"', '"+telefono+"');");
                        db.setTransactionSuccessful(); //commit your changes
                        finish();
                    }
                    else{
                        name.setError("Introduzca un valor");
                        phone.setError("Introduzca un valor");
                    }
                }catch (Exception e) {
                    Log.getStackTraceString(e);
                }
            }
        });
    }
}
