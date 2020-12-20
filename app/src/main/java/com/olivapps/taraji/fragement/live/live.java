package com.olivapps.taraji.fragement.live;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.olivapps.taraji.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class live extends Fragment  {

    FloatingActionButton b;
    public YouTubePlayerView youTubePlayerView;
    public YouTubePlayer.OnInitializedListener onInitializedListener;
    private LiveViewModel mViewModel;
    private RequestQueue requestQueue;
    private JsonObjectRequest jsonObjectRequest;
    private String videoId="";
    public static live newInstance() {
        return new live();
    }
    YouTubePlayerSupportFragment youTubePlayerFragment;
    FrameLayout livelyout ;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.live_fragment, container, false);
        //
         youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.youtubeview, youTubePlayerFragment);
        transaction.commit();




        b=root.findViewById(R.id.btn);
        livelyout=root.findViewById(R.id.livelayout);

        initVolley("https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=UC9DfUXSwlRfUmou0e38tt2g&eventType=live&type=video&key=AIzaSyDq98a3lxRW1XhodF7KeVCTRKudPSOvGRQ");
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initVolley("https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=UC9DfUXSwlRfUmou0e38tt2g&eventType=live&type=video&key=AIzaSyDq98a3lxRW1XhodF7KeVCTRKudPSOvGRQ");
            }
        });
        return  root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LiveViewModel.class);
        // TODO: Use the ViewModel
    }

    private void initVolley(String url) {

        requestQueue = Volley.newRequestQueue(getActivity());


            if (url.startsWith("https://www.googleapis.com/youtube/v3/")) {
                jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("items");
                            if (jsonArray.length() > 0) {
                                for (int getItem = 0; getItem < jsonArray.length(); getItem++) {

                                    JSONObject jsonObject = jsonArray.getJSONObject(getItem).getJSONObject("id");


                                    //Log.d("TAG", "void id " + videoID);

                                    //mUrlList.add(new video(jsonArray.getJSONObject(getItem).getJSONObject("snippet").getString("title"), jsonArray.getJSONObject(getItem).getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("medium").getString("url"), " ", "https://www.youtube.com/watch?v=" + videoID, true));
                                    videoId=jsonArray.getJSONObject(getItem).getJSONObject("id").getString("videoId");
                                    InitializedLive(videoId);
                                    youTubePlayerFragment.initialize("AIzaSyDq98a3lxRW1XhodF7KeVCTRKudPSOvGRQ", onInitializedListener);

                                }


                            }
                            else {
                            Snackbar snack = Snackbar.make(livelyout, "live n'est pas disponible actuellement", Snackbar.LENGTH_LONG);
                            View view = snack.getView();

                            CoordinatorLayout.LayoutParams params =(CoordinatorLayout.LayoutParams)view.getLayoutParams();
                                params.setMargins(0, 20, 0, 0);
//                                params.gravity = Gravity.TOP;
                            view.setLayoutParams(params);
                            snack.show();}

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                });
                requestQueue.add(jsonObjectRequest);
            }

    }

    public void InitializedLive(String id){
        onInitializedListener =new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(id);

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
    }


}