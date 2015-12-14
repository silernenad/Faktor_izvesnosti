package com.example.user.faktorizvesnosti;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.StringTokenizer;

public class Ostalo extends AppCompatActivity {
    static int id=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ostalo);

        Bundle data = getIntent().getExtras();
        String pravilaMessage = data.getString("pravila");

        ListaPravila2 listaPravila = new ListaPravila2();        //lista svih pravila

        ListaZakljucaka2 listaZakljucaka = new ListaZakljucaka2();  //lista svih zakljucaka pravila

        //cita se sa ulaza i pravi se novo pravilo

        StringTokenizer ts = new StringTokenizer(pravilaMessage );
        while (ts.hasMoreTokens()){
            Pravilo novoPravilo = new Pravilo();
            //pravilo koje ce se ubacivati u listu
            traziPreduslov(ts, novoPravilo, listaZakljucaka); //trazi jedno pravilo sa ulaza

            novoPravilo.setujRedniBroj(id++);

            listaPravila.addLast(novoPravilo);
        }

        //STAMPANJE OPAZANJA

        StringBuilder opazanja=new StringBuilder();

        LinkedList<String> lista=new LinkedList<String>();

        boolean nasao=false;
        Pravilo tekPravilo;
        for(int i=0;i<listaPravila.size();i++){                 //obilazi sva pravila

            tekPravilo=listaPravila.get(i);
            String tekPred;
            for (int j=0;j<tekPravilo.preduslov.size();j++){    //obailazi sve preduslove
                nasao=false;
                tekPred=tekPravilo.preduslov.get(j).getNaziv();

                Zakljucak zzz;
                for (int k=0;k<listaZakljucaka.size();k++){    //obilazi listu zakljucaka
                    zzz=listaZakljucaka.get(k);
                    if (zzz.getNaziv().equals(tekPred)){
                        nasao=true;
                        break;
                    }
                }
 //               if (!nasao)opazanja.append(tekPred+" (  )\n");
                if (!nasao && !lista.contains(tekPred))
                    lista.addLast(tekPred);
            }
        }

        for (int i=0;i<lista.size();i++)
//            opazanja.append(lista.get(i)+ " (  )\n");
            opazanja.append(lista.get(i)+ "   \n");



    final TextView opazanjaText=(TextView)findViewById(R.id.opazanjaInput);
    opazanjaText.setText(opazanja);




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

        startActivity(i);

    }


    public void traziPreduslov(StringTokenizer ts, Pravilo pravilo, ListaZakljucaka2 listaZakljucaka){
        pravilo.reset();
        while (!ts.nextToken().equals("AKO")  ){
            if(!ts.hasMoreTokens())break;// return;

        }


        //   if(!ts.hasMoreTokens()) return;

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
                case "-":
                    s=ts.nextToken();
                    pravilo.dodajPreduslov(s);
                    pravilo.dodajIzraz(s +" ");
                    break;

                default:
                    pravilo.dodajPreduslov(s);
                    pravilo.dodajIzraz(s +" ");
                    break;
            }//switch end
            s=ts.nextToken();
            if(s.equals("ONDA"))                                    //dodao.......
                break;
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