package com.projects.saaasaab.smartinsole;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
public class MainActivity extends AppCompatActivity {

    Bluetooth bt;
    Context context;

    static float s1 = 150;
    static float s2 = 100;
    static float s3 = 250;
    static float displayAvaliable = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        context = this.getApplicationContext();
        bt = new Bluetooth(context, this);
        tryConnect(context);

//
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Turn title off
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(new GamePanel(this));
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;
    }
    //toggle motor
    public void toggleMotor(View view) {
        //send a flag to tell the Arduino to toggle a motor
        try {
            bt.sendData((byte) 'H');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //toggle light
    public void toggleLight(View view) {

        //send a flag to tell the Arduino to toggle a light
        try {
            bt.sendData((byte) 'L');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //BLUETOOTH GARBAGE
    //Connect to device over Bluetooth

    /**
     * attempt to connect to bluetooth automatically, then listen to connect button
     * this filters out any case where the user wasn't originally paired
     * with the device, preventing crashes
     * NOTE: the app will crash if bluetooth is not turned on!
     **/
    public void tryConnect(Context context) {
        //check if the target device is paired
        //if not, pull up the settings and let the user pair
        bt.findBT();
        //try again to find the device
        if (bt.findBT() == -1) {
            Toast.makeText(context, "Cannot connect to device", Toast.LENGTH_SHORT).show();
            return;
        }
        // Attempt open a Bluetooth connection
        try {
            bt.openBT();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //start listening for data coming from bluetooth device
        bt.beginListenForData();
        //make sure we're connected properly with a little handshake
        try {
            bt.sendData((byte) 'h');
        } catch (Exception e) {
            Toast.makeText(context, "cannot communicate with device...", Toast.LENGTH_SHORT).show();
        }
    }

    //function captures data coming in over Bluetooth
    public void receiveData(byte[] data) throws UnsupportedEncodingException {

//        final TextView sen1 = (TextView) findViewById(R.id.sensor1);
//        final TextView sen2 = (TextView) findViewById(R.id.sensor2);
//        final TextView sen3 = (TextView) findViewById(R.id.sensor3);
        String s = "";
        String value1 = "";
        String value2 = "";
        String value3 = "";
        //sen1.setText("" + data.length);
        if(data.length > 0 ) {
            s = new String(data, "UTF-8");
            String[] splited = s.split("\\s+");
//            value1 = splited[2];
//            value2 = splited[0];
//            value3 = splited[1];
//
//            value1 = splited[2];
//            value2 = splited[0];
//            value3 = splited[1];

            value1 = splited[1];
            value2 = splited[2];
            value3 = splited[0];

            s1 = Integer.parseInt(value1);
            s2 = Integer.parseInt(value2);
            s3 = Integer.parseInt(value3);

//            sen1.setText(value1);
//            sen2.setText(value2);
//            sen3.setText(value3);
        }
    }
}
