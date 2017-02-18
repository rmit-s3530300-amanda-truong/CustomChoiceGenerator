package com.example.struong.customchoicegenerator;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ChooseOptions extends AppCompatActivity {

    //create instances outside of inner class to not be declared final
    LinkedHashMap<Integer,LinkedList> optionMap = new LinkedHashMap<Integer, LinkedList>();
    LinkedList<String> optionSet = new LinkedList<String>();
    LinkedList<String> opNoS = new LinkedList<String>();
    int setNo = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_options);
        Bundle bundle = getIntent().getExtras();
        final TextView tvSetDisplay = (TextView) findViewById(R.id.tvSetDisplay);
        Button bBack = (Button) findViewById(R.id.bBack);

        Button bAddOp = (Button) findViewById(R.id.bAddOp);
        Button bFinish = (Button) findViewById(R.id.bFinish);
        Button bViewSet = (Button) findViewById(R.id.bViewSet);
        Button bChangeSet = (Button) findViewById(R.id.bChangeSet);
        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chooseSetIntent = new Intent(ChooseOptions.this, ChooseSet.class);
                ChooseOptions.this.startActivity(chooseSetIntent);
            }
        });

        //extract the data inside the bundle
        int setAnswer = bundle.getInt("setAnswer");

        for(int i= 1; i<=setAnswer; i++)
        {
            optionMap.put(i,null);
            opNoS.add(Integer.toString(i));
        }

        tvSetDisplay.setText("Set Number: 1");

        bChangeSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence[] sArray = opNoS.toArray(new CharSequence[opNoS.size()]);

                AlertDialog.Builder builder = new AlertDialog.Builder(ChooseOptions.this);
                builder.setTitle("Choose a set")
                        .setItems(sArray, new DialogInterface.OnClickListener(){
                           public void onClick(DialogInterface dialog, int which){
                               setNo = which+1;
                               tvSetDisplay.setText("Set Number: " + Integer.toString(setNo));
                           }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });

        bAddOp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                EditText etOption = (EditText) findViewById(R.id.etOption);
                String optionAns = etOption.getText().toString();
                if(optionAns.isEmpty())
                {
                    etOption.setError("Must enter an option");
                }
                else{
                    if(optionMap.get(setNo) == null)
                    {
                        optionMap.put(setNo, new LinkedList<String>());
                    }
                    optionMap.get(setNo).add(optionAns);
                    etOption.getText().clear();
                    Toast.makeText(getApplicationContext(),"Added", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bViewSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(optionMap.get(setNo) == null)
                {
                    Toast.makeText(getApplicationContext(),"No options added yet", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    for(Map.Entry<Integer, LinkedList> entry: optionMap.entrySet())
                    {
                        Integer key = entry.getKey();
                        System.out.println(key + " : " + entry.getValue());
                        //put values into an array
                    }

                    System.out.println(optionMap.keySet());
                    //optionSet = optionMap.values();

                    optionSet = optionMap.get(setNo);
                    CharSequence[] oArray = optionSet.toArray(new CharSequence[optionSet.size()]);
                    final boolean[] selected = new boolean[optionSet.size()];

                    for(int i=0; i<optionSet.size(); i++)
                    {
                        selected[i] = false;
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(ChooseOptions.this);
                    builder.setTitle("Set " + setNo + " Tick the options you want to delete and press OK");
                    builder.setMultiChoiceItems(oArray,null,new DialogInterface.OnMultiChoiceClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked)
                        {
                            selected[which] = isChecked;
                            if(isChecked)
                            {
                                String item = optionSet.get(which);
                                optionMap.get(setNo).remove(item);
                            }
                        }
                    });

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int id){

                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }

            }
        });

}
}
