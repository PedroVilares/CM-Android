package com.example.hw1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnOne;
    Button btnTwo;
    Button btnThree;
    Button btnFour;
    Button btnFive;
    Button btnSix;
    Button btnSeven;
    Button btnEight;
    Button btnNine;
    Button btnZero;
    Button btnAst;
    Button btnHash;
    Button btnDial;
    Button btnDelete;
    EditText editDialer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOne = findViewById(R.id.buttonOne);
        btnTwo = findViewById(R.id.buttonTwo);
        btnThree = findViewById(R.id.buttonThree);
        btnFour = findViewById(R.id.buttonFour);
        btnFive = findViewById(R.id.buttonFive);
        btnSix = findViewById(R.id.buttonSix);
        btnSeven = findViewById(R.id.buttonSeven);
        btnEight = findViewById(R.id.buttonEight);
        btnNine = findViewById(R.id.buttonNine);
        btnZero = findViewById(R.id.buttonZero);
        btnAst = findViewById(R.id.buttonAst);
        btnHash = findViewById(R.id.buttonHash);
        btnDial = findViewById(R.id.buttonCall);
        btnDelete = findViewById(R.id.buttonDelete);
        editDialer = findViewById(R.id.editDial);

        btnOne.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onLongPress(v);
                return false;
            }
        });

        btnTwo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onLongPress(v);
                return false;
            }
        });

        btnThree.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onLongPress(v);
                return false;
            }
        });
    }

    public void onLongPress(View view){
        Intent intent = new Intent(this,LongPress.class);
        startActivity(intent);
    }

    public void one(View view) {
        onButtonPress(btnOne,editDialer,"1");
    }

    public void two(View view) {
        onButtonPress(btnTwo,editDialer,"2");
    }

    public void three(View view) {
        onButtonPress(btnThree,editDialer,"3");
    }

    public void four(View view) {
        onButtonPress(btnFour,editDialer,"4");
    }

    public void five(View view) {
        onButtonPress(btnFive,editDialer,"5");
    }

    public void six(View view) {
        onButtonPress(btnSix,editDialer,"6");
    }

    public void seven(View view) {
        onButtonPress(btnSeven,editDialer,"7");
    }

    public void eight(View view) {
        onButtonPress(btnEight,editDialer,"8");
    }

    public void nine(View view) {
        onButtonPress(btnNine,editDialer,"9");
    }

    public void ast(View view) {
        onButtonPress(btnAst,editDialer,"*");
    }

    public void zero(View view) {
        onButtonPress(btnZero,editDialer,"0");
    }

    public void hash(View view) {
        onButtonPress(btnHash,editDialer,"#");
    }

    public void onDial(View view) {
        if(editDialer.getText().length() <= 3){
            Toast.makeText(this,"Invalid Number",Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(Intent.ACTION_DIAL);
            String hash = editDialer.getText().toString();
            if (hash.contains("#")){
                hash.replace("#","%23");
            }
            intent.setData(Uri.parse("tel:" + hash));
            startActivity(intent);
        };

    }

    public void onDelete(View view) {
        editDialer.setText("");
    }

    public void onButtonPress(Button button, EditText inputNumber, String number){
        String dialing_number = editDialer.getText().toString();
        inputNumber.setText(dialing_number+number);
    }
}
