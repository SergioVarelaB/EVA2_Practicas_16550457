package com.example.eva2_8_sqlite3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Update extends AppCompatActivity {
    EditText name, phone;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Intent intento = getIntent();
        final int id = intento.getIntExtra("id",-1);
        name = findViewById(R.id.etNombreU);
        phone = findViewById(R.id.etTelefonoU);
        add = findViewById(R.id.btnUpdate);
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
                    String [] whereArgs = {id+""};
                    ContentValues updValues = new ContentValues();
                    if(!n.equals("")  || !telefono.equals("")){
                        if(!n.equals("")){
                            updValues.put("name", n);
                        }
                        if(!telefono.equals("")){
                            updValues.put("phone",telefono);
                        }
                        db.update( "tblAMIGO",
                                updValues,
                                "recID  = ?",
                                whereArgs );
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
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
