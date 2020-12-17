package com.olivapps.taraji.fragement.music;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.olivapps.taraji.R;
import com.olivapps.taraji.adapters.MusicAdapter;
import com.olivapps.taraji.remote.model.Music;

import java.util.ArrayList;

public class musicFragment extends Fragment {

    private MusicViewModel musicViewModel;
    public MusicAdapter adapter;
    Context mContext;
    View view;
    RecyclerView recyclerView;
    ArrayList<Music> music = new ArrayList<>();

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
        /***************Static Data**********************/
        music.add(new Music(1,"Et chaque semaine"));
        music.add(new Music(2,"elkhawana-الخونة"));
        music.add(new Music(3,"taraji fel galb"));
        music.add(new Music(4,"انت الدنيا لي نعيشوها"));
        music.add(new Music(5,"no taraji no life"));
        music.add(new Music(6,"الدولة العاصمية"));
        music.add(new Music(7,"خلي العالم ينشرح"));

        /**********************************************************/


        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        recyclerView = root.findViewById(R.id.music_recycleview);
        recyclerView.setLayoutManager(manager);
        adapter = new MusicAdapter(mContext, music);
        recyclerView.setAdapter(adapter);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        musicViewModel = new ViewModelProvider(this).get(MusicViewModel.class);
        // TODO: Use the ViewModel
    }

}