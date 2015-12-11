package com.example.user.faktorizvesnosti;

import java.util.LinkedList;

public class ListaZakljucaka2 extends LinkedList<Zakljucak> {

    public ListaZakljucaka2() {
        super();
    }

    public Zakljucak nadji(String s){
        for (int i=0;i<size();i++){
            if (s.equals(get(i).getNaziv()))
                return get(i);
        }
        return null;
    }

}
