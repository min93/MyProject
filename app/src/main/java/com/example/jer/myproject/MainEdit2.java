package com.example.jer.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainEdit2 extends AppCompatActivity {
    private EditText Edit1, Edit2, Edit3, Edit4, Edit5, Edit6, Edit7;
    public static String up_numaddressString, up_AlleyString, up_RoadString, up_districtString,
            up_countyString, up_provinceString, up_postofficeString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_edit2);

        bindWitget();



    }

    private void bindWitget() {

        Edit1 = (EditText) findViewById(R.id.editText1);
        Edit2 = (EditText) findViewById(R.id.editText2);
        Edit3 = (EditText) findViewById(R.id.editText3);
        Edit4 = (EditText) findViewById(R.id.editText4);
        Edit5 = (EditText) findViewById(R.id.editText5);
        Edit6 = (EditText) findViewById(R.id.editText6);
        Edit7 = (EditText) findViewById(R.id.editText7);
        Edit1.setInputType(InputType.TYPE_CLASS_TEXT);
        Edit2.setInputType(InputType.TYPE_CLASS_TEXT);
        Edit3.setInputType(InputType.TYPE_CLASS_TEXT);
        Edit4.setInputType(InputType.TYPE_CLASS_TEXT);
        Edit5.setInputType(InputType.TYPE_CLASS_TEXT);
        Edit6.setInputType(InputType.TYPE_CLASS_TEXT);
        Edit7.setInputType(InputType.TYPE_CLASS_TEXT);




    }

    public void clickType(View view){
        up_numaddressString = Edit1.getText().toString();
        up_AlleyString = Edit2.getText().toString();
        up_RoadString = Edit3.getText().toString();
        up_districtString = Edit4.getText().toString();
        up_countyString = Edit5.getText().toString();
        up_provinceString = Edit6.getText().toString();
        up_postofficeString = Edit7.getText().toString();
        Log.d("abcc",">>>>"+up_numaddressString);

        startActivity(new Intent(MainEdit2.this, MainEdit3.class));

    }
}
