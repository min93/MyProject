package com.example.jer.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainSignType extends AppCompatActivity {

    private EditText Edit1, Edit2, Edit3, Edit4, Edit5, Edit6, Edit7;
    public static String numaddressString, AlleyString, RoadString, districtString, countyString,
                    provinceString, postofficeString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sign_type);

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
        numaddressString = Edit1.getText().toString();
        AlleyString = Edit2.getText().toString();
        RoadString = Edit3.getText().toString();
        districtString = Edit4.getText().toString();
        countyString = Edit5.getText().toString();
        provinceString = Edit6.getText().toString();
        postofficeString = Edit7.getText().toString();
        Log.d("abcc",">>>>"+numaddressString);

            startActivity(new Intent(MainSignType.this, MainSignUp2.class));

    }

}
