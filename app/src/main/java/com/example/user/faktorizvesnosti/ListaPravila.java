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


    public boolean dodaj(Pravilo p) {       //trebalo bi da je dobro...

        ElemPravilo novi = new ElemPravilo(p);
        if (null == prvi) prvi = posl = novi;
        else posl.sled = novi;
        posl = posl.sled;
        return true;
    }





}

