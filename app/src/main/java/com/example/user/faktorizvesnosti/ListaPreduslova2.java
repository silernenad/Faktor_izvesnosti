package com.example.user.faktorizvesnosti;


import java.util.LinkedList;

public class ListaPreduslova2 extends LinkedList<String> {

    public ListaPreduslova2(){
        super();
    }

public String toString(){
    String s = getFirst();
    StringBuilder stringBuilder = new StringBuilder();


    for (int i =0; s!=null ;i++) {
        stringBuilder.append(s + "\n");
        s = get(i);

    }
    return stringBuilder.toString();
}

}
