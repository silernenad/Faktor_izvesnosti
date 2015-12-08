package com.example.user.faktorizvesnosti;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import java.util.StringTokenizer;

public class Resenje extends AppCompatActivity {

    static int id=1;
    static int idZak=1;

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


/*      String url = "http://howtodoinjava.com/java-initerview-questions";  ako nesto nije u redu moze i ovako da se deli
        StringTokenizer multiTokenizer = new StringTokenizer(url, "://.-");
        probaj sa ovim (sting, "\t\n\r ")
 */
        /***    UNOS PRAVILA    ***/


        ListaPravila2 listaPravila = new ListaPravila2();        //lista svih pravila

        ListaZakljucaka2 listaZakljucaka = new ListaZakljucaka2();  //lista svih zakljucaka pravila

        //cita se sa ulaza i pravi se novo pravilo

        StringTokenizer ts = new StringTokenizer(pravilaMessage );
        while (ts.hasMoreTokens()){
            Pravilo novoPravilo = new Pravilo();
                                  //pravilo koje ce se ubacivati u listu
            traziPreduslov(ts, novoPravilo, listaZakljucaka, listaPravila); //trazi jedno pravilo sa ulaza

            novoPravilo.setujRedniBroj(id++);

            listaPravila.addLast(novoPravilo);
        }


        //sredjivanje rednih brojeva

        for (int i = 0; i < listaZakljucaka.size(); i++) {
            listaZakljucaka.get(i).setRedniBroj(i + 1);
        }




        //TOKENIZACIJA OPAZANJA
        opazanjaMessage=opazanjaMessage.replace("(", "");
        opazanjaMessage=opazanjaMessage.replace(")", "");
        StringTokenizer opazanja = new StringTokenizer(opazanjaMessage );

        boolean nasao=false;

        while (opazanja.hasMoreTokens()) {
            Pravilo tekPravilo;
            for (int i = 0; i < listaPravila.size(); i++) {                 //obilazi sva pravila
                tekPravilo = listaPravila.get(i);
                String tekPred;
                for (int j = 0; j < tekPravilo.preduslov.size(); j++) {    //obailazi sve preduslove
                    tekPred = tekPravilo.preduslov.get(j).getNaziv();

                    if (tekPred.equals(opazanja.nextToken())){
                        String a = opazanja.nextToken();
                        double broj = Double.parseDouble(a);
                        tekPravilo.preduslov.get(j).setMB(broj);
                    }

                }
            }
        }

        //pravljenje stabla
        for (int i=0;i<listaPravila.size();i++){
            listaPravila.get(i).uredi();
        }


        //ZAKLJUCAK KOJI SE TRAZI

        Zakljucak glavni=new Zakljucak(zakljucakMessage);




        resenjeText.setText(glavni.getNaziv());

    }











    public void onClick(View view){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }



    public void traziPreduslov(StringTokenizer ts, Pravilo pravilo,
                               ListaZakljucaka2 listaZakljucaka,ListaPravila2 listaPravila ){
        pravilo.reset();
        while (!ts.nextToken().equals("AKO")  ){
            if(!ts.hasMoreTokens())break;

        }

        String s = ts.nextToken();
        while (!s.equals("ONDA") && ts.hasMoreTokens()) {


            switch (s) {
                case "ILI":
                    pravilo.dodajIzraz(s +" ");
                    break;
                case "I":
                    pravilo.dodajIzraz(s +" ");
                    break;
                case "(":
                    pravilo.dodajIzraz(s +" ");
                    break;
                case ")":
                    pravilo.dodajIzraz(s +" ");
                    break;

                default:
                    pravilo.dodajPreduslov(s);
                    pravilo.dodajIzraz(s +" ");
                    break;
            }
            s=ts.nextToken();
        }
        if (ts.hasMoreTokens()) {
            String mb = ts.nextToken(); //dohvata se vrednost u zagradama i izbauju se zagrade

            mb = mb.replace("(", "");
            mb = mb.replace(")", "");

            double broj = Double.parseDouble(mb);//pretvara se u broj i setuje se MD' ili MD'
            if (broj > 0) pravilo.setMB_(broj);
            else pravilo.setMD_(broj);

            /*     UBACIVANJE ZAKLJUCKA U LISTU PRAVILA    */

            Zakljucak zakljucak = new Zakljucak(ts.nextToken());

            if (!listaZakljucaka.isEmpty()) {                   //lista nije prazna

                Zakljucak tek;
                int i;
                boolean nasao=false;
                for ( i = 0; i < listaZakljucaka.size(); i++) {  //trazi zakljucak u listi zakljucaka
                    tek = listaZakljucaka.get(i);
                    if (tek.jednako(zakljucak)) {               //zakljucak nadjen u listi
                        tek.povecajBRojPravila();
                        tek.dodajPravilo(id + " ");
                        pravilo.setZakljucak(tek);
                        nasao=true;
                        break;
                    }
                }
                if (!nasao){                            //nije pronadjen zakljucak u listi
                    zakljucak.setPravila(id + " ");
                    zakljucak.setRedniBroj(idZak++);
                    listaZakljucaka.add(zakljucak);
                    pravilo.setZakljucak(zakljucak);

                }

            }
            else{                                                 //prazna lista
                zakljucak.setPravila(id + " ");
                zakljucak.setRedniBroj(idZak++);
                listaZakljucaka.add(zakljucak);
                pravilo.setZakljucak(zakljucak);
            }

        }

    }






}
