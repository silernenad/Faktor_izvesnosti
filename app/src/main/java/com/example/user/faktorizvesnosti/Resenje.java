package com.example.user.faktorizvesnosti;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import java.io.Serializable;
import java.util.StringTokenizer;

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


        Intent intent = getIntent();


 //       Zakljucak aaa = (Zakljucak) intent.getSerializableExtra("nesto");


        final TextView resenjeText = (TextView) findViewById(R.id.resenjeOutput);



        ////////////////////////////////////////////////////////////////////////
        int id =1;

/*
        String url = "http://howtodoinjava.com/java-initerview-questions";  ako nesto nije u redu moze i ovako da se deli
        StringTokenizer multiTokenizer = new StringTokenizer(url, "://.-");
        probaj sa ovim (sting, "\t\n\r ")

        */


  //      ListaPravila listaPravila = new ListaPravila();         //lista svih pravila

        ListaPravila2 listaPravila = new ListaPravila2();
        Pravilo novoPravilo = new Pravilo();                    //pravilo koje ce se ubacivati u listu
        //cita se sa ulaza i pravi se novo pravilo

        StringTokenizer ts = new StringTokenizer(pravilaMessage );
        while (ts.hasMoreTokens()){
            traziPreduslov(ts, novoPravilo); //trazi jedno pravilo sa ulaza

            novoPravilo.setujRedneBrojeveZiP(id++);

            listaPravila.add(novoPravilo);


            /**ostaje da se jos nameste stekovi**/

        }

        resenjeText.setText(listaPravila.getFirst().toSting() );




 //       i.putExtra("listapravila", listaPravila);    //salje listuPravial na ledeci activiy




        ///////////////////////////////////////////////////////////////////////




    }
    public void onClick(View view){

        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }



    public void traziPreduslov(StringTokenizer ts, Pravilo pravilo){        //prebacio sam sve retrurn u breack!!!!!!!!!1
        pravilo.reset();
        while (!ts.nextToken().equals("AKO")  ){
            if(!ts.hasMoreTokens())break;// return;

        }


        //   if(!ts.hasMoreTokens()) return;

        String s = ts.nextToken();
        while (!s.toUpperCase().equals("ONDA") && ts.hasMoreTokens()) {     //mozda traba prebaciti na veliak slova!!!!!! toUper



            switch (s) {
                case "ILI":
                    //  ts.nextToken();
                    break;
                case "I":
                    //  ts.nextToken();
                    break;
                case "(":
                    //   ts.nextToken();
                    break;
                case ")":
                    //   ts.nextToken();
                    break;

                default:
                    pravilo.dodajPreduslov(s.toLowerCase());
                    //  ts.nextToken();
                    break;
            }//switch end
            s=ts.nextToken();
        }
        if (ts.hasMoreTokens()) {
            String mb = ts.nextToken(); //dohvata se vrednost u zagradama i izbauju se zagrade

            mb = mb.replace("(", "");
            mb = mb.replace(")", "");

            double broj = Double.parseDouble(mb);//pretvara se u broj i setuje se MD' ili MD'
            if (broj > 0) pravilo.setMB_(broj);
            else pravilo.setMD_(broj);
            pravilo.setZakljucak(new Zakljucak(ts.nextToken()));   //dodaje se zakljucak

        }

    }






}
