package com.example.user.faktorizvesnosti;


import java.util.LinkedList;


public class Pravilo {

    private double MB_ =0;        //MB' je MB_          //faktorI kod simulatora
    private double MD_ =0;
    private double MB = 0;
    private double MD = 0;
    public ListaPreduslova preduslov;
    private Zakljucak zakljucak;
    private int redniBr;

    public Pravilo() {
        preduslov = new ListaPreduslova();
    }



    public double getMB_() {
        return MB_;
    }

    public void setMB_(double MB_) {
        this.MB_ = MB_;
    }

    public double getMD_() {
        return MD_;
    }

    public void setMD_(double MD_) {
        this.MD_ = MD_;
    }

    public Zakljucak getZakljucak() {
        return zakljucak;
    }

    public void setZakljucak(Zakljucak zakljucak) {
        this.zakljucak = zakljucak;
    }

    public double getMB() {
        return MB;
    }

    public void setMB(double MB) {
        this.MB = MB;
    }

    public double getMD() {
        return MD;
    }

    public void setMD(double MD) {
        this.MD = MD;
    }

    public int getRedniBr() {
        return redniBr;
    }

    public void setRedniBr(int redniBr) {
        this.redniBr = redniBr;
    }
}
