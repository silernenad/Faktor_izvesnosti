package com.example.user.faktorizvesnosti;


public class Cinjenice extends ListaPreduslova {

    private static int ukId = 0;

    private int MB=0;
    private int MD=0;

    public int getMB() {
        return MB;
    }
    public  void  resetID(){
        ukId=0;
    }

    public void setMB(int MB) {
        this.MB = MB;
    }

    public int getMD() {
        return MD;
    }

    public void setMD(int MD) {
        this.MD = MD;
    }
}
