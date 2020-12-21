package com.olivapps.taraji.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.olivapps.taraji.MainActivity;
import com.olivapps.taraji.R;
import com.olivapps.taraji.remote.model.Music;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MusicService extends Service implements
        MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener,
        MediaPlayer.OnCompletionListener {
    //media player
    private MediaPlayer player;
    //music list
    private ArrayList<Music> musics;
    //current position
    private int musicPosn;
    //binder
    private final IBinder musicBind = new MusicBinder();
    //title of current music
    private String currentMusicTitle="";
    //notification id
    private static final int NOTIFY_ID=1;
    //shuffle flag and random
    private boolean shuffle=false;
    private Random rand;
    public void onCreate(){
        //create the service
        super.onCreate();
        //initialize position
        musicPosn=0;
        //random
        rand=new Random();
        //create player
        player = new MediaPlayer();
        //initialize
        initMusicPlayer();
    }
    //pass music list
    public void setList(ArrayList<Music> theMusics){
        musics=theMusics;
    }
    //binder
    public class MusicBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }
    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        //check if playback has reached the end of a track
        if(player.getCurrentPosition()>0){
            mediaPlayer.reset();
            try {
                playNext();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        Log.v("MUSIC PLAYER", "Playback Error");
        mediaPlayer.reset();
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        //start playback
        mediaPlayer.start();
        //notification
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {startMyOwnForeground();}

        else {
            Intent notIntent = new Intent(this, MainActivity.class);
            notIntent.putExtra("fromservice", true);
            notIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendInt = PendingIntent.getActivity(this, 0,
                    notIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            Notification.Builder builder = new Notification.Builder(this);

            builder.setContentIntent(pendInt)
                    .setSmallIcon(R.drawable.play)
                    .setTicker(currentMusicTitle)
                    .setOngoing(true)
                    .setContentTitle("Playing")
                    .setContentText(currentMusicTitle);
            Notification not = builder.build();
            startForeground(NOTIFY_ID, not);
        }

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return musicBind;
    }
    //release resources when unbind
    @Override
    public boolean onUnbind(Intent intent){
        player.stop();
        player.release();
        return false;
    }
    //play a music
    public void playMusic(){
        //play
        player.reset();
        //get song
        Music playSong = musics.get(musicPosn);
        //get title
        currentMusicTitle=playSong.getName();
        //get id
        long currSong = playSong.getId();
        //set uri
        Uri trackUri = ContentUris.withAppendedId(
                android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                currSong);
        //set the data source
        try{
            player.setDataSource(getApplicationContext(), trackUri);
        }
        catch(Exception e){
            Log.e("MUSIC SERVICE", "Error setting data source", e);
        }
        player.prepareAsync();
    }
    //set the song
    public void setMusic(int musicIndex){
        musicPosn=musicIndex;
    }
    //playback methods
    public int getPosn(){
        return player.getCurrentPosition();
    }
    public int getDur(){
        return player.getDuration();
    }
    public boolean isPng(){
        return player.isPlaying();
    }
    public void pausePlayer(){
        player.pause();
    }
    public void seek(int posn){
        player.seekTo(posn);
    }
    public void go(){
        player.start();
    }
    //skip to previous track
    public void playPrev() throws IOException {
        musicPosn--;
        if(musicPosn<0) musicPosn=musics.size()-1;
        playSong();
    }
    //skip to next
    public void playNext() throws IOException {
        if(shuffle){
            int newSong = musicPosn;
            while(newSong==musicPosn){
                newSong=rand.nextInt(musics.size());
            }
            musicPosn=newSong;
        }
        else{
            musicPosn++;
            if(musicPosn>=musics.size()) musicPosn=0;
        }
        playSong();
    }
    @Override
    public void onDestroy() {
        stopForeground(true);
    }
    //toggle shuffle
    public void setShuffle(){
        if(shuffle) shuffle=false;
        else shuffle=true;
    }
    //play a song
    public void playSong() throws IOException {
        //play
        player.reset();
        //get song
        Music playSong = musics.get(musicPosn);
        //get title
        currentMusicTitle=playSong.getName();
        //get id
        long currSong = playSong.getId();
        //set uri
        Uri trackUri = ContentUris.withAppendedId(
                android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                currSong);
        //set the data source
        try{
            player.setDataSource(getApplicationContext(), trackUri);
        }
        catch(Exception e){
            Log.e("MUSIC SERVICE", "Error setting data source", e);
        }
        player.prepare();
        player.start();


    }
    public void initMusicPlayer(){
        //set player properties
        player.setWakeMode(getApplicationContext(),
                PowerManager.PARTIAL_WAKE_LOCK);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        //set listeners
        player.setOnPreparedListener(this);
        player.setOnCompletionListener(this);
        player.setOnErrorListener(this);
    }
    //set the song
    public void setSong(int songIndex){
        musicPosn=songIndex;
    }


// med

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startMyOwnForeground(){
        String NOTIFICATION_CHANNEL_ID = "com.example.simpleapp";
        String channelName = "My Background Service";
        NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_NONE);
        chan.setLightColor(Color.BLUE);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.createNotificationChannel(chan);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        Notification notification = notificationBuilder.setOngoing(true)
                .setSmallIcon(R.drawable.play)
                .setContentTitle("App is running in background")
                .setPriority(NotificationManager.IMPORTANCE_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build();
        startForeground(2, notification);
    }

}
