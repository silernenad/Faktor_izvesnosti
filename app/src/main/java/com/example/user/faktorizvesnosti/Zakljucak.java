package com.example.user.faktorizvesnosti;


import java.io.Serializable;


public class Zakljucak implements Serializable{

    private String naziv=null;
    private String pravila; //e1,e2,...  ako ima vise od jednog pravila koja vode do zakljucka
    private double CF = -2.0;
    private double MB=0;
    private double MD=0;
    private int redniBr = 0;        //mozda i ne treba...
    private int brPravila=1; //brj pravila iz kojih sledi zakljucak

    public Zakljucak(String naziv) {
        this.naziv = naziv;
    }
    public Zakljucak(){}


    public String getNaziv(){return naziv;}
    public double getFaktorI(){return CF;}
    public double getMB(){return MB;}
    public double getMD(){return MD;}
    public int getRedniBroj(){return redniBr;}
    public int getBrPravila(){return brPravila;}
    public String getPravila(){return pravila;}
    public void getBrPravila(int brPravila){this.brPravila= brPravila;}

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
}
