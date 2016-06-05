package com.example.jer.myproject;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class MainLogin extends AppCompatActivity {

    private EditText edtlogin1, edtlogon2;
    public static String edtString1, edtString2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        bindWidget();
    }


    private void bindWidget() {

        edtlogin1 = (EditText) findViewById(R.id.EditLogin1);
        edtlogon2 = (EditText) findViewById(R.id.EditLogin2);

    }

    public void clickLogin (View view){

        edtString1 = edtlogin1.getText().toString();
        edtString2 = edtlogon2.getText().toString();
        Log.d("sdd",">>>>>||||"+edtString1);

        checkUser();
    }

    private void checkUser() {



//        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(threadPolicy);
//
//        try {
//
//            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//            nameValuePairs.add(new BasicNameValuePair("User", edtString1));
//            nameValuePairs.add(new BasicNameValuePair("Pass", edtString2));
//
//            HttpClient httpClient = new DefaultHttpClient();
//            HttpPost httpPost = new HttpPost("http://192.168.105.1/login.php");
//            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
//            httpClient.execute(httpPost);
//
//            Toast.makeText(MainLogin.this, "ถูกต้อง", Toast.LENGTH_SHORT).show();
//            finish();
//
//        } catch (Exception e) {
//            Toast.makeText(MainLogin.this, "ชื่อผู้ใช้และรหัสผ่านไม่ถูกต้อง", Toast.LENGTH_SHORT).show();
//        }


        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        Log.d("aassdd",">>>>>||||"+edtString1);
        Log.d("aassdd",">>>>>"+edtString2);
        try {

            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("User", edtString1));
            nameValuePairs.add(new BasicNameValuePair("Pass", edtString2));
            HttpClient httpClient = new DefaultHttpClient();


            HttpEntity httpEntity = null;
            HttpResponse httpResponse = null;

            HttpPost httpPost = new HttpPost("http://192.168.224.102/login.php");
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

            Log.d("aassdd","===>>||||<<"+response);
            if(response.trim().equals("LOGIN_OK")){
                startActivity(new Intent(MainLogin.this, MainEdit.class));
            }else {
                Toast.makeText(MainLogin.this, "Username && Password ผิด", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {

        }

    }
}
