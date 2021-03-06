package com.example.eva3_7_handler_banner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView imgVwBanner;
    Thread tBanner;
    int iCont = 0;
    //a traves de un handler interactuar con la interfaz grafica
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            //Se interactua con la interfaz grafica
           int image;
           if (iCont ==0){
               image = R.drawable.f1;
               iCont++;

           }else if (iCont == 1){
               image = R.drawable.f2;
               iCont++;

           }else{
               image = R.drawable.f3;
               iCont = 0;

           }
           imgVwBanner.setImageResource(image);


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgVwBanner = findViewById(R.id.imgVwBanner);
        tBanner = new Thread(){
            @Override
            public void run() {
                super.run();
                while (true){
                    try {
                        Thread.sleep(1000);
                        //solicitar mensaje
                        Message message = handler.obtainMessage();
                        //devolverlo
                        handler.sendMessage(message);
                        //enviar mensajee
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        };
        tBanner.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tBanner.interrupt();
    }
}