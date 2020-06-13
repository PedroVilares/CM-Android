package com.example.roomwordssample.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.roomwordssample.R;

public class NewWordActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.roomwordssample.REPLY";

    private EditText mNewWordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);

        mNewWordText = findViewById(R.id.edit_word);

        final Button mSaveButton = findViewById(R.id.button_save);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent saveIntent = new Intent();
                if(TextUtils.isEmpty(mNewWordText.getText())){
                    setResult(RESULT_CANCELED,saveIntent);
                } else {
                    String newWord = mNewWordText.getText().toString();
                    saveIntent.putExtra(EXTRA_REPLY,newWord);
                    setResult(RESULT_OK,saveIntent);
                }
                finish();
            }
        });
    }
}
