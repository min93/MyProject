package com.example.jer.myproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by JER on 21/4/2559.
 */
public class MyAdepter extends BaseAdapter {

    //Explicit
    private Context objContext;
    private String[] pro, amp,pri;

    public MyAdepter(Context objContext, String[] pro, String[] amp,String[] pri) {
        this.objContext = objContext;
        this.pro = pro;
        this.amp = amp;
        this.pri = pri;
    } // Constructor

    @Override
    public int getCount() {
        return pro.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Open Service
        LayoutInflater objLayoutInflater = (LayoutInflater) objContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View objView1 = objLayoutInflater.inflate(R.layout.activity_show_list, parent, false);

        //Show Icon


        //Show Title
        TextView proTextView = (TextView) objView1.findViewById(R.id.textPro);
        proTextView.setText(pro[position]);

        //Show Detail
        TextView ampTextView = (TextView) objView1.findViewById(R.id.textAmp);
        ampTextView.setText(amp[position]);

        TextView priTextView = (TextView) objView1.findViewById(R.id.textPri);
        priTextView.setText(pri[position]);

        return objView1;
    }

}
