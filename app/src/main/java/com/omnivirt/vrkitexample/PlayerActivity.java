package com.omnivirt.vrkitexample;

import android.support.v7.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.games.Player;
import com.omnivirt.vrkit.AdState;
import com.omnivirt.vrkit.Mode;
import com.omnivirt.vrkit.OnVRAdInteractionListener;
import com.omnivirt.vrkit.OnVRPlayerInteractionListener;
import com.omnivirt.vrkit.Quality;
import com.omnivirt.vrkit.VRAd;
import com.omnivirt.vrkit.VRPlayerFragment;
import com.omnivirt.vrkit.VRPlayerSupportFragment;

import java.lang.reflect.Array;

public class PlayerActivity extends AppCompatActivity implements OnVRPlayerInteractionListener {
    private VRPlayerSupportFragment mPlayer = null;
    private TextView mTextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        mTextView = (TextView) this.findViewById(R.id.vrplayer_output_textview); // Player State Logging

        mPlayer = (VRPlayerSupportFragment) this.getSupportFragmentManager().findFragmentById(R.id.vrplayer_fragment);

        // Use this method to disable interface
        //
        // mPlayer.setInterface(Mode.OFF);
    }

    ////////////////////////////
    // Listener for VRPlayer
    ////////////////////////////

    @Override
    public void onVRPlayerFragmentCreated() {
        mPlayer.load(24);
    }

    @Override
    public void onVRPlayerLoaded(Integer maxQuality, Quality quality, Mode mode) {
        this.log("Loaded maxQuality:" + maxQuality.toString() + " currentQuality:" + quality.toString() + " currentMode:" + mode.toString());
    }

    @Override
    public void onVRPlayerStarted() {
        this.log("Started");
    }

    @Override
    public void onVRPlayerPaused() {
        this.log("Paused");
    }

    @Override
    public void onVRPlayerEnded() {
        this.log("Ended");
    }

    @Override
    public void onVRPlayerSkipped() {
        this.log("Skipped");
    }

    @Override
    public void onVRPlayerDurationChanged(Double duration) {
        this.log("Duration changed to " + duration.toString());
    }

    @Override
    public void onVRPlayerProgressChanged(Double progress) {
        this.log("Progress changed to " + progress.toString());
    }

    @Override
    public void onVRPlayerBufferChanged(Double bufferLength) {
        this.log("Buffer changed to " + bufferLength.toString());
    }

    @Override
    public void onVRPlayerSeekChanged(Double position) {
        this.log("Seek changed to " + position.toString());
    }

    @Override
    public void onVRPlayerCardboardChanged(Mode mode) {
        this.log("Cardboard changed to " + mode.toString());
    }

    @Override
    public void onVRPlayerVolumeChanged(Double volume) {
        this.log("Volume changed to " + volume.toString());
    }

    @Override
    public void onVRPlayerQualityChanged(Quality quality) {
        this.log("Quality changed to " + quality.toString());
    }

    @Override
    public void onVRPlayerExpanded() {
        this.log("Expanded");
        final LinearLayout layout = (LinearLayout) this.findViewById(R.id.vrplayer_container);
        final LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) layout.getLayoutParams();
        params.height = LinearLayout.LayoutParams.MATCH_PARENT;
        layout.post(new Runnable() {
            @Override
            public void run() {
                layout.setLayoutParams(params);
            }
        });
    }

    @Override
    public void onVRPlayerCollapsed() {
        this.log("Collapsed");
        final LinearLayout layout = (LinearLayout) this.findViewById(R.id.vrplayer_container);
        final LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) layout.getLayoutParams();
        params.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, getResources().getDisplayMetrics());
        layout.post(new Runnable() {
            @Override
            public void run() {
                layout.setLayoutParams(params);
            }
        });
    }

    @Override
    public void onVRPlayerLatitudeChanged(Double latitude) {
        this.log("Latitude changed to " + latitude.toString());
    }

    @Override
    public void onVRPlayerLongitudeChanged(Double longitude) {
        this.log("Longitude changed to " + longitude.toString());
    }

    @Override
    public void onVRPlayerSwitched(String sceneName, Array history) {
        this.log("Swicthed scene to " + sceneName);
    }

    private void log(final String text) {
        mTextView.post(new Runnable() {
            @Override
            public void run() {
                mTextView.setText(mTextView.getText() + text + "\n");
                mTextView.invalidate();
            }
        });
    }
}
