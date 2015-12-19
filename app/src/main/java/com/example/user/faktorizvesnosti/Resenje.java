package com.example.user.faktorizvesnosti;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import java.util.StringTokenizer;

public class Resenje extends AppCompatActivity {

    static int id=1;
    static int idZak=1;
    static StringBuilder poruka= new StringBuilder();

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

        try {


            //TOKENIZACIJA OPAZANJA
            opazanjaMessage = opazanjaMessage.replace("(", "");
            opazanjaMessage = opazanjaMessage.replace(")", "");
            StringTokenizer opazanja = new StringTokenizer(opazanjaMessage);

            boolean nasao = false;

            while (opazanja.hasMoreTokens()) {

                String s = opazanja.nextToken();
                Pravilo tekPravilo;
                //nasao=false;
                String a = opazanja.nextToken();
                double broj = Double.parseDouble(a);

                for (int i = 0; i < listaPravila.size(); i++) {                 //obilazi sva pravila
                    tekPravilo = listaPravila.get(i);
                    String tekPred;
                    for (int j = 0; j < tekPravilo.preduslov.size(); j++) {    //obailazi sve preduslove
                        tekPred = tekPravilo.preduslov.get(j).getNaziv();
                        if (tekPred.equals(s)) {                                      //ovo je nesto sumnjivo
                            if (broj >= 0) tekPravilo.preduslov.get(j).setMB(broj);
                            else tekPravilo.preduslov.get(j).setMD(broj);
                            break;
                        }

                    }

                }
            }
        }catch (Exception e){
            String string = "Nisu uneti svi parametri!!!";
            ispis(this.findViewById(android.R.id.content), string);

        }


        //pravljenje stabla
        for (int i=0;i<listaPravila.size();i++){
            listaPravila.get(i).uredi();
        }

        //MD i MB zaljucaka pokazuju na MD i MB preduslova koji su u stvari zakljucci
        listaPravila.urediPreduslove(listaZakljucaka);


        //ZAKLJUCAK KOJI SE TRAZI
/*
        Zakljucak glavni=new Zakljucak(zakljucakMessage);
        for (int i=0;i<listaZakljucaka.size();i++){
            if (listaZakljucaka.get(i).getNaziv().equals(glavni.getNaziv()))
                glavni = listaZakljucaka.get(i);
            break;
        }
*/
        if (zakljucakMessage.equals(null)||listaZakljucaka.nadji(zakljucakMessage)==null){
            ispis(this.findViewById(android.R.id.content),
                    "Zakljucak nije unet ili ne postoji pod tim nazivom");
        }
        Zakljucak glavni=listaZakljucaka.nadji(zakljucakMessage);


        poruka.append("Racunamo faktor izvesnosti zakljucka: \n z" + glavni.getRedniBroj()+
                "="+glavni+"\n pomocu formule:\n");

        if (glavni.getBrPravila()==1){
            /*
            String pravila=glavni.getPravila();
            StringTokenizer a = new StringTokenizer(pravila);
            String tek;
            tek=a.nextToken();
            poruka.append("CF(z"+ glavni.getRedniBroj()+",eP"+tek+")="+
                "MB(z"+ glavni.getRedniBroj()+",eP"+tek+") - "+
                 "MD(z"+ glavni.getRedniBroj()+",eP"+tek+")  "
            );
            */
            poruka.append("CF(z"+ glavni.getRedniBroj()+",eP"+glavni.getPravila()+")="+
                            "MB(z"+ glavni.getRedniBroj()+",eP"+glavni.getPravila()+") - "+
                            "MD(z"+ glavni.getRedniBroj()+",eP"+glavni.getPravila()+")  "
            );
        }
        else {
             ts = new StringTokenizer(glavni.getPravila() );


            poruka.append("CF(z"+ glavni.getRedniBroj()+")=");
            StringBuilder stringBuilder=new StringBuilder();
            while (ts.hasMoreTokens()){
                stringBuilder.append("P"+ts.nextToken());
            }
             poruka.append("MBcum(z"+ glavni.getRedniBroj()+",e"+stringBuilder+") - "+
                     "MDcum(z"+ glavni.getRedniBroj()+",e"+stringBuilder+")");
        }


        poruka.append("\n na onovu pravila ") ;

        for (int i =0;i<glavni.getBrPravila();i++){
 //           StringTokenizer tokenizer=new StringTokenizer(glavni.getPravila());
            String tek=glavni.getPravila();
            ts=new StringTokenizer(tek);
            int brojP=Integer.parseInt(ts.nextToken());
            int brojZ=glavni.getRedniBroj();

            poruka.append(listaPravila.get(brojP - 1).toSting()+"\n\n")  ;

            poruka.append("Gde su:\n" +
                    "MB(z"+brojZ+",eP"+brojP+")- mera poverenje zakljucka z"+brojZ+
                    " na osnovu pravila P"+brojP+"\n" +
                    "MD(z"+brojZ+",eP"+brojP+")- mera nepoverenja zakljucka z"+brojZ+
                    " na osnovu pravila P"+brojP +
                    "\nMeru poverenja:\n" +
                    "MB(z"+brojZ+",eP"+brojP+")\n" +
                    "i mera nepoverenja:\n" +
                    "MD(z"+brojZ+",eP"+brojP+")\n" +
                    "zakljucka z"+ brojZ +" na osnovu pravila P"+brojP+"\n" +
                    "racunaju se pomocu formule:\n\n") ;

            poruka.append("MB(z"+brojZ+",eP"+brojP+") = MB'(z"+brojZ+",eP"+brojP+")* max(0,CF(eP"+brojP+"))\n" +
                    "MD(z"+brojZ+",eP"+brojP+") = MD'(z" +brojZ+",eP"+brojP+")* max(0,CF(eP"+brojP+"))\n" +
                    "\n" +
                    "Gde su:\n" +
                    "MB'( z"+brojZ+",eP"+brojP+")-mera poverenje zakljucka\n" +
                    " z"+brojZ+" na osnovu pravila P"+brojP+"\n" +
                    "MD'( z"+brojZ+",eP"+brojP+")-mera nepoverenja zakljucka\n" +
                    " z"+brojZ+" na osnovu pravila P"+brojP+"\n" +
                    "u slucaju potpune izvesnosti pretpostavke\n" +
                    "pravila P"+brojP+"\n" +
                    "\n" +
                    "CF(eP"+brojP+")-faktor izvesnosi pretpostavke \n" +
                    "pravila P"+brojP+"") ;
            if (i+1<glavni.getBrPravila())
                poruka.append("\ni pravila \n ");
        }

        //izracunavanje
        glavni.izracunaj(listaPravila, listaZakljucaka);
        resenjeText.setText(poruka);



        /********       ovako se poruka ispisuje u obaku na ekranu   *****/

//       String food = "tekst greske";
//        Toast.makeText(MainActivity.this, food, Toast.LENGTH_LONG).show();

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
                case "-":
                    s=ts.nextToken();
                    pravilo.dodajPreduslov(s);
                    pravilo.preduslov.getLast().setNegacija(true);
                    pravilo.dodajIzraz(s +" ");
                    break;

                default:
                    pravilo.dodajPreduslov(s);
                    pravilo.dodajIzraz(s +" ");
                    break;
            }

            s=ts.nextToken();
            if(s.equals("ONDA"))
                break;
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
    public void ispis(View view){
        AlertDialog.Builder upozorenje = new AlertDialog.Builder(this);
        upozorenje.setMessage("Greska u gramatici!\nProverite pravilo broj " + id).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
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
            }
        })
                .setTitle("Upozorenje!").create();
        upozorenje.show();
    }

}
