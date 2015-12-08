package com.example.user.faktorizvesnosti;


import java.util.LinkedList;
import java.util.StringTokenizer;

public class Stablo {
    private String operacija;
    public LinkedList<ElemPreduslov> operandi;
    public LinkedList<Stablo> dete;
    private int num=0;


    public Stablo(){
        this.operandi= new LinkedList<ElemPreduslov>();
        this.dete = new LinkedList<Stablo>();
    }

    public void setOperacija(String s){
        this.operacija=s;
    }
    public void setNum(int a){
        this.num=a;
    }





    public int getNum(){
        return num;
    }


}
