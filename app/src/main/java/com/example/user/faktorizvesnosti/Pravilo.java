package com.example.user.faktorizvesnosti;


import java.io.Serializable;
import java.security.PublicKey;


public class Pravilo implements Serializable {

    private double MB_ =0;        //MB' je MB_          //faktorI kod simulatora
    private double MD_ =0;
    private double MB = 0;
    private double MD = 0;
    public ListaPreduslova preduslov;
    private Zakljucak zakljucak;
    private int redniBr;

    public Pravilo() {
        preduslov = new ListaPreduslova();
        zakljucak = new Zakljucak();
    }



    public double getMB_() {
        return MB_;
    }



    public double getMD_() {
        return MD_;
    }
    public Zakljucak getZakljucak() {
        return zakljucak;
    }
    public double getMB() {
        return MB;
    }
    public double getMD() {
        return MD;
    }
    public int getRedniBr() {
        return redniBr;
    }



    public void setMD_(double MD_) {
        this.MD_ = MD_;
    }
    public void setMB_(double MB_) {
        this.MB_ = MB_;
    }
    public void setZakljucak(Zakljucak zakljucak) {
        this.zakljucak = zakljucak;
    }
    public void setMB(double MB) {
        this.MB = MB;
    }
    public void setMD(double MD) {
        this.MD = MD;
    }
    public void setRedniBr(int redniBr) {
        this.redniBr = redniBr;
    }

    public void dodajPreduslov(String s){
        preduslov.dodaj(s);
    }

    public void setujRedneBrojeveZiP(int a){
        zakljucak.setRedniBroj(a);
        setRedniBr(a);
    }

    public void reset(){
        setMB(0.0);
        setMD(0.0);
        setRedniBr(0);
        setZakljucak(new Zakljucak());
        preduslov = new ListaPreduslova();

    }

    public String toSting(){
        double broj;
        if (getMB_()!=0)broj=getMB_();
        else broj=getMD_();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("P" + redniBr + "/n" + "AKO /n");
        stringBuilder.append(preduslov + "/n ONDA (" + broj + ") /n" );
        stringBuilder.append(zakljucak);
        return stringBuilder.toString();
    }

}
