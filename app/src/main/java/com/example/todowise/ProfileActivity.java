package com.example.todowise;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.todowise.UI.NewsFragment;
import com.example.todowise.UI.SettingsFragment;
import com.example.todowise.UI.Weatherragment;
public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent=getIntent();
        int fragmentPosition=intent.getIntExtra("fragmentposition",0);
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        if(fragmentPosition==0){
            fragmentTransaction.replace(R.id.profileActivityFrameLayout,new NewsFragment()).commit();
        }
        else if(fragmentPosition==1){
            fragmentTransaction.replace(R.id.profileActivityFrameLayout,new Weatherragment()).commit();
        }
        else if(fragmentPosition==2){
            fragmentTransaction.replace(R.id.profileActivityFrameLayout,new SettingsFragment(ProfileActivity.this)).commit();
        }
        else if(fragmentPosition==3){
            System.out.println(1);
        }
    }
}