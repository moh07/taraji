package com.olivapps.taraji.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.olivapps.taraji.R;
import com.olivapps.taraji.remote.model.Music;

import java.util.ArrayList;
import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {
    private ArrayList<Music> mData=new ArrayList<>();
    private LayoutInflater mInflater;
    private MusicAdapter.ItemClickListener mClickListener;
    public  Context mContext;
    // data is passed into the constructor
    public MusicAdapter(Context context, ArrayList<Music> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData.addAll(data);
        mContext=context;


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
        ViewHolder(View itemView) {
            super(itemView);
            containerLayout=itemView.findViewById(R.id.music_item_layout);

            id_music =  itemView.findViewById(R.id.music_id);
            nom_music =  itemView.findViewById(R.id.music_name);



        }
        public void update(ArrayList<Music> music) {
            mData.clear();
            mData.addAll(music);
            notifyDataSetChanged();
        }
        public void updateNext(ProgressBar prog, List<Music> music) {
            mData.addAll(music);
            notifyDataSetChanged();
            prog.setVisibility(View.GONE);

        }



    }
}
