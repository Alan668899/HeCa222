package com.example.heca222;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ScrollView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static List<MoviePicList> mList = new ArrayList<>();
    //private Handler handler = new Handler();
    private int page=1;
    MoviePicListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //MoviePicList m=new MoviePicList("name","actor","https://puui.qpic.cn/vcover_vt_pic/0/5bactpuyxmoppwv1560236916/260","link");

        //mList.add(m);

        getData();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.MoviePicList);

        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(20));


        //recyclerView.addItemDecoration(new GridLayout(30));
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);
         adapter = new MoviePicListAdapter(this,mList);


        recyclerView.setAdapter(adapter);
        //上滑加载更多
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {


                //#####################################################################################
                //方法1
//                GridLayoutManager lm = (GridLayoutManager) recyclerView.getLayoutManager();
//                int totalItemCount = recyclerView.getAdapter().getItemCount();
//                int lastVisibleItemPosition = lm.findLastVisibleItemPosition();
//                int visibleItemCount = recyclerView.getChildCount();
//                if (newState == RecyclerView.SCROLL_STATE_IDLE
//                        && lastVisibleItemPosition == totalItemCount - 1
//                        && visibleItemCount > 0) {
//                    //加载更多
//                    initFruits();
//                    //adapter.notifyDataSetChanged();
//                    adapter.notifyItemInserted(adapter.getItemCount());
//                }

                //#####################################################################################

                //方法2
                GridLayoutManager gm = (GridLayoutManager) recyclerView.getLayoutManager();
                int lastItem = gm.findLastVisibleItemPosition();
                if (newState == RecyclerView.SCROLL_STATE_IDLE && adapter.getItemCount() > lastItem){
                    getData();
                    adapter.notifyItemInserted(adapter.getItemCount());
                }
                //#####################################################################################
            }
        });

        //adapter.notifyItemInserted(adapter.getItemCount());
        //Log.e("GGGGGGGGGGGyyy",String.valueOf(mList.size()));
    }







    private  void getData()  {

        new Thread(new Runnable(){
            @Override
            public void run() {

                Document doc=null;
                try {
                    doc = Jsoup.connect("http://m.f4yy.com/vod-type-id-1-pg-"+page+".html").get();
                    Log.e("jjjjjjjjjjjjj",doc.toString());


                    Elements videoList = doc.select("ul.list_tab_img").select("li");
                    for (Element videoElement : videoList) {
                        String name = videoElement.select("a").first().attr("title");
                        String link = videoElement.select("a").first().attr("href");
                        String pic = videoElement.select("img").attr("data-original");
                        if (pic.length() > 5) {
                            String head = pic.substring(0, 5);
                            if (!head.contains("http")) {
                                pic = "http:" + pic;
                            }
                        }
                        String score = videoElement.select("label.score").text();
                        String title = videoElement.select("label.title").text();
                        String actor = videoElement.select("p").text();
                        MoviePicList m=new MoviePicList(name,actor,pic,link);
                        Log.e("GGGGGGGGGGG",name);
                        //Log.e("GGGGGGGGGGGGGG",pic);
                        mList.add(m);

                        Message message = Message.obtain();
                        message.obj=mList;   //message.obj=bundle  传值也行
                        //message.what = 1;
                        handler.sendMessage(message);

                        //Log.e("GGGGGGGGGGG1",String.valueOf(mList.size()));
                    }

                    //Log.e("GGGGGGGGGGGGGG","name:::"+link+"link::::"+pic+"p::::"+p);

                    //Log.e("GGGGGGGGGGG2",String.valueOf(mList.size()));

                    if(doc != null){
                        Log.e("ooooooooooo",doc.toString());
                    }else{
                        Log.e("ooooooooooo","nulldoc.toString()");
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }






            }
        }).start();

        //Log.e("GGGGGGGGGGG3",String.valueOf(mList.size()));
        ++page;
    }



     Handler handler = new Handler(new Handler.Callback() {

         @Override
         public boolean handleMessage(Message msg) {


             adapter.setMlist((List<MoviePicList>)msg.obj);
             adapter.notifyItemInserted(adapter.getItemCount());
             //Log.e("GGGGGGGGGGG8",((MoviePicList) msg.obj).getImageUrl());
             return false;
         }
     });



}