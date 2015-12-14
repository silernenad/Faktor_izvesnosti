package com.example.user.faktorizvesnosti;


/**  OVA LISTA JE DOBRA   */
//stavio sam da bude lista<ElemPreduslov> umesto String...

import java.util.LinkedList;

public class ListaPreduslova2 extends LinkedList<ElemPreduslov> {

    public ListaPreduslova2(){
        super();
    }

    public String toString(){
        String s =super.toString();
        s=s.replace("[", "");
        s=s.replace("]", "");
        return s;
}

    public ElemPreduslov nadji(String s){
        for (int i=0;i<size();i++){
            if (s.equals(get(i).getNaziv()))
                return get(i);
        }
        return null;
    }



}
