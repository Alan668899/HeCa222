package com.example.heca222.xiangqing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heca222.MoviePicList;
import com.example.heca222.R;
import com.example.heca222.xiangQingActivity;

import java.util.ArrayList;
import java.util.List;

public class xiangqingAdapter  extends ArrayAdapter<xiangqing> {
    private int resourceId;
    private Context context;
    private List<xiangqing> xList = new ArrayList<>();


    public void setXlist(List<xiangqing> xList){
        this.xList=xList;
    }

    public xiangqingAdapter(Context context,int textViewResourceId, List<xiangqing> xList){
        super(context,textViewResourceId,xList);
        this.context = context;
        this.resourceId=textViewResourceId;
        this.xList=xList;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        xiangqing fruit = getItem(position); //获取当前项的Fruit实例
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

        TextView fruitName =(TextView) view.findViewById(R.id.name);
        //xiangqing x = xList.get(position);
        fruitName.setText(fruit.getName());
        fruitName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "click " + fruit.getUrl(), Toast.LENGTH_SHORT).show();
            //)ontext.getClass().play("");
                xiangQingActivity x=(xiangQingActivity)context;
                x.webView.loadUrl("http://m.f4yy.com/"+fruit.getUrl());
            }
        });

        return view;
    }
}
