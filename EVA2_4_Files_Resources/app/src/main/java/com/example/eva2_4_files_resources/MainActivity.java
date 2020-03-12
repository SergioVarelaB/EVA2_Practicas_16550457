package com.example.eva2_4_files_resources;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    TextView tvLorem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvLorem = findViewById(R.id.tvLorem);

    }
    @Override
    protected void onStart() {
        super.onStart();
        InputStream is = getResources().openRawResource(R.raw.lorem);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader bsr = new BufferedReader(isr);
        String mensaje;
        try{
            while ((mensaje = bsr.readLine())!= null){
                tvLorem.append(mensaje);
                tvLorem.append("\n");
            }
            bsr.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
