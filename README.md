# 360° Video Player and Monetization for Android

OmniVirt makes the leading player for 360° video experiences across mobile and desktop. Upload your 360° content to OmniVirt and serve it into your app in very easy steps.

The OmniVirt advertising platform enables developers and publishers to monetize their apps with engaging VR content.
Simply integrate the OmniVirt SDK into your iOS, Android or Web application and get paid for presenting sponsored 360° video experiences to your users. Backfill your inventory with premium CPM experiences from OmniVirt’s network of advertisers. We support both 360° and 2D video ads inside VR apps.

Contact us for more info at [contact@omnivirt.com](mailto:contact@omnivirt.com).
Visit [www.omnivirt.com](https://www.omnivirt.com/) to upload content or create ad space.


## Add the OmniVirt SDK to your app
 
Add the following lines to build.gradle of your "app" module folder (not your project root build.gradle).
<pre>
dependencies {
    compile 'com.omnivirt:omnivirt-android-sdk:0.9.0'
} 
 
repositories {
    maven {
        url 'https://dl.bintray.com/omnivirt/OmniVirtSDK'
    }
}
</pre>

## Use the OmniVirt Player

### Display your own VR content
1. Sign up for an account at [OmniVirt](www.omnivirt.com)
2. Upload your VR/360-degree photo or video at [OmniVirt](https://www.omnivirt.com/).
3. OmniVirt will assign an ID to your content as part of the upload. Copy the content ID and pass it to the VRPlayer's launch method.

### Monetize your app with sponsored VR content

1. Sign up for an account at [OmniVirt](www.omnivirt.com)
2. Create one or more Ad Spaces for your app (for each Ad Space you can select different content and will get separate reporting)
3. Select what content to run in each Ad Space (e.g. OmniVirt's network ads)
4. Add one or more instances of the OmniVirt VRPlayer to your app (one for each Ad Space)

## Tutorials
### Add a Full Screen VRPlayer
 
1. Add the following to AndroidManifest.xml
```xml
<activity android:name="com.omnivirt.vrkit.FullscreenVRPlayer"
    android:configChanges="orientation|screenSize"></activity>
```

2. Import vrkit into your code
<pre>
import com.omnivirt.vrkit.*;
</pre>
3.    To open fullscreen player with adspace use the following code:
<pre>
FullscreenVRPlayer.launch(YOUR_ACTIVITY, CONTENT_ID, AUTOPLAY, CARDBOARD_MODE, ADSPACE_ID)
</pre>
4.    To open fullscreen player with no adspace use the following code instead:
<pre>
FullscreenVRPlayer.launch(YOUR_ACTIVITY, CONTENT_ID, AUTOPLAY, CARDBOARD_MODE);
</pre>
5.    Implement interface OnVRPlayerInteractionListener in the caller activity and add the following functions:
<pre>
void onFragmentCreated();
void onLoaded(Integer maximumQuality, Quality currentQuality, Mode cardboardMode);
void onStarted();
void onPaused();
void onEnded();
void onSkipped();
void onDurationChanged(Double value);
void onProgressChanged(Double value);
void onBufferChanged(Double value);
void onSeekChanged(Double value);
void onCardboardChanged(Mode value);
void onAudioChanged(Double value);
void onQualityChanged(Quality value);
void onExpanded();
void onCollapsed();
void onLatitudeChanged(Double value);
void onLongitudeChanged(Double value);
void onSwitched(String sceneName, Array history);
</pre>


### Embed a VRPlayer into an activity
 
1.    Add this fragment to your activity layout xml file
```xml
<fragment
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:name="com.omnivirt.vrkit.VRPlayerFragment"
	android:id="@+id/vrplayer_fragment" />
```
 
2.    Import vrkit into your code
<pre>
import com.omnivirt.vrkit.*;
</pre>

3.    Add the following snippet to your activity 
<pre>
VRPlayerFragment  player = (VRPlayerFragment)this.getFragmentManager().findFragmentById(R.id.vrplayer_fragment);
player.load(CONTENT_ID);
player.setCardboard(CARDBOARD_MODE);
</pre>
 
4.    Implement the interface OnVRPlayerInteractionListener and add the following functions:
<pre>
void onFragmentCreated();
void onLoaded(Integer maximumQuality, Quality currentQuality, Mode cardboardMode);
void onStarted();
void onPaused();
void onEnded();
void onSkipped();
void onDurationChanged(Double value);
void onProgressChanged(Double value);
void onBufferChanged(Double value);
void onSeekChanged(Double value);
void onCardboardChanged(Mode value);
void onAudioChanged(Double value);
void onQualityChanged(Quality value);
void onExpanded();
void onCollapsed();
void onLatitudeChanged(Double value);
void onLongitudeChanged(Double value);
void onSwitched(String sceneName, Array history);
</pre>


### Add a VR Ad
 
1.    Import vrkit into your code
<pre>
import com.omnivirt.vrkit.*;
</pre>
	
2.    Use the following code to load a VRAd
<pre>
VRAd  vrAd = new VRAd(ADSPACE_ID, ACTIVITY);
vrAd.load();
</pre>
3.    Implement the OnVRAdInteractionListener interface and add the following functions

<pre>
void onAdStatusChanged(VRAd instance, AdState status);
</pre>
4.    Listen for the status of the ad to be "Ready" before showing your ad with
<pre>
vrAd.show(CARDBOARD_MODE);
</pre>
 
 
### Scan a QR Code
 
1.    Add the following to your app's build.gradle
<pre>
compile 'com.google.android.gms:play-services:7.8.0'
</pre>
2.    Import vrkit into your code
<pre>
import com.omnivirt.vrkit.*;
</pre>
3.    Call the following function to open QR Code scanner
<pre>
QRReaderFragment.launchCardboardQRScanner(ACTIVITY);
</pre>


# Questions?

Please email us at [contact@omnivirt.com](mailto:contact@omnivirt.com)
