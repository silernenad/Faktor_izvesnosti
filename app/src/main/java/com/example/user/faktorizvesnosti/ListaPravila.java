package com.example.user.faktorizvesnosti;

import java.io.Serializable;


public class ListaPravila implements Serializable {
        public class ElemPravilo{
            Pravilo p;
            ElemPravilo sled;
            ElemPravilo (Pravilo p){
              this.p  = p;
              }
          }

    private static ListaPravila instanca;
    ElemPravilo prvi, posl = null;




    public static ListaPravila getInstanca(){
        if (null==instanca)return instanca=new ListaPravila();
        else return instanca;
    }


    public void dodaj(Pravilo p) {       //bilo je boolean

        ElemPravilo novi = new ElemPravilo(p);

        if (null == prvi) prvi = posl = novi;
        else {
            posl.sled = novi;
            posl = novi;
        }

    }

    public String toString(){
        /*
        StringBuilder s= new StringBuilder();
        ElemPravilo tek = prvi;
        while (tek != null) {
            s.append(tek.p + "/n/n");
        }
        return s.toString();
        */
        return "uspeo da progura";
    }





}

