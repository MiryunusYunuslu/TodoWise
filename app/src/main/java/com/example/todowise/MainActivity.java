package com.example.todowise;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.todowise.Adapter.MainAdapter;
import com.example.todowise.MusicPlayer.Musicplayer;
import com.example.todowise.UI.AddFragment;
import com.example.todowise.UI.HomeFragment;
import com.example.todowise.UI.ProfileFragment;
import com.example.todowise.UI.SplashScreen;
public class MainActivity extends AppCompatActivity {
    private MeowBottomNavigation meowBottomNavigation;
    public SharedPreferences sharedPreferences;
    public static MediaPlayer mediaPlayer, mediaClick;
    private ConstraintLayout constraintLayout;
    private FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainAdapter.mainActivity = MainActivity.this;
        uploadMusic();
        initialize();
        setSplahScreen();
        checkMusic();
        meowBottomNavigation = findViewById(R.id.bottom_navigation);
        meowBottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_baseline_home_24));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_baseline_add_24));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_baseline_person_24));
        AddFragment.meowBottomNavigation=meowBottomNavigation;
        meowBottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                clickSoundCheck();
                if(item.getId()==1){
                    loadFragment(new HomeFragment());
                }
                else if(item.getId()==2){
                    loadFragment(new AddFragment());
                }
                else{
                    loadFragment(new ProfileFragment());
                }
            }
        });
        meowBottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                if(item.getId()==1){
                    loadFragment(new HomeFragment());
                }
                else if(item.getId()==2){

                    loadFragment(new AddFragment());
                }
                else{
                    loadFragment(new ProfileFragment());
                }
            }
        });
    }
    private void uploadMusic() {
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music);
        mediaClick = MediaPlayer.create(getApplicationContext(), R.raw.clicksound);
    }

    @SuppressLint("ResourceAsColor")
    private void setSplahScreen() {
        fragmentTransaction.replace(R.id.frameLayout, new SplashScreen()).commit();
       new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                constraintLayout.setVisibility(View.INVISIBLE);
                meowBottomNavigation.setVisibility(View.VISIBLE);
                meowBottomNavigation.show(1, true);

            }

        }, 5000);
        }
    public void checkMusic() {
        if (sharedPreferences.getInt("musicNumber", 0) == 1) {
            Musicplayer.mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music);
            Musicplayer.OpenMusic();
        }
    }
    private void initialize() {
        sharedPreferences = getSharedPreferences("checkMusic", MODE_PRIVATE);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        constraintLayout = findViewById(R.id.mainActivityConstraint);
    }
    public void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
        fragmentTransaction1.replace(R.id.frameLayout, fragment).commit();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Musicplayer.stopMusic();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Musicplayer.stopMusic();
    }
    public void clickSoundCheck() {
        SharedPreferences checkClickSound = getSharedPreferences("checkSound", MODE_PRIVATE);
        if (checkClickSound.getInt("checkSound", 0) == 1) {
            Musicplayer.clickSounds();
        }
    }
}