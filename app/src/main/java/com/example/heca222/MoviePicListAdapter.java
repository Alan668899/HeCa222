package com.example.heca222;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MoviePicListAdapter extends RecyclerView.Adapter<MoviePicListAdapter.ViewHolder>{

    private List<MoviePicList> mList;
    private Context context;


    public void setMlist (List<MoviePicList> mList){
        this.mList = mList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView pic;
        TextView title;
        TextView xiangqing;

        public ViewHolder (View view)
        {
            super(view);

            pic = (ImageView) view.findViewById(R.id.pic);
            title = (TextView) view.findViewById(R.id.title);
            xiangqing = (TextView) view.findViewById(R.id.xiangqing);
        }

    }

    public  MoviePicListAdapter (Context context,List <MoviePicList> fruitList){
        this.context = context;
        this.mList = fruitList;
    }

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movieiclist,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position){



        MoviePicList fruit = mList.get(position);
        //holder.pic.setImageResource(fruit.getImageUrl());
        //holder.pic.setImageBitmap(bitmap);

        Glide.with(context).load(fruit.getImageUrl()).into(holder.pic);

        Log.e("yyyyyyyy",fruit.getImageUrl());
        holder.title.setText(fruit.getTitle());
        holder.xiangqing.setText(fruit.getXiangqing());

        holder.pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "click " + fruit.getLinkUrl(), Toast.LENGTH_SHORT).show();
                Intent integer = new Intent(context, xiangQingActivity.class);
                integer.putExtra("url",fruit.getLinkUrl());
                context.startActivity(integer);

            }
        });
    }







    @Override
    public int getItemCount(){
        return mList.size();
    }









}
