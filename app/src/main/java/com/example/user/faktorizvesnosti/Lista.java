package com.example.user.faktorizvesnosti;




public class Lista {

    private Elem prvi = null, posl = null;

    public boolean dodaj(Elem e) {


        if (null == prvi) prvi = posl = e;
        else posl = e;
        posl = e;
        return true;
    }



    public void izbaci(Elem e){
        Elem tek = prvi, pret = null;
        while (null!=tek)
            if (tek.equals(e)){                      //izmeni
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

    public boolean prazna()
    {
        if (null==prvi) return false;
        else return true;
    }
}
