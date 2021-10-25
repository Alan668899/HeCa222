package com.example.heca222;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ListView;

import com.example.heca222.WebView.OwnWebView;
import com.example.heca222.WebView.WebViewUtils;
import com.example.heca222.xiangqing.xiangqing;
import com.example.heca222.xiangqing.xiangqingAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JzvdStd;

public class xiangQingActivity extends AppCompatActivity {


    private List<xiangqing> xList = new ArrayList<>();
    private String url;
    private xiangqingAdapter adapter;
    public static OwnWebView webView;
    JzvdStd jzvdStd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_qing);

        Button b=(Button)findViewById(R.id.bt);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(jzvdStd.getVisibility()==View.VISIBLE){
                    jzvdStd.setVisibility(View.INVISIBLE);
                }else{
                    jzvdStd.setVisibility(View.VISIBLE);
                }
                //jzvdStd.get
            }
        });


         url=getIntent().getStringExtra("url");
         jzvdStd = (JzvdStd) findViewById(R.id.jz_video);
         jzvdStd.setUp("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4"
                , "饺子闭眼睛");
        //jzvdStd.posterImageView.setImage("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640");


//        AlertDialog.Builder ab=new AlertDialog.Builder(this);  //(普通消息框)
//
//        ab.setTitle("TextValue");  //设置标题
//        ab.setIcon(null);//设置图标
//        ab.setMessage(url);//设置消息内容
//        ab.setPositiveButton("text",null);//设置确定按钮
//        ab.setNegativeButton("取消",null);//设置取消按钮
//        ab.setNeutralButton("其他",null);
//        ab.show();//显示弹出框


        //initFruits();// 初始化水果数据
        thread.start();
         adapter = new xiangqingAdapter(xiangQingActivity.this,R.layout.xiangqing_item,xList);

        ListView listView = (ListView) findViewById(R.id.xiangqingListView);
        listView.setAdapter(adapter);


         webView=findViewById(R.id.webView);
        //初始化WebView
        WebViewUtils.initWebSettings(webView, webViewClient);
         //webView.loadUrl("http://m.f4yy.com/vod-detail-id-28112.html");
         //webView
    }

     public void play(String url){
        Log.e("UUUUUUUUUUUU","http://m.f4yy.com/"+url);
         //webView.setVisibility(View.INVISIBLE);
        //webView.loadUrl("http://m.f4yy.com/"+url);


         AlertDialog.Builder ab=new AlertDialog.Builder(xiangQingActivity.this);  //(普通消息框)

         ab.setTitle("TextValue");  //设置标题
         ab.setIcon(null);//设置图标
         ab.setMessage(url);//设置消息内容
         ab.setPositiveButton("text",null);//设置确定按钮
         ab.setNegativeButton("取消",null);//设置取消按钮
         ab.setNeutralButton("其他",null);
         ab.show();//显示弹出框

    };



    WebViewClient webViewClient = new WebViewClient() {


        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            if (url.contains(".mp4") || url.contains(".m3u8") || url.contains(".avi") ||
                    url.contains(".mov") || url.contains(".mkv") || url.contains(".flv") ||
                    url.contains(".f4v") || url.contains(".rmvb")) {



                //play(url);


                //Log.e("播放urlhtml：",url);
//                if (!TextUtils.equals(finalUrl, url)) {
//
//                    log("播放urlhtml："+id+":" +finalUrl+"****"+ url);
//                    id++;
//                    finalUrl = url;
                    Message msg = Message.obtain();
                    msg.what = 2;
                    msg.obj = url;
                    handler.sendMessage(msg);

//                }
            }
            //log("url：gethtml" + url);
            return super.shouldInterceptRequest(view, url);
        }
    };





        Thread thread= new Thread(new Runnable(){
            @Override
            public void run() {

                Document doc = null;
                try {
                    doc = Jsoup.connect("http://m.f4yy.com"+url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Log.e("ooooooooooo",doc.toString());






                Elements sourceList = doc.select("div.play-title");
                Elements contentElement = doc.select("div.play-box").select("ul");
                int sourceSize = sourceList.size();
                int contentSize = contentElement.size();
                for (int i = 0; i < contentSize; i++) {
                    if (i == sourceSize) {
                        break;
                    }
                    List<xiangqing> list = new ArrayList<>();
                    Elements elements = contentElement.get(i).select("li").select("a");
                    for (Element element : elements) {
                        //Log.w(TAG, "getHtml555: ****************" + element.attr("href"));
                        list.add(new xiangqing(element.attr("title"),element.attr("href")
                                ));
                        xList.add(new xiangqing(element.attr("title"),element.attr("href")
                        ));
                        //Log.w(TAG, "getHtmllistEpisode: ****************" + element.attr("href")+"*******" +element.attr("title"));
                        Log.e("GGGGGGGGGGG",element.attr("title"));
                        Log.e("GGGGGGGGGGG",element.attr("href"));
                    }


                    //MoviePicList m=new MoviePicList(name,actor,pic,link);
                    //Log.e("GGGGGGGGGGG",name);
                    //Log.e("GGGGGGGGGGGGGG",pic);
                    //mList.add(m);




                    Element e = sourceList.get(i);
                    String id = e.select("span").last().attr("id");
                    String source = e.select("span").last().select("a").text();
                    //Log.w(TAG, "getHtmlSource: ****************" + source+"*******" +id);
                    //sources.add(new Source(source, id, list));
                }
                Message message = Message.obtain();
                message.obj=xList;   //message.obj=bundle  传值也行
                message.what = 1;
                handler.sendMessage(message);

            }
        });//.start();

        //Log.e("GGGGGGGGGGG3",String.valueOf(mList.size()));







    Handler handler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {


            if(msg.what==1){

                adapter.setXlist((List<xiangqing>)msg.obj);
                List<xiangqing> l=(List<xiangqing>)msg.obj;
                adapter.notifyDataSetChanged();
                Log.e("GGGGGGGGGGG666",String.valueOf(l.size()));
                //adapter.notifyAll();
                //adapter.notifyItemInserted(adapter.getItemCount());
                //Log.e("GGGGGGGGGGG8",((MoviePicList) msg.obj).getImageUrl());
                thread.interrupt();

            }


            if(msg.what==2){
                Log.e("播放urlhtml：",msg.obj.toString());
                jzvdStd.setUp(msg.obj.toString(),"");
            }


            return false;
        }
    });


    @Override
    public void onBackPressed() {
        if (jzvdStd.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        jzvdStd.releaseAllVideos();
    }

}