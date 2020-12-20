package com.olivapps.taraji.fragement.rating;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.olivapps.taraji.R;
import com.olivapps.taraji.adapters.GalleryAdapter;
import com.olivapps.taraji.adapters.RatingAdapter;
import com.olivapps.taraji.fragement.gallery.GalleryViewModel;
import com.olivapps.taraji.remote.model.Gallery;
import com.olivapps.taraji.remote.model.Player;
import com.olivapps.taraji.remote.model.Rating;

import java.util.ArrayList;

public class RatingFragment extends Fragment {

    private RatingViewModel mViewModel;
    private RecyclerView recyclerView;
    private RatingAdapter mAdapter;
    private ArrayList<Rating> rating_list;
    Context mContext;

    public static RatingFragment newInstance() {
        return new RatingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel =
                new ViewModelProvider(this).get(RatingViewModel.class);
        View root = inflater.inflate(R.layout.rating_fragment, container, false);
        mContext = getActivity().getApplicationContext();
        recyclerView = (RecyclerView) root.findViewById(R.id.rating_recycleview);
        rating_list = new ArrayList<>();
        /***************Static Data**********************/

        rating_list.add(new Rating(1,new Player(1,"nom","prenom","https://tmssl.akamaized.net/images/portrait/header/s_108831_3342_2014_02_19_1.jpg"),5));
        rating_list.add(new Rating(1,new Player(1,"nom","prenom","https://tmssl.akamaized.net/images/portrait/header/s_69097_3342_2014_02_19_1.jpg"),4));
        rating_list.add(new Rating(1,new Player(1,"nom","prenom","https://tmssl.akamaized.net/images/portrait/originals/142252-1517327609.jpg"),3));
        rating_list.add(new Rating(1,new Player(1,"nom","prenom","https://tmssl.akamaized.net/images/portrait/header/s_108831_3342_2014_02_19_1.jpg"),5));
        rating_list.add(new Rating(1,new Player(1,"nom","prenom","https://tmssl.akamaized.net/images/portrait/header/s_69097_3342_2014_02_19_1.jpg"),4));
        rating_list.add(new Rating(1,new Player(1,"nom","prenom","https://tmssl.akamaized.net/images/portrait/originals/142252-1517327609.jpg"),3));

        /**********************************************************/
        GridLayoutManager mLayoutManager = new GridLayoutManager(mContext, 3);
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RatingAdapter(mContext, rating_list);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RatingViewModel.class);
        // TODO: Use the ViewModel
    }

}