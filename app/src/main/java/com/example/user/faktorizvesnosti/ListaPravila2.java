package com.example.user.faktorizvesnosti;

import java.util.LinkedList;


public class ListaPravila2 extends LinkedList<Pravilo> {



    public ListaPravila2() {
       super();
    }


    public String toSting(){
        Pravilo pravilo = getFirst();
        StringBuilder stringBuilder = new StringBuilder();
    /*    int i = 0;
        while (pravilo!=null) {
            stringBuilder.append(pravilo.toSting());
            pravilo=get(i++);
        }*/

        for (int i =0; pravilo!=null ;i++) {
            stringBuilder.append(pravilo.toSting() + "\n");
            pravilo = get(i);

        }
        return stringBuilder.toString();
    }

}
