package com.example.jer.myproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity{

    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editMain);
        editText.setInputType(InputType.TYPE_CLASS_TEXT);


    }
    public void clickSignUp(View view){

        startActivity(new Intent(MainActivity.this, MainSignUp.class));

    }
    public void clickSignIn(View view){

        startActivity(new Intent(MainActivity.this, MainLogin.class));

    }




}
