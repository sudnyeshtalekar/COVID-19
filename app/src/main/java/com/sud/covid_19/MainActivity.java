package com.sud.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    int SPLASH_TIME = 4000; //This is 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Code to start timer and take action after the timer ends
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do any action here. Now we are moving to next page
                Intent mySuperIntent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(mySuperIntent);

                //This 'finish()' is for exiting the app when back button pressed from Home page which is ActivityHome
                finish();

            }
        }, SPLASH_TIME);


    }
}
