package com.saaasaab.scott.spotter;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public List<String> weightSamplesTotal = new ArrayList<>();
    private TextView comparison;
    private String[][] comparisons = {{"1", "Guinea pigs"}, {"10", "Large Watermelons"},
            {"100", "Toilets"}, {"1000", "Telephone Poles"}, {"10000", "Small Buses"},
            {"100000", "Water Towers"}, {"1000000", "Space Shuttles"}, {"10000000", "Rocket Launch Towers"},
            {"100000000", "The Populations of Wyoming"}, {"1000000000", "You know what? You win. I don't think anything weighs over a billion pounds"}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String fileNameTotal = "fileTotal.txt";
        String fileNameArm = "fileArm.txt";
        String fileNameLeg = "fileLeg.txt";
        String fileNameBackAndCore = "fileBackAndCore.txt";
        String fileNameDate = "fileDate.txt";
        String fileAllData = "fileAllData.txt";
        String fileNameMachines = "fileMachines.txt";
        String fileNameWeights = "fileWeights.txt";
        String fileNameFloor = "fileFloor.txt";

//        try {
//            clearFile(fileNameTotal);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            clearFile(fileNameArm);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            clearFile(fileNameLeg);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            clearFile(fileNameBackAndCore);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            clearFile(fileNameDate);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            clearFile(fileAllData);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            clearFile(fileNameMachines);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            clearFile(fileNameWeights);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            clearFile(fileNameFloor);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

        Button btnNewWorkout = (Button) findViewById(R.id.new_workout_button);
        Button btnPastWorkout = (Button) findViewById(R.id.past_workout_button);
        Button btnViewBadges = (Button) findViewById(R.id.badges_button);
        Button btnHelp = (Button) findViewById(R.id.help_button);

        set_comparison_of_total_to_other_things();

        btnNewWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("MySettings", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putLong("time", System.currentTimeMillis());
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), NewWorkoutScreen.class);
                startActivity(intent);
            }

        });

        btnPastWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PastWorkoutScreen.class);
                startActivity(intent);
            }

        });

        btnViewBadges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BadgesScreen.class);
                startActivity(intent);
            }

        });

        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Help.class);
                startActivity(intent);
            }

        });
    }

    /**
     * Back button listener.
     * Will close the application if the back button pressed twice.
     */
    @Override
    public void onBackPressed() {

        int backButtonCount;
        SharedPreferences sharedPreferences = getSharedPreferences("MySettings", Context.MODE_PRIVATE);
        backButtonCount = Integer.parseInt(sharedPreferences.getString("backButtonCount", "0"));

        if (backButtonCount >= 1) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("backButtonCount", "0");
            editor.commit();

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Press the back button again to close Spotter.", Toast.LENGTH_LONG).show();
            backButtonCount++;

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("backButtonCount", Integer.toString(backButtonCount));

            editor.commit();
        }
    }

    void set_comparison_of_total_to_other_things() {
        String fileNameTotal = "fileTotal.txt";
        DecimalFormat df = new DecimalFormat("#.#");
        int factor;

        comparison = (TextView) findViewById(R.id.comparison);
        double sum = 0;
        weightSamplesTotal = readData(fileNameTotal, weightSamplesTotal);
        sum = sumOfArray(sum, weightSamplesTotal);
        factor = find_factor(sum);
//        find_comparison(factor);

        comparison.setText("Total: " + (int) sum + " lbs - Which is equivilant to " + df.format(sum / (Math.pow(10, (factor - 1))))
                + " " + comparisons[factor - 1][1]);
    }

    private void find_comparison(int factor) {

    }

    private void clearFile(String file) throws FileNotFoundException {
        try {
            FileOutputStream fos = openFileOutput(file, MODE_PRIVATE | Context.MODE_PRIVATE);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write("");
            bw.close();
            fos.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int find_factor(double sum) {
        double sumChanged = sum;
        boolean running = true;
        int factor = 0;

        while (running) {
            if (sumChanged / 10.0 < 1) {
                running = false;
            } else {
                sumChanged /= 10.0;
            }
            factor++;
        }
        return (factor);
    }

    private List<String> readData(String file1, List<String> dataHolders) {
        String text = "";

        try {
            FileInputStream fis = openFileInput(file1);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(fis, Charset.forName("UTF-8"))
            );

            while ((text = reader.readLine()) != null) {
                // Read the data
                dataHolders.add(text);
            }
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();

            SharedPreferences sharedPreferences = getSharedPreferences("MySettings", Context.MODE_PRIVATE);
            int starting = Integer.parseInt(sharedPreferences.getString("starting_value", "0"));



            if (starting == 0) {

                Toast.makeText(MainActivity.this, "Welcome to Spotter! Press 'Start New Workout' to begin", Toast.LENGTH_LONG).show();

                long unixTime = System.currentTimeMillis() / 1000L;
                long time_at_installation = unixTime;//Integer.parseInt(sharedPreferences.getString("starting_value", "0"));
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putLong("time_at_installation", time_at_installation);
                editor.commit();
            } //else {
//                Toast.makeText(MainActivity.this, "ERROR: File cannot be read!", Toast.LENGTH_SHORT).show();
//            }


        }
        return (dataHolders);
    }

    double sumOfArray(double sum, List<String> array) {
        for (int i = 0; i < array.size(); i++) {
            sum += Double.parseDouble(array.get(i));
        }
        return (sum);
    }
}




