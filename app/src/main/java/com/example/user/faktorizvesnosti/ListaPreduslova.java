package com.example.user.faktorizvesnosti;


import java.io.Serializable;

public class ListaPreduslova implements Serializable {

    private static class ElemPreduslov {
        public String naziv;
        public ElemPreduslov sled = null;

        //mozda

        public ElemPreduslov(String s){naziv = s;}
        public ElemPreduslov(String s, ElemPreduslov e){
            naziv = s;
            sled = e;
        }

    }


    private ElemPreduslov prvi = null, posl = null; //proveri sta se desava ovde

    public ListaPreduslova(){
        prvi = posl = null;
    }
    public void dodaj(String s) {

        ElemPreduslov e = new ElemPreduslov(s);
        if (null == prvi) prvi = posl = e;
        else {
            posl.sled = e;
            posl = e;
        }
    }



    public void izbaci(ElemPreduslov e){        //vidi da li treba boolean da vidis da li je uopste i postojao
        ElemPreduslov tek = prvi, pret = null;
        while (null!=tek)
            if (tek.naziv.equals(e.naziv)){
                pret = tek; tek = tek.sled;
            }
            else {
                tek = tek.sled;
                if (null==pret)prvi=tek;
                else pret.sled=tek;
            }
    }

    public void isprazni(){
        prvi = posl = null;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        ElemPreduslov tek = prvi;
        while (null != tek) {
            stringBuilder.append(tek.naziv + "\n");
            tek = tek.sled;
        }
        return stringBuilder.toString();

    }
}
