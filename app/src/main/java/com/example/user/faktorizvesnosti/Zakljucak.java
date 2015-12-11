package com.example.user.faktorizvesnosti;


import java.io.Serializable;
import java.util.StringTokenizer;


public class Zakljucak implements Serializable{

    private String naziv=null;
    private String pravila; //P1,P2,...  ako ima vise od jednog pravila koja vode do zakljucka
    private double CF = -2.0;
    private double MB=0;        //kada ima kumulativno inace je kao za preduslov
    private double MD=0;
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
            Pravilo tekPravilo=listaPravila.get(tek-1);     //dohvata to pravilo
            //prolazi se kroz preduslove tekuceg pravila da se vidi da li su inicijalizovani
            for (int j = 0;i<tekPravilo.preduslov.size();j++){
                ElemPreduslov tekElem=tekPravilo.preduslov.get(j);
                if (0==tekElem.getMB() && 0== tekElem.getMD()){//nadjen je preduslov koji je u stvari zakljucak
                    Zakljucak tekZakljucak = listaZakljucaka.nadji(tekElem.getNaziv());
                    tekZakljucak.izracunaj(listaPravila,listaZakljucaka);
                }

                //ovde su svi preduslovi definisani tj nisu zakljucci
                //sad treba izracunati MB(eP1) i MD(eP1)
                //racunamo preko stabla

                double broj=tekPravilo.getKoren().racunajMB();
                tekPravilo.setMB_P(broj);
                broj=tekPravilo.getKoren().racunajMD();
                tekPravilo.setMD_P(broj);

                //MB(z1,eP1) i MD(z1,eP1)

                broj=tekPravilo.getMB_() * tekPravilo.getMB_P();
                tekPravilo.setMB(broj);

                broj=tekPravilo.getMB_P()-tekPravilo.getMD_P();
                broj=tekPravilo.getMD_() * Math.max(0,broj);
                tekPravilo.setMD(broj);

            }

        }

        token =new StringTokenizer(pravila);

        //zakljucak.MB predstavlja MB(z1,eP1) tj pravilo.MB

        int tek=Integer.parseInt(token.nextToken());    //vraca br pravila koje vodi do zakljucka
        Pravilo tekPravilo=listaPravila.get(tek-1);     //dohvata to pravilo
        double broj=tekPravilo.getMB();
        setMB(broj);
        broj=tekPravilo.getMD();
        setMD(broj);


        if (brPravila>1) {      //ako je dva pravila vode do ovog zakljucka racuan se kumulativno
            tek = Integer.parseInt(token.nextToken());    //vraca br pravila koje vodi do zakljucka
            tekPravilo = listaPravila.get(tek - 1);     //dohvata to pravilo

            broj = getMB() + tekPravilo.getMB() + getMB() * tekPravilo.getMB();
            setMB(broj);
            broj=getMD() + tekPravilo.getMD() + getMD() * tekPravilo.getMD();
            setMB(broj);
        }
        broj=getMB()-getMD();
        setFaktorI(broj);

        }





}
