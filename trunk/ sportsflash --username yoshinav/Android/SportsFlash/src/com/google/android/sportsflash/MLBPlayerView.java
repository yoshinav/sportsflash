package com.google.android.sportsflash;

import android.app.Activity;
import android.os.Bundle;

public class MLBPlayerView extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.mlbplayer_list);
    }

}
