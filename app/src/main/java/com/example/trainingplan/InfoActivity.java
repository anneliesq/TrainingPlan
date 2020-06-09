package com.example.trainingplan;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class InfoActivity extends AppCompatActivity {
    private Button back;
    private Button calculatePace;
    private EditText hoursField;
    private EditText minutesField;
    private EditText secondsField;
    private double hoursValue = 0;
    private double minutesValue = 0;
    private double secondsValue = 0;
    private double totalSeconds = 0;
    private CheckBox mileCheckBox;
    private CheckBox fivekCheckBox;
    private CheckBox tenkCheckBox;
    private double distance = 0;
    private TextView milePace;
    private TextView threekPace;
    private TextView fivekPace;
    private TextView tenkPace;
    private TextView tempoPace;
    private TextView easyPace;
    private TextView longPace;

    private double tempo = 0;
    private double tempoMinute = 0;
    private double tempoSecond = 0;
    private double easyRun = 0;
    private double easyMinute = 0;
    private double easySecond = 0;
    private double longRun = 0;
    private double longMinute = 0;
    private double longSecond = 0;
    private double tenkConversion = 0;
    private double tenkMinute = 0;
    private double tenkSecond = 0;
    private double fivekConversion = 0;
    private double fivekMinute = 0;
    private double fivekSecond = 0;
    private double threekConversion = 0;
    private double threekMinute = 0;
    private double threekSecond = 0;
    private double mileConversion = 0;
    private double mileMinute = 0;
    private double mileSecond = 0;
    private double remainder = 0;
    private double minutes;
    //Formats the minute and second values to fit a typical digital time layout
    NumberFormat minuteDecimal = new DecimalFormat ("####");
    NumberFormat secondsDecimal = new DecimalFormat("#00");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
//button goes back to the user's training plan
        back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//initializes variables (EditText, Button, CheckBox, TextView)
        hoursField = (EditText) findViewById(R.id.hours);
        minutesField = (EditText) findViewById(R.id.minutes);
        secondsField = (EditText) findViewById(R.id.seconds);
        calculatePace = (Button) findViewById(R.id.calculate);
        mileCheckBox = (CheckBox)findViewById(R.id.mileCheckBox);
        fivekCheckBox = (CheckBox)findViewById(R.id.fivekCheckBox);
        tenkCheckBox = (CheckBox)findViewById(R.id.tenkCheckBox);
        milePace = (TextView)findViewById(R.id.milePace);
        threekPace = (TextView)findViewById(R.id.threekPace);
        fivekPace = (TextView)findViewById(R.id.fivekPace);
        tenkPace = (TextView)findViewById(R.id.tenkPace);
        tempoPace = (TextView)findViewById(R.id.tempoPace);
        easyPace = (TextView)findViewById(R.id.easyPace);
        longPace = (TextView)findViewById(R.id.longPace);
        //When pressed, button initiates the conversion from entered time/distance to the other training paces.
        calculatePace.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String initialHours = hoursField.getText().toString();
                hoursValue = Double.parseDouble(initialHours);
                String initialMinutes = minutesField.getText().toString();
                minutesValue = Double.parseDouble(initialMinutes);
                String initialSeconds = secondsField.getText().toString();
                secondsValue = Double.parseDouble(initialSeconds);
                equivalentPaces();
                //Prints out the paces in a digital time format (pace is /mile)
                milePace.setText("1 mile:   " + minuteDecimal.format(mileMinute) + ":" + secondsDecimal.format(mileSecond) + " /mile");
                threekPace.setText("3k:     " + minuteDecimal.format(threekMinute) + ":" + secondsDecimal.format(threekSecond)+ " /mile");
                fivekPace.setText("5k:      " + minuteDecimal.format(fivekMinute) + ":" + secondsDecimal.format(fivekSecond)+ " /mile");
                tenkPace.setText("10k:      " + minuteDecimal.format(tenkMinute) + ":" + secondsDecimal.format(tenkSecond)+ " /mile");
                tempoPace.setText("Tempo:      " + minuteDecimal.format(tempoMinute) + ":" + secondsDecimal.format(tempoSecond)+ " /mile");
                easyPace.setText("Easy Run:      " + minuteDecimal.format(easyMinute) + ":" + secondsDecimal.format(easySecond)+ " /mile");
                longPace.setText("Long Run:      " + minuteDecimal.format(longMinute) + ":" + secondsDecimal.format(longSecond)+ " /mile");

            }
        });
    }

    //Method takes in pace in  seconds and calculates the minute values
    public double  paceMinute (double pace) {
        double intermediateMinute = pace/60;
        double intermediateRemainder = pace%60;
        intermediateRemainder = intermediateRemainder / 60;
        minutes = Math.round(intermediateMinute-intermediateRemainder);
        return minutes;
    }
    //Methos takes in seconds and calculates the seconds for the mile pace in minute:second format
    public double paceSecond (double pace) {
        remainder = Math.round(pace%60);
        return remainder;

    }
    //
    //Method uses conversion factors to calculate the other training paces
    public void equivalentPaces () {
        hoursValue = (hoursValue * 3600);
        minutesValue= (minutesValue * 60);
        totalSeconds = hoursValue + minutesValue + secondsValue;
        //converts to 5k and 10k value
        if (mileCheckBox.isChecked () ) {
            distance = 1;
            mileConversion = totalSeconds;
            fivekConversion = mileConversion * 1.13;
            tenkConversion = fivekConversion * 1.04;

        //converts to 10k value
        }
        else if (fivekCheckBox.isChecked () ) {
            distance = 3.1;
            fivekConversion = totalSeconds/distance;
            tenkConversion = fivekConversion * 1.04;
        //converts to 5k value
        }
        else if (tenkCheckBox.isChecked() ) {
            distance = 6.2;
            tenkConversion = totalSeconds/distance;
            fivekConversion = tenkConversion /1.04;
        }
        //using the 5k and 10k values, all other paces are calculated
        tempo = fivekConversion * 1.066;
        easyRun = tenkConversion + 120;
        longRun = tenkConversion + 150;
        mileConversion = fivekConversion / 1.13;
        threekConversion = fivekConversion / 1.07;

        //calls the paceMinute and paceSecond methods to determine the minute and second values for the pace
        tempoMinute = paceMinute(tempo);
        tempoSecond = paceSecond(tempo);
        easyMinute = paceMinute(easyRun);
        easySecond = paceSecond(easyRun);
        longMinute = paceMinute(longRun);
        longSecond = paceSecond(longRun);
        tenkMinute = paceMinute(tenkConversion);
        tenkSecond = paceSecond(tenkConversion);
        fivekMinute = paceMinute(fivekConversion);
        fivekSecond = paceSecond(fivekConversion);
        threekMinute = paceMinute(threekConversion);
        threekSecond = paceSecond(threekConversion);
        mileMinute = paceMinute(mileConversion);
        mileSecond = paceSecond(mileConversion);


    }


}
