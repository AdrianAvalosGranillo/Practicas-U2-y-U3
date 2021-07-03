package com.example.eva3_6_hanlder_message;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtMensa;
    Thread thread;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            // podemos modificar la interfaz grafica
            //trabajo ligero
            String cade =(String) msg.obj;
            int what = msg.what;
            txtMensa.append("El hilo= " + what +"imprime " + cade + "\n");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMensa = findViewById(R.id.txtMensa);
        // txtMensa.setText("HOLA MUNDO CRUEL!");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                        int i = 0;
                        String cade = "i = " + i;
                        i++;
                        //solicitar un mensaje
                        //poner info
                        //devolverlo
                        Message message = handler.obtainMessage(1000, cade);
                        //devolverlo
                        handler.sendMessage(message);
                        txtMensa.append(cade + "\n");
                        Log.wtf("runnable ",cade);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }

            }
        };
        thread = new Thread(runnable);
        thread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        thread.interrupt();
    }
}