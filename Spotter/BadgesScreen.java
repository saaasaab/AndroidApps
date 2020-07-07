package com.saaasaab.scott.spotter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Scott on 7/24/2017 on 9:54 AM
 */

public class BadgesScreen extends Activity {
    public List<String> weightSamples = new ArrayList<>();
    public List<String> weightSamplesArms = new ArrayList<>();
    public List<String> weightSamplesLegs = new ArrayList<>();
    public List<String> weightSamplesBackAndCore = new ArrayList<>();
    public List<String> weightSamplesTotal = new ArrayList<>();
    public List<String> weightSamplesDate = new ArrayList<>();
    public List<String> weightMachines = new ArrayList<>();
    public List<String> weightWeights = new ArrayList<>();
    public List<String> weightFloor = new ArrayList<>();

    String[] unBrokenDates;

    double sum = 0;
    double sumArms = 0;
    double sumLegs = 0;
    double sumBackAndCore = 0;
    double sumMachines = 0;
    double sumWeights = 0;
    double sumFloor = 0;

    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;
    private TextView change_description;

    public String[] badgeNames = {
            "Beginner", "100K", "250K",
            "Hercules", "Zeus", "Atlas",
            "Machines Level 1", "Machines Level 2", "Machines Level 3",
            "Free Weights Level 1", "Free Weights Level 2", "Free Weights Level 3",
            "Floor Exercises Level 1", "Floor Exercises Level 2", "Floor Exercises Level 3",
            "Arms Level 1", "Arms Level 2", "Arms Level 3",
            "Legs Level 1", "Legs Level 2", "Legs Level 3",
            "Core and Back Level 1", "Core and Back Level 2", "Core and Back Level 3",
            "5 Day Streak", "15 day Streak", "50 day streak",
            "Marshmallow Man", "Spartan Man", "Iron Man",
            "An Effort was Made", "Let the body hit the floor", "Mr. Friendly"

    };
    final int[] layouts = {
            R.layout.badge_beginner, R.layout.badge_one_hundred_k, R.layout.badge_two_hundred_and_fifty_k,
            R.layout.badge_hercules, R.layout.badge_zeus, R.layout.badge_atlas,
            R.layout.badge_machine_level_1, R.layout.badge_machine_level_2, R.layout.badge_machine_level_3,
            R.layout.badge_weights_level_1, R.layout.badge_weights_level_2, R.layout.badge_weights_level_3,
            R.layout.badge_floor_level_1, R.layout.badge_floor_level_2, R.layout.badge_floor_level_3,
            R.layout.badge_arms_level_1, R.layout.badge_arms_level_2, R.layout.badge_arms_level_3,
            R.layout.badge_legs_level_1, R.layout.badge_legs_level_2, R.layout.badge_legs_level_3,
            R.layout.badge_core_level_1, R.layout.badge_core_level_2, R.layout.badge_core_level_3,
            R.layout.badge_5_day_streak, R.layout.badge_15_day_streak, R.layout.badge_50_day_streak,
            R.layout.badge_marshmallow_man, R.layout.badge_spartan_man, R.layout.badge_iron_man,
            R.layout.badge_an_effort_was_made, R.layout.badge_let_the_bodies, R.layout.badge_mr_friendly};

    private int[] mThumbIds = {
            R.drawable.beginner, R.drawable.one_hundred_k, R.drawable.two_hundred_and_fifty_k,
            R.drawable.hercules, R.drawable.zeus, R.drawable.atlas,
            R.drawable.machines_level_1, R.drawable.machines_level_2, R.drawable.machines_level_3,
            R.drawable.free_weights_level_1, R.drawable.free_weights_level_2, R.drawable.free_weights_level_3,
            R.drawable.floor_level_1, R.drawable.floor_level_2, R.drawable.floor_level_3,
            R.drawable.arms_level_1, R.drawable.arms_level_2, R.drawable.arms_level_3,
            R.drawable.leg_level_1, R.drawable.leg_level_2, R.drawable.leg_level_3,
            R.drawable.core_level_1, R.drawable.core_level_2, R.drawable.core_level_3,
            R.drawable.five_day_streak, R.drawable.fifteen_day_streak, R.drawable.fifty_day_streak,
            R.drawable.marshmallow_man, R.drawable.spartan_man, R.drawable.iron_man,
            R.drawable.an_attempt_was_made, R.drawable.let_the_bodies_hit_the_floor, R.drawable.mr_friendly};



    int[] badges = new int[badgeNames.length];
    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.badges);


        SharedPreferences sharedPreferences = getSharedPreferences("MySettings", Context.MODE_PRIVATE);
        int starting = Integer.parseInt(sharedPreferences.getString("starting_value", "0"));


        sharedPreferences = getSharedPreferences("MyBadges", Context.MODE_PRIVATE);

        for (int i = 0; i < badgeNames.length; i++) {
            badges[i] = sharedPreferences.getInt("badge" + (i + 1), 1);
        }
        badges[32] = sharedPreferences.getInt("mr_friendly", 1);

        if (starting == 0) {
            Toast.makeText(BadgesScreen.this, "Welcome to Spotter! To earn badges, you fulfill the requirements by starting new workouts", Toast.LENGTH_LONG).show();
        } else {
            setBadges();
        }

        for (int i = 0; i < badgeNames.length; i++) {
            badges[i] = sharedPreferences.getInt("badge" + (i + 1), 1);
        }
        badges[32] = sharedPreferences.getInt("mr_friendly", 1);


//        for(int i = 1; i < badgeNames.length+1; i++){
//            testing_protocol("badge"+i);
//        }


        final GridView gridView = (GridView) findViewById(R.id.badges_view);
        gridView.setAdapter(new ImageAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                ViewGroup container = (ViewGroup) layoutInflater.inflate(R.layout.badge_error, null);

                if (position >= 0 && position <= badgeNames.length) {
                    container = (ViewGroup) layoutInflater.inflate(layouts[position], null);
                }

                DisplayMetrics dm = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dm);

                int width = dm.widthPixels;
                int height = dm.heightPixels;

                popupWindow = new PopupWindow(container, (int) (width * 1), (int) (height * 1), true);

                popupWindow.showAtLocation(gridView, Gravity.NO_GRAVITY, (int) (width * .00), (int) (height * .00));
                if (position == 32) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(BadgesScreen.this);
                    builder.setMessage("Did you share the app with a friend?")
                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    SharedPreferences sharedPreferences = getSharedPreferences("MyBadges", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putInt("mr_friendly", 0);
                                    editor.apply();

                                    Intent intent = new Intent(getApplicationContext(), BadgesScreen.class);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("NO", null);
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                container.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return false;
                    }
                });
            }

        });

    }

    private class ImageAdapter extends BaseAdapter {
        private Context mContext;

        ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(mContext);
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);

            int width = dm.widthPixels;
            int height = dm.heightPixels;

            imageView.setLayoutParams(new GridView.LayoutParams(width/3, width/3));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(5, 5, 5, 5);

            if (badges[position] == 0) {
                imageView.setImageResource(mThumbIds[position]);
            } else {
                Resources r = getResources();
                Drawable[] layers = new Drawable[2];

                layers[1] = getDrawable(R.drawable.empty);
                layers[0] = getDrawable(mThumbIds[position]);
                LayerDrawable layers2 = new LayerDrawable(layers);
                imageView.setImageDrawable(layers2);
            }
            return imageView;
        }


    }


    protected void setBadges() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyBadges", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String fileNameTotal = "fileTotal.txt";
        String fileNameArm = "fileArm.txt";
        String fileNameLeg = "fileLeg.txt";
        String fileNameBackAndCore = "fileBackAndCore.txt";
        String fileNameDate = "fileDate.txt";
        String fileAllData = "fileAllData.txt";
        String fileNameMachines = "fileMachines.txt";
        String fileNameWeights = "fileWeights.txt";
        String fileNameFloor = "fileFloor.txt";


        weightSamplesArms = readData(fileNameArm, weightSamplesArms);
        weightSamplesLegs = readData(fileNameLeg, weightSamplesLegs);
        weightSamplesBackAndCore = readData(fileNameBackAndCore, weightSamplesBackAndCore);
        weightSamplesTotal = readData(fileNameTotal, weightSamplesTotal);
        weightSamplesDate = readData(fileNameDate, weightSamplesDate);
        weightMachines = readData(fileNameMachines, weightMachines);
        weightWeights = readData(fileNameWeights, weightWeights);
        weightFloor = readData(fileNameFloor, weightFloor);
        unBrokenDates = weightSamplesDate.toArray(new String[weightSamplesTotal.size()]);
        //double sum;


        sum = sumOfArray(sum, weightSamplesTotal);
        sumArms = sumOfArray(sumArms, weightSamplesArms);
        sumLegs = sumOfArray(sumLegs, weightSamplesLegs);
        sumBackAndCore = sumOfArray(sumBackAndCore, weightSamplesBackAndCore);
        sumMachines = sumOfArray(sumMachines, weightMachines);
        sumWeights = sumOfArray(sumWeights, weightWeights);
        sumFloor = sumOfArray(sumFloor, weightFloor);

        for(int i = 1; i < badgeNames.length+1; i++){
            testing_protocol("badge"+i);
        }
        setSum(sum, 50000, "badge1", 0);
        setSum(sum, 100000, "badge2", 1);
        setSum(sum, 250000, "badge3", 2);

        setSum(sum, 1000000, "badge4", 3);
        setSum(sum, 5000000, "badge5", 4);
        setSum(sum, 20000000, "badge6", 5);

        //MACHINES
        setSum(sumMachines, 50000, "badge7", 6);
        setSum(sumMachines, 100000, "badge8", 7);
        setSum(sumMachines, 500000, "badge9", 8);

        //FREE WEIGHTS
        setSum(sumWeights, 50000, "badge10", 9);
        setSum(sumWeights, 100000, "badge11", 10);
        setSum(sumWeights, 500000, "badge12", 11);

        //FLOOR EXERCISES
        setSum(sumFloor, 50000, "badge13", 12);
        setSum(sumFloor, 100000, "badge14", 13);
        setSum(sumFloor, 500000, "badge15", 14);

        //ARMS
        setSum(sumArms, 50000, "badge16", 15);
        setSum(sumArms, 100000, "badge17", 16);
        setSum(sumArms, 500000, "badge18", 17);

        //LEGS
        setSum(sumLegs, 50000, "badge19", 18);
        setSum(sumLegs, 100000, "badge20", 19);
        setSum(sumLegs, 500000, "badge21", 20);

        //CORE AND BACK
        setSum(sumBackAndCore, 50000, "badge22", 21);
        setSum(sumBackAndCore, 100000, "badge23", 22);
        setSum(sumBackAndCore, 500000, "badge24", 23);

        //Day Streaks
        streak_badge(4, "badge25");
        streak_badge(14, "badge26");
        streak_badge(49, "badge27");
//        editor.putInt("badge25", 1);
//        editor.putInt("badge26", 1);
//        editor.putInt("badge27", 1);

        setManBadges(50000, "badge28", 27);
        setManBadges(250000, "badge29", 28);
        setManBadges(1000000, "badge30", 29);

        int efforted = 1;
        for (int i = 0; i < weightSamplesTotal.size(); i++) {
            if (Double.parseDouble(weightSamplesTotal.get(i)) < 1000) {
                efforted = 0;
            }
        }

        editor.putInt("badge31", efforted);
//        editor.putInt("badge31", 1);  //three day streak on the leaderboard
        editor.putInt("badge32", 1);  //Bodies to the floor
        //mr_friendly("badge33");//mr friendly
        // editor.putInt("badge33", 1);


//        editor.putInt("badge17", 1);
//        editor.putInt("badge18", 1);
//        editor.putInt("badge19", 1);
        editor.apply();
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
                Toast.makeText(BadgesScreen.this, "Welcome to Spotter! To earn badges, you must fulfill the requirements by starting new workouts", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(BadgesScreen.this, "ERROR reading file", Toast.LENGTH_SHORT).show();
            }

        }
        return (dataHolders);
    }

    void streak_badge(int x, String badge) {


        SharedPreferences sharedPreferences = getSharedPreferences("MySettings", Context.MODE_PRIVATE);
        long time_at_installation = sharedPreferences.getLong("time_at_installation", 0);

        sharedPreferences = getSharedPreferences("MyBadges", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(badge, 1);

        double[] shortenedDates = new double[unBrokenDates.length];
        List<Double> shortenedDates_without_repeats = new ArrayList<Double>();

        for (int i = 0; i < unBrokenDates.length; i++) {
            shortenedDates[i] = Math.floor((Double.parseDouble(unBrokenDates[i]) - time_at_installation) / (60 * 60 * 24));
        }

        if (shortenedDates.length > 0) {
            //Toast.makeText(PastWorkoutScreen.this, "" + shortenedDates[0], Toast.LENGTH_SHORT).show();
            shortenedDates_without_repeats.add(shortenedDates[0]);
            for (int i = 1; i < shortenedDates.length; i++) {
                if (shortenedDates[i] != shortenedDates[i - 1]) {
                    shortenedDates_without_repeats.add(shortenedDates[i]);
                    //Toast.makeText(PastWorkoutScreen.this, "" + shortenedDates_without_repeats[i], Toast.LENGTH_SHORT).show();
                }
            }
            for (int i = 0; i < shortenedDates_without_repeats.size() - x; i++) {
                if (shortenedDates_without_repeats.get(i + x) > -1) {
                    if (shortenedDates_without_repeats.get(i + x) - shortenedDates_without_repeats.get(i) < 1.4 * (x + 1) - 1) {
//                        Toast.makeText(BadgesScreen.this, "You have a 10 day streak", Toast.LENGTH_SHORT).show();
                        editor.putInt(badge, 0);
                    }

                }

            }
        }


        editor.apply();
    }

    void mr_friendly(String badge) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyBadges", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        int mr_friendly_acquired = sharedPreferences.getInt("mr_friendly", 1);
        editor.putInt(badge, mr_friendly_acquired);
        editor.apply();
    }

    void setSum(double sum, double limit, String badge, int index) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyBadges", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (sum > limit) {
            editor.putInt(badge, 0);
        } else {
            editor.putInt(badge, 1);
        }
        editor.apply();
    }


    void setManBadges(int limit, String badge, int index) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyBadges", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (sumArms > limit && sumLegs > limit && sumBackAndCore > limit
                && sumMachines > limit && sumWeights > limit && sumFloor > limit) {
            editor.putInt(badge, 0);
        } else {
            editor.putInt(badge, 1);
        }

        editor.apply();
    }


    double sumOfArray(double sum, List<String> array) {
        for (int i = 0; i < array.size(); i++) {
            sum += Double.parseDouble(array.get(i));
        }
        return (sum);
    }

    void testing_protocol(String badge) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyBadges", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(badge, 0);
        editor.apply();
    }
}
