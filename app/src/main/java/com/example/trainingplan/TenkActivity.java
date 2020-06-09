package com.example.trainingplan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TenkActivity extends AppCompatActivity {
    private Button MoreInfo;
    private Button NewPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mile);
        //When button is pressed, calls method to start info activity
        MoreInfo = (Button) findViewById(R.id.MoreInfo);
        MoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startInfo();
            }
        });
        //When buttonis pressed, calls method to go back to the main activity
        NewPlan = (Button)findViewById(R.id.NewPlan);
        NewPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMain();

            }
        });
    }
    //Method opens InfoActivity
    public void startInfo () {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }
    //Method opens MainActivity
    public void startMain () {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}