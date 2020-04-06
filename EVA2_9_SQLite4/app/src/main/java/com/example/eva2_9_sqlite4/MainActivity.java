package com.example.eva2_9_sqlite4;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String sRutaSD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sRutaSD = getExternalFilesDir(null).getPath();//Para crear una carpeta en Android/data y guardarlo ahí
        Toast.makeText(this,sRutaSD+"/myfriendsDB-db",Toast.LENGTH_SHORT).show();
        final SQLiteDatabase db = this.openOrCreateDatabase(
                sRutaSD+"/myfriendsDB-db",
                MODE_PRIVATE,
                null);
        db.beginTransaction();
        try {
            //perform your database operations here ...
            db.execSQL("create table tblAMIGO ("
                    + " recID integer PRIMARY KEY autoincrement, "
                    + " name text, "
                    + " phone text ); " );
            db.setTransactionSuccessful(); //commit your changes
            Toast.makeText(getApplicationContext(),"Se creó la tabla",Toast.LENGTH_SHORT).show();
        }
        catch (SQLiteException e) {
            //report problem
            //Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        finally {
            db.endTransaction();
        }
    }
}
