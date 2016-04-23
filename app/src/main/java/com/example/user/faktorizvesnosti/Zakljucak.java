package com.example.user.faktorizvesnosti;


import java.util.StringTokenizer;


public class Zakljucak{

    private String naziv=null;  //naziv zakljucka
    private String pravila; //P1,P2,...  ako ima vise od jednog pravila koja vode do zakljucka    ako en isto je kao i redni broj...
    private double CF = -2.0;   //faktor izvesnosti zakljucka
 //   private double MB=0;
//    private double MD=0;
    private double MB=2;
    private double MD=2;
     private int redniBr = 0;        //mozda i ne treba...
     private int brPravila=1; //brj pravila iz kojih sledi zakljucak

    public Zakljucak(String naziv) {
        this.naziv = naziv;
    }
    public Zakljucak(){}
    public Zakljucak(Zakljucak z){
        this.naziv=z.getNaziv();
        this.pravila=z.getPravila();
        this.CF=z.getFaktorI();
        this.MB=z.getMB();
        this.MD=z.getMD();
        this.redniBr=z.getRedniBroj();
        this.brPravila=z.getBrPravila();
    }


    public String getNaziv(){return naziv;}
    public double getFaktorI(){return CF;}
    public double getMB(){return MB;}
    public double getMD(){return MD;}
    public int getRedniBroj(){return redniBr;}
    public int getBrPravila(){return brPravila;}
    public String getPravila(){return pravila;}
//    public void getBrPravila(int brPravila){this.brPravila= brPravila;}

    public void setNaziv(String naziv){this.naziv = naziv;}
    public void setFaktorI(double faktorIzvesnosti){this.CF = faktorIzvesnosti;}
    public void setMB(double MB){this.MB = MB;}
    public void setMD(double MD){this.MD = MD;}
    public void setRedniBroj(int redniBr){this.redniBr = redniBr;}

    public void setPravila(String pravila) {this.pravila = pravila;}
    public void dodajPravilo(String p){this.pravila = this.pravila + p;}

    public void povecajBRojPravila(){
        this.brPravila+=1;
    }

    public String toString(){return naziv;}

    public boolean jednako(Zakljucak z){
        return this.naziv.equals(z.naziv);
    }






    public void izracunaj(ListaPravila2 listaPravila, ListaZakljucaka2 listaZakljucaka){
        StringTokenizer token =new StringTokenizer(pravila);
        for (int i=0;i<brPravila;i++){                     //koliko pravila vode do istog zakljucka
            int tek=Integer.parseInt(token.nextToken());    //vraca br pravila koje vodi do zakljucka

            Resenje.poruka.append("MB'(z"+this.redniBr+",eP"+tek+") \n i \n"
                    +"MD'(z"+this.redniBr+",eP"+tek+")"+
                    "su zadati u postavci zadatka\n");
            Resenje.poruka.append("\nKorak " + Resenje.korak++ + "\n");
            Resenje.poruka.append("Da bi smo izracunali MB(ep"+tek+") \n i \n " +
                    "MD(ep"+tek+") \n moramo odrediti meru poverenja i meru nepoverenja " +
                    "svih elemenata pretpostavke.\n");

            Pravilo tekPravilo=listaPravila.get(tek-1);     //dohvata to pravilo
            //prolazi se kroz preduslove tekuceg pravila da se vidi da li su inicijalizovani
            for (int j = 0;j<tekPravilo.preduslov.size();j++){
                String tekString =tekPravilo.preduslov.get(j).getNaziv();
                ElemPreduslov tekElem=tekPravilo.preduslov.nadji(tekString);
                 if (2==tekElem.getMB() && 2== tekElem.getMD()){//nadjen je preduslov koji je u stvari zakljucak
                    Zakljucak tekZakljucak = listaZakljucaka.nadji(tekElem.getNaziv());
                    Resenje.poruka.append("\nKorak " + Resenje.korak++ + "\n");
                    Resenje.poruka.append("Elemenat:\n"
                            +tekZakljucak.getNaziv()+"\n" +
                            "pretpostavke pravila P" +tekPravilo.getRedniBr() +"\n" +
                            "je zakljucak nekog drugog\n" +
                            "pravila pa njegov faktor izvesnosti \n" +
                            "moramo da izracunamo.\n");
                    tekZakljucak.izracunaj(listaPravila, listaZakljucaka);
                    //izracunao je zakljucka koji je u stvati preduslov
                    //sad treba upisati te vrednosti u tekElem


                    tekElem.setMB(tekZakljucak.getMB());
                    tekElem.setMD(tekZakljucak.getMD());
                     Resenje.poruka.append("\nKorak " + Resenje.korak++ + "");
                    Resenje.poruka.append("\nSada kada smo izracunali mere poverenja i nepoverenja " +
                            "elementa\n"+tekZakljucak.getNaziv()+"\nmozemo se vratiti na predhodno " +
                            "izracunavanje.\n");
                 }
                else {
                     if (tekElem.getMB()==2)tekElem.setMB(0);       //ako su na default vrednosti tj ako nisu menjani staviti ih na 0
                     if (tekElem.getMD()==2)tekElem.setMD(0);
                    Resenje.poruka.append("\nKorak " + Resenje.korak++ + "\n");
                    Resenje.poruka.append("Element: \n"+tekString +"\n pretpostavke pravila P"+
                        tekPravilo.getRedniBr()+ " je opazanje ciji je faktor izvesnosti zadat u " +
                            "postavci i ne moramo ga racunati.");
                    Resenje.poruka.append("\nMera poverenja u njega je:\n MB("+
                        tekString+")="+ tekElem.getMB()+"\n a mera nepoverenja u njega je: MD("+
                        tekString +")=" + tekElem.getMD()+"\n");
                }

                /*

                //ovde su svi preduslovi definisani tj nisu zakljucci
                //sad treba izracunati MB(eP1) i MD(eP1)
                //racunamo preko stabla

                double broj=tekPravilo.getKoren().racunajMB();
                tekPravilo.setMB_P(broj);
                double broj1=tekPravilo.getKoren().racunajMD();
                tekPravilo.setMD_P(broj1);

                //MB(z1,eP1) i MD(z1,eP1)

                double broj2=tekPravilo.getMB_() * tekPravilo.getMB_P();
                tekPravilo.setMB(broj2);

                double broj3=tekPravilo.getMB_P()-tekPravilo.getMD_P();
                broj3=tekPravilo.getMD_() * Math.max(0,broj3);
                tekPravilo.setMD(broj3);
*/
            }
            Resenje.poruka.append("\nKorak " + Resenje.korak++ + "\n");
            Resenje.poruka.append("Posto smo odredili meru poverenje i meru nepoverenja" +
                    " svih elemenata pretpostavke pravila P" + tekPravilo.getRedniBr() +
                    " posebno, mozemo izracunati meru poverenja: \n MB(eP" + tekPravilo.getRedniBr() +
                    ")\n i meru nepoverenja \n MD(eP" + tekPravilo.getRedniBr()+") \npretpostavke " +
                    "pravila P"+ tekPravilo.getRedniBr()+ ".\n");


            //sada su svi preduslovi izracunati
            //sad treba izracunati MB(eP1) i MD(eP1)
            //racunamo preko stabla
            StringBuilder stringBuilder=new StringBuilder();
            double broj=tekPravilo.getKoren().racunajMB(stringBuilder);                             //racunajMB

            Resenje.poruka.append("\nKorak " + Resenje.korak++ + "\n");
            Resenje.poruka.append("MB(eP" + tekPravilo.getRedniBr() +
                    ") = ");
            if (tekPravilo.getKoren().getOperacija().equals("I"))
                Resenje.poruka.append("min("+tekPravilo.getIzraz()+") = ");// I-min
            else
                Resenje.poruka.append("max("+tekPravilo.getIzraz()+") = ");// ILI-max

            Resenje.poruka.append(stringBuilder + " = " + broj + "\n");
            tekPravilo.setMB_P(broj);


            stringBuilder = new StringBuilder();
            double broj1=tekPravilo.getKoren().racunajMD(stringBuilder);                            //racunajMD
            Resenje.poruka.append("MD(eP" + tekPravilo.getRedniBr() +
                            ") = " );
            if (tekPravilo.getKoren().getOperacija().equals("I"))
                Resenje.poruka.append("max("+tekPravilo.getIzraz()+") = ");//I-max
            else
                Resenje.poruka.append("min("+tekPravilo.getIzraz()+") = ");//ILI-min



            Resenje.poruka.append(stringBuilder + " = " + broj1 + "\n");
            tekPravilo.setMD_P(broj1);

            //MB(z1,eP1) i MD(z1,eP1)

            Resenje.poruka.append("\nKorak " + Resenje.korak++ + "\n");
            Resenje.poruka.append("Mera poverenja i nepoverenja zakljucka: \n" + getNaziv()+
                    "\n na osnovu pravila P"+tekPravilo.getRedniBr()+" se sada mogu izracunati" +
                    " na osnovu formula:\n MB(z"+getRedniBroj()+",eP"+tekPravilo.getRedniBr()+
                    ") = MB'(z"+getRedniBroj()+",eP"+tekPravilo.getRedniBr()+") * MB(eP"+
                    tekPravilo.getRedniBr()+") \ni \n " +

                    " MD(z"+getRedniBroj()+",eP"+tekPravilo.getRedniBr()+
                    ") = MD'(z"+getRedniBroj()+",eP"+tekPravilo.getRedniBr()+") * MD(eP"+
                    tekPravilo.getRedniBr()+")");

            Resenje.poruka.append("\nGde su:MB'(z" + getRedniBroj() + ",eP" + tekPravilo.getRedniBr() + ") - "
                    + "mera poverenja zakljucka z" + getRedniBroj() + " na osnovu pravila P" +
                    tekPravilo.getRedniBr() + "\nMD'(z" + getRedniBroj() + ",eP" + tekPravilo.getRedniBr() +
                    ")- " + "mera nepoverenja zakljucka z" + getRedniBroj() + " na osnovu pravila P" +
                    tekPravilo.getRedniBr() + "\n u slucaju potpune izvesnosti pravila P" +
                    tekPravilo.getRedniBr() + "\n");

            double broj2=tekPravilo.getMB_() * tekPravilo.getMB_P();
            tekPravilo.setMB(broj2);

            Resenje.poruka.append("MB(z" + getRedniBroj() + ",eP" + tekPravilo.getRedniBr() +
                    ") = " + tekPravilo.getMB_P() + " * " + tekPravilo.getMB_() + " = " + broj2
                    + "\n");

            double broj3=tekPravilo.getMB_P()-tekPravilo.getMD_P();
            broj3=tekPravilo.getMD_() * Math.max(0,broj3);
            tekPravilo.setMD(broj3);

            Resenje.poruka.append("MD(z" + getRedniBroj() + ",eP" + tekPravilo.getRedniBr() +
                    ") = " + tekPravilo.getMD_P() + " * " + tekPravilo.getMD_() + " = " + broj3
                    + "\n");
        }

        //ovde se racuna MB i MB za zakljucak

        token =new StringTokenizer(pravila);

        //zakljucak.MB predstavlja MB(z1,eP1) tj pravilo.MB



        /*
        int tek=Integer.parseInt(token.nextToken());    //vraca br pravila koje vodi do zakljucka
        Pravilo tekPravilo=listaPravila.get(tek-1);     //dohvata to pravilo
        double broj=tekPravilo.getMB();
        setMB(broj);
        broj=tekPravilo.getMD();
        setMD(broj);


        if (brPravila>1) {      //ako je dva pravila vode do ovog zakljucka racuan se kumulativno
            tek = Integer.parseInt(token.nextToken());    //vraca br pravila koje vodi do zakljucka
            tekPravilo = listaPravila.get(tek - 1);     //dohvata to pravilo

            broj = getMB() + tekPravilo.getMB() - getMB() * tekPravilo.getMB();
            setMB(broj);
            broj=getMD() + tekPravilo.getMD() - getMD() * tekPravilo.getMD();
            setMB(broj);
        }
        broj=getMB()-getMD();
        setFaktorI(broj);
*/


        Resenje.poruka.append("\nKorak " + Resenje.korak++ + "\n");
        Resenje.poruka.append("Sada na osnovu pocetne formule racunamo faktor izvenosti zakljucka:\n" +
                "z = " + getNaziv()+"\n na onovu pravila ");

        if (brPravila==1){
            int tek=Integer.parseInt(token.nextToken());    //vraca br pravila koje vodi do zakljucka
            Pravilo tekPravilo=listaPravila.get(tek-1);     //dohvata to pravilo

            Resenje.poruka.append(tekPravilo.toSting() + "\n\n");

            double broj1=tekPravilo.getMB();
            setMB(broj1);
            double broj2=tekPravilo.getMD();
            setMD(broj2);
            Resenje.poruka.append("CF(z" + getRedniBroj() +") = MB(z"+ getRedniBroj()+
            ",eP"+tek+") - MD(z" + getRedniBroj()+",eP" + tek+") = " );
        }
        else{
            int tek=Integer.parseInt(token.nextToken());    //vraca br pravila koje vodi do zakljucka
            Pravilo tekPravilo=listaPravila.get(tek - 1);     //dohvata to pravilo
            double a1=tekPravilo.getMB();
            double a2=tekPravilo.getMD();

            int tek1 = Integer.parseInt(token.nextToken());    //vraca br pravila koje vodi do zakljucka
            tekPravilo = listaPravila.get(tek1 - 1);     //dohvata to pravilo
            double a3 =  a1 + tekPravilo.getMB() - a1 * tekPravilo.getMB();
            double a4 = a2 + tekPravilo.getMD() - a2 * tekPravilo.getMD();
            setMB(a3);
            setMD(a4);
            Resenje.poruka.append("\n Do zakljucka: \n z" + getRedniBroj() + " = " + getNaziv() + "\n" +
                    "vode " + getBrPravila() + " pravila\n zbog toga moramo izracunati zbirne meru poverenja " +
                    "i nepoverenja zakljucka:\n z" + getRedniBroj() + "\n pomocu formula: \n \n " +
                    "Zbirnu meru poverenja \n MBcum(z");
            Resenje.poruka.append("Zbirna mera poverenja\n" +
                    "MBcum(z, e1,2)= 0 ako je MDcum(z, e1,2)= 1\n" +
                    "MBcum(z, e1,2)= \n" +
                    "   MB(z,e1) + MB(z,e2) - MB(z,e1) * MB(z,e2)\n" +
                    "\n" +
                    "Zbirna mera nepoverenja\n" +
                    "MDcum(z, e1,2)= 0 ako je MBcum(z, e1,2)= 1\n" +
                    "MDcum(z, e1,2)= \n" +
                    "   MD(z,e1) + MD(z,e2) - MD(z,e1) * MD(z,e2)\n" +
                    "\n" +
                    "a zatim i zbirni faktor izvesnosti\n" +
                    "na osnovu formule:\n" +
                    "CF(z, e1,2)= MBcum(z, e1,2) - MDcum(z, e1,2)\n" +
                    "\n" +
                    "Gde su e1 i e2 dve nezavisne pretpostavke\n\n");

            Resenje.poruka.append("Zbirna mera poverenja \nMBcum(z"+getRedniBroj()+",eP"+tek+
                    "ep"+tek1+") = " +
                            "MB(z"+getRedniBroj()+",eP"+tek+") + " +
                            "MB(z"+getRedniBroj()+",eP"+tek1+") - " +
                            "MB(z"+getRedniBroj()+",eP"+tek+") * " +
                            "MB(z"+getRedniBroj()+",eP"+tek1+") = "+
                            a1 +" + "+ tekPravilo.getMB()+" - "+  a1 +" * "+
                            tekPravilo.getMB()+" = "+ a3);

            Resenje.poruka.append("\nZbirna mera nepoverenja \nMDcum(z"+getRedniBroj()+",eP"+tek+
                    "ep"+tek1+") = " +
                    "MD(z"+getRedniBroj()+",eP"+tek+") + " +
                    "MD(z"+getRedniBroj()+",eP"+tek1+") - " +
                    "MD(z"+getRedniBroj()+",eP"+tek+") * " +
                    "MD(z"+getRedniBroj()+",eP"+tek1+") = "+
                    a2 +" + "+ tekPravilo.getMD()+" - "+  a2 +" * "+
                    tekPravilo.getMD()+" = "+ a4);

            Resenje.poruka.append("\nCF(z" + getRedniBroj() +") = " +
                    "MBcum(z" + getRedniBroj() + ",eP" + tek +"eP"+tek1+") -"+
                    "MDcum(z" + getRedniBroj() + ",eP" + tek +"eP"+tek1+") = "+
                    a3+" - "+ a4+ " = " );

        }
        double cf= getMB()-getMD();
        setFaktorI(cf);
        Resenje.poruka.append(cf);


        }





}
