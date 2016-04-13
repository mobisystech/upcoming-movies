package com.mobisys.android.upcoming_movies.assignment.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mobisys.android.upcoming_movies.assignment.R;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        getSupportActionBar().hide();
    }
}
