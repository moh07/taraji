package com.olivapps.taraji.fragement.music;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.widget.MediaController;

import com.olivapps.taraji.R;

public class MusicController extends MediaController {

    public MusicController(Context context) {
        super(new ContextThemeWrapper(context, R.style.Theme_MusicPlayer));
    }
    public void hide(){}
}
