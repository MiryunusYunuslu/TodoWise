package com.example.todowise.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todowise.MusicPlayer.Musicplayer;
import com.example.todowise.ProfileActivity;
import com.example.todowise.R;

public class AdapterProfile extends RecyclerView.Adapter<AdapterProfile.RowHolder> {
    private final Context context;
    private final String[] names;
    private final int[] images;
    private Fragment fragment;

    public AdapterProfile(Context context, String[] names, int[] images) {
        this.context = context;
        this.names = names;
        this.images = images;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_profile, parent, false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        holder.nameOfImage.setText(names[position]);
        holder.imageView.setBackgroundResource(images[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSoundCheck();
                changeFragment(position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return images.length;
    }
    public static class RowHolder extends RecyclerView.ViewHolder {
        TextView nameOfImage;
        ImageView imageView;
        public RowHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.images_inprofile);
            nameOfImage = itemView.findViewById(R.id.text_inprofile);

        }
    }
    public void changeFragment(int position) {
        Intent intent = new Intent(context, ProfileActivity.class);
        intent.putExtra("fragmentposition", position);
        context.startActivity(intent);
    }
    public void clickSoundCheck() {
        SharedPreferences checkClickSound = context.getSharedPreferences("checkSound", Context.MODE_PRIVATE);
        ;
        if (checkClickSound.getInt("checkSound", 0) == 1) {
            Musicplayer.clickSounds();
        }
    }
}
