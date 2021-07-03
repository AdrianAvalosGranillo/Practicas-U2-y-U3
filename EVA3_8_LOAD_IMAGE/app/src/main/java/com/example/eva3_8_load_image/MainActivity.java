package com.example.eva3_8_load_image;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView imgVwLoad;
    Bitmap bitmap;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            //aqui motramos la imagen
            imgVwLoad.setImageBitmap(bitmap);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgVwLoad = findViewById(R.id.imgVwLoad);
        // meter esta linea dentro de un hilo de ejecucion
        // Bitmap bitmap = descargarImagen("https://es.wikipedia.org/wiki/IPhone_12#/media/Archivo:IPhone_12_Blue.svg");
        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                // se hace la conexion
                bitmap = descargarImagen("https://es.wikipedia.org/wiki/IPhone_12#/media/Archivo:IPhone_12_Blue.svg");
                Message msg = handler.obtainMessage();
                handler.sendMessage(msg);
            }
        };
        thread.start();

        imgVwLoad.setImageBitmap(bitmap);

    }

    private Bitmap descargarImagen (String url){
        try {
            InputStream inputStream = (InputStream) new URL(url).getContent();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }


}