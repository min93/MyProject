package com.example.jer.myproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MapRegister extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Button btnSave;
    private double latADouble = 0, lngADouble = 0;
    public static String latString, lngString, IdString, NumString;
    private ArrayList<String> jsonString;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sign_up3);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        bindWidget();

    } //Main Class


    private void bindWidget() {

        btnSave = (Button) findViewById(R.id.button6);

    }

    public void clickSaveData(View view){

        if (latADouble == 0){
            MyAlertDialog myAlertDialog = new MyAlertDialog();
            myAlertDialog.myDialog(this, R.drawable.icon_myaccount, "ยังไม่เลือกพิกัด",
                    "โปรดเลือกพิกัด");
        }else {

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
                    updateToMySQL();
                    upDataSQL();

//                    Handler objHandler = new Handler();
//                    objHandler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//
//                            selectid();
//
//                        } // event
//                    }, 500);


                    dialog.dismiss();
                    startActivity(new Intent(MapRegister.this, MainActivity.class));
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

    }

    private void upDataSQL() {

        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);


        for(int i=0;i<MainSignUp2.myList.size();i++) {
            try {

                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("isAdd", "true"));
                nameValuePairs.add(new BasicNameValuePair("Id", IdString));
                nameValuePairs.add(new BasicNameValuePair("Name", MainSignUp.StringEdit0));
                nameValuePairs.add(new BasicNameValuePair("Namevin", MainSignUp.StringEdit4));
                nameValuePairs.add(new BasicNameValuePair("Tel", MainSignUp.StringEdit5));
                nameValuePairs.add(new BasicNameValuePair("Timestart", MainSignUp.startString));
                nameValuePairs.add(new BasicNameValuePair("Timestop", MainSignUp.stopString));
                nameValuePairs.add(new BasicNameValuePair("Numaddress", MainSignType.numaddressString));
                nameValuePairs.add(new BasicNameValuePair("Alley", MainSignType.AlleyString));
                nameValuePairs.add(new BasicNameValuePair("Road", MainSignType.RoadString));
                nameValuePairs.add(new BasicNameValuePair("District", MainSignType.districtString));
                nameValuePairs.add(new BasicNameValuePair("County", MainSignType.countyString));
                nameValuePairs.add(new BasicNameValuePair("Province", MainSignType.provinceString));
                nameValuePairs.add(new BasicNameValuePair("Postoffice", MainSignType.postofficeString));
                nameValuePairs.add(new BasicNameValuePair("Destinationprovince", MainSignUp2.myList.get(i).pro.toString()));
                nameValuePairs.add(new BasicNameValuePair("Destinationcounty", MainSignUp2.myList.get(i).amp.toString()));
                nameValuePairs.add(new BasicNameValuePair("Price", MainSignUp2.myList.get(i).pri.toString()));
                nameValuePairs.add(new BasicNameValuePair("Latvin", latString));
                nameValuePairs.add(new BasicNameValuePair("Lngvin", lngString));
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://192.168.224.102/datainformation.php");
                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
                httpClient.execute(httpPost);

            } catch (Exception e) {
                Toast.makeText(MapRegister.this, "ไม่สามาถรอัพข้อมูลได้", Toast.LENGTH_SHORT).show();
            }
        }
    }

//    private void selectid() {
//
//        try {
//            new AsyncTask<Void, Void, Void>() {
//                @Override
//                protected Void doInBackground(Void... params) {
//                    try {
//                        URL url = new URL("http://192.168.224.101/selectjson.php");
//
//                        URLConnection urlConnection = url.openConnection();
//
//                        HttpURLConnection httpURLConnection = (HttpURLConnection)urlConnection;
//                        httpURLConnection.setAllowUserInteraction(false);
//                        httpURLConnection.setInstanceFollowRedirects(true);
//                        httpURLConnection.setRequestMethod("POST");
//                        httpURLConnection.connect();
//
//                        InputStream inputStream = null;
//
//                        if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK)
//                            inputStream = httpURLConnection.getInputStream();
//
//                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,
//                                "iso-8859-1"),8);
//
//                        StringBuilder stringBuilder = new StringBuilder();
//                        String line = null;
//
//                        while ((line=reader.readLine()) != null ){
//
//                            stringBuilder.append(line + "\n");
//
//                        }
//
//                        inputStream.close();
//
//                        Log.d("asss","++>"+stringBuilder.toString());
//
//                        JSONObject jsonObject = new JSONObject(stringBuilder.toString());
//                        JSONArray exArray = jsonObject.getJSONArray("data");
//                        JSONObject object = exArray.getJSONObject(0);
//                        Log.d("asss","===>>"+object.getString("id"));
//                        String IdString = object.getString("id");
//                        String NumString = object.getString("num");
//
//                        Log.d("asss","===>>>>+"+object.getString("id"));
//                        Log.d("asss","===>>>+++"+object.getString("num"));
//
//
//
//
//                    } catch (MalformedURLException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    return null;
//                }
//            }.execute();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }


    private void updateToMySQL() {


        //Change Policy
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);


            try {

                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("isAdd", "true"));
                nameValuePairs.add(new BasicNameValuePair("User", MainSignUp.StringEdit1));
                nameValuePairs.add(new BasicNameValuePair("Pass", MainSignUp.StringEdit2));
                nameValuePairs.add(new BasicNameValuePair("Num", MainSignUp.StringEdit3));
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
                Log.d("aassdd","===>><<"+response);

                JSONObject jsonObject = new JSONObject(response);
                JSONArray exArray = jsonObject.getJSONArray("data");
                JSONObject object = exArray.getJSONObject(0);
                Log.d("asss","===>>"+object.getString("id"));
                IdString = object.getString("id");
                NumString = object.getString("num");

                Log.d("asss","===>>>>+"+object.getString("id"));
                Log.d("asss","===>>>+++"+object.getString("num"));

                Toast.makeText(MapRegister.this, "บันทึกข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show();
                finish();

            } catch (Exception e) {
                Toast.makeText(MapRegister.this, "ไม่สามาถรอัพข้อมูลได้", Toast.LENGTH_SHORT).show();
            }


    }   // updateToMySQL



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //กำหนดจุดเริ่มต้นที่อนุสาวรี

        LatLng latLng = new LatLng(13.764995, 100.538432);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));

        //create Marker When Click onMap
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                //Clear marker
                mMap.clear();

                //ShowMarker
                mMap.addMarker(new MarkerOptions().position(latLng));

                //get Lat, Lng
                latADouble = latLng.latitude;
                lngADouble = latLng.longitude;
                latString = Double.toString(lngADouble);
                lngString = Double.toString(lngADouble);

            }   // onMapClick
        });

    }   // onMapReady
}
