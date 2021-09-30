package com.example.todowise.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.todowise.MusicPlayer.Musicplayer;
import com.example.todowise.NewsModel.ListModel;
import com.example.todowise.R;
import com.example.todowise.UI.WebView;

import java.util.ArrayList;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.RowHoler> {
    private final Context context;
    private ArrayList<ListModel> arrayList;

    public NewsListAdapter(Context context, ArrayList<ListModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    public void setMovielist(ArrayList<ListModel> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RowHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rowholder, parent, false);
        return new RowHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHoler holder, int position) {
        holder.titleOfNews.setText(arrayList.get(position).getTitle());
        holder.publishedTime.setText(arrayList.get(position).getPublishedAt().substring(0, 10));
        if (arrayList.get(position).getUrlToIMage() != null) {
            Glide.with(context).load(arrayList.get(position).getUrlToIMage()).into(holder.imageofnews);
        }
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSoundCheck();
                AppCompatActivity appCompatActivity = (AppCompatActivity) v.getContext();
                WebView webView = new WebView();
                webView.setUrl(arrayList.get(position).getUrl());
                appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_News_Frame, webView).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (this.arrayList != null) {
            return this.arrayList.size();
        }
        return 0;
    }

    public static class RowHoler extends RecyclerView.ViewHolder {
        TextView titleOfNews, publishedTime;
        ImageView imageofnews;
        ConstraintLayout constraintLayout;

        public RowHoler(@NonNull View itemView) {
            super(itemView);
            titleOfNews = itemView.findViewById(R.id.titleofnews);
            publishedTime = itemView.findViewById(R.id.publishedtime);
            imageofnews = itemView.findViewById(R.id.imagenews);
            constraintLayout = itemView.findViewById(R.id.constrainLayout);
        }
    }

    public void clickSoundCheck() {
        SharedPreferences checkClickSound = context.getSharedPreferences("checkSound", Context.MODE_PRIVATE);
        ;
        if (checkClickSound.getInt("checkSound", 0) == 1) {
            Musicplayer.clickSounds();
        }
    }
}
