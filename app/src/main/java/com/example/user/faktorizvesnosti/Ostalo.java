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

    }
    public void onClick(View view){



        Intent i = new Intent(this,Resenje.class);
        Bundle data = getIntent().getExtras();
        String pravilaMessage = data.getString("pravila");
        i.putExtra("pravila", pravilaMessage);




        final EditText opazanjaInput = (EditText) findViewById(R.id.opazanjaInput);
        String opazanjaMessage = opazanjaInput.getText().toString();
        i.putExtra("opazanja", opazanjaMessage);

        final EditText zakljucakInput = (EditText) findViewById(R.id.zakljucakInput);
        String zakljucakMessage = zakljucakInput.getText().toString();
        i.putExtra("zakljucak", zakljucakMessage);
        int a = 10;
        i.putExtra("broj", a);
        Zakljucak zakljucak = new Zakljucak("Radi");

        i.putExtra("nesto",zakljucak);

        startActivity(i);

    }

}
