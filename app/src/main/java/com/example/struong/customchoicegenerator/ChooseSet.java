package com.example.struong.customchoicegenerator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChooseSet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_set);

        final int[] setAnswer = new int[1];
        final Button bOkSet = (Button) findViewById(R.id.bOKSet);
        final EditText etSetAnswer = (EditText) findViewById(R.id.etSetAnswer);


        bOkSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String setStr = etSetAnswer.getText().toString();
                setAnswer[0] = Integer.parseInt(setStr);
                Intent chooseOptIntent = new Intent(ChooseSet.this, ChooseOptions.class);
                chooseOptIntent.putExtra("setAnswer",setAnswer);
                ChooseSet.this.startActivity(chooseOptIntent);
            }
        });
    }



}
