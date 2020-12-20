package com.olivapps.taraji.fragement.gallery;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.olivapps.taraji.R;
import com.olivapps.taraji.adapters.GalleryAdapter;
import com.olivapps.taraji.fragement.SlideshowDialogFragment;
import com.olivapps.taraji.remote.model.Classement;
import com.olivapps.taraji.remote.model.Equipe;
import com.olivapps.taraji.remote.model.Gallery;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private RecyclerView recyclerView;
    private GalleryAdapter mAdapter;
    private ArrayList<Gallery> gallery_list;
    Context mContext;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        mContext = getActivity().getApplicationContext();
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);
        gallery_list = new ArrayList<>();
        /***************Static Data**********************/

        gallery_list.add(new Gallery(1,"nom","http://static.est.org.tn/1607947060_157a6129_w.jpg"));
        gallery_list.add(new Gallery(2,"nom","http://static.est.org.tn/1607947062_20201214_124306_w.jpg"));
        gallery_list.add(new Gallery(3,"nom","http://static.est.org.tn/1607947062_20201214_124323_w.jpg"));
        gallery_list.add(new Gallery(4,"nom","http://static.est.org.tn/1607947063_20201214_124402_w.jpg"));
        gallery_list.add(new Gallery(5,"e5","http://static.est.org.tn/1607947063_20201214_124430_w.jpg"));
        /**********************************************************/

        GridLayoutManager mLayoutManager = new GridLayoutManager(mContext, 2);

        // Create a custom SpanSizeLookup where the first item spans both columns
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == 0 ? 2 : 1;
            }
        });
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new GalleryAdapter(mContext, gallery_list);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new GalleryAdapter.RecyclerTouchListener(mContext, recyclerView, new GalleryAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("images", gallery_list);
                bundle.putInt("position", position);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                SlideshowDialogFragment newFragment = new SlideshowDialogFragment();
                newFragment.setArguments(bundle);
                newFragment.show(ft, "slideshow");
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return root;
    }

}