package com.example.user.faktorizvesnosti;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.util.*;

public class UnosPravila extends AppCompatActivity {


    int id =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unos_pravila);

    }

    public void onClickPravila(View view){

        Intent i = new Intent(this,Ostalo.class);
        final EditText pravilaInput = (EditText) findViewById(R.id.pravilaInput);
        String pravilaMessage = pravilaInput.getText().toString();                  //ulazni Sting

        i.putExtra("pravila", pravilaMessage);
     //   startActivity(i);


        /**************   DODATAK     **************/
/*
        String url = "http://howtodoinjava.com/java-initerview-questions";  ako nesto nije u redu moze i ovako da se deli
        StringTokenizer multiTokenizer = new StringTokenizer(url, "://.-");
        */

        /**  dodata1 **/


        ListaPravila listaPravila = new ListaPravila();         //lista svih pravila
        Pravilo novoPravilo = new Pravilo();                    //pravilo koje ce se ubacivati u listu
                                                //cita se sa ulaza i pravi se novo pravilo

        StringTokenizer ts = new StringTokenizer(pravilaMessage);
        while (ts.hasMoreTokens()){
            traziPreduslov(ts, novoPravilo); //trazi jedno pravilo sa ulaza
            novoPravilo.setujRedneBrojeveZiP(id++);
            listaPravila.dodaj(novoPravilo);

            /**ostaje da se jos nameste stekovi**/

            }





        i.putExtra("listapravila",listaPravila);    //salje listuPravial na ledeci activiy





        startActivity(i);



        }   /*****  END onClick() *******/


    public void traziPreduslov(StringTokenizer ts, Pravilo pravilo){
        while (ts.toString()!="AKO"){
            ts.nextToken();
        }

        while (ts.toString() !="ONDA") {     //mozda traba prebaciti na veliak slova!!!!!! toUper
           String s = ts.nextToken();
            switch (s){
                case "ILI":
                    ts.nextToken();
                    break;
                case "I":
                    ts.nextToken();
                    break;
                case "(":
                    ts.nextToken();
                    break;
                case ")":
                    ts.nextToken();
                    break;

                default:
                    pravilo.dodajPreduslov(ts.toString());
                    ts.nextToken();
                    break;
            }//switch end
        }

        String mb = ts.nextToken(); //dohvata se vrednost u zagradama i izbauju se zagrade
        mb.substring(1);
        mb.substring(mb.length()-1);
        double broj = Double.parseDouble(mb);//pretvara se u broj i setuej se MD' ili MD'
        if (broj>0) pravilo.setMB_(broj);
        else  pravilo.setMD_(broj);
        pravilo.setZakljucak(new Zakljucak( ts.nextToken()));   //dodaje se zakljucak
        return;

    }





}
