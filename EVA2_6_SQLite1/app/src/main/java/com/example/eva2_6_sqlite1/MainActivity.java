package com.example.eva2_6_sqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase db = this.openOrCreateDatabase(
                "myfriendsDB",
                MODE_ENABLE_WRITE_AHEAD_LOGGING,
                null);

        try {
            //perform your database operations here ...
            db.execSQL("create table tblAMIGO ("
                    + " recID integer PRIMARY KEY autoincrement, "
                    + " name text, "
                    + " phone text ); " );
            db.execSQL( "insert into tblAMIGO(name, phone) values ('Juan', '555-1111');" );
            db.execSQL( "insert into tblAMIGO(name, phone) values ('Pedro', '555-2222');" );
            db.execSQL( "insert into tblAMIGO(name, phone) values ('Jose', '555-3333');" );
            db.setTransactionSuccessful(); //commit your changes
        }
        catch (SQLiteException e) {
            Log.getStackTraceString(e);
        }
    }
}
