package com.olivapps.taraji.fragement.roulette;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.olivapps.taraji.R;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class roulette extends Fragment {
    private static final String[] sectors = { "32 red", "15 black",
            "19 red", "4 black", "21 red", "2 black", "25 red", "17 black", "34 red",
            "6 black", "27 red","13 black", "36 red", "11 black", "30 red", "8 black",
            "23 red", "10 black", "5 red", "24 black", "16 red", "33 black",
            "1 red", "20 black", "14 red", "31 black", "9 red", "22 black",
            "18 red", "29 black", "7 red", "28 black", "12 red", "35 black",
            "3 red", "26 black", "zero"
    };
    Button spinBtn;
    TextView resultTv;
    ImageView wheel;
    // We create a Random instance to make our wheel spin randomly
    private static final Random RANDOM = new Random();
    private int degree = 0, degreeOld = 0;
    // We have 37 sectors on the wheel, we divide 360 by this value to have angle for each sector
    // we divide by 2 to have a half sector
    private static final float HALF_SECTOR = 360f / 37f / 2f;

    private RouletteViewModel mViewModel;

    public static roulette newInstance() {
        return new roulette();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.roulette_fragment, container, false);
        ButterKnife.bind(root);
        spinBtn=root.findViewById(R.id.spinBtn);
        resultTv=root.findViewById(R.id.resultTv);
        wheel=root.findViewById(R.id.wheel);
        spinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spin();
            }
        });
        return  root;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RouletteViewModel.class);
        // TODO: Use the ViewModel
    }

    public void spin() {
        Log.d("spin", "spin: on click");
        degreeOld = degree % 360;
        // we calculate random angle for rotation of our wheel
        degree = RANDOM.nextInt(360) + 720;
        // rotation effect on the center of the wheel
        RotateAnimation rotateAnim = new RotateAnimation(degreeOld, degree,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotateAnim.setDuration(3600);
        rotateAnim.setFillAfter(true);
        rotateAnim.setInterpolator(new DecelerateInterpolator());
        rotateAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // we empty the result text view when the animation start
                resultTv.setText("");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // we display the correct sector pointed by the triangle at the end of the rotate animation
                resultTv.setText(getSector(360 - (degree % 360)));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        // we start the animation
        wheel.startAnimation(rotateAnim);
    }


    private String getSector(int degrees) {
        int i = 0;
        String text = null;

        do {
            // start and end of each sector on the wheel
            float start = HALF_SECTOR * (i * 2 + 1);
            float end = HALF_SECTOR * (i * 2 + 3);

            if (degrees >= start && degrees < end) {
                // degrees is in [start;end[
                // so text is equals to sectors[i];
                text = sectors[i];
            }

            i++;
            // now we can test our Android Roulette Game :)
            // That's all !
            // In the second part, you will learn how to add some bets on the table to play to the Roulette Game :)
            // Subscribe and stay tuned !

        } while (text == null  &&  i < sectors.length);

        return text;
    }

}