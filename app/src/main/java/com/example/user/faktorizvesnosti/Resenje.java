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
        /***    UNOS PRAVILA    ***/

  //      ListaPravila listaPravila = new ListaPravila();           //lista svih pravila

        ListaPravila2 listaPravila = new ListaPravila2();
        Pravilo novoPravilo = new Pravilo();                        //pravilo koje ce se ubacivati u listu
        ListaZakljucaka2 listaZakljucaka = new ListaZakljucaka2();  //lista svih zakljucaka pravila

        //cita se sa ulaza i pravi se novo pravilo

        StringTokenizer ts = new StringTokenizer(pravilaMessage );
        while (ts.hasMoreTokens()){
            traziPreduslov(ts, novoPravilo, listaZakljucaka); //trazi jedno pravilo sa ulaza

            novoPravilo.setujRedneBrojeveZiP(id++);

            listaPravila.add(novoPravilo);


            /**ostaje da se jos nameste stekovi**/
        }

        /***    UNOS OPAZANJA    ***/
        StringTokenizer opazanja = new StringTokenizer(opazanjaMessage);

        Pravilo p = listaPravila.getFirst();
        String tekOpazanje;



        
        ElemPreduslov elemPreduslov;

/*
        while (opazanja.hasMoreTokens()) {                              //doklegod ima jos tokena
            for (int i = 0; p != null; p = listaPravila.get(i), i++) {  //krece se kroz listu pravila
                for (elemPreduslov = p.preduslov.prvi(); elemPreduslov != null; elemPreduslov = elemPreduslov.sled) {   //krece se kroz listu preduslova

                }
            }
        }
*/

        resenjeText.setText("\n"+opazanja.nextToken() );




 //       i.putExtra("listapravila", listaPravila);    //salje listuPravial na ledeci activiy




        ///////////////////////////////////////////////////////////////////////




    }
    public void onClick(View view){

        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }



    public void traziPreduslov(StringTokenizer ts, Pravilo pravilo, ListaZakljucaka2 listaZakljucaka){        //prebacio sam sve retrurn u breack!!!!!!!!!1
        pravilo.reset();
        while (!ts.nextToken().equals("AKO")  ){
            if(!ts.hasMoreTokens())break;// return;

        }


        //   if(!ts.hasMoreTokens()) return;

        String s = ts.nextToken();
        while (!s.equals("ONDA") && ts.hasMoreTokens()) {     //vidi za .toUpperCase()



            switch (s) {    //mozda da stavim s.toUpperCase()
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
                    pravilo.dodajPreduslov(s);
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
            Zakljucak z = new Zakljucak(ts.nextToken());
            pravilo.setZakljucak(z);   //dodaje se zakljucak
            listaZakljucaka.add(z);

        }

    }






}
