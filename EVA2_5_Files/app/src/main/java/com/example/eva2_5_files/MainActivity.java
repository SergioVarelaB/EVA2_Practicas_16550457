package com.example.eva2_5_files;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText algo;
    Button leer, guardar;
    final String  Archivo = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        algo = findViewById(R.id.etAlgo);
        leer = findViewById(R.id.btnLeer);
        leer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leer();
            }
        });
        guardar = findViewById(R.id.btnGuardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar();
            }
        });
    }

    public void leer(){
        try {
            InputStream is = openFileInput(Archivo);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String mensaje;
            try{
                while ((mensaje = br.readLine())!= null){
                    algo.append(mensaje);
                    algo.append("\n");
                }
                br.close();
            }catch (IOException e){
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void guardar(){
        try {
            OutputStream os = openFileOutput(Archivo, 0);
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter br = new BufferedWriter(osw);
            String mensaje;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
