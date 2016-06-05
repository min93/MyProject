package com.example.jer.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainEdit extends AppCompatActivity {

    private EditText Edit0, Edit1, Edit2, Edit3, Edit4, Edit5, Edit6, Edit7, Edit8;
    private Spinner spinStart, spinStop;
    public static String up_startString, up_stopString,up_StringEdit0,up_StringEdit1, up_StringEdit2,
            up_StringEdit3, up_StringEdit4, up_StringEdit5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_edit);

        bindWidget();

        spinnerTime();

    }

    public void signup_next1(View view){

        up_StringEdit0 = Edit0.getText().toString();
        up_StringEdit1 = Edit1.getText().toString();
        up_StringEdit2 = Edit2.getText().toString();
        up_StringEdit3 = Edit3.getText().toString();
        up_StringEdit4 = Edit4.getText().toString();
        up_StringEdit5 = Edit5.getText().toString();


        if (up_StringEdit0.equals("") ||
                up_StringEdit1.equals("") ||
                up_StringEdit2.equals("") ||
                up_StringEdit3.equals("") ||
                up_StringEdit4.equals("") ||
                up_StringEdit5.equals("")) {

            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.myDialog(MainEdit.this, R.drawable.danger,
                    "Have Space", "Please Fill All Blank");

        } else {

            //No Space
            startActivity(new Intent(MainEdit.this, MainEdit2.class));

        }



    }
    private void spinnerTime() {

        //spinner เวลาเปิดบริการ
        final String[] startProvinceStrings = getResources().getStringArray(R.array.time_start);
        ArrayAdapter<String> startArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, startProvinceStrings);
        spinStart.setAdapter(startArrayAdapter);
        spinStart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                up_startString = startProvinceStrings[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                up_startString = startProvinceStrings[0];
            }
        });

        //spinner เวลาปิดบริการ
        final String[] stopProvinceStrings = getResources().getStringArray(R.array.time_stop);
        ArrayAdapter<String> stopArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, stopProvinceStrings);
        spinStop.setAdapter(stopArrayAdapter);
        spinStop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                up_stopString = stopProvinceStrings[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                up_stopString = stopProvinceStrings[0];
            }
        });
    }

    public void bindWidget() {

        Edit0 = (EditText) findViewById(R.id.editText_0);
        Edit1 = (EditText) findViewById(R.id.Edit_Data_1);
        Edit2 = (EditText) findViewById(R.id.Edit_Data_2);
        Edit3 = (EditText) findViewById(R.id.Edit_Data_3);
        Edit4 = (EditText) findViewById(R.id.Edit_Data_4);
        Edit5 = (EditText) findViewById(R.id.Edit_Data_5);
        spinStart = (Spinner) findViewById(R.id.spinner3);
        spinStop = (Spinner) findViewById(R.id.spinner4);
        Edit1.setInputType(InputType.TYPE_CLASS_TEXT);
        Edit2.setInputType(InputType.TYPE_CLASS_TEXT);
        Edit3.setInputType(InputType.TYPE_CLASS_TEXT);
        Edit4.setInputType(InputType.TYPE_CLASS_TEXT);
        Edit5.setInputType(InputType.TYPE_CLASS_TEXT);

    }
}
