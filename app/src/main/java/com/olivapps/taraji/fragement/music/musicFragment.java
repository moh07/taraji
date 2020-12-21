package com.olivapps.taraji.fragement.music;

import androidx.lifecycle.ViewModelProvider;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;

import com.olivapps.taraji.R;
import com.olivapps.taraji.adapters.MusicAdapter;
import com.olivapps.taraji.remote.model.Music;
import com.olivapps.taraji.services.MusicService;

import java.io.IOException;
import java.util.ArrayList;

public class musicFragment extends Fragment implements MediaController.MediaPlayerControl {

    private MusicViewModel musicViewModel;
    public MusicAdapter adapter;
     Context mContext;
    public  View view;
    RecyclerView recyclerView;
    ArrayList<Music> music = new ArrayList<>();
    public static MusicService musicSrv;
    private Intent playIntent;
    //binding
    public static boolean musicBound=false;
    //activity and playback pause flags
    private boolean paused=false;
    public static boolean playbackPaused=false;
    //controller
    public static MusicController controller;

    public static musicFragment newInstance() {
        return new musicFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        musicViewModel =
                new ViewModelProvider(this).get(MusicViewModel.class);
        View root = inflater.inflate(R.layout.music_fragment, container, false);
        mContext = getActivity().getApplicationContext();
        view =root;
        getSongList();



        /**********************************************************/

        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        recyclerView = root.findViewById(R.id.music_recycleview);
        recyclerView.setLayoutManager(manager);
        adapter = new MusicAdapter(mContext, music,controller,view);
        recyclerView.setAdapter(adapter);
        setController();
        return root;
    }
    //connect to the service
    private ServiceConnection musicConnection = new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MusicBinder binder = (MusicService.MusicBinder)service;
            //get service
            musicSrv = binder.getService();
            //pass list
            musicSrv.setList(music);
            musicBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicBound = false;
        }
    };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        musicViewModel = new ViewModelProvider(this).get(MusicViewModel.class);
        // TODO: Use the ViewModel
    }
    //start and bind the service when the activity starts
    @Override
    public void onStart() {
        super.onStart();
        if(playIntent==null){
            Log.d("laaaaaaaa", "onStart: here");
            playIntent = new Intent(mContext.getApplicationContext(), MusicService.class);
            mContext.bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
            mContext.startService(playIntent);
        }
    }

    //method to retrieve song info from device
    public void getSongList(){
        //query external audio
        ContentResolver musicResolver = mContext.getContentResolver();
        Uri musicUri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor musicCursor = musicResolver.query(musicUri, null, null, null, null);
        //iterate over results if valid
        if(musicCursor!=null && musicCursor.moveToFirst()){
            //get columns
            int titleColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.TITLE);
            int idColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media._ID);
            int artistColumn = musicCursor.getColumnIndex
                    (android.provider.MediaStore.Audio.Media.ARTIST);
            //add songs to list
            do {
                int thisId = musicCursor.getInt(idColumn);
                String thisTitle = musicCursor.getString(titleColumn);
                String thisArtist = musicCursor.getString(artistColumn);
                music.add(new Music(thisId, thisTitle));
            }
            while (musicCursor.moveToNext());
        }
    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        //menu item selected
//        switch (item.getItemId()) {
//            case R.id.action_shuffle:
//                musicSrv.setShuffle();
//                break;
//            case R.id.action_end:
////                stopService(playIntent);
//                musicSrv=null;
//                System.exit(0);
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void start() {
        musicSrv.go();
    }

    @Override
    public void pause() {
        playbackPaused=true;
        musicSrv.pausePlayer();
    }

    @Override
    public int getDuration() {
        if(musicSrv!=null && musicBound && musicSrv.isPng())
            return musicSrv.getDur();
        else return 0;
    }

    @Override
    public int getCurrentPosition() {
        if(musicSrv!=null && musicBound && musicSrv.isPng())
            return musicSrv.getPosn();
        else return 0;
    }

    @Override
    public void seekTo(int i) {
        musicSrv.seek(i);
    }

    @Override
    public boolean isPlaying() {
        if(musicSrv!=null && musicBound)
        return musicSrv.isPng();
        return false;
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }
    //set the controller up
    public MusicController setController(){
        controller = new MusicController(view.getContext());
        //set previous and next button listeners
        controller.setPrevNextListeners(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    playNext();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    playPrev();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        //set and show
        controller.setMediaPlayer(this);
        controller.setAnchorView(view.findViewById(R.id.media_controller));
        controller.setEnabled(true);
        controller.setPressed(true);
        controller.setBackgroundColor(getResources().getColor(R.color.yellow));
        return controller;
    }
    private void playNext() throws IOException {
        musicSrv.playNext();
        if(playbackPaused){
            setController();
            playbackPaused=false;
        }
        controller.show(0);
    }
    private  void playPrev()throws IOException {
        musicSrv.playPrev();
        if(playbackPaused){
            setController();
            playbackPaused=false;
        }
        controller.show(0);
    }

    @Override
    public void onPause(){
        super.onPause();
        musicSrv.pausePlayer();
        paused=true;
    }

    @Override
    public void onResume(){
        super.onResume();
        if(paused==true)
        {try {
            musicSrv.playSong();
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller.show();
        paused=false;}
    }

    @Override
    public void onStop() {
        controller.hide();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        getActivity().stopService(playIntent);
        musicSrv=null;
        super.onDestroy();
    }
}