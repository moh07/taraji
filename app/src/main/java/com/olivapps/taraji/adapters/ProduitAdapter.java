package com.olivapps.taraji.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.olivapps.taraji.R;
import com.olivapps.taraji.remote.model.Classement;
import com.olivapps.taraji.remote.model.Gallery;
import com.olivapps.taraji.remote.model.Product;

import java.util.ArrayList;
import java.util.List;

public class AbonementAdapter extends RecyclerView.Adapter<AbonementAdapter.ViewHolder> {
    private ArrayList<Product> mData=new ArrayList<>();
    private LayoutInflater mInflater;
    public Context mContext;
    // data is passed into the constructor
    public AbonementAdapter(Context context, ArrayList<Product> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData.addAll(data);
        mContext=context;


    }

    @NonNull
    @Override
    public AbonementAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.abonnement_item, parent, false);
        return new AbonementAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AbonementAdapter.ViewHolder holder, int position) {
        Product product = mData.get(position);

        Glide.with(mContext).load(product.getImage())
                .thumbnail(0.5f)
//                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder  {
        public ImageView thumbnail;
        public ViewHolder(View view) {
            super(view);
            thumbnail = (ImageView) view.findViewById(R.id.abonment_thumbnail);
        }
        public void update(ArrayList<Product>  producs) {
            mData.clear();
            mData.addAll(producs);
            notifyDataSetChanged();
        }
        public void updateNext(ProgressBar prog, List<Product> products) {
            mData.addAll(products);
            notifyDataSetChanged();
            prog.setVisibility(View.GONE);

        }



    }

}
