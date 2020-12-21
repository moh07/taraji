package com.olivapps.taraji.fragement.cellue;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.olivapps.taraji.R;
import com.olivapps.taraji.activity.portabilite;
import com.olivapps.taraji.activity.sim;

public class CellueFragment extends Fragment {

    private CellueViewModel mViewModel;
    Button join_choice;
    RadioGroup rg;
    Context mContext;

    public static CellueFragment newInstance() {
        return new CellueFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       View root= inflater.inflate(R.layout.cellue_fragment, container, false);
       mContext=getContext().getApplicationContext();
        join_choice= root.findViewById(R.id.join_choice);
        rg = root.findViewById(R.id.choicegroup);
        join_choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (rg.getCheckedRadioButtonId()){
                    case R.id.choice1 :{
                        Intent i1 = new Intent(mContext, sim.class);
                        startActivity(i1);
                        break;
                    }
                    case R.id.choice2 :{
                        Intent i2 = new Intent(mContext, portabilite.class);
                        startActivity(i2);
                        break;
                    }
                }

            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CellueViewModel.class);
        // TODO: Use the ViewModel
    }

}