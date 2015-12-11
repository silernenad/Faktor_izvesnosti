package com.example.user.faktorizvesnosti;

import java.util.LinkedList;


public class ListaPravila2 extends LinkedList<Pravilo> {



    public ListaPravila2() {
       super();
    }


    public String toSting(){
        Pravilo pravilo = getFirst();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i =0; pravilo!=null ;i++) {
            stringBuilder.append(pravilo.toSting() + "\n");
            pravilo = get(i);
        }
        return stringBuilder.toString();
    }

    public void urediPreduslove(ListaZakljucaka2 listaZakljucaka){
        Pravilo tekPtavilo;
        for (int i = 0;i<size();i++){                   //ide se kroz listu pravila
            tekPtavilo=get(i);
            ElemPreduslov tekElem;
            for (int j = 0;j<tekPtavilo.preduslov.size();j++){//ide se kroz listu preduslova tekPrav
                tekElem=tekPtavilo.preduslov.get(j);
                Zakljucak tekZak = null;
                tekZak=listaZakljucaka.nadji(tekElem.getNaziv());//gleda se da li je preduslov zakljucak
                if (null!=tekZak){
                    tekElem.setMB(tekZak.getMB());
                    tekElem.setMD(tekZak.getMD());
                }
            }
        }
    }



}
