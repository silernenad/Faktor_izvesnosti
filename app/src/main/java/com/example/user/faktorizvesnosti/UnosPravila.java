package com.example.user.faktorizvesnosti;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.io.StreamTokenizer;
import java.util.*;
import com.example.user.faktorizvesnosti.Tokenizer;

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

        /**************   DODATAK     **************/

        ListaPravila listaPravila = new ListaPravila();         //lista svih pravila
        Pravilo novoPravilo = new Pravilo();                    //pravilo koje ce se ubacivati u listu
                                                //cita se sa ulaza i pravi se novo pravilo


        StringTokenizer ts = new StringTokenizer(pravilaMessage);
        while (ts.hasMoreTokens()){
            while (ts.nextToken()!="AKO"){
                ts.nextToken();
            }


        }






        }








}
