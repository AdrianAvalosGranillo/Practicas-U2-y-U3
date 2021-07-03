package com.example.eva2_5_extras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText edTxtNom, edtTxtSal;
    CheckBox chkBxInfo;
    RadioGroup rdGrpEstadoCivil ;

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this, Secundaria.class);
    }


    @Override
    protected void onStart() {
        super.onStart();
        edTxtNom = findViewById(R.id.edtTxtNombre);
        edtTxtSal = findViewById(R.id.edtTxtSal);
        chkBxInfo = findViewById(R.id.chkBxInfo);
        rdGrpEstadoCivil= findViewById(R.id.rdGrpEstadoCivil);

    }

    public void onClick (View v){
        intent.putExtra("NOMBRE", edTxtNom.getText().toString() );
        Double dSalario = 0.0;
        dSalario = Double.parseDouble(edtTxtSal.getText().toString() );
        intent.putExtra("SALARIO", dSalario );
        intent.putExtra("INFO",  chkBxInfo.isChecked());
        intent.putExtra("ESTADO CIVIL", rdGrpEstadoCivil.getCheckedRadioButtonId() );
        startActivity(intent);

    }
}