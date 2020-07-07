package com.saaasaab.scott.spotter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class ChangeGoal extends Activity {
    private EditText changeGoalTextView;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_goal);

        changeGoalTextView = (EditText) findViewById(R.id.change_goal_input);
        Button submitGoalChanges = (Button) findViewById(R.id.change_goal_submit);

        submitGoalChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Integer.valueOf(changeGoalTextView.getText().toString()) < 1000000000) {

                    text = changeGoalTextView.getText().toString();
                    SharedPreferences sharedPreferences = getSharedPreferences("MySettings", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("goal", text);
                    //Toast.makeText(ChangeGoal.this, "Goal set to: " + text, Toast.LENGTH_SHORT).show();

                    editor.apply();

                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }


}
