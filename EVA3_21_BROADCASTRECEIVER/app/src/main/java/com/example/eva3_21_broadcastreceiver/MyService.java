package com.example.eva3_21_broadcastreceiver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    Thread hilo;
    Intent intent;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        intent = new Intent("MI_MENSAJE");
        intent.putExtra("MENSAJE","onCreat");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        hilo = new Thread(){
            @Override
            public void run() {
                super.run();
                while (true){
                    try {
                        Thread.sleep(1000);
                        //Log.wtf("SERVICIO","MESAJE");
                        //enviar mensajes

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        };
        hilo.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hilo.interrupt();
        intent = new Intent("MI_MENSAJE");
        intent.putExtra("MENSAJE","onDestroy");
        sendBroadcast(intent);
    }
}