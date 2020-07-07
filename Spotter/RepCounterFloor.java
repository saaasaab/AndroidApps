package com.saaasaab.scott.spotter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Scott on 6/19/2017.
 */

public class RepCounterFloor extends Activity {
    public double[] separate = {};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rep_counter);

        Intent intentReceive = getIntent();
        final String workout = intentReceive.getStringExtra("Workout");
        final String sectionType = intentReceive.getStringExtra("SectionType");
        final double total = intentReceive.getDoubleExtra("Total",0);
        separate = intentReceive.getDoubleArrayExtra("Separate");

        Button btnRep1 = (Button)findViewById(R.id.rep_1);
        Button btnRep2 = (Button)findViewById(R.id.rep_2);
        Button btnRep3 = (Button)findViewById(R.id.rep_3);
        Button btnRep4 = (Button)findViewById(R.id.rep_4);
        Button btnRep5 = (Button)findViewById(R.id.rep_5);
        Button btnRep6 = (Button)findViewById(R.id.rep_6);
        Button btnRep7 = (Button)findViewById(R.id.rep_7);
        Button btnRep8 = (Button)findViewById(R.id.rep_8);
        Button btnRep9 = (Button)findViewById(R.id.rep_9);
        Button btnRep10 = (Button)findViewById(R.id.rep_10);
        Button btnRep11 = (Button)findViewById(R.id.rep_11);
        Button btnRep12 = (Button)findViewById(R.id.rep_12);
        Button btnRep13 = (Button)findViewById(R.id.rep_13);
        Button btnRep14 = (Button)findViewById(R.id.rep_14);
        Button btnRep15 = (Button)findViewById(R.id.rep_15);
        Button btnRep16 = (Button)findViewById(R.id.rep_16);
        Button btnRep17 = (Button)findViewById(R.id.rep_17);
        Button btnRep18 = (Button)findViewById(R.id.rep_18);
        Button btnRep19 = (Button)findViewById(R.id.rep_19);
        Button btnRep20 = (Button)findViewById(R.id.rep_20);
        Button btnRep21 = (Button)findViewById(R.id.rep_21);
        Button btnRep22 = (Button)findViewById(R.id.rep_22);
        Button btnRep23 = (Button)findViewById(R.id.rep_23);
        Button btnRep24 = (Button)findViewById(R.id.rep_24);
        Button btnRep25 = (Button)findViewById(R.id.rep_25);
        Button btnRep26 = (Button)findViewById(R.id.rep_26);
        Button btnRep27 = (Button)findViewById(R.id.rep_27);
        Button btnRep28 = (Button)findViewById(R.id.rep_28);
        Button btnRep29 = (Button)findViewById(R.id.rep_29);
        Button btnRep30 = (Button)findViewById(R.id.rep_30);
        Button btnRep31 = (Button)findViewById(R.id.rep_31);
        Button btnRep32 = (Button)findViewById(R.id.rep_32);
        Button btnRep33 = (Button)findViewById(R.id.rep_33);
        Button btnRep34 = (Button)findViewById(R.id.rep_34);
        Button btnRep35 = (Button)findViewById(R.id.rep_35);
        Button btnRep36 = (Button)findViewById(R.id.rep_36);
        Button btnRep37 = (Button)findViewById(R.id.rep_37);
        Button btnRep38 = (Button)findViewById(R.id.rep_38);
        Button btnRep39 = (Button)findViewById(R.id.rep_39);
        Button btnRep40 = (Button)findViewById(R.id.rep_40);

        btnRep1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(1,workout,sectionType,total);
            }
        });
        btnRep2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(2,workout,sectionType,total);
            }
        });
        btnRep3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(3,workout,sectionType,total);
            }
        });
        btnRep4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(4,workout,sectionType,total);
            }
        });
        btnRep5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(5,workout,sectionType,total);
            }
        });
        btnRep6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(6,workout,sectionType,total);
            }
        });
        btnRep7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(7,workout,sectionType,total);
            }
        });
        btnRep8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(8,workout,sectionType,total);
            }
        });
        btnRep9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(9,workout,sectionType,total);
            }
        });
        btnRep10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(10,workout,sectionType,total);
            }
        });
        btnRep11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(11,workout,sectionType,total);
            }
        });
        btnRep12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(12,workout,sectionType,total);
            }
        });
        btnRep13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(13,workout,sectionType,total);
            }
        });
        btnRep14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(14,workout,sectionType,total);
            }
        });
        btnRep15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(15,workout,sectionType,total);
            }
        });
        btnRep16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(16,workout,sectionType,total);
            }
        });
        btnRep17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(17,workout,sectionType,total);
            }
        });
        btnRep18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(18,workout,sectionType,total);
            }
        });
        btnRep19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(19,workout,sectionType,total);
            }
        });
        btnRep20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(20,workout,sectionType,total);
            }
        });
        btnRep21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(21,workout,sectionType,total);
            }
        });
        btnRep22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(22,workout,sectionType,total);
            }
        });
        btnRep23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(23,workout,sectionType,total);
            }
        });
        btnRep24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(24,workout,sectionType,total);
            }
        });
        btnRep25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(25,workout,sectionType,total);
            }
        });
        btnRep26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(26,workout,sectionType,total);
            }
        });
        btnRep27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(27,workout,sectionType,total);
            }
        });
        btnRep28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(28,workout,sectionType,total);
            }
        });
        btnRep29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(29,workout,sectionType,total);
            }
        });
        btnRep30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(30,workout,sectionType,total);
            }
        });
        btnRep31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                createNewIntent(31,workout,sectionType,total);
            }
        });
        btnRep32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(32,workout,sectionType,total);
            }
        });
        btnRep33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(33,workout,sectionType,total);
            }
        });
        btnRep34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(34,workout,sectionType,total);
            }
        });
        btnRep35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(35,workout,sectionType,total);
            }
        });
        btnRep36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(36,workout,sectionType,total);
            }
        });
        btnRep37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(37,workout,sectionType,total);
            }
        });
        btnRep38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(38,workout,sectionType,total);
            }
        });
        btnRep39.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(39,workout,sectionType,total);}
        });
        btnRep40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createNewIntent(40,workout,sectionType,total);
            }
        });

    }
    private void createNewIntent(int reps, String workout, String sectionType, Double total){
        Intent intent = new Intent();
        intent.putExtra("Workout",workout);
        intent.putExtra("Num_Reps",reps);
        intent.putExtra("Total",total);
        intent.putExtra("Separate", separate);
        intent.putExtra("SectionType",sectionType);
        setResult(RESULT_OK,intent);
        finish();
    }
}
