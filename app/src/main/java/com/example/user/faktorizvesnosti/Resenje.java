package com.example.user.faktorizvesnosti;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Resenje extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resenje);

        Bundle data = getIntent().getExtras();
        if (null==data) {
            return;
        }

        String opazanjaMessage = data.getString("opazanja");
        String zakljucakMessage = data.getString("zakljucak");
        String pravilaMessage = data.getString("pravila");

        final TextView resenjeText = (TextView) findViewById(R.id.resenjeOutput);
        resenjeText.setText(pravilaMessage + opazanjaMessage + zakljucakMessage);
    }
    public void onClick(View view){

        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

}
