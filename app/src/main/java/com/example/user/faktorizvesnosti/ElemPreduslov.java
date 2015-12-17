package com.example.user.faktorizvesnosti;


public class ElemPreduslov {
    private String naziv;
    private double MB = 0;      //MB(e1)
    private double MD = 0;
    boolean negacija=false;




    public ElemPreduslov(){}
    public ElemPreduslov(String s){naziv = s;}


    public double getMB() {return MB;}
    public double getMD() {return MD;}
    public String getNaziv(){return naziv;}
    public boolean getNegacija(){return negacija;}

    public void setMB(double MB) {this.MB = MB;}
    public void setMD(double MD) {this.MD = MD;}
    public void setNegacija(boolean a){this.negacija=a;}

    public String toString(){
        return naziv;
    }

}