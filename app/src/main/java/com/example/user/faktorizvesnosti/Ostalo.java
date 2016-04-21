package com.example.user.faktorizvesnosti;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

    }

    @Override
    protected void onStart() {
        super.onStart();
        id=1;


        Bundle data = getIntent().getExtras();
        String pravilaMessage = data.getString("pravila");
        String mainOpazanja = data.getString("opazanja");

        ListaPravila2 listaPravila = new ListaPravila2();        //lista svih pravila

        ListaZakljucaka2 listaZakljucaka = new ListaZakljucaka2();  //lista svih zakljucaka pravila

        //cita se sa ulaza i pravi se novo pravilo

        StringTokenizer ts = new StringTokenizer(pravilaMessage );
        while (ts.hasMoreTokens()){
            Pravilo novoPravilo = new Pravilo();
            //pravilo koje ce se ubacivati u listu
            try {
                traziPreduslov(ts, novoPravilo, listaZakljucaka); //trazi jedno pravilo sa ulaza
            }catch (Exception e){
                ispis(this.findViewById(android.R.id.content),
                        "Greska u gramatici!!!");
            }
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

        if (MainActivity.file=true){
            String mainZakljucak = "";
            opazanja = new StringBuilder();
            StringTokenizer tokenizer = new StringTokenizer(mainOpazanja,"\n");
            while (tokenizer.hasMoreTokens()){
                opazanja.append(mainZakljucak);
                mainZakljucak = tokenizer.nextToken();
            }
            final EditText zakljucakInput = (EditText) findViewById(R.id.zakljucakInput);
            zakljucakInput.setText(mainZakljucak);
        }


        opazanjaText.setText(opazanja);


    }
    public void onClick(View view){



        //prihvata podatke
        Bundle data = getIntent().getExtras();
        String pravilaMessage = data.getString("pravila");

        //sprema podatke za slanje
        Intent i = new Intent(this,Resenje.class);






        final EditText opazanjaInput = (EditText) findViewById(R.id.opazanjaInput);
        String opazanjaMessage = opazanjaInput.getText().toString();
        final EditText zakljucakInput = (EditText) findViewById(R.id.zakljucakInput);
        String zakljucakMessage = zakljucakInput.getText().toString();


        i.putExtra("pravila", pravilaMessage);
        i.putExtra("opazanja", opazanjaMessage);
        i.putExtra("zakljucak", zakljucakMessage);

        startActivity(i);

    }


    public void traziPreduslov(StringTokenizer ts, Pravilo pravilo, ListaZakljucaka2 listaZakljucaka)
    throws Exception{
        boolean greska=false;
        pravilo.reset();
        while (!ts.nextToken().equals("AKO")  ){
            if(!ts.hasMoreTokens())break;// return;

        }


        //   if(!ts.hasMoreTokens()) return;
        String tekuci="", prethodni="";
        String s = ts.nextToken();
        while (!s.equals("ONDA") && ts.hasMoreTokens()) {
       prethodni=tekuci;
            tekuci=s;
            greska=false;
            switch (s) {

                case "ILI":
                    pravilo.dodajIzraz(s +" ");
                    if (jeZnak(prethodni)&&!prethodni.equals(")"))
                        greska=true;
                    break;
                case "I":
                    pravilo.dodajIzraz(s +" ");
                    if (jeZnak(prethodni)&&!prethodni.equals(")"))
                        greska=true;
                    break;
                case "(":
                    pravilo.dodajIzraz(s +" ");
                    if (jeZnak(prethodni))
                        greska=true;
                    break;
                case ")":
                    pravilo.dodajIzraz(s +" ");
                    if (jeZnak(prethodni))
                        greska=true;
                    break;
                case "-":
                    s=ts.nextToken();
                    pravilo.dodajPreduslov(s);
                    pravilo.dodajIzraz(s +" ");
                    if (jeZnak(prethodni))
                        greska=true;
                    break;

                default:
                    pravilo.dodajPreduslov(s);
                    pravilo.dodajIzraz(s +" ");
                    break;
            }//switch end
            if (greska) {
                ispis(this.findViewById(android.R.id.content));

            }
            s= ts.nextToken();
            if(s.equals("ONDA")) {                                    //dodao.......
                prethodni="ONDA";
                break;
            }
        }
        if (!prethodni.equals("ONDA"))
            ispis(this.findViewById(android.R.id.content));


        if (ts.hasMoreTokens()) {

            String mb = ts.nextToken(); //dohvata se vrednost u zagradama i izbauju se zagrade

            if (mb.equals("(")){
                mb = ts.nextToken();
            }
            mb = mb.replace("(", "");
            mb = mb.replace(")", "");
            double broj=0.0;

            broj = Double.parseDouble(mb);
            if (broj<0||broj>1) {
                String string="Mera poverenja zakljucka broj "+id+"\nmora biti u okviru od 0 do 1";
                ispis(this.findViewById(android.R.id.content), string);
            }




            if (broj > 0) pravilo.setMB_(broj);
            else pravilo.setMD_(broj);
            if (!ts.hasMoreTokens()){
                ispis(this.findViewById(android.R.id.content));
            }
            mb = ts.nextToken();
            if (mb.equals(")")){
                mb = ts.nextToken();
            }

           // Zakljucak z = new Zakljucak(ts.nextToken());
            Zakljucak z = new Zakljucak(mb);
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
    public boolean jeZnak(String string){
        return    string.equals("ILI")||string.equals("AKO")||string.equals("(")||string.equals(")")||
                string.equals("I")||string.equals("-");
    }
    public void ispis(View view){
        AlertDialog.Builder upozorenje = new AlertDialog.Builder(this);
        upozorenje.setMessage("Greska u gramatici!\nProverite pravilo broj " + id).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
                System.exit(0);
            }
        })
                .setTitle("Upozorenje!").create();
        upozorenje.show();
    }
    public void ispis(View view, String s){
        AlertDialog.Builder upozorenje = new AlertDialog.Builder(this);
        upozorenje.setMessage(s).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
                System.exit(0);
            }
        })
                .setTitle("Upozorenje!").create();
        upozorenje.show();
    }



}