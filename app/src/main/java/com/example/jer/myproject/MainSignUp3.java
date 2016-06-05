package com.example.jer.myproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.*;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainSignUp3 extends AppCompatActivity {

    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sign_up3);



    }

    private void bindWidget() {


        btnSave = (Button) findViewById(R.id.button6);

    }

    public void clickSaveData(View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String m="";
        for(int i=0;i<MainSignUp2.myList.size();i++)
        {
            m=m+"\n         "+MainSignUp2.myList.get(i).pro+"  "+
                    MainSignUp2.myList.get(i).amp+"  "+MainSignUp2.myList.get(i).pri;
        }
        builder.setIcon(R.drawable.icon_myaccount);
        builder.setTitle("โปรดตรวจสอบข้อมูล");
        builder.setMessage(
                ("ข้อมูล1:  ") + MainSignUp.StringEdit3 + "\n" +
                ("ข้อมูล2:  ") + MainSignUp.StringEdit4 + "\n" +
                ("ข้อมูล3:  ") + MainSignUp.StringEdit5 + "\n" +
                ("ข้อมูล8:  ") + MainSignUp.startString + "\n" +
                ("ข้อมูล9:  ") + MainSignUp.stopString + "\n" +
                ("ข้อมูล10: ") + m.toString());

        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //updateToMySQL();
                dialog.dismiss();
                startActivity(new Intent(MainSignUp3.this, MainActivity.class));
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

            }
        });
        builder.show();

    }

    private final OkHttpClient client = new OkHttpClient();

    private void updateToMySQL() {

        //Change Policy
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        for(int i=0;i<MainSignUp2.myList.size();i++) {

            try {

                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("isAdd", "true"));
                nameValuePairs.add(new BasicNameValuePair("User", MainSignUp.StringEdit1));
                nameValuePairs.add(new BasicNameValuePair("Pass", MainSignUp.StringEdit2));
                nameValuePairs.add(new BasicNameValuePair("data1", MainSignUp.StringEdit3));
                nameValuePairs.add(new BasicNameValuePair("data2", MainSignUp.StringEdit4));
                nameValuePairs.add(new BasicNameValuePair("data3", MainSignUp.StringEdit5));
                nameValuePairs.add(new BasicNameValuePair("data8", MainSignUp.startString));
                nameValuePairs.add(new BasicNameValuePair("data9", MainSignUp.stopString));
                nameValuePairs.add(new BasicNameValuePair("data10", MainSignUp2.myList.get(i).pro.toString()));
                nameValuePairs.add(new BasicNameValuePair("data11", MainSignUp2.myList.get(i).amp.toString()));
                nameValuePairs.add(new BasicNameValuePair("data12", MainSignUp2.myList.get(i).pri.toString()));

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://192.168.105.1/loginregister.php");
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
                httpClient.execute(httpPost);

                Toast.makeText(MainSignUp3.this, "บันทึกข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show();
                finish();

            } catch (Exception e) {
                Toast.makeText(MainSignUp3.this, "ไม่สามาถรอัพข้อมูลได้", Toast.LENGTH_SHORT).show();
            }
        }

    }   // updateToMySQL

    public class postHttp {

        OkHttpClient client = new OkHttpClient();
        String run(String url,RequestBody body) throws IOException {

            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        }
    }

}
