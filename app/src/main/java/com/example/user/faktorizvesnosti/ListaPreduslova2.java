package com.example.user.faktorizvesnosti;


/**  OVA LISTA JE DOBRA   */

import java.util.LinkedList;

public class ListaPreduslova2 extends LinkedList<String> {

    public ListaPreduslova2(){
        super();
    }

public String toString(){
    String s =super.toString();
    s=s.replace("[", "");
    s=s.replace("]", "");
    return s;


}

}
