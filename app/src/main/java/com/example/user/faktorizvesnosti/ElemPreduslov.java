package com.example.user.faktorizvesnosti;


public class ElemPreduslov {
    private String naziv;
    private double MB = 0;
    private double MD = 0;

 //   public ElemPreduslov sled = null;


    public ElemPreduslov(){}
    public ElemPreduslov(String s){naziv = s;}


    public double getMB() {return MB;}
    public double getMD() {return MD;}
    public String getNaziv(){return naziv;}

    public void setMB(double MB) {this.MB = MB;}
    public void setMD(double MD) {this.MD = MD;}

}