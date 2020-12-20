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

import java.util.ArrayList;
import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {
    private ArrayList<Music> mData=new ArrayList<>();
    private LayoutInflater mInflater;
    private MusicAdapter.ItemClickListener mClickListener;
    public  Context mContext;
    private MusicController controller;

    View view;
    // data is passed into the constructor
    public MusicAdapter(Context context, ArrayList<Music> data,MusicController controller,View v,MusicController mc) {
        this.mInflater = LayoutInflater.from(context);
        this.mData.addAll(data);
        mContext=context;
        this.controller=mc;
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
                if (musicFragment.musicBound) {
                    Log.d("id", "onClick: " + musicFragment.musicSrv.toString());
                    musicFragment.musicSrv.setSong(position);
                    musicFragment.musicSrv.playSong();
                  /*  if (musicFragment.playbackPaused) {
                        musicFragment.playbackPaused = false;

                    }*/
                    controller.show(0);



                }
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
}
