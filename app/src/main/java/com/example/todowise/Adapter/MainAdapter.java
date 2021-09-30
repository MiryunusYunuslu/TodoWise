package com.example.todowise.Adapter;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.todowise.Database.MainData;
import com.example.todowise.Database.RoomDataBase;
import com.example.todowise.MainActivity;
import com.example.todowise.MusicPlayer.Musicplayer;
import com.example.todowise.R;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.RowHolder> {
    private final List<MainData> listData;
    private final Context context;
    public static MainActivity mainActivity;
    private RoomDataBase roomDataBase;
    private TextView Time;
    private final int[] colors = {R.color.color1, R.color.color2, R.color.color3, R.color.color4, R.color.color5, R.color.color6, R.color.color7, R.color.color8};

    public MainAdapter(List<MainData> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }


    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_layout, parent, false);
        return new RowHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        int colorPosition = position;
        holder.basicText.setText(listData.get(position).getBasicTitle());
        holder.underBasicText.setText(listData.get(position).getUnderBasicTitle());
        if (position >= 8) {
            colorPosition = position % 8;
        }
        holder.leftColorItem.setBackgroundResource(colors[colorPosition]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSoundCheck();
                holderItemClickListener(position);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void holderItemClickListener(int position) {
        Dialog dialog = new Dialog(mainActivity);
        dialog.setContentView(R.layout.dialog_view);
        int width = WindowManager.LayoutParams.MATCH_PARENT;
        int height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);
        dialog.show();
        ImageView cancel_image = dialog.findViewById(R.id.cancel_Image);
        EditText basicText, underBasicText;
        Button updateButton = dialog.findViewById(R.id.update_Button);
        Button deleteButton = dialog.findViewById(R.id.delete_Button);
        basicText = dialog.findViewById(R.id.basicTextInDialog);
        underBasicText = dialog.findViewById(R.id.underBasicTextInDialog);
        Time = dialog.findViewById(R.id.timeInDialog);
        basicText.setText(listData.get(position).getBasicTitle());
        underBasicText.setText(listData.get(position).getUnderBasicTitle());
        setTime(position);
        cancel_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSoundCheck();
                dialog.dismiss();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSoundCheck();
                SharedPreferences sharedPreferences1 = context.getSharedPreferences("channelText1", Context.MODE_PRIVATE);
                int channelTextNumber = sharedPreferences1.getInt("channelText", 0);
                sharedPreferences1.edit().putInt("channelText", channelTextNumber - 1).apply();
                SharedPreferences sharedPreferences = context.getSharedPreferences("idCalendar", Context.MODE_PRIVATE);
                int a = sharedPreferences.getInt("idCalendar", 0);
                sharedPreferences.edit().putInt("idCalendar", a - 1).apply();
                dialog.dismiss();
                roomDataBase = RoomDataBase.getInstance(context);
                MainData data = listData.get(position);
                roomDataBase.dao().delete(data);
                listData.clear();
                listData.addAll(roomDataBase.dao().getAllData());
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, listData.size());
            }
        });
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSoundCheck();
                if (!TextUtils.isEmpty(underBasicText.getText().toString()) && !TextUtils.isEmpty(underBasicText.getText().toString())) {
                    dialog.dismiss();
                    MainData mainData = listData.get(position);
                    mainData.setBasicTitle(basicText.getText().toString());
                    roomDataBase = RoomDataBase.getInstance(context);
                    int id = mainData.getID();
                    roomDataBase.dao().updateTitle(basicText.getText().toString(), id);
                    roomDataBase.dao().updateUnderTitle(underBasicText.getText().toString(), id);
                    listData.clear();
                    listData.addAll(roomDataBase.dao().getAllData());
                    notifyDataSetChanged();
                    Toasty.success(context, "Your work updated successfully!", Toasty.LENGTH_SHORT).show();
                } else {
                    Toasty.info(context, "Please Fill Lines", Toasty.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void setTime(int position) {
        String minute = String.valueOf(listData.get(position).getTimeList().get(4));
        String hour = String.valueOf(listData.get(position).getTimeList().get(3));

        if (listData.get(position).getTimeList().get(4) < 10) {
            minute = "0" + listData.get(position).getTimeList().get(4);
        }
        if (listData.get(position).getTimeList().get(3) < 10) {
            hour = "0" + listData.get(position).getTimeList().get(3);
        }
        Time.setText(("Date-" + listData.get(position).getTimeList().get(0) + "/" + listData.get(position).getTimeList().get(1) + "/" + listData.get(position).getTimeList().get(2) + "\n" + "Time-" + hour + ":" + minute));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public static class RowHolder extends RecyclerView.ViewHolder {
        TextView basicText, underBasicText;
        ImageView leftColorItem;
        CardView cardView;

        public RowHolder(@NonNull View itemView) {
            super(itemView);
            basicText = itemView.findViewById(R.id.basicText);
            underBasicText = itemView.findViewById(R.id.underBasicText);
            leftColorItem = itemView.findViewById(R.id.leftColorsItem);
            cardView = itemView.findViewById(R.id.cardViewForHomeList);
        }
    }

    public void clickSoundCheck() {
        SharedPreferences checkClickSound = context.getSharedPreferences("checkSound", Context.MODE_PRIVATE);

        if (checkClickSound.getInt("checkSound", 0) == 1) {
            Musicplayer.clickSounds();
        }
    }
}