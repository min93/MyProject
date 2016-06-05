package com.example.jer.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainSignUp2 extends AppCompatActivity {

    private EditText priceEditText;
    private Spinner spinProvince, spinAmp;
    private String provinceString, ampString, EditAlet;
    private String[] ampStrings,samp,spro,spri;
    private ListView trafficListView;
    public static List<TreeString> myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sign_up2);

        bindWidget();

        spinnerLocation();

    }

    private void spinnerLocation() {

        final String[] startProvinceStrings = getResources().getStringArray(R.array.stop);
        ArrayAdapter<String> startArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, startProvinceStrings);
        spinProvince.setAdapter(startArrayAdapter);

        spinProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                provinceString = startProvinceStrings[position];
                setAmp(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                provinceString = startProvinceStrings[0];
            }
        });

    }

    private void setAmp(int position) {

        if(position==0){ ampStrings = getResources().getStringArray(R.array.P0); }
        else if(position==1){ ampStrings = getResources().getStringArray(R.array.P1); }

        ArrayAdapter<String> ampArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, ampStrings);
        spinAmp.setAdapter(ampArrayAdapter);

        spinAmp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ampString = ampStrings[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ampString = ampStrings[0];
            }
        });

    }

    public void clickAdd(View view){

        EditAlet = priceEditText.getText().toString();
        if (EditAlet.equals("")){
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.myDialog(MainSignUp2.this, R.drawable.danger,
                    "Have Space", "Please Fill All Blank");
        }else {
            myList.add(new TreeString(provinceString, ampString, priceEditText.getText().toString()));
            listViewController();
        }
    }

    private void listViewController() {

        setlist();
        MyAdepter objMyAdepter = new MyAdepter(MainSignUp2.this, spro, samp,spri);
        trafficListView.setAdapter(objMyAdepter);

    }

    private void setlist() {

        spro=new String[myList.size()];
        samp=new String[myList.size()];
        spri=new String[myList.size()];
        for(int i=0;i<myList.size();i++)
        {
            spro[i]=myList.get(i).pro.toString();
            samp[i]=myList.get(i).amp.toString();
            spri[i]=myList.get(i).pri.toString();
        }

    }

    private void bindWidget() {

        trafficListView = (ListView) findViewById(R.id.listview1);
        priceEditText = (EditText) findViewById(R.id.edit_price);
        spinProvince = (Spinner) findViewById(R.id.spinner);
        spinAmp = (Spinner) findViewById(R.id.spinner2);
        myList = new ArrayList<TreeString>();
        priceEditText.setInputType(InputType.TYPE_CLASS_TEXT);

    }

    public void clickNext(View view){

        if(myList.size()==0){
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.myDialog(MainSignUp2.this, R.drawable.danger,
                    "Have Space", "Please Fill All Blank");
        }else {

            startActivity(new Intent(MainSignUp2.this, MapRegister.class));
        }
    }


}
