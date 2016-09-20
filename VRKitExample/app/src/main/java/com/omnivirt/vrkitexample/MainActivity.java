package com.omnivirt.vrkitexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.omnivirt.vrkit.Mode;
import com.omnivirt.vrkit.Quality;
import com.omnivirt.vrkit.VRPlayerFragment;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity implements VRPlayerFragment.OnVRPlayerInteractionListener {

    private VRPlayerFragment mPlayer = null;
    private TextView mTextView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView)this.findViewById(R.id.vrplayer_output_textview);

        mPlayer = (VRPlayerFragment)this.getSupportFragmentManager().findFragmentById(R.id.vrplayer_fragment);
    }

    @Override
    public void onFragmentCreated() {
        mPlayer.load(24);
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
        Toast.makeText(this, "TODO: plese expand our player to full screen.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCollapsed() {
        this.log("Collapsed");
        Toast.makeText(this, "TODO: plese collapse our player to normal position.", Toast.LENGTH_SHORT).show();
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
