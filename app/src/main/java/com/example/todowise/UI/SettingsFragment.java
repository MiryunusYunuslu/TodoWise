package com.example.todowise.UI;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.todowise.MainActivity;
import com.example.todowise.MusicPlayer.Musicplayer;
import com.example.todowise.ProfileActivity;
import com.example.todowise.R;

import es.dmoral.toasty.Toasty;


public class SettingsFragment extends Fragment {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch musicSwitch, soundSwitch;
    private SharedPreferences sharedPreferences, checkSound;
    private TextView reportProblem, aboutUs;
    private final ProfileActivity profileActivity;

    public SettingsFragment(ProfileActivity profileActivity) {
        this.profileActivity = profileActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize(view);
        checkSwitch();
        musicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                musicSwithcListener(isChecked);
            }

        });
        soundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                soundSwicthClickListener(isChecked);
            }
        });
        reportProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSoundCheck();
                reportProblem();
            }
        });
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSoundCheck();
                aboutUs();
            }
        });
    }

    private void initialize(View view) {
        MediaPlayer mediaPlayer = MediaPlayer.create(getContext(), R.raw.music);
        sharedPreferences = requireContext().getSharedPreferences("checkMusic", Context.MODE_PRIVATE);
        checkSound = requireContext().getSharedPreferences("checkSound", Context.MODE_PRIVATE);
        musicSwitch = view.findViewById(R.id.openMusic);
        soundSwitch = view.findViewById(R.id.openClickSounds);
        reportProblem = view.findViewById(R.id.reportProblem);
        aboutUs = view.findViewById(R.id.aboutUs);
    }

    private void checkSwitch() {
        if (sharedPreferences.getInt("musicNumber", 0) == 1) {
            musicSwitch.setChecked(true);
        }
        if (checkSound.getInt("checkSound", 0) == 1) {
            soundSwitch.setChecked(true);
        }
    }

    public void soundSwicthClickListener(boolean isChecked) {
        if (isChecked) {
            checkSound.edit().putInt("checkSound", 1).apply();
        } else {
            checkSound.edit().putInt("checkSound", 0).apply();
        }
    }

    public void musicSwithcListener(boolean isChecked) {
        if (isChecked) {
            sharedPreferences.edit().putInt("musicNumber", 1).apply();
            Musicplayer.OpenMusic();

        } else {
            sharedPreferences.edit().putInt("musicNumber", 0).apply();
            //MainActivity.checkMusic();
            Musicplayer.stopMusic();
            MainActivity.mediaPlayer = MediaPlayer.create(getContext(), R.raw.music);
            Musicplayer.mediaPlayer = MediaPlayer.create(getContext(), R.raw.music);
        }
    }

    public void reportProblem() {
        Dialog dialog = new Dialog(profileActivity);
        dialog.setContentView(R.layout.report_problem_dialog);
        int width = WindowManager.LayoutParams.MATCH_PARENT;
        int height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);
        EditText sendMessage = dialog.findViewById(R.id.reportProblemEditText);
        Button cancelButton = dialog.findViewById(R.id.bt_cancel);
        Button sendButton = dialog.findViewById(R.id.bt_send);
        ImageView cancelImage = dialog.findViewById(R.id.cancelImageInReport);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSoundCheck();
                dialog.dismiss();
            }
        });
        cancelImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSoundCheck();
                dialog.dismiss();
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSoundCheck();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "developerstudent637@gmail.com"));
                if (!TextUtils.isEmpty(sendMessage.getText().toString())) {
                    intent.putExtra(Intent.EXTRA_TEXT, sendMessage.getText().toString());
                    startActivity(intent);
                }
                else{
                    Toasty.error(requireContext(),"Please type something",Toasty.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }

    public void aboutUs() {
        Dialog dialog = new Dialog(profileActivity);
        int width = WindowManager.LayoutParams.MATCH_PARENT;
        int height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.setContentView(R.layout.about_us_dialog);
        dialog.getWindow().setLayout(width, height);
        dialog.show();
    }
    public void clickSoundCheck() {
        SharedPreferences checkClickSound = requireContext().getSharedPreferences("checkSound", Context.MODE_PRIVATE);
        ;
        if (checkClickSound.getInt("checkSound", 0) == 1) {
            Musicplayer.clickSounds();
        }
    }
}