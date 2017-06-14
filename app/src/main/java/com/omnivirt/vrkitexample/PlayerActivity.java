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

import com.omnivirt.vrkit.AdState;
import com.omnivirt.vrkit.Mode;
import com.omnivirt.vrkit.OnVRAdInteractionListener;
import com.omnivirt.vrkit.OnVRPlayerInteractionListener;
import com.omnivirt.vrkit.Quality;
import com.omnivirt.vrkit.VRAd;
import com.omnivirt.vrkit.VRPlayerFragment;

import java.lang.reflect.Array;

public class PlayerActivity extends AppCompatActivity implements OnVRPlayerInteractionListener, OnVRAdInteractionListener {
    private VRPlayerFragment mPlayer = null;
    private TextView mTextView = null;
    private Button mAdButton = null;
    private VRAd mVrAd = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        mTextView = (TextView)this.findViewById(R.id.vrplayer_output_textview); // Player State Logging

        mPlayer = (VRPlayerFragment)this.getSupportFragmentManager().findFragmentById(R.id.vrplayer_fragment);

        // Use this method to disable interface
        //
        // mPlayer.setInterface(Mode.OFF);

        // For manually creating VRplayer without storyboard, please uncomment the following code.
        //
        // mPlayer = VRPlayerFragment.newInstance(this);

        // Creating VR Ad instance
        //
        mVrAd = new VRAd(1, this);

        // Loading and starting VR Ad using a button
        //
        mAdButton = (Button)this.findViewById(R.id.start_ad_button);
        mAdButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String title = mAdButton.getText().toString();
                if (title.equals("Load Ad")) {
                    // Load Ad
                    //
                    mVrAd.load();
                } else if (title.equals("Start Ad")) {
                    // Select the option to turn on / off Cardboard mode for ads
                    //
                    AlertDialog.Builder builder = new AlertDialog.Builder(PlayerActivity.this);
                    builder.setMessage("Please select Cardboard mode")
                            .setCancelable(false)
                            .setPositiveButton("OFF", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // Start Ad without cardboard
                                    //
                                    mVrAd.show(Mode.OFF);
                                }
                            })
                            .setNegativeButton("ON", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // Start Ad with cardboard
                                    //
                                    mVrAd.show(Mode.ON);
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });
    }

    @Override
    public void onAdStatusChanged(VRAd instance, AdState status) {
        switch (status) {
            case None:
                break;
            case Loading:
                this.log("Ad state is loading");
                mAdButton.setText("Loading Ad");
                mAdButton.setEnabled(false);
                break;
            case Ready:
                this.log("Ad state is ready");
                mAdButton.setText("Start Ad");
                mAdButton.setEnabled(true);
                break;
            case Showing:
                this.log("Ad state is showing");
                mAdButton.setText("Showing Ad");
                mAdButton.setEnabled(false);
                mPlayer.setIdle(Mode.ON); // Idling any video player to reserve GPU resources for VR Ad
                break;
            case Completed:
                this.log("Ad state is completed");
                mAdButton.setText("Load Ad");
                mAdButton.setEnabled(true);
                mPlayer.setIdle(Mode.OFF); // Resume the video player
                break;
            case Failed:
                this.log("Ad state is failed");
                mAdButton.setText("Load Ad");
                mAdButton.setEnabled(true);
                break;
        }
    }

    @Override
    public void onFragmentCreated() {
        mPlayer.load(24);
    }

    @Override
    public void onConfigurationChanged(Configuration config) {
        // This method helps player from reloading when orientation changes.
        //
        super.onConfigurationChanged(config);
    }

    @Override
    public void onBackPressed() {
        // This method will close VR Ad when user hit back button.
        //
        if (mVrAd.getStatus() == AdState.Showing) {
            mVrAd.unload();

            // Uncomment this line to prevent player activity from closing.
            // return;
        }

        super.onBackPressed();
    }

    @Override
    public void onLoaded(Integer maxQuality, Quality quality, Mode mode) {
        this.log("Loaded maxQuality:" + maxQuality.toString() + " currentQuality:" + quality.toString() + " currentMode:" + mode.toString());
    }

    @Override
    public void onStarted() {
        this.log("Started");
    }

    @Override
    public void onPaused() {
        this.log("Paused");
    }

    @Override
    public void onEnded() {
        this.log("Ended");
    }

    @Override
    public void onSkipped() { this.log("Skipped"); }

    @Override
    public void onDurationChanged(Double aDouble) {
        // this.log("Duration changed to " + aDouble.toString());
    }

    @Override
    public void onProgressChanged(Double aDouble) {
        // this.log("Progress changed to " + aDouble.toString());
    }

    @Override
    public void onBufferChanged(Double aDouble) {
        // this.log("Buffer changed to " + aDouble.toString());
    }

    @Override
    public void onSeekChanged(Double aDouble) {
        // this.log("Seek changed to " + aDouble.toString());
    }

    @Override
    public void onCardboardChanged(Mode mode) {
        this.log("Cardboard changed to " + mode.toString());
    }

    @Override
    public void onAudioChanged(Double aDouble) {
        this.log("Audio changed to " + aDouble.toString());
    }

    @Override
    public void onQualityChanged(Quality quality) {
        this.log("Quality changed to " + quality.toString());
    }

    @Override
    public void onExpanded() {
        this.log("Expanded");
        final LinearLayout layout = (LinearLayout)this.findViewById(R.id.vrplayer_container);
        final LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)layout.getLayoutParams();
        params.height = LinearLayout.LayoutParams.MATCH_PARENT;
        layout.post(new Runnable() {
            @Override
            public void run() {
                layout.setLayoutParams(params);
            }
        });
    }

    @Override
    public void onCollapsed() {
        this.log("Collapsed");
        final LinearLayout layout = (LinearLayout)this.findViewById(R.id.vrplayer_container);
        final LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)layout.getLayoutParams();
        params.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, getResources().getDisplayMetrics());
        layout.post(new Runnable() {
            @Override
            public void run() {
                layout.setLayoutParams(params);
            }
        });
    }

    @Override
    public void onLatitudeChanged(Double aDouble) {
        // this.log("Latitude changed to " + aDouble.toString());
    }

    @Override
    public void onLongitudeChanged(Double aDouble) {
        // this.log("Longitude changed to " + aDouble.toString());
    }

    @Override
    public void onSwitched(String s, Array array) {
        this.log("Swicthed scene to " + s);
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
