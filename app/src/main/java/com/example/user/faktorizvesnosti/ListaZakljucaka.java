package com.example.user.faktorizvesnosti;

import java.io.Serializable;


public class ListaZakljucaka implements Serializable {
    public class ElemZakljucak{
        Zakljucak zakljucak;
        ElemZakljucak sled;

        public ElemZakljucak(Zakljucak zakljucak){
            this.zakljucak=zakljucak;
        }



    }

    private static ListaPravila instanca;
    ElemZakljucak prvi, posl = null;


    public ListaZakljucaka(){
        prvi= posl=null;
    }

    public static ListaPravila getInstanca(){
        if (null==instanca)return instanca=new ListaPravila();
        else return instanca;
    }


    public boolean dodaj(Zakljucak p) {       //trebalo bi da je dobro...

        ElemZakljucak novi = new ElemZakljucak(p);
        if (null == prvi) prvi = posl = novi;
        else posl.sled = novi;
        posl = posl.sled;
        return true;
    }


}
