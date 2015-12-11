package com.example.user.faktorizvesnosti;


import java.util.LinkedList;
import java.util.StringTokenizer;

public class Stablo {
    private String operacija="I";
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

    public double racunajMB(){
        if (operacija.equals("ILI")){
            double max=-2.0;
            if (operandi.size()!=0)
                for (int i = 0;i<operandi.size();i++)
                    max=Math.max(operandi.get(i).getMB(), max);
            if (dete.size()!=0){
                for (int i =0;i<dete.size();i++)
                    max=Math.max(max,dete.get(i).racunajMB());
            }
            return max;
        }
        else if (operacija.equals("I")){
            double min = 2.0;
            if (operandi.size()!=0)
                for (int i = 0;i<operandi.size();i++)
                    min=Math.min(operandi.get(i).getMB(), min);
            if (dete.size()!=0){                                                                    //zasto je ovde usao?????
                for (int i =0;i<dete.size();i++)
                    min=Math.max(min,dete.get(i).racunajMB());
            }
            return min;
        }
        else {          //sluvaj kada se preduslov pravila sastoji iz samo jednog elementa
            return operandi.getFirst().getMB();
        }
    }


    public double racunajMD(){      //npr MD(eP1)
        if (operacija.equals("ILI")){
            double min = 2.0;
            if (operandi.size()!=0)
                for (int i = 0;i<operandi.size();i++)
                    min=Math.min(operandi.get(i).getMD(), min);
            if (dete.size()!=0){
                for (int i =0;i<dete.size();i++)
                    min=Math.max(min,dete.get(i).racunajMD());
            }
            return min;
        }
        else if (operacija.equals("I")){
            double max=-2.0;
            if (operandi.size()!=0)
                for (int i = 0;i<operandi.size();i++)
                    max=Math.max(operandi.get(i).getMD(),max);
            if (dete.size()!=0){
                for (int i =0;i<dete.size();i++)
                    max=Math.max(max,dete.get(i).racunajMD());
            }
            return max;
        }
        else {
            return operandi.getFirst().getMD();
        }

    }



    public int getNum(){
        return num;
    }

}
