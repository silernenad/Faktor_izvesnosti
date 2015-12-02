package com.example.user.faktorizvesnosti;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Resenje extends AppCompatActivity {

    static int id=1;

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



        ////////////////////////////////////////////////////////////////////////
 //       int id =1;

/*
        String url = "http://howtodoinjava.com/java-initerview-questions";  ako nesto nije u redu moze i ovako da se deli
        StringTokenizer multiTokenizer = new StringTokenizer(url, "://.-");
        probaj sa ovim (sting, "\t\n\r ")

        */
        /***    UNOS PRAVILA    ***/

  //      ListaPravila listaPravila = new ListaPravila();           //lista svih pravila

        ListaPravila2 listaPravila = new ListaPravila2();

        ListaZakljucaka2 listaZakljucaka = new ListaZakljucaka2();  //lista svih zakljucaka pravila

        //cita se sa ulaza i pravi se novo pravilo

        StringTokenizer ts = new StringTokenizer(pravilaMessage );
        while (ts.hasMoreTokens()){
            Pravilo novoPravilo = new Pravilo();
                                  //pravilo koje ce se ubacivati u listu
            traziPreduslov(ts, novoPravilo, listaZakljucaka); //trazi jedno pravilo sa ulaza

            novoPravilo.setujRedneBrojeveZiP(id++);

            listaPravila.addLast(novoPravilo);
        }

        /***    UNOS OPAZANJA    ***/
/*
        pravilaMessage = pravilaMessage.replace("(", "");
        pravilaMessage = pravilaMessage.replace(")", "");
        StringTokenizer opazanja = new StringTokenizer(pravilaMessage);

        while (opazanja.hasMoreTokens()){
            ..........
        }


*/
       // resenjeText.setText(listaPravila.getFirst().preduslov.toString() );
        resenjeText.setText(listaPravila.get(1).getZakljucak().toString());

    }











    public void onClick(View view){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }



    public void traziPreduslov(StringTokenizer ts, Pravilo pravilo, ListaZakljucaka2 listaZakljucaka){
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
            pravilo.setZakljucak(z);   //dodaje se zakljucak   Proveri!!!

            Zakljucak zakljucak=z;

            if (!listaZakljucaka.isEmpty()) {

                Zakljucak tek = listaZakljucaka.getFirst();
                int i;
                for (i = 0; i < listaZakljucaka.size(); i++) {      //lista nije prazna
                    if (tek.jednako(zakljucak)) {
                        tek.povecajBRojPravila();
                        tek.dodajPravilo(id + " ");
                        break;
                    }


                }
                if (i==listaZakljucaka.size()){     //nije pronadjen zakljucak u listi
                    zakljucak.setPravila(id + " ");
                    zakljucak.setRedniBroj(listaZakljucaka.getLast().getRedniBroj()+1);
                    listaZakljucaka.add(zakljucak);
                }

            }
            else{       //prazna lista
                zakljucak.setPravila(id + " ");
                zakljucak.setRedniBroj(1);
                listaZakljucaka.add(zakljucak);
            }

        }

    }






}
