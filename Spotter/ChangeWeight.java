package com.saaasaab.scott.spotter;

/**
 * Created by Scott on 6/29/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChangeWeight extends Activity {
    private EditText changeWeightTextView;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_weight);

        changeWeightTextView = (EditText) findViewById(R.id.change_weight_input);


        Button submitWeightChanges = (Button) findViewById(R.id.change_weight_submit);
        submitWeightChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent();
                text = changeWeightTextView.getText().toString();

                if (Integer.valueOf(changeWeightTextView.getText().toString()) < 2000) {
                    SharedPreferences sharedPreferences = getSharedPreferences("MySettings", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("weight", text);
                    editor.commit();

//                    intent.putExtra("New_Weight", text);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

    }
}