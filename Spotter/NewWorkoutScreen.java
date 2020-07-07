package com.saaasaab.scott.spotter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class NewWorkoutScreen extends Activity {
    public static long mStartTime;
    private TextView mTvTime;
    private Button mBtnStart;
    private Button mBtnLap;
    private Button mBtnStop;
    private String time_from_timer;
    private Context mContext;
    private Chronometer mChronometer;
    private Thread mThreadChrono;

    private TextView score_text_view;
    private TextView change_goal;

    public int weightOfWorkout;
    String goal = "";
    String weight = "";

    int weightForCalculations;

    public double total = 0;
    public double arms = 0;
    public double legs = 0;
    public double coreAndBack = 0;
    public double machines = 0;
    public double weights = 0;
    public double floor = 0;

    public double[] separate = {};

    TextView txtLabel;
    String name;

    Button end_session, b_read;
    String fileNameTotal = "fileTotal.txt";
    String fileNameArm = "fileArm.txt";
    String fileNameLeg = "fileLeg.txt";
    String fileNameBackAndCore = "fileBackAndCore.txt";
    String fileNameDate = "fileDate.txt";
    String fileNameTimer = "fileTimer.txt";
    String fileAllData = "fileAllData.txt";
    String fileNameMachines = "fileMachines.txt";
    String fileNameWeights = "fileWeights.txt";
    String fileNameFloor = "fileFloor.txt";

    private EditText amountTextView;
    public String workout;
    public String sectionType;
    public int reps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_workout_screen);
        score_text_view = (TextView) findViewById(R.id.score_text_view);
        change_goal = (TextView) findViewById(R.id.goal_display);
        end_session = (Button) findViewById(R.id.end_session);

        SharedPreferences sharedPreferences = getSharedPreferences("MySettings", Context.MODE_PRIVATE);
        mStartTime = sharedPreferences.getLong("time", 1);
        goal = sharedPreferences.getString("goal", "10000");
        change_goal.setText("Goal: " + goal + " lbs");

        weight = sharedPreferences.getString("weight", "200");
        weightForCalculations = Integer.parseInt(weight);

        end_session.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                mChronometer.stop();
                mThreadChrono.interrupt();
                mThreadChrono = null;
                mChronometer = null;
                total = Math.round(total);
                String[] textToSend = {
                        Integer.toString((int) total),
                        Integer.toString((int) arms),
                        Integer.toString((int) legs),
                        Integer.toString((int) coreAndBack),
                        Integer.toString((int) machines),
                        Integer.toString((int) weights),
                        Integer.toString((int) floor)};

                saveFile(fileNameTotal, fileNameArm, fileNameLeg, fileNameBackAndCore, fileNameDate,
                        fileNameTimer, fileNameMachines, fileNameWeights, fileNameFloor, textToSend);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        mContext = this;
        mTvTime = (TextView) findViewById(R.id.tv_time);


        if (mChronometer == null) {
            mChronometer = new Chronometer(mContext);
            mThreadChrono = new Thread(mChronometer);
            mThreadChrono.start();
            mChronometer.start();
        }

        int starting = Integer.parseInt(sharedPreferences.getString("starting_value", "0"));

        if (starting == 0) {
            Toast.makeText(NewWorkoutScreen.this, "Select the workout you are doing", Toast.LENGTH_LONG).show();
        }

        if (getIntent().getExtras() != null) {
            Intent intentReceive = getIntent();
            workout = intentReceive.getStringExtra("Workout");
            sectionType = intentReceive.getStringExtra("SectionType");
            weightOfWorkout = intentReceive.getIntExtra("Weight", 0);
            reps = intentReceive.getIntExtra("Num_Reps", 1);
            total = intentReceive.getDoubleExtra("Total", 0) + weightOfWorkout * reps;
            separate = intentReceive.getDoubleArrayExtra("Separate");

            arms = separate[0];
            legs = separate[1];
            coreAndBack = separate[2];
            machines = separate[3];
            weights = separate[4];
            floor = separate[5];

            score_text_view = (TextView) findViewById(R.id.score_text_view);
            score_text_view.setText("Lifted: " + Math.round(total) + " lbs");

            if ("1".equals(workout)) {
                arms = arms + weightOfWorkout * reps;
            }
            if ("2".equals(workout)) {
                legs = legs + weightOfWorkout * reps;
            }
            if ("3".equals(workout)) {
                coreAndBack = coreAndBack + weightOfWorkout * reps;
            }


            if ("1".equals(sectionType)) {
                machines = machines + weightOfWorkout * reps;
            }
            if ("2".equals(sectionType)) {
                weights = weights + weightOfWorkout * reps;
            }
            if ("3".equals(sectionType)) {
                floor = floor + weightOfWorkout * reps;
            }
        }


        //FLOOR EXCERCISES
        Button btnPushUp = (Button) findViewById(R.id.push_ups);
        Button btnTricepsPushUp = (Button) findViewById(R.id.triceps_push_up);
        Button btnToeUps = (Button) findViewById(R.id.toe_ups);
        Button btnPullUp = (Button) findViewById(R.id.pull_up);
        Button btnCrunches = (Button) findViewById(R.id.crunches);
        Button btnLegLifts = (Button) findViewById(R.id.leg_lifts);
        Button btnSquat = (Button) findViewById(R.id.squat);
        Button btnSideCrunches = (Button) findViewById(R.id.side_crunches);
        //Button btnPlanks = (Button) findViewById(R.id.planks);

        //MACHINE and WEIGHTS
        Button btnBenchPress = (Button) findViewById(R.id.bench_press);
        Button btnDumbBells = (Button) findViewById(R.id.dumb_bells);
        Button btnMachine = (Button) findViewById(R.id.machine);
        Button btnMedicineBall = (Button) findViewById(R.id.medicine_ball);

        Button btnHamstring = (Button) findViewById(R.id.hamstring);
        Button btnCalf = (Button) findViewById(R.id.calf);
        Button btnShoulders = (Button) findViewById(R.id.shoulders);
        Button btnLegPress = (Button) findViewById(R.id.leg_press);
        Button btnDeadLift = (Button) findViewById(R.id.dead_lift);
        Button btnLunge = (Button) findViewById(R.id.lunge);
        Button btnLegMachine = (Button) findViewById(R.id.leg_machine);
        Button btnAbMachine = (Button) findViewById(R.id.ab_machine);


        //OPTIONS
        Button reset = (Button) findViewById(R.id.reset);
        Button btnChangeWeight = (Button) findViewById(R.id.change_weight);
        Button btnChangeGoal = (Button) findViewById(R.id.change_goal);

        btnDumbBells.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntentForMachines("1", "2");
            }
        });
        btnBenchPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntentForMachines("1", "2");
            }
        });
        btnShoulders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntentForMachines("1", "2");
            }
        });

        btnHamstring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntentForMachines("2", "2");
            }

        });
        btnCalf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntentForMachines("2", "2");
            }

        });
        btnLegPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntentForMachines("2", "2");
            }

        });
        btnSquat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntentForMachines("2", "2");
            }
        });

        btnDeadLift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntentForMachines("3", "2");
            }

        });
        btnMedicineBall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntentForMachines("3", "2");
            }

        });


        //MACHINES

        btnMachine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntentForMachines("1", "1");
            }
        });
        btnLegMachine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntentForMachines("2", "1");
            }

        });
        btnAbMachine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntentForMachines("3", "1");
            }
        });

        //FLOOR EXCERCISES
        btnSideCrunches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntentForFloorExercisesWithSpecialNumbers("3", "3", 60);
            }
        });
        btnLunge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntentForFloorExercisesWithSpecialNumbers("2", "3", 70);
            }
        });
        btnPushUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntentForFloorExercisesWithSpecialNumbers("1", "3", 100);
            }
        });
        btnTricepsPushUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntentForFloorExercisesWithSpecialNumbers("1", "3", 90);
            }
        });
        btnToeUps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntentForFloorExercisesWithSpecialNumbers("2", "3", 80);
            }
        });
        btnPullUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntentForFloorExercisesWithSpecialNumbers("1", "3", 70);
            }
        });
        btnCrunches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntentForFloorExercisesWithSpecialNumbers("3", "3", 60);
            }
        });
        btnLegLifts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewIntentForFloorExercisesWithSpecialNumbers("3", "3", 59);
            }
        });


        //SETTINGS
        btnChangeGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChangeGoal.class);
                startActivityForResult(intent, 50);
            }
        });
        btnChangeWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChangeWeight.class);
                startActivityForResult(intent, 40);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total = 0;
                score_text_view.setText("Lifted: " + Math.round(total) + " lbs");
            }
        });
    }

    public void updateTimerText(final String time) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTvTime.setText(time);
            }
        });
    }

    private void createNewIntentForMachines(String typeOfWorkout, String sectionType) {
        Intent intent = new Intent(getApplicationContext(), SelectWeight.class);
        intent.putExtra("Workout", typeOfWorkout);
        intent.putExtra("SectionType", sectionType);
        intent.putExtra("Total", total);
        double[] separate = {arms, legs, coreAndBack, machines, weights, floor};
        intent.putExtra("Separate", separate);
        startActivity(intent);
    }

    private void createNewIntentForFloorExercisesWithSpecialNumbers(String typeOfWorkout, String sectionType, int caseNumber) {
        Intent intent = new Intent(getApplicationContext(), RepCounterFloor.class);
        intent.putExtra("Total", total);
        intent.putExtra("SectionType", sectionType);
        intent.putExtra("Workout", typeOfWorkout);
        intent.putExtra("SectionType", sectionType);

        double[] separate = {arms, legs, coreAndBack, machines, weights, floor};
        intent.putExtra("Separate", separate);
        startActivityForResult(intent, caseNumber);
    }

    private void getIntentForFloorExercises(double multiplier, Intent data) {
        workout = data.getStringExtra("Workout");
        sectionType = data.getStringExtra("SectionType");
        reps = data.getIntExtra("Num_Reps", 1);
        total = total + Integer.parseInt(weight) * multiplier * reps;
        score_text_view.setText("Lifted: " + Math.round(total) + " lbs");
        separate = data.getDoubleArrayExtra("Separate");


        arms = separate[0];
        legs = separate[1];
        coreAndBack = separate[2];
        machines = separate[3];
        weights = separate[4];
        floor = separate[5];

        if ("1".equals(workout)) {
            arms = arms + Integer.parseInt(weight) * reps * multiplier;
        }
        if ("2".equals(workout)) {
            legs = legs + Integer.parseInt(weight) * reps * multiplier;
        }
        if ("3".equals(workout)) {
            coreAndBack = coreAndBack + Integer.parseInt(weight) * reps * multiplier;
        }

        if ("1".equals(sectionType)) {
            machines = machines + Integer.parseInt(weight) * reps * multiplier;
        }
        if ("2".equals(sectionType)) {
            weights = weights + Integer.parseInt(weight) * reps * multiplier;
        }
        if ("3".equals(sectionType)) {
            floor = floor + Integer.parseInt(weight) * reps * multiplier;
        }
        Toast.makeText(NewWorkoutScreen.this, Integer.parseInt(weight) * reps * multiplier + "lbs lifted", Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                {
                    getIntentForFloorExercises(.47, data);
                }
            }
            super.onActivityResult(requestCode, resultCode, data);
        } else if (requestCode == 90) {
            if (resultCode == RESULT_OK) {
                {
                    getIntentForFloorExercises(.25, data);
                }
            }
            super.onActivityResult(requestCode, resultCode, data);
        } else if (requestCode == 80) {
            if (resultCode == RESULT_OK) {
                {
                    getIntentForFloorExercises(.9, data);
                }
            }
            super.onActivityResult(requestCode, resultCode, data);
        } else if (requestCode == 70) {
            if (resultCode == RESULT_OK) {
                {
                    getIntentForFloorExercises(1, data);
                }
            }
            super.onActivityResult(requestCode, resultCode, data);
        } else if (requestCode == 60) {
            if (resultCode == RESULT_OK) {
                {
                    getIntentForFloorExercises(.2, data);
                }
            }
            super.onActivityResult(requestCode, resultCode, data);
        } else if (requestCode == 59) {
            if (resultCode == RESULT_OK) {
                {
                    getIntentForFloorExercises(.2, data);
                }
            }
            super.onActivityResult(requestCode, resultCode, data);
        } else if (requestCode == 50) {
            if (resultCode == RESULT_OK) {
                SharedPreferences sharedPreferences = getSharedPreferences("MySettings", Context.MODE_PRIVATE);
                goal = sharedPreferences.getString("goal", "10000");
                change_goal.setText("Goal: " + goal + " lbs");
                Toast.makeText(NewWorkoutScreen.this, "Goal set to: " + goal, Toast.LENGTH_SHORT).show();
            }
            super.onActivityResult(requestCode, resultCode, data);
        } else if (requestCode == 40) {
            if (resultCode == RESULT_OK) {
                {
                    SharedPreferences sharedPreferences = getSharedPreferences("MySettings", Context.MODE_PRIVATE);
                    weight = sharedPreferences.getString("weight", "200");
                    Toast.makeText(NewWorkoutScreen.this, "Weight set to: " + weight, Toast.LENGTH_SHORT).show();
                }
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void saveFile(String fileTotal, String fileArm, String fileLeg, String fileCoreAndBack,
                         String fileDate, String timer, String fileMachines, String fileWeights, String fileFloor,
                         String[] text) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());

        long unixTime = System.currentTimeMillis() / 1000L;


        try {
            //Toast.makeText(NewWorkoutScreen.this, formattedDate, Toast.LENGTH_SHORT).show();
            //Save to the total file

            FileOutputStream fos = openFileOutput(fileTotal, MODE_APPEND | Context.MODE_PRIVATE);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(text[0]);
            bw.newLine();
            bw.close();
            fos.close();

            //Save to the arm file
            fos = openFileOutput(fileArm, MODE_APPEND | Context.MODE_PRIVATE);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(text[1]);
            bw.newLine();
            bw.close();
            fos.close();

            //Save to the leg file
            fos = openFileOutput(fileLeg, MODE_APPEND | Context.MODE_PRIVATE);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(text[2]);
            bw.newLine();
            bw.close();
            fos.close();

            //Save to the Core and Back file
            fos = openFileOutput(fileCoreAndBack, MODE_APPEND | Context.MODE_PRIVATE);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(text[3]);
            bw.newLine();
            bw.close();
            fos.close();

            fos = openFileOutput(fileMachines, MODE_APPEND | Context.MODE_PRIVATE);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(text[4]);
            bw.newLine();
            bw.close();
            fos.close();

            fos = openFileOutput(fileWeights, MODE_APPEND | Context.MODE_PRIVATE);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(text[5]);
            //Toast.makeText(NewWorkoutScreen.this, "" + text[5], Toast.LENGTH_SHORT).show();
            bw.newLine();
            bw.close();
            fos.close();

            fos = openFileOutput(fileFloor, MODE_APPEND | Context.MODE_PRIVATE);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(text[6]);
            bw.newLine();
            bw.close();
            fos.close();

            //Save to the Date
            fos = openFileOutput(fileDate, MODE_APPEND | Context.MODE_PRIVATE);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(Long.toString(unixTime));//formattedDate);
            bw.newLine();
            bw.close();
            fos.close();

            if (mChronometer != null) {
                //Save to the Timer Value
                fos = openFileOutput(timer, MODE_APPEND | Context.MODE_PRIVATE);
                bw = new BufferedWriter(new OutputStreamWriter(fos));
                bw.write(Long.toString(mChronometer.since));//formattedDate);
                bw.newLine();
                bw.close();
                fos.close();

                mChronometer.stop();
                mThreadChrono.interrupt();
                mThreadChrono = null;
                mChronometer = null;
            }


            fos = openFileOutput(fileAllData, MODE_APPEND | Context.MODE_PRIVATE);
            bw = new BufferedWriter(new OutputStreamWriter(fos));

            String[] spaces = {
                    "",
                    " ",
                    "  ",
                    "   ",
                    "    ",
                    "     ",
                    "      ",
                    "       ",
                    "        ",
                    "         "};

//            bw.write(formattedDate  + "  ");//formattedDate + "  ");
//
//            bw.write(text[0] + " lbs" + spaces[10 - (text[0] + "lbs").length()]);
//            bw.write(text[1] + " lbs" + spaces[10 - (text[1] + "lbs").length()]);
//            bw.write(text[2] + " lbs" + spaces[10 - (text[2] + "lbs").length()]);
//            bw.write(text[3] + " lbs");
            bw.write(text[0] + " lbs");
            bw.newLine();
            bw.close();
            fos.close();


            //Toast.makeText(NewWorkoutScreen.this, "" +  (10-(text[1] + "lbs").length())+  (10-(text[2] + "lbs").length())+  (10-(text[3] + "lbs").length()), Toast.LENGTH_SHORT).show();

            SharedPreferences sharedPreferences = getSharedPreferences("MySettings", Context.MODE_PRIVATE);
            int starting = Integer.parseInt(sharedPreferences.getString("starting_value", "0"));

            if (starting == 0) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("starting_value", "1");
                editor.commit();
            }

            Toast.makeText(NewWorkoutScreen.this, "Saved!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(NewWorkoutScreen.this, "ERROR Saving file!", Toast.LENGTH_SHORT).show();
        }
    }

}






