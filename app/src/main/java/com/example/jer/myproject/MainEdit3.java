package com.example.jer.myproject;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainEdit3 extends AppCompatActivity {

    private EditText priceEditText;
    private Spinner spinProvince, spinAmp;
    private String provinceString, ampString, EditAlet;
    private String[] ampStrings,samp,spro,spri;
    private ListView trafficListView;
    public static List<TreeString> myListweb;
    public static JSONArray dataJsonArray, locationjJsonArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_edit3);

        bindWidget();
        //getweb();
        //getmylist();
        spinnerLocation();
    }



    private void getmylist() {

        for(int i=0;i<locationjJsonArray.length();i++)
        {
            try {
                JSONObject jj=dataJsonArray.getJSONObject(i);
                myListweb.add(new TreeString(jj.getString("destinationprovince"),jj.getString("destinationcounty"),jj.getString("price")));
                listViewController();
            }
            catch (Exception e){}

             }


    }

    private void getweb(){

        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);


        try {

            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("User", MainLogin.edtString1));
            HttpClient httpClient = new DefaultHttpClient();

            HttpEntity httpEntity = null;
            HttpResponse httpResponse = null;

            HttpPost httpPost = new HttpPost("http://192.168.224.102/loginregister.php");
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            httpResponse = httpClient.execute(httpPost);

            httpEntity = httpResponse.getEntity();
            InputStream inputStream = httpEntity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            inputStream.close();
            String response = sb.toString();

            String[] str=response.split("^");


            JSONObject jsonObject = new JSONObject(str[0]);
            dataJsonArray = jsonObject.getJSONArray("data");
            //dataJsonObject = exArray.getJSONObject(0);

            JSONObject jsonObjec = new JSONObject(str[1]);
            locationjJsonArray= jsonObjec.getJSONArray("data");
            //locationJsonObject = exArra.getJSONObject(0);

//            IdString = object.getString("id");
//            NumString = object.getString("num");
//
//            Log.d("asss","===>>>>+"+object.getString("id"));
//            Log.d("asss","===>>>+++"+object.getString("num"));

            Toast.makeText(MainEdit3.this, "บันทึกข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show();
            finish();

        } catch (Exception e) {
            Toast.makeText(MainEdit3.this, "ไม่สามาถรอัพข้อมูลได้", Toast.LENGTH_SHORT).show();
        }

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
            objMyAlertDialog.myDialog(MainEdit3.this, R.drawable.danger,
                    "Have Space", "Please Fill All Blank");
        }else {
            myListweb.add(new TreeString(provinceString, ampString, priceEditText.getText().toString()));
            listViewController();
        }
    }

    private void listViewController() {

        setlist();
        MyAdepter objMyAdepter = new MyAdepter(MainEdit3.this, spro, samp,spri);
        trafficListView.setAdapter(objMyAdepter);

    }

    private void setlist() {

        spro=new String[myListweb.size()];
        samp=new String[myListweb.size()];
        spri=new String[myListweb.size()];
        for(int i=0;i<myListweb.size();i++)
        {
            spro[i]=myListweb.get(i).pro.toString();
            samp[i]=myListweb.get(i).amp.toString();
            spri[i]=myListweb.get(i).pri.toString();
        }

    }

    private void bindWidget() {

        trafficListView = (ListView) findViewById(R.id.listview1);
        priceEditText = (EditText) findViewById(R.id.edit_price);
        spinProvince = (Spinner) findViewById(R.id.spinner);
        spinAmp = (Spinner) findViewById(R.id.spinner2);
        myListweb = new ArrayList<TreeString>();
        priceEditText.setInputType(InputType.TYPE_CLASS_TEXT);

    }

    public void clickNext(View view){

        if(myListweb.size()==0){
            MyAlertDialog objMyAlertDialog = new MyAlertDialog();
            objMyAlertDialog.myDialog(MainEdit3.this, R.drawable.danger,
                    "Have Space", "Please Fill All Blank");
        }else {

            startActivity(new Intent(MainEdit3.this, MapRegister.class));
        }
    }
}
