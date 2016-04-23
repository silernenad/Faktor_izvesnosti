package com.example.user.faktorizvesnosti;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    StringBuilder stringBuilder = new StringBuilder();
    String opazanja="";
    static boolean file=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Button1
        Button dirChooserButton1 = (Button) findViewById(R.id.button1);
        dirChooserButton1.setOnClickListener(new View.OnClickListener() {
            String m_chosen;

            @Override
            public void onClick(View v) {
                /////////////////////////////////////////////////////////////////////////////////////////////////
                //Create FileOpenDialog and register a callback
                /////////////////////////////////////////////////////////////////////////////////////////////////
                SimpleFileDialog FileOpenDialog = new SimpleFileDialog(MainActivity.this, "FileOpen",
                        new SimpleFileDialog.SimpleFileDialogListener() {
                            @Override
                            public void onChosenDir(String chosenDir) {
                                // The code in this function will be executed when the dialog OK button is pushed
                                m_chosen = chosenDir;
                                Toast.makeText(MainActivity.this, "Chosen FileOpenDialog File: " +
                                        m_chosen, Toast.LENGTH_LONG).show();


                                try {
                                    FileInputStream fis = new FileInputStream(m_chosen);
                                    InputStreamReader isr = new InputStreamReader(fis);
                                    BufferedReader buff = new BufferedReader(isr);
                                    String line;
                                    StringTokenizer tokenizer;

                                    file = true;
                                    while ((line = buff.readLine()) != null) {
                                        tokenizer = new StringTokenizer(line);
                                        String temp = tokenizer.nextToken();
                                        if (temp.equals("AKO")) {
                                            stringBuilder.append(temp+"\n");
                                            while (tokenizer.hasMoreTokens()){
                                                temp = tokenizer.nextToken();
                                                stringBuilder.append(temp+"\n");
                                            }
                                       //     stringBuilder.append(temp);
                                       //    stringBuilder.append(line);
                                            stringBuilder.append("\n");
                                        } else {
                                            opazanja = opazanja + line + "\n";

                                        }

                                    }

                                } catch (IOException e) {

                                    e.printStackTrace();
                                }


                            }
                        });

                //You can change the default filename using the public variable "Default_File_Name"
                FileOpenDialog.Default_File_Name = "";
                FileOpenDialog.chooseFile_or_Dir();

                /////////////////////////////////////////////////////////////////////////////////////////////////


            }
        });

        /****************************************/
    }

    public void onClickPodaci(View view){

        Intent i = new Intent(this,UnosPravila.class);
        i.putExtra("mPravila",stringBuilder.toString());
        i.putExtra("mOpazanja",opazanja);
        startActivity(i);


    }

    public void onClickAlgoritam(View view){
        Intent i = new Intent(this,Algoritam.class);
        startActivity(i);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
/*
    public void gasi(View view){

            finish();
            System.exit(0);

    }
*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
