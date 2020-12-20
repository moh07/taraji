package com.olivapps.taraji.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.olivapps.taraji.R;
import com.olivapps.taraji.remote.model.Rating;

import java.util.List;

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.MyViewHolder> {
    private List<Rating> rating_list;
    private Context mContext;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView thumbnail;
        public RatingBar rb;

        public MyViewHolder(View view) {
            super(view);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            rb = (RatingBar) view.findViewById(R.id.player_rating);
        }
    }
    public RatingAdapter(Context context, List<Rating> ratings) {
        mContext = context;
        this.rating_list = ratings;
    }
    @NonNull
    @Override
    public RatingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rating_item, parent, false);

        return new RatingAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RatingAdapter.MyViewHolder holder, int position) {
        Rating rating = rating_list.get(position);
        int radius = mContext.getResources().getDimensionPixelSize(R.dimen.corner_radius);
        Glide.with(mContext).load(rating.getPlayer().getPicture()).transforms(new CenterCrop(),new RoundedCorners(radius))
                .thumbnail(0.5f)
//                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.thumbnail);
        holder.rb.setRating(rating.getRating());

    }

    @Override
    public int getItemCount() {
        return rating_list.size();
    }

}
