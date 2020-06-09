package com.example.trainingplan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button mileButton;
    private Button fivekButton;
    private Button tenkButton;
    private TextView choosePlan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        choosePlan = findViewById(R.id.textView1);
        //when button is pressed, method is called that opens mile activity
        mileButton = findViewById(R.id.mileButton);
        mileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMile();
            }
        });
        //when button is pressed, method is called that opens 5k activity
        fivekButton = findViewById(R.id.fivekButton);
        fivekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFivek();
            }
        });

        //when button is pressed, method is called that opens 10k activity
        tenkButton = findViewById(R.id.tenkButton);
        tenkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTenk();

            }
        });
    }
    //Method opens MileActivity
    public void startMile () {
        Intent intent = new Intent(this, MileActivity.class);
        startActivity(intent);
    }
    //Method opens FivekActivity
    public void startFivek () {
        Intent intent = new Intent(this, FivekActivity.class);
        startActivity(intent);
    }
    //Method opens TenkActivity
    public void startTenk () {
        Intent intent = new Intent(this, TenkActivity.class);
        startActivity(intent);
    }
}
