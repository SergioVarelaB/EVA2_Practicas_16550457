package com.example.eva2_8_sqlite3;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Lista extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listview;
    private ArrayList<Info> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        final SQLiteDatabase db = this.openOrCreateDatabase(
                "myfriendsDB",
                MODE_ENABLE_WRITE_AHEAD_LOGGING,
                null);
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
                names.add(new Info(name, phone));
            }
            Log.wtf("tel", phone);
        }
        c1.close();
        listview = findViewById(R.id.listview);
        listview.setAdapter(new InfoAdapter(this, R.layout.lista, names));
        listview.setOnItemClickListener(this);
    }
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
