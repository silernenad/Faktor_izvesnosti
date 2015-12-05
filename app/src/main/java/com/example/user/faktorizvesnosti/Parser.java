package com.example.user.faktorizvesnosti;


import java.util.StringTokenizer;

public class Parser {

    static int id=1;

    public Parser() {
    }

    public void traziPreduslov(StringTokenizer ts, Pravilo pravilo,
                               ListaZakljucaka2 listaZakljucaka,ListaPravila2 listaPravila ){
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

            /**     UBACIVANJE ZAKLJUCKA U LISTU PRAVILA    **/

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
