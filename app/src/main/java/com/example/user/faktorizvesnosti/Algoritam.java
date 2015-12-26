package com.example.user.faktorizvesnosti;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Algoritam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algoritam);

        TextView algoritam =  (TextView) findViewById(R.id.algoritamText);
        algoritam.setText(
                       "NACIN UNOSA: \n"+
                       "\n\n"     +
                               "U drugoj aktovnosti u tekstualnom polju sa nazovom Pravila treba" +
                               "uneti pravila postujuci gramatiku.\n Klikom na dugme " +
                               "DEFINISITE PRAVILA I ZAKLJUCAK prelazi se na sledecu aktivnost.\n" +
                               "U gornjem tekstualnom polju pojavice se cinjenice i pored njih" +
                               " treba uneti meru poverenja za svaku od njuh, u opsegu od 0 do 1. \n" +
                               "Udonjem tekstualnom polju treba uneti zakljucak za koji se trazi" +
                               "faktor izvesnosti.\n"
                          +

                        "ALGORITAM: \n" +
                        "1)Model faktora izvesnosti bio je najpre razvijen u ekspertskom sistemu Mycin, MIT.     \n" +
                        "2)Pomoću njega se kvantifikuje stepen poverenja u neki zaključak (hipotezu) na osnovu     \n" +
                        "   datog skupa događaja, pojava, činjenica, opažanja.                                     \n\n" +

                        "3)Definišu se dva pojma MB(h,e)i MD(h,e), koja predstavljaju subjektivne verovatnoće.     \n" +
                        "4)Umesto tradicionalnih metoda verovatnoće, teško da se tačno procene-Bayes'ovo pravilom.  \n\n" +

                        "5)Svakom pravilu u sistemu se dodeljuje faktor izvesnosti koji se zasniva na ekspertskoj  \n" +
                        "  proceni i intuiciji-CF i komponenta faktora izvesnosti.                                 \n\n" +

                        "6)Pravila moraju da budu tako strukturirana da bilo koje pravilo doprinosi poverenju ili  \n" +
                        "  nepoverenju u dati zaključak. Komponenta faktora izvesnosti se dobija na sledeći način: \n" +
                        "  CFcomp(h,e) = MBcomp(h,e) -MDcomp(h,e)                                                  \n\n" +

                        "7)Izračunavanje kumulativnog faktora izvesnosti je potrebno kada ima više pravila koja    \n" +
                        "  doprinose za ili protiv zaključka.                                                      \n" +
                        "  Moraju se računati kumulativne vrednosti i za MB i MD.                                  \n" +
                        "8)Najpre se MB i MD inicijalizuju na nulu, zatim se inkrementalno uključuju efekti svakog \n" +
                        "  primenjivanog pravila.                                                                  \n" +
                        "  Svaki put kada se razmatra dodatno pravilo, izračunavaju se nove vrednosti za MB i MD   \n" +
                        "  na osnovu efekta novog pravila uz postojeće vrednosti.                                  \n" +
                        "9)Kombinovanje se može izvesti na osnovu ograničenja da je skup opažanja koji postoji     \n" +
                        "  međusobno nezavisan. Ako su međusobno zavisni, tada se moraju desiti u istom pravilu.   \n" +
                        "  Elementi posmatranja su s1, koji može da predstavlja skup pravila čije je kumulativno   \n" +
                        "  dejstvo ranije razmatrano, i s2, koji predstavlja novo pravilo čije efekte treba dodati \n" +
                        "  na postojeæe kumulativno poverenje.                                                     \n" +
                        "10)Ako postoji neizvesnost u pogledu uslova, tada CF koje je pridruzeno pravilu nije u    \n" +
                        "   potpunosti primenjivo, pa se CF mora revidirati.                                       \n\n" +
                        "11)Izračunavanje se zasniva na CF koje opisuje stepen poverenja u zahtevane uslove,       \n" +
                        "   t.j. opažanja za pravilo:                                                              \n" +
                        "   –Postoji manje ukupno poverenje u opažanje kada se ono dostavlja sistemu. Na primer,   \n" +
                        "       opažanje je zaključak testa sa pomešanim rezultatima.                              \n" +
                        "   –Opažanje je zaključak koji je proizašao iz izvršavanja drugog pravila sa nekim CF.    \n\n" +

                        " 1. Opseg vrednosti parametara su:                                                        \n" +
                        "	0 <= MB(h,e) <=1                                                                        \n" +
                        "	0 <= MD(h,e) <=1                                                                        \n" +
                        "	-1 <= CF(h,e) <=1                                                                       \n" +
                        " 2. Ako se radi o uzajamno isključivim hipotezama, tada je:                               \n" +
                        "	P(h/e)=1: MB(h,e)=1, MD(h,e)=0, CF(h,e)=1,\n" +
                        "	P(~h/e)=1: MB(h,e)=0, MD(h,e)=1, CF(h,e)=-1\n" +
                        " 3. Ako postoji odsustvo opažanja:                                                        \n" +
                        "		MB(h,e)=0 – ako e ne potvrđuje h,                                                  \n" +
                        "		MD(h,e)=0 – ako e ne osporava h,                                                   \n" +
                        "		CF(h,e)=0 – ako e niti ne potvrđuje niti osporava h                                \n" +
                        " Za kumulativni faktor izvesnosti:                                                        \n" +
                        "			CF(h,eh) = MB(h,ez) -MD(h,ep)                                                  \n" +
                        " važi:                                                                                    \n" +
                        "    h –hipoteza koja se posmatra,                                                         \n" +
                        "    eh –sva opažanja vezana za hipotezu h koja su uzeta u obzir do posmatranog trenutka,  \n" +
                        "    CF(h,eh) –kumulativni faktor izvesnosti za zaključak (hipotezu) h uz data opažanja eh,\n" +
                        "	 ez –opažanja koja podržavaju h,                                                       \n" +
                        "	 ep –opažanja koja osporavaju h,                                                       \n" +
                        "	 MB(h,ez) –kumulativna mera poverenja u h na bazi ez,                                  \n" +
                        "	 MD(h,ep) –kumulativna mera poverenja u h na bazi ep.                                  \n" +
                        " Za kumulativni faktor izvesnosti mogu se izvesti sledeæi zakljucci:                      \n" +
                        " 1. Ako postoje dva izvora opazanja definise se inkrementalno poverenje:                  \n" +
                        "	 MB(h,e1 e2) = 0 ako je MD(h,e1 e2) = 1,                                               \n" +
                        "	 MB(h,e1 e2) = MB(h,e1) + MB(h,e2)(1-MD(h,e1))                                         \n" +
                        " 2. U slucaju konjunkcije zakljucaka, odnosno hipoteza:                                   \n" +
                        "	 MB(h1 h2,e) = min(MB(h1,e), MB(h2, e))                                                \n" +
                        "	 MD(h1 h2,e) = max(MD(h1,e), MD(h2, e))                                                \n" +
                        " 3. U slucaju disjunkcije zakljucaka, odnosno hipoteza:                                   \n" +
                        "	 MB(h1 h2,e) = max(MB(h1,e), MB(h2, e))                                                \n" +
                        "	 MD(h1 h2,e) = min(MD(h1,e), MD(h2, e))                                                \n" +
                        " 4. Jačina opažanja:                                                                      \n" +
                        "	 MB(h,e) = MB’(h,e)*max(0, CF(e,ea))                                                   \n" +
                        "	 MD(h,e) = MD’(h,e)*max(0, CF(e,ea))                                                   \n" +
                        "gde važi:                                                                                 \n" +
                        "	 MB’(h,e) -MB(zaključka), pri potpunom poverenju u e,                                  \n" +
                        "	 CF(e,ea) –stvarno poverenje u e ustanovljeno prethodnim opažanjem ea                  \n"
        );


    }

    public void onClick(View view){

        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }

}
