package com.example.user.faktorizvesnosti;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.util.*;

public class UnosPravila extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unos_pravila);
    }

    public void onClickPravila(View view){

        Intent i = new Intent(this,Ostalo.class);
        final EditText pravilaInput = (EditText) findViewById(R.id.pravilaInput);
        String pravilaMessage = pravilaInput.getText().toString();                  //ulazni Sting

        i.putExtra("pravila", pravilaMessage);
        startActivity(i);
        }
}
