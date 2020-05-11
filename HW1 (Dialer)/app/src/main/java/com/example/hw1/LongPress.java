package com.example.hw1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LongPress extends AppCompatActivity {

    EditText mName;
    EditText mNumber;
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_press);

        mName = findViewById(R.id.longPressLabel);
        mNumber = findViewById(R.id.longPressNumber);
        mButton = findViewById(R.id.longSaveButton);
    }

    public void onLong(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
