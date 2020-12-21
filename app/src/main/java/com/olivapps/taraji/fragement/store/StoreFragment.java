package com.olivapps.taraji.fragement.store;

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
import com.olivapps.taraji.adapters.AbonementAdapter;
import com.olivapps.taraji.adapters.GalleryAdapter;
import com.olivapps.taraji.adapters.ProduitAdapter;
import com.olivapps.taraji.remote.model.Gallery;
import com.olivapps.taraji.remote.model.Product;

import java.util.ArrayList;

public class StoreFragment extends Fragment {
    Context mContext;
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private AbonementAdapter mAdapter;
    private ProduitAdapter mAdapter2;
    private ArrayList<Product> abonment_list;
    private ArrayList<Product> product_list;


    private StoreViewModel mViewModel;

    public static StoreFragment newInstance() {
        return new StoreFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.store_fragment, container, false);
        mContext = getActivity().getApplicationContext();
        recyclerView = (RecyclerView) root.findViewById(R.id.abonment_recycle);
        recyclerView2 = (RecyclerView) root.findViewById(R.id.produit_recycle);
        abonment_list = new ArrayList<>();
        /***************Static Data**********************/
        abonment_list.add(new Product(1,"nom","http://static.est.org.tn/1607947060_157a6129_w.jpg","gold",500,"abonement"));
        abonment_list.add(new Product(1,"nom","http://static.est.org.tn/1607947060_157a6129_w.jpg","gold",500,"abonement"));
        abonment_list.add(new Product(1,"nom","http://static.est.org.tn/1607947060_157a6129_w.jpg","gold",500,"abonement"));
        abonment_list.add(new Product(1,"nom","http://static.est.org.tn/1607947060_157a6129_w.jpg","gold",500,"abonement"));

        /**********************************************************/
        GridLayoutManager mLayoutManager = new GridLayoutManager(mContext, 3);
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new AbonementAdapter(mContext, abonment_list);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        /***************Static Data**********************/
        product_list = new ArrayList<>();
        product_list.add(new Product(1,"nom","http://static.est.org.tn/1607947060_157a6129_w.jpg","gold",500,"produit"));
        product_list.add(new Product(1,"nom","http://static.est.org.tn/1607947060_157a6129_w.jpg","gold",500,"produit"));
        product_list.add(new Product(1,"nom","http://static.est.org.tn/1607947060_157a6129_w.jpg","gold",500,"produit"));
        product_list.add(new Product(1,"nom","http://static.est.org.tn/1607947060_157a6129_w.jpg","gold",500,"produit"));

        /**********************************************************/

        GridLayoutManager mLayoutManager2 = new GridLayoutManager(mContext, 3);
        recyclerView2.setLayoutManager(mLayoutManager2);
        mAdapter2 = new ProduitAdapter(mContext, product_list);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setAdapter(mAdapter);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(StoreViewModel.class);
        // TODO: Use the ViewModel
    }

}