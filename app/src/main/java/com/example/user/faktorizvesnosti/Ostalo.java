package com.example.user.faktorizvesnosti;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.StringTokenizer;

public class Ostalo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ostalo);

    }
    public void onClick(View view){



        //prihvata podatke
        Bundle data = getIntent().getExtras();
        String pravilaMessage = data.getString("pravila");

        //sprema podatke za slanje
        Intent i = new Intent(this,Resenje.class);
        i.putExtra("pravila", pravilaMessage);




        final EditText opazanjaInput = (EditText) findViewById(R.id.opazanjaInput);
        String opazanjaMessage = opazanjaInput.getText().toString();
        i.putExtra("opazanja", opazanjaMessage);

        final EditText zakljucakInput = (EditText) findViewById(R.id.zakljucakInput);
        String zakljucakMessage = zakljucakInput.getText().toString();
        i.putExtra("zakljucak", zakljucakMessage);

        startActivity(i);

    }

}