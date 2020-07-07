package com.saaasaab.scott.spotter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class SelectWeight extends Activity {
    public double[] separate = {};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_weight);

        Intent intentReceive = getIntent();

        final String workout = intentReceive.getStringExtra("Workout");
        final String sectionType = intentReceive.getStringExtra("SectionType");
        final double total = intentReceive.getDoubleExtra("Total",0);
        separate = intentReceive.getDoubleArrayExtra("Separate");
        Button btnButton5 = (Button)findViewById(R.id.button5);
        Button btnButton10 = (Button)findViewById(R.id.button10);
        Button btnButton15 = (Button)findViewById(R.id.button15);
        Button btnButton20 = (Button)findViewById(R.id.button20);
        Button btnButton25 = (Button)findViewById(R.id.button25);
        Button btnButton30 = (Button)findViewById(R.id.button30);
        Button btnButton35 = (Button)findViewById(R.id.button35);
        Button btnButton40 = (Button)findViewById(R.id.button40);
        Button btnButton45 = (Button)findViewById(R.id.button45);
        Button btnButton50 = (Button)findViewById(R.id.button50);
        Button btnButton55 = (Button)findViewById(R.id.button55);
        Button btnButton60 = (Button)findViewById(R.id.button60);
        Button btnButton65 = (Button)findViewById(R.id.button65);
        Button btnButton70 = (Button)findViewById(R.id.button70);
        Button btnButton75 = (Button)findViewById(R.id.button75);
        Button btnButton80 = (Button)findViewById(R.id.button80);
        Button btnButton85 = (Button)findViewById(R.id.button85);
        Button btnButton90 = (Button)findViewById(R.id.button90);
        Button btnButton95 = (Button)findViewById(R.id.button95);
        Button btnButton100 = (Button)findViewById(R.id.button100);

        Button btnButton105 = (Button)findViewById(R.id.button105);
        Button btnButton110 = (Button)findViewById(R.id.button110);
        Button btnButton115 = (Button)findViewById(R.id.button115);
        Button btnButton120 = (Button)findViewById(R.id.button120);
        Button btnButton125 = (Button)findViewById(R.id.button125);
        Button btnButton130 = (Button)findViewById(R.id.button130);
        Button btnButton135 = (Button)findViewById(R.id.button135);
        Button btnButton140 = (Button)findViewById(R.id.button140);
        Button btnButton145 = (Button)findViewById(R.id.button145);
        Button btnButton150 = (Button)findViewById(R.id.button150);
        Button btnButton155 = (Button)findViewById(R.id.button155);
        Button btnButton160 = (Button)findViewById(R.id.button160);
        Button btnButton165 = (Button)findViewById(R.id.button165);
        Button btnButton170 = (Button)findViewById(R.id.button170);
        Button btnButton175 = (Button)findViewById(R.id.button175);
        Button btnButton180 = (Button)findViewById(R.id.button180);
        Button btnButton185 = (Button)findViewById(R.id.button185);
        Button btnButton190 = (Button)findViewById(R.id.button190);
        Button btnButton195 = (Button)findViewById(R.id.button195);
        Button btnButton200 = (Button)findViewById(R.id.button200);

        Button btnButton205 = (Button)findViewById(R.id.button205);
        Button btnButton210 = (Button)findViewById(R.id.button210);
        Button btnButton215 = (Button)findViewById(R.id.button215);
        Button btnButton220 = (Button)findViewById(R.id.button220);
        Button btnButton225 = (Button)findViewById(R.id.button225);
        Button btnButton230 = (Button)findViewById(R.id.button230);
        Button btnButton235 = (Button)findViewById(R.id.button235);
        Button btnButton240 = (Button)findViewById(R.id.button240);
        Button btnButton245 = (Button)findViewById(R.id.button245);
        Button btnButton250 = (Button)findViewById(R.id.button250);
        Button btnButton255 = (Button)findViewById(R.id.button255);
        Button btnButton260 = (Button)findViewById(R.id.button260);
        Button btnButton265 = (Button)findViewById(R.id.button265);
        Button btnButton270 = (Button)findViewById(R.id.button270);
        Button btnButton275 = (Button)findViewById(R.id.button275);
        Button btnButton280 = (Button)findViewById(R.id.button280);
        Button btnButton285 = (Button)findViewById(R.id.button285);
        Button btnButton290 = (Button)findViewById(R.id.button290);
        Button btnButton295 = (Button)findViewById(R.id.button295);
        Button btnButton300 = (Button)findViewById(R.id.button300);


        btnButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout,sectionType, total, 5);
            }
        });
        btnButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout,sectionType, total, 10);
            }
        });
        btnButton15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout,sectionType, total, 15);
            }
        });
        btnButton20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout,sectionType, total, 20);
            }
        });
        btnButton25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout,sectionType, total, 25);
            }
        });
        btnButton30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout, sectionType,total, 30);
            }
        });
        btnButton35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout, sectionType,total, 35);
            }
        });
        btnButton40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout,sectionType, total, 40);
            }
        });
        btnButton45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout,sectionType, total, 45);
            }
        });
        btnButton50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout, sectionType,total, 50);
            }
        });
        btnButton55.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout, sectionType,total, 55);
            }
        });
        btnButton60.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout, sectionType,total, 60);
            }
        });
        btnButton65.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout,sectionType, total, 65);
            }
        });
        btnButton70.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout, sectionType,total, 70);
            }
        });
        btnButton75.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout,sectionType, total, 75);
            }
        });
        btnButton80.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout, sectionType,total, 80);
            }
        });

        btnButton85.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout,sectionType, total, 85);
            }
        });
        btnButton90.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout,sectionType, total, 90);
            }
        });
        btnButton95.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout,sectionType, total, 95);
            }
        });
        btnButton100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout,sectionType, total, 100);
            }
        });
        btnButton105.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout,sectionType, total, 105);
            }
        });
        btnButton110.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout, sectionType, total, 110);
            }
        });
        btnButton115.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout, sectionType, total, 115);
            }
        });
        btnButton120.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout, sectionType, total, 120);
            }
        });
        btnButton125.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout,sectionType, total, 125);
            }
        });
        btnButton130.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout,sectionType, total, 130);
            }
        });
        btnButton135.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout, sectionType,total, 135);
            }
        });
        btnButton140.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout,sectionType, total, 140);
            }
        });
        btnButton145.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout,sectionType, total, 145);
            }
        });
        btnButton150.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout,sectionType, total, 150);
            }
        });
        btnButton155.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout,sectionType, total, 155);
            }
        });
        btnButton160.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout,sectionType, total, 160);
            }
        });
        btnButton165.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout, sectionType,total, 165);
            }
        });
        btnButton170.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout,sectionType, total, 170);
            }
        });
        btnButton175.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout, sectionType,total, 175);
            }
        });
        btnButton180.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout,sectionType, total, 180);
            }
        });
        btnButton185.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout,sectionType, total, 185);
            }
        });
        btnButton190.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout, sectionType, total, 190);
            }
        });
        btnButton195.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout, sectionType, total, 195);
            }
        });
        btnButton200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout, sectionType, total, 200);
            }
        });
        btnButton205.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout, sectionType, total, 205);
            }
        });
        btnButton210.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout, sectionType, total, 210);
            }
        });
        btnButton215.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout, sectionType, total, 215);
            }
        });
        btnButton220.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout, sectionType, total, 220);
            }
        });
        btnButton225.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout, sectionType, total, 225);
            }
        });
        btnButton230.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout, sectionType, total, 230);
            }
        });
        btnButton235.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout, sectionType, total, 235);
            }
        });
        btnButton240.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout, sectionType, total, 240);
            }
        });
        btnButton245.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout, sectionType,total, 245);
            }
        });
        btnButton250.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout, sectionType,total, 250);
            }
        });
        btnButton255.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout, sectionType,total, 255);
            }
        });
        btnButton260.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout, sectionType, total, 260);
            }
        });
        btnButton265.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout,sectionType, total, 265);
            }
        });
        btnButton270.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout, sectionType,total, 270);
            }
        });
        btnButton275.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout,sectionType, total, 275);
            }
        });
        btnButton280.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout,sectionType, total, 280);
            }
        });
        btnButton285.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout,sectionType, total, 285);
            }
        });
        btnButton290.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout,sectionType, total, 290);
            }
        });
        btnButton295.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout,sectionType, total, 295);
            }
        });
        btnButton300.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntent(workout,sectionType, total, 300);
            }
        });

    }
    private void createNewIntent(String workout, String sectionType, Double total, int weight){
        Intent intent = new Intent(getApplicationContext(), RepCounter.class);
        intent.putExtra("Workout",workout);
        intent.putExtra("Total",total);
        intent.putExtra("Weight",weight);
        intent.putExtra("SectionType",sectionType);
        intent.putExtra("Separate", separate);
        startActivity(intent);
    }

}