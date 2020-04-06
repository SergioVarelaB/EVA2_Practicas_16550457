package com.example.eva2_8_sqlite3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listview;
    private ArrayList<Info> names;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = this.openOrCreateDatabase(
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
        //Todo poner un metodo aqui
        update();
        Button Insert = findViewById(R.id.btnInsert);
        Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Add.class));
            }
        });

    }
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        alertDialog(names.get(i).getId());
    }

    private void alertDialog(int pos) {
        final int i = pos;
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("¿Qué acción quieres hacer?");
        dialog.setPositiveButton(
                "Eliminar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"no manches no!!" + i,Toast.LENGTH_SHORT).show();
                        eliminar(i);
                    }
                });
        dialog.setNegativeButton(
                "Actualizar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which){
                        Intent intento = new Intent(getApplicationContext(),Update.class);
                        intento.putExtra("id",i);
                        startActivity(intento);
                        //Toast.makeText(getApplicationContext(),"Que onda!!"+ i,Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }

    private void eliminar(int pos){
        final int i = pos;
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("¿Seguro?");
        dialog.setPositiveButton(
                "Si",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String [] whereArgs = {i+""};
                        int recAffected = db.delete( "tblAMIGO", "recID = ?", whereArgs);
                        Toast.makeText(getApplicationContext(),"status = " + recAffected,Toast.LENGTH_SHORT).show();
                        update();
                    }
                });
        dialog.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which){

                    }
                });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }

    public void update(){
        names = new ArrayList<Info>();
        String sql = "select * from tblAmigo";
        Cursor c1 = db.rawQuery(sql, null);
        c1.moveToPosition(-1);
        while (c1.moveToNext()) {
            int recId = c1.getInt(0);
            String name = c1.getString(1);
            String phone = c1.getString(c1.getColumnIndex("phone"));
            Log.wtf("nombre", name);
            if (name != null) {
                names.add(new Info(name, phone, recId));
            }
            Log.wtf("tel", phone);
        }
        c1.close();
        listview = findViewById(R.id.listview);
        listview.setAdapter(new InfoAdapter(this, R.layout.lista, names));
        listview.setOnItemClickListener(this);
    }
}