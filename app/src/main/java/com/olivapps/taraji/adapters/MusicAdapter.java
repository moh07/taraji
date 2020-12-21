package com.olivapps.taraji.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.olivapps.taraji.R;
import com.olivapps.taraji.fragement.music.MusicController;
import com.olivapps.taraji.fragement.music.musicFragment;
import com.olivapps.taraji.remote.model.Music;
import com.olivapps.taraji.services.MusicService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {
    private ArrayList<Music> mData=new ArrayList<>();
    private LayoutInflater mInflater;
    private MusicAdapter.ItemClickListener mClickListener;
    public  Context mContext;

    View view;
    // data is passed into the constructor
    public MusicAdapter(Context context, ArrayList<Music> data,MusicController controller,View v) {
        this.mInflater = LayoutInflater.from(context);
        this.mData.addAll(data);
        mContext=context;
        this.view=v;


    }

    @NonNull
    @Override
    public MusicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.music_item, parent, false);
        return new MusicAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicAdapter.ViewHolder holder, int position) {


        holder.id_music.setText(String.valueOf(mData.get(position).getId()));
        holder.nom_music.setText(mData.get(position).getName());

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("id", "onClick: " + musicFragment.musicSrv.toString());

                if (musicFragment.musicBound) {
                    Log.d("id", "onClick: " + musicFragment.musicSrv.toString());

                    musicFragment.musicSrv.setSong(position);
                    try {
                        musicFragment.musicSrv.playSong();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

//                    setController();
////                   if (musicFragment.playbackPaused) {
////                       setController();
////                       musicFragment.playbackPaused = false;
////
////                    }

                    musicFragment.controller.show(0);

                    }
                   // musicFragment.musicSrv.go();




            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        LinearLayout containerLayout;
        TextView id_music,nom_music;
        Button btn;
        ViewHolder(View itemView) {
            super(itemView);
            containerLayout=itemView.findViewById(R.id.music_item_layout);

            id_music =  itemView.findViewById(R.id.music_id);
            nom_music =  itemView.findViewById(R.id.music_name);
            btn =  itemView.findViewById(R.id.play);



        }




    }


    public MusicController setController(){
        musicFragment.controller = new MusicController(view.getContext());
        //set previous and next button listeners
        musicFragment.controller.setPrevNextListeners(new View.OnClickListener() {
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
        musicFragment.controller.setMediaPlayer(
                new MediaController.MediaPlayerControl()
                {
                    @Override
                    public void start() {
                        musicFragment.musicSrv.go();
                    }

                    @Override
                    public void pause() {
                        musicFragment.playbackPaused=true;
                        musicFragment.musicSrv.pausePlayer();
                    }

                    @Override
                    public int getDuration() {
                        if(musicFragment.musicSrv!=null && musicFragment.musicBound && musicFragment.musicSrv.isPng())
                            return musicFragment.musicSrv.getDur();
                        else return 0;
                    }

                    @Override
                    public int getCurrentPosition() {
                        if(musicFragment.musicSrv!=null && musicFragment.musicBound && musicFragment.musicSrv.isPng())
                            return musicFragment.musicSrv.getPosn();
                        else return 0;
                    }

                    @Override
                    public void seekTo(int i) {

                        musicFragment.musicSrv.seek(i);
                    }

                    @Override
                    public boolean isPlaying() {
                        if(musicFragment.musicSrv!=null && musicFragment.musicBound)
                            return musicFragment.musicSrv.isPng();
                        return false;
                    }


            @Override
            public int getBufferPercentage() {
                return 0;
            }

            @Override
            public boolean canPause() {
                return false;
            }

            @Override
            public boolean canSeekBackward() {
                return false;
            }

            @Override
            public boolean canSeekForward() {
                return false;
            }

            @Override
            public int getAudioSessionId() {
                return 0;
            }
        });
        musicFragment.controller.setAnchorView(view.findViewById(R.id.media_controller));
        musicFragment.controller.setEnabled(true);
        musicFragment.controller.setBackgroundColor(view.getResources().getColor(R.color.yellow));
        return musicFragment.controller;
    }

    private void playNext()throws IOException {
        musicFragment.musicSrv.playNext();
        if(musicFragment.playbackPaused){
            setController();
            musicFragment.playbackPaused=false;
        }
        musicFragment.controller.show(0);
    }
    private  void playPrev()throws IOException {
        musicFragment.musicSrv.playPrev();
        if(musicFragment.playbackPaused){
            setController();
            musicFragment.playbackPaused=false;
        }
        musicFragment.controller.show(0);
    }
}
