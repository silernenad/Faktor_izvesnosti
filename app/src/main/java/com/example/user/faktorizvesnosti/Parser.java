package com.example.user.faktorizvesnosti;


import java.util.StringTokenizer;

public class Parser {

    static int id=1;
    static int idZak=1;

    public Parser() {
    }

    public void unesiPravila(StringTokenizer ts,
                             ListaZakljucaka2 listaZakljucaka,ListaPravila2 listaPravila ){

        while (ts.hasMoreTokens()){
            Pravilo novoPravilo = new Pravilo();
            //pravilo koje ce se ubacivati u listu
            traziPreduslov(ts, novoPravilo, listaZakljucaka, listaPravila); //trazi jedno pravilo sa ulaza

            novoPravilo.setujRedniBroj(id++);

            listaPravila.addLast(novoPravilo);
        }


        //sredjivanje rednih brojeva

        for (int i = 0; i < listaZakljucaka.size(); i++) {
            listaZakljucaka.get(i).setRedniBroj(i+1);
        }

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

    public void unesiOpazanja(StringTokenizer opazanja,ListaPravila2 listaPravila){
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
    }



}
