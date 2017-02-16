package com.example.struong.customchoicegenerator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChooseSet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_set);

        final Button bOkSet = (Button) findViewById(R.id.bOKSet);

            bOkSet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText etSetAnswer = (EditText) findViewById(R.id.etSetAnswer);
                    String setStr = etSetAnswer.getText().toString();
                    int setAnswer;
                    setAnswer = Integer.parseInt(setStr);

                    if(setAnswer == 0){
                        etSetAnswer.setError("Value must be at least 1");
                    }

                    //else if(){
                    // check if value is empty
                    //}
                    else{
                        Intent chooseOptIntent = new Intent(ChooseSet.this, ChooseOptions.class);
                        chooseOptIntent.putExtra("setAnswer", setAnswer);
                        ChooseSet.this.startActivity(chooseOptIntent);
                    }


                }

            });


    }
}
