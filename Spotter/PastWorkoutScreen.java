package com.saaasaab.scott.spotter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Scott on 6/29/2017.
 */

public class PastWorkoutScreen extends Activity {
    LineGraphSeries<DataPoint> series;
    LineGraphSeries<DataPoint> series2;
    LineGraphSeries<DataPoint> series3;
    LineGraphSeries<DataPoint> series4;
    Double[] weight = {};
    String fileNameTotal = "fileTotal.txt";
    String fileNameArm = "fileArm.txt";
    String fileNameLeg = "fileLeg.txt";
    String fileNameBackAndCore = "fileBackAndCore.txt";
    String fileNameDate = "fileDate.txt";
    String fileAllData = "fileAllData.txt";
    String fileNameMachines = "fileMachines.txt";
    String fileNameWeights = "fileWeights.txt";
    String fileNameFloor = "fileFloor.txt";
    String fileNameTimer = "fileTimer.txt";


    private List<String> weightSamples = new ArrayList<>();
    private List<String> weightSamplesArms = new ArrayList<>();
    private List<String> weightSamplesLegs = new ArrayList<>();
    private List<String> weightSamplesBackAndCore = new ArrayList<>();
    private List<String> weightSamplesMachines = new ArrayList<>();
    private List<String> weightSamplesFreeWeights = new ArrayList<>();
    private List<String> weightSamplesFloorExercises = new ArrayList<>();
    private List<String> weightSamplesTotal = new ArrayList<>();
    private List<String> weightSamplesTotalDisplay = new ArrayList<>();
    private List<String> weightSamplesDate = new ArrayList<>();

//   private List<String> weightSamplesAllData = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.past_workout);
        Button btnClear = (Button) findViewById(R.id.genocide);


        //readData(fileNameTotal, fileNameArm, fileNameLeg, fileNameBackAndCore, fileNameDate);
        readData();
        populateListView();
        populateTextView();
        registerClickCallback();

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PastWorkoutScreen.this);
                builder.setMessage("Are you sure?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {

                                    FileOutputStream fos = openFileOutput(fileNameTotal, Context.MODE_PRIVATE);
                                    fos.write(("").getBytes());
                                    fos.close();

                                    fos = openFileOutput(fileNameArm, Context.MODE_PRIVATE);
                                    fos.write(("").getBytes());
                                    fos.close();

                                    fos = openFileOutput(fileNameLeg, Context.MODE_PRIVATE);
                                    fos.write(("").getBytes());
                                    fos.close();

                                    fos = openFileOutput(fileNameBackAndCore, Context.MODE_PRIVATE);
                                    fos.write(("").getBytes());
                                    fos.close();

                                    fos = openFileOutput(fileNameMachines, Context.MODE_PRIVATE);
                                    fos.write(("").getBytes());
                                    fos.close();

                                    fos = openFileOutput(fileNameWeights, Context.MODE_PRIVATE);
                                    fos.write(("").getBytes());
                                    fos.close();

                                    fos = openFileOutput(fileNameFloor, Context.MODE_PRIVATE);
                                    fos.write(("").getBytes());
                                    fos.close();

                                    fos = openFileOutput(fileNameDate, Context.MODE_PRIVATE);
                                    fos.write(("").getBytes());
                                    fos.close();
                                    fos = openFileOutput(fileNameTimer, Context.MODE_PRIVATE);
                                    fos.write(("").getBytes());
                                    fos.close();

                                    fos = openFileOutput(fileAllData, Context.MODE_PRIVATE);
                                    fos.write(("").getBytes());
                                    fos.close();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Toast.makeText(PastWorkoutScreen.this, "ERROR Saving file!", Toast.LENGTH_SHORT).show();
                                }
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                Toast.makeText(PastWorkoutScreen.this, "File Cleared", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel", null);
                AlertDialog alert = builder.create();
                alert.show();

            }

        });


    }

    int find_factors(int i) {
        boolean running = true;
        int factor = 0;
        double x = (double) i;
        while (running) {
            //running = (x/10.0 < 1)?false:(true);
            if (x / 10.0 < 1) {
                running = false;
            } else {
                x = x / 10.0;
            }
            factor++;
        }
        return (factor);
    }


    private void readData() {
        String text = "";

        try {
            FileInputStream fis = openFileInput(fileAllData);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(fis, Charset.forName("UTF-8"))
            );
            int i = 1;
            while ((text = reader.readLine()) != null) {
                // Read the data
                String space = "     ";
                int factor = find_factors(i);
                if (factor == 1) {
                    space = "     ";
                } else if (factor == 2) {
                    space = "    ";
                } else if (factor == 3) {
                    space = "   ";
                } else if (factor == 4) {
                    space = "  ";
                } else {
                    space = " ";
                }
                // Toast.makeText(PastWorkoutScreen.this, ""+text, Toast.LENGTH_SHORT).show();
                weightSamples.add(" " + Integer.toString(i) + space + text);
                i++;
            }
            fis.close();
            text = "";
/*************************************************************************/

            fis = openFileInput(fileNameArm);
            reader = new BufferedReader(
                    new InputStreamReader(fis, Charset.forName("UTF-8"))
            );

            while ((text = reader.readLine()) != null) {
                // Read the data
                weightSamplesArms.add(text);
            }
            fis.close();
            text = "";

/**************************************************************************/

            fis = openFileInput(fileNameDate);
            reader = new BufferedReader(
                    new InputStreamReader(fis, Charset.forName("UTF-8"))
            );

            while ((text = reader.readLine()) != null) {
                // Read the data
                weightSamplesDate.add(text);
            }
            fis.close();
            text = "";

/*************************************************************************/
            fis = openFileInput(fileNameLeg);
            reader = new BufferedReader(
                    new InputStreamReader(fis, Charset.forName("UTF-8"))
            );

            while ((text = reader.readLine()) != null) {
                // Read the data
                weightSamplesLegs.add(text);
            }
            fis.close();
            text = "";

/*************************************************************************/
            fis = openFileInput(fileNameBackAndCore);
            reader = new BufferedReader(
                    new InputStreamReader(fis, Charset.forName("UTF-8"))
            );

            while ((text = reader.readLine()) != null) {
                // Read the data
                weightSamplesBackAndCore.add(text);
            }
            fis.close();
            text = "";
/*************************************************************************/
            fis = openFileInput(fileNameMachines);
            reader = new BufferedReader(
                    new InputStreamReader(fis, Charset.forName("UTF-8"))
            );

            while ((text = reader.readLine()) != null) {
                // Read the data
                weightSamplesMachines.add(text);
            }
            fis.close();
            text = "";
/*************************************************************************/

            fis = openFileInput(fileNameWeights);
            reader = new BufferedReader(
                    new InputStreamReader(fis, Charset.forName("UTF-8"))
            );

            while ((text = reader.readLine()) != null) {
                // Read the data
                weightSamplesFreeWeights.add(text);
            }
            fis.close();
            text = "";

/*************************************************************************/

            fis = openFileInput(fileNameFloor);
            reader = new BufferedReader(
                    new InputStreamReader(fis, Charset.forName("UTF-8"))
            );

            while ((text = reader.readLine()) != null) {
                // Read the data
                weightSamplesFloorExercises.add(text);
            }
            fis.close();
            text = "";
/*************************************************************************/

            fis = openFileInput(fileNameTotal);
            reader = new BufferedReader(
                    new InputStreamReader(fis, Charset.forName("UTF-8"))
            );

            while ((text = reader.readLine()) != null) {
                // Read the data
                weightSamplesTotalDisplay.add(text);
                weightSamplesTotal.add(text);

            }
            fis.close();
            text = "";
/*************************************************************************/
        } catch (Exception e) {
            e.printStackTrace();

            SharedPreferences sharedPreferences = getSharedPreferences("MySettings", Context.MODE_PRIVATE);
            int starting = Integer.parseInt(sharedPreferences.getString("starting_value", "0"));

            if (starting == 0) {
                Toast.makeText(PastWorkoutScreen.this, "Welcome to Spotter! Press 'Start New Workout' to begin", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(PastWorkoutScreen.this, "ERROR reading file", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void populateTextView() {
        TextView change_text;
        float sumArms = 0;
        float sumLegs = 0;
        float sumBackAndCore = 0;
        float sumMachines = 0;
        float sumFreeWeights = 0;
        float sumFloorExercise = 0;
        float sumTotal = 0;

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
                "         ",
                "          ",
                "           ",
                "            ",
                "             ",
                "              ",
                "               ",
                "                ",
                "                 ",
                "                  ",
                "                   ",
                "                    ",
                "                     ",
                "                      ",
                "                       ",
                "                        ",
                "                         "};

        String[] unSummedArms = weightSamplesArms.toArray(new String[weightSamplesArms.size()]);
        String[] unSummedLegs = weightSamplesLegs.toArray(new String[weightSamplesLegs.size()]);
        String[] unSummedBackAndCore = weightSamplesBackAndCore.toArray(new String[weightSamplesBackAndCore.size()]);
        String[] unSummedMachines = weightSamplesMachines.toArray(new String[weightSamplesMachines.size()]);
        String[] unSummedFreeWeights = weightSamplesFreeWeights.toArray(new String[weightSamplesFreeWeights.size()]);
        String[] unSummedFloorExercises = weightSamplesFloorExercises.toArray(new String[weightSamplesFloorExercises.size()]);
        String[] unSummedTotal = weightSamplesTotal.toArray(new String[weightSamplesTotal.size()]);
        String[] unBrokenDates = weightSamplesDate.toArray(new String[weightSamplesTotal.size()]);




        for (int i = 0; i < unSummedArms.length; i++) {
            sumArms = sumArms + Float.parseFloat(unSummedArms[i]);
            sumLegs = sumLegs + Float.parseFloat(unSummedLegs[i]);
            sumBackAndCore = sumBackAndCore + Float.parseFloat(unSummedBackAndCore[i]);
            sumMachines = sumMachines + Float.parseFloat(unSummedMachines[i]);
            sumFreeWeights = sumFreeWeights + Float.parseFloat(unSummedFreeWeights[i]);
            sumFloorExercise = sumFloorExercise + Float.parseFloat(unSummedFloorExercises[i]);
            sumTotal = sumTotal + Float.parseFloat(unSummedTotal[i]);
        }
        //Toast.makeText(PastWorkoutScreen.this,""+(int)(sumTotal) , Toast.LENGTH_LONG).show();
        String text[] = {" Arms", " Legs", " Back and Core", " Machines", " Free Weights", " Floor Exercises"};

        //Toast.makeText(PastWorkoutScreen.this, "" + sumFloorExercise, Toast.LENGTH_SHORT).show();
        change_text = (TextView) findViewById(R.id.textViewArms);
        change_text.setText(text[0] + spaces[17 - (text[0]).length()] + (int) (sumArms));

        change_text = (TextView) findViewById(R.id.textViewLegs);
        change_text.setText(text[1] + spaces[17 - (text[1]).length()] + (int) sumLegs);


        change_text = (TextView) findViewById(R.id.textViewBackAndCore);
        change_text.setText(text[2] + spaces[17 - (text[2]).length()] + (int) sumBackAndCore);

        change_text = (TextView) findViewById(R.id.textViewMachines);
        change_text.setText(text[3] + spaces[17 - (text[3]).length()] + (int) sumMachines);

        change_text = (TextView) findViewById(R.id.textViewFreeWeights);
        change_text.setText(text[4] + spaces[17 - (text[4]).length()] + (int) sumFreeWeights);

        change_text = (TextView) findViewById(R.id.textViewFloorExercises);
        change_text.setText(text[5] + spaces[17 - (text[5]).length()] + (int) sumFloorExercise);

        change_text = (TextView) findViewById(R.id.textViewTotalPastWorkouts);

        change_text.setText("" + (int) (sumTotal));
    }


    private void populateListView() {
        String[] myItems1 = weightSamples.toArray(new String[weightSamples.size()]);
        String[] myItems2 = weightSamplesArms.toArray(new String[weightSamplesArms.size()]);
        String[] myItems3 = weightSamplesLegs.toArray(new String[weightSamplesLegs.size()]);
        String[] myItems4 = weightSamplesBackAndCore.toArray(new String[weightSamplesBackAndCore.size()]);
        String[] myItems5Display = weightSamplesTotalDisplay.toArray(new String[weightSamplesTotal.size()]);
        String[] myItems5 = weightSamplesTotal.toArray(new String[weightSamplesTotal.size()]);

        // Build the adapter
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                this,              //Contect for the activity
                R.layout.da_item,  //Layout to use
                myItems1);          // Items to be displayed

        // Configure the list view
        ListView list1 = (ListView) findViewById(R.id.listViewAll);
        list1.setAdapter(adapter1);
        //Toast.makeText(PastWorkoutScreen.this, "" + myItems2[0], Toast.LENGTH_SHORT).show();
        double x, y, x2, y2, x3, y3, x4, y4;
        GraphView graph = (GraphView) findViewById(R.id.workout_graph);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(10);
        series = new LineGraphSeries<DataPoint>();
        series2 = new LineGraphSeries<DataPoint>();
        series3 = new LineGraphSeries<DataPoint>();
        series4 = new LineGraphSeries<DataPoint>();

        for (int i = 0; i < myItems2.length; i++) {
            x = i;
            y = Double.parseDouble(myItems2[i]);

            series.appendData(new DataPoint(x, y), true, myItems2.length);
            x2 = i;
            x3 = i;
            x4 = i;
            y2 = Double.parseDouble(myItems3[i]); // Legs
            y3 = Double.parseDouble(myItems4[i]);
            y4 = Double.parseDouble(myItems5[i]);

            series2.appendData(new DataPoint(x2, y2), true, myItems3.length);
            series3.appendData(new DataPoint(x3, y3), true, myItems4.length);
            series4.appendData(new DataPoint(x4, y4), true, myItems5.length);
        }
//
        series.setColor(Color.BLACK);// Arms
        series2.setColor(Color.BLUE);// Legs
        series3.setColor(Color.RED); // Back and Core
        series4.setThickness(10);
        series4.setColor(Color.GREEN);
        series4.setDrawDataPoints(true);
        //series.setDataPointsRadius(20);


        graph.getViewport().setScrollable(true);
        graph.addSeries(series4);
        graph.addSeries(series); // Arms
        graph.addSeries(series2);
        graph.addSeries(series3);


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.listViewAll);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View viewClicked, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(PastWorkoutScreen.this);

                builder.setMessage("Delete Row " + (position + 1) + "?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //THIS IS TO DELETE A SINGLE ENTRY
                                try {
                                    TextView textView = (TextView) viewClicked;
                                    String message = textView.getText().toString();


                                    removeLine(fileAllData, position, message);
                                    removeLine(fileNameTotal, position, message);
                                    removeLine(fileNameArm, position, message);
                                    removeLine(fileNameLeg, position, message);
                                    removeLine(fileNameBackAndCore, position, message);
                                    removeLine(fileNameMachines, position, message);
                                    removeLine(fileNameWeights, position, message);
                                    removeLine(fileNameFloor, position, message);
                                    removeLine(fileNameDate, position, message);


                                    Toast.makeText(PastWorkoutScreen.this, "Entry Cleared", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), PastWorkoutScreen.class);
                                    startActivity(intent);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Toast.makeText(PastWorkoutScreen.this, "Nothing Happened!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancel", null);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }


    public void removeLine(final String file, final int lineIndex, final String lineToRemove) throws IOException {

        List<String> fileContents = new ArrayList<String>() {
        };
        String text = "";
        FileInputStream fis = openFileInput(file);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(fis, Charset.forName("UTF-8"))
        );
        int i = 0;

        //FIND THE LINE TO BE DISTROYED
        while ((text = reader.readLine()) != null) {
//            if(!text.equals(lineToRemove)) {
//                fileContents.add(text);
//            }
            if (i != lineIndex) {
                fileContents.add(text);
            }
            i++;

        }
        fis.close();

        //CLEAR FILE
        FileOutputStream fos = openFileOutput(file, Context.MODE_PRIVATE);
        fos.write(("").getBytes());
        fos.close();

        //OVERWRITE THE OLD FILE
        FileOutputStream fos2 = openFileOutput(file, MODE_APPEND | Context.MODE_PRIVATE);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos2));
        for (i = 0; i < fileContents.size(); i++) {
            bw.write(fileContents.get(i));
            bw.newLine();
            //   Toast.makeText(PastWorkoutScreen.this, fileContents.get(i), Toast.LENGTH_LONG).show();
        }
        bw.close();
        fos2.close();
    }

}

