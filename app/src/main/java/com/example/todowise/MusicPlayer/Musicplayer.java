package com.example.todowise.MusicPlayer;

import android.media.MediaPlayer;

import com.example.todowise.MainActivity;

public class Musicplayer {
    public static MediaPlayer mediaPlayer = MainActivity.mediaPlayer;
    public static MediaPlayer mediaClick = MainActivity.mediaClick;
    public static void OpenMusic() {
        mediaPlayer.start();
    }
    public static void stopMusic() {
        mediaPlayer.stop();
    }
    public static void pauseMusic() {
        mediaPlayer.pause();
    }
    public static void clickSounds() {
        mediaClick.start();
    }
}
