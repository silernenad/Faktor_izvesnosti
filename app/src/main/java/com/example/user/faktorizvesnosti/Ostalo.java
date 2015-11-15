package com.example.user.faktorizvesnosti;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Ostalo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ostalo);

        //ako hocu da prevucem preduslove da bi se samo unele verovatnoce
        /*
        Bundle applesData = getIntent().getExtras();
        if (null==applesData) {
            return;
        }
        String applesMessage = applesData.getString("appleMessage");
        final TextView baconText = (TextView) findViewById(R.id.opazanjaInput);
        */

        /*** PROVERA DA LI TADI SLANJE I STAMPANJE LISTE_PRAVILA ***/
/*
        Intent intent = getIntent();
        ListaPravila listaPravila = (ListaPravila) intent.getSerializableExtra("listapravila");

        final EditText test = (EditText) findViewById(R.id.opazanjaInput);
        test.setText(listaPravila.toString());
*/
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


       /*********** ODAVDE ************/
/*
        int a = 10;
        i.putExtra("broj", a);



        Zakljucak zakljucak = new Zakljucak("Radi");
        i.putExtra("nesto",zakljucak);
*/
        /*********** DOVDE ************/
        /** MOZE DA SE OBRISE **/

        startActivity(i);

    }

}
