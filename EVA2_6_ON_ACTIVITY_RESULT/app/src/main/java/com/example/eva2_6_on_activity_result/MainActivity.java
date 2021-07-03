package com.example.eva2_6_on_activity_result;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final static int CODIGO_SECUN=1000;
    final static int CODIGO_CONTACTOS=2000;
    final static int CODIGO_IMAGENES=000;
    Button btnIniSecu;
    Intent intent, intentCont, intentImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this,Secundaria.class);
        intentCont = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        intentImg = new Intent(Intent.ACTION_PICK, Uri.parse("content://media/external/images/media"));
    }

    @Override
    protected void onStart() {
        super.onStart();
        btnIniSecu = findViewById(R.id.btnIniSecu);
        btnIniSecu .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("DATOS","Informacion enviada desde principal");
                startActivityForResult(intent,CODIGO_SECUN);

            }
        });

    }
    public void onClickCont(View v){
        if (v.getId() == R.id.btnCont)
        startActivityForResult(intentCont,CODIGO_CONTACTOS);

        else
            startActivityForResult(intentImg,CODIGO_IMAGENES);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //PROCESAMOS LA RESPUESTA DE LA ACTIVIDAD
        //1. INDENTIFICAR ÑA ACTIVIDAD QUE DEVOLVIO EL RESULTADO
        //2. IDENTIFICAR SI SE DEVOLVIO UN VALOR O NO
        ///LEER LOS DATOS (INTENT)
        switch (requestCode){
            case CODIGO_SECUN:
                if (resultCode == Activity.RESULT_OK){
                    Toast.makeText(this,data.getStringExtra("VALOR"),Toast.LENGTH_LONG).show();

                }
                break;
            case CODIGO_CONTACTOS:
                if (resultCode == Activity.RESULT_OK){
                    String returnedData = data.getDataString();
                    Toast.makeText(this, returnedData, Toast.LENGTH_LONG).show();
                }

                break;

            case CODIGO_IMAGENES:
                if (resultCode == Activity.RESULT_OK){
                    String returnedData = data.getDataString();
                    Toast.makeText(this, returnedData, Toast.LENGTH_LONG).show();
                }

                break;

            default:
        }
    }
}