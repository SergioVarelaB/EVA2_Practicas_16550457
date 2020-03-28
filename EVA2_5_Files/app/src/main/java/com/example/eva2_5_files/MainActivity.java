package com.example.eva2_5_files;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText algo;
    Button leer, guardar;
    final String  Archivo = "w.txt";
    final int PERMISO = 51;
    String rutaSD;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //TODO pedir permisos

    }
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
        rutaSD = getExternalFilesDir(Environment.DIRECTORY_MUSIC).getPath();//Environment.getExternalStorageDirectory().getAbsolutePath();
        Toast.makeText(this,rutaSD,Toast.LENGTH_SHORT).show();
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISO
            );
        }


    }

    public void leer(){
        try {
            //InputStream is = openFileInput(Archivo);
            File file = new File(getExternalFilesDir(null), Archivo);
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
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
        String mensaje = algo.getText().toString();
        try {
            //FileOutputStream os = openFileOutput(rutaSD + Archivo, 0);
            File file = new File(getExternalFilesDir(null), Archivo);
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter br = new BufferedWriter(osw);
            br.write(mensaje);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
