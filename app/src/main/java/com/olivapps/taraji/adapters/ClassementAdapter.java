package com.olivapps.taraji.adapters;

import android.content.Context;
import android.content.Intent;
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
import com.olivapps.taraji.remote.model.Classement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ClassementAdapter extends RecyclerView.Adapter<ClassementAdapter.ViewHolder> {
    private ArrayList<Classement> mData=new ArrayList<>();
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    public  Context mContext;
    // data is passed into the constructor
    public ClassementAdapter(Context context, ArrayList<Classement> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData.addAll(data);
        mContext=context;


    }

    @NonNull
    @Override
    public ClassementAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.classement_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassementAdapter.ViewHolder holder, int position) {
        if(mData.get(position).getEquipe().getName().equals("Es Tunis"))
        {
            holder.containerLayout.setBackgroundColor(mContext.getResources().getColor(R.color.yellow));

        }

        holder.rang.setText(String.valueOf(mData.get(position).getRang()));
        holder.nom_equipe.setText(mData.get(position).getEquipe().getName());
        holder.j_equipe.setText(String.valueOf(mData.get(position).getNbrJ()));
        holder.g_equipe.setText(String.valueOf(mData.get(position).getNbrG()));
        holder.n_equipe.setText(String.valueOf(mData.get(position).getNbrN()));
        holder.p_equipe.setText(String.valueOf(mData.get(position).getNbrP()));
        holder.bp_equipe.setText(String.valueOf(mData.get(position).getNbrBp()));

        Log.d("position", "onBindViewHolder: "+position+" name= "+mData.get(position).getNbrBp());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        ImageView img_equipe;
        LinearLayout containerLayout;
        TextView rang,nom_equipe,j_equipe,g_equipe,n_equipe,p_equipe,bp_equipe;
        ViewHolder(View itemView) {
            super(itemView);
            containerLayout=itemView.findViewById(R.id.classement_item_layout);
            img_equipe =  itemView.findViewById(R.id.img_equipe);
            nom_equipe =  itemView.findViewById(R.id.nom_equipe);
            j_equipe =  itemView.findViewById(R.id.j_equipe);
            g_equipe =  itemView.findViewById(R.id.g_equipe);
            n_equipe =  itemView.findViewById(R.id.n_equipe);
            p_equipe =  itemView.findViewById(R.id.p_equipe);
            bp_equipe =  itemView.findViewById(R.id.bp_equipe);
            rang =  itemView.findViewById(R.id.rang);


        }
        public void update(ArrayList<Classement>  classements) {
            mData.clear();
            mData.addAll(classements);
            notifyDataSetChanged();
        }
        public void updateNext(ProgressBar prog, List<Classement> classements) {
            mData.addAll(classements);
            notifyDataSetChanged();
            prog.setVisibility(View.GONE);

        }



    }

}
