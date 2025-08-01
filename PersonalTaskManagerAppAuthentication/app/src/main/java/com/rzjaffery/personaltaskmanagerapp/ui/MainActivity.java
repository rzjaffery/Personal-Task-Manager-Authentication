package com.rzjaffery.personaltaskmanagerapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.rzjaffery.personaltaskmanagerapp.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.button_start);

        if (startButton == null) {
            Log.e("MainActivity", "startButton is NULL – check layout or ID!");
        } else {
            startButton.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, TaskListActivity.class);
                startActivity(intent);
            });
        }

    }
}
