package com.example.user.faktorizvesnosti;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.io.Serializable;
import java.util.*;

public class UnosPravila extends AppCompatActivity {

    static int id=1;
    String opazanja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unos_pravila);


    }
    @Override
    protected void onStart() {
        super.onStart();
        id=1;
        Bundle data = getIntent().getExtras();
        String pravila = data.getString("mPravila");
        opazanja = data.getString("mOpazanja");
        if (MainActivity.file) {
            final EditText pravilaInput = (EditText) findViewById(R.id.pravilaInput);
            pravilaInput.setText(pravila);
        }

    }



    public void onClickPravila(View view){
        Intent i = new Intent(this,Ostalo.class);
        final EditText pravilaInput = (EditText) findViewById(R.id.pravilaInput);
        String pravilaMessage = pravilaInput.getText().toString();                  //ulazni Sting
        i.putExtra("pravila", pravilaMessage);
        i.putExtra("mOpazanja",opazanja);
        startActivity(i);
        }

    public  void dodajPravilo(View view){
        Intent i = new Intent(this,Ostalo.class);
        final EditText pravilaInput = (EditText) findViewById(R.id.pravilaInput);
        String pravilaMessage = pravilaInput.getText().toString();                  //ulazni Sting
        StringBuilder poruka=new StringBuilder();
        if (id==1){
            poruka.append("P1:\nAKO\n\nONDA\n( ) ");
        }
        else {
            poruka.append(pravilaMessage);
            poruka.append("\nP" + id + ":\n" +
                    "AKO\n" +
                    "\n" +
                    "ONDA\n" +
                    "( ) ");
        }
        id++;

        pravilaInput.setText(poruka);
    }


}
