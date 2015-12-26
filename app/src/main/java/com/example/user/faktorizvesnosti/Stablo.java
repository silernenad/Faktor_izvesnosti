package com.example.user.faktorizvesnosti;


import java.util.LinkedList;

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
    public String getOperacija(){
        return operacija;
    }

    public double racunajMB(StringBuilder b){
        double broj=0;
        if (operacija.equals("ILI")){
            b.append("max(");
            double max=-2.0;
            if (operandi.size()!=0)
                for (int i = 0;i<operandi.size();i++) {
                    if (operandi.get(i).getNegacija()){
                        max = Math.max(operandi.get(i).getMD(), max);
                        b.append(" -" +operandi.get(i).getMB()+",");
                    }
                    else {
                        max = Math.max(operandi.get(i).getMB(), max);
                        b.append(" " +operandi.get(i).getMB()+",");
                    }
                }
            if (dete.size()!=0){
                for (int i =0;i<dete.size();i++)
                    max=Math.max(max,dete.get(i).racunajMB(b));
            }
            broj= max;
            b.append(")");
        }
        else if (operacija.equals("I")){
            b.append("min(");
            double min = 2.0;
            if (operandi.size()!=0)
                for (int i = 0;i<operandi.size();i++) {
                    if (operandi.get(i).getNegacija()){
                        min = Math.min(operandi.get(i).getMD(), min);
                        b.append(" -" +operandi.get(i).getMB()+",");
                    }
                    else {
                        min = Math.min(operandi.get(i).getMB(), min);
                        b.append(" " +operandi.get(i).getMB()+",");
                    }
                }
            if (dete.size()!=0){
                for (int i =0;i<dete.size();i++)
                    min=Math.min(min,dete.get(i).racunajMB(b));
            }
            broj= min;
        }
        b.append(")");
        return broj;

    }


    public double racunajMD(StringBuilder b){      //npr MD(eP1)
        double broj = 0;
        if (operacija.equals("ILI")){
            b.append("min(");
            double min = 2.0;
            if (operandi.size()!=0){
                for (int i = 0;i<operandi.size();i++){
                    if (operandi.get(i).getNegacija()) {
                        min = Math.min(operandi.get(i).getMB(), min);
                        b.append(" -" +operandi.get(i).getMB()+",");
                    }
                    else {
                        min = Math.min(operandi.get(i).getMD(), min);
                        b.append(" "+operandi.get(i).getMB()+",");
                    }
                }
            }

            if (dete.size()!=0){
                for (int i =0;i<dete.size();i++)
                    min=Math.min(min,dete.get(i).racunajMD(b));
            }
            broj = min;


            b.append(")");
        }
        else if (operacija.equals("I")){
            b.append("max(");
            double max=-2.0;
            if (operandi.size()!=0)
                for (int i = 0;i<operandi.size();i++) {
                    if (operandi.get(i).getNegacija()) {
                        max = Math.max(operandi.get(i).getMB(), max);
                        b.append(" -" + operandi.get(i).getMB() + ",");
                    }
                    else {
                        max = Math.max(operandi.get(i).getMD(), max);
                        b.append(" " + operandi.get(i).getMB() + ",");
                    }
                }
            if (dete.size()!=0){
                for (int i =0;i<dete.size();i++)
                    max=Math.max(max,dete.get(i).racunajMD(b));
            }
            broj = max;
        }
        b.append(")");

        return broj;

    }



    public int getNum(){
        return num;
    }

}
