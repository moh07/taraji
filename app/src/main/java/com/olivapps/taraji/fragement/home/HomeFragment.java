package com.olivapps.taraji.fragement.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.olivapps.taraji.R;
import com.olivapps.taraji.adapters.ClassementAdapter;
import com.olivapps.taraji.remote.model.Classement;
import com.olivapps.taraji.remote.model.Equipe;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public ClassementAdapter adapter;
    Context mContext;
    View view;
    RecyclerView recyclerView;
    ArrayList<Classement> classement = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        mContext = getActivity().getApplicationContext();
        /***************Static Data**********************/
        Equipe e1= new Equipe(1,"ES Metlaoui","");
        Equipe e2= new Equipe(1,"Ca Bizertin","");
        Equipe e3= new Equipe(1,"Club Affricain","");
        Equipe e4= new Equipe(1,"Us Monsterienne","");
        Equipe e5= new Equipe(1,"Es Tunis","");
        classement.add(new Classement(e1,1,1,1,1,1,1));
        classement.add(new Classement(e2,2,1,1,1,1,1));
        classement.add(new Classement(e3,3,1,1,1,1,1));
        classement.add(new Classement(e4,4,1,1,1,1,1));
        classement.add(new Classement(e5,5,1,1,1,1,1));
        /**********************************************************/


        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        recyclerView = root.findViewById(R.id.classement_recycleview);
        recyclerView.setLayoutManager(manager);
        adapter = new ClassementAdapter(mContext, classement);
        recyclerView.setAdapter(adapter);
        Log.d("here1", "onCreateView: ");


       /* final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}