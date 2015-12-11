package com.example.user.faktorizvesnosti;


import java.io.Serializable;
import java.security.PublicKey;
import java.util.StringTokenizer;


public class Pravilo implements Serializable {

    private double MB_ =0;          //MB'(z1,eP1)          //faktorI kod simulatora
    private double MD_ =0;
    private double MB = 0;          //MB(z1,eP1)
    private double MD = 0;
    private double MB_P = 0;         //MB(eP1)
    private double MD_P = 0;
    public ListaPreduslova2 preduslov;
 //   public ListaPreduslova preduslov;
    private Zakljucak zakljucak;
    private int redniBr;
    private Stablo koren;
    String izraz=" ";

    public Pravilo() {
      //  preduslov = new ListaPreduslova();
        preduslov = new ListaPreduslova2();
        zakljucak = new Zakljucak();
        koren = new Stablo();
    }



    public double getMB_() {
        return MB_;
    }
    public Stablo getKoren(){
        return koren;
    }



    public double getMD_() {
        return MD_;
    }
    public Zakljucak getZakljucak() {
        return zakljucak;
    }
    public double getMB() {
        return MB;
    }
    public double getMD() {
        return MD;
    }
    public int getRedniBr() {
        return redniBr;
    }
    public String getIzraz(){return izraz;}
    public double getMB_P(){return MB_P;}
    public double getMD_P(){return MD_P;}


    public void setMB_P(double broj){this.MB_P=broj; }
    public void setMD_P(double broj){this.MD_P=broj; }

    public void setMD_(double MD_) {
        this.MD_ = MD_;
    }
    public void setMB_(double MB_) {
        this.MB_ = MB_;
    }
    public void setZakljucak(Zakljucak zakljucak) {
        this.zakljucak = zakljucak;
    }
    public void setMB(double MB) {
        this.MB = MB;
    }
    public void setMD(double MD) {
        this.MD = MD;
    }
    public void setRedniBr(int redniBr) {
        this.redniBr = redniBr;
    }
    public void setIzraz(String a){
        this.izraz=a;
    }
    public void dodajIzraz(String a){
        this.izraz=this.izraz+a;
    }

    public void dodajPreduslov(String s){

        //preduslov.dodaj(s);
        ElemPreduslov e=new ElemPreduslov(s);
        preduslov.add(e);
    }

    public void setujRedniBroj(int a){
        zakljucak.setRedniBroj(a);
        setRedniBr(a);
    }

    public void reset(){
        setMB(0.0);
        setMD(0.0);
        setRedniBr(0);
        setZakljucak(new Zakljucak());
       // preduslov = new ListaPreduslova();

        preduslov.clear();
        setIzraz(" ");
    }


    public ElemPreduslov nadjiPreduslov(String s){
        ElemPreduslov rez = new ElemPreduslov();
        for (int i = 0; i<preduslov.size();i++){
            if (preduslov.get(i).getNaziv().equals(s)) {
                rez = preduslov.get(i);
                break;
            }
        }
        return rez;
    }

    public String toSting(){
        double broj;
        if (getMB_()!=0)broj=getMB_();
        else broj=getMD_();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("P" + redniBr + ":\n" + "AKO \n");
        stringBuilder.append(preduslov.toString() + "ONDA (" + broj + ") \n" );
        stringBuilder.append(getZakljucak().toString());
        return stringBuilder.toString();
        }


    public void uredi(){    //pravi stabli od Stringa preduslova
        String string=getIzraz();
        StringTokenizer ts = new StringTokenizer(string);

        Stablo koren=getKoren();
        while(ts.hasMoreTokens()){
            String s;
            s=ts.nextToken();
            switch (s){
                case "(":                //naisao na otvorenu zagradu ubacuje novo dete u stablo
                    koren.dete.add(new Stablo());
                    int a =koren.getNum()+1;
                    koren.setNum(a);

                    while (!(s=ts.nextToken()).equals(")")){
                        if (s.equals("I") || s.equals("ILI")){
                            koren.dete.getLast().setOperacija(s);
                        }
                        else
                            dodajOperand(koren.dete.getLast(), nadjiPreduslov(s));
                    }
                    break;
                case "I":
                    koren.setOperacija(s);
                    break;
                case "ILI":
                    koren.setOperacija(s);
                    break;
                default:
                    dodajOperand(koren, nadjiPreduslov(s));
                    break;
            }   //end switch
        }
    }

    public void dodajOperand(Stablo stablo, ElemPreduslov e){
        stablo.operandi.add(e);
    }




}
