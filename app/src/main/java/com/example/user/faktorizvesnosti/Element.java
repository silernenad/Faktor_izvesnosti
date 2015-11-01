package com.example.user.faktorizvesnosti;


public class Element {

    private String naziv;
    private int MB=0;
    private int MD=0;

    public Element() {

    }

    public Element getElement(){return new Element();}
    public String getNaziv() {return naziv;}

    public int getMB() {return MB;}
    public int getMD() {return MD;}

    public void setNaziv(String naziv) {this.naziv = naziv;}
    public void setMB(int MB) {this.MB = MB;}
    public void setMD(int MD) {this.MD = MD;}

}
