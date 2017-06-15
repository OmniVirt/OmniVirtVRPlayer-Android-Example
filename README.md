# 360° Video Player and Monetization for Android

**OmniVirt** makes the leading player for 360° video experiences across mobile and desktop. Upload your 360° content to OmniVirt and serve it into your app in very easy steps.

The OmniVirt advertising platform enables developers and publishers to monetize their apps with engaging VR content.
Simply integrate the OmniVirt SDK into your iOS, Android or Web application and get paid for presenting sponsored 360° video experiences to your users. Backfill your inventory with premium CPM experiences from OmniVirt’s network of advertisers. We support both 360° and 2D video ads inside VR apps.

Visit [omnivirt.com](https://omnivirt.com/) to upload content or create ad space. Contact us for more info at [contact@omnivirt.com](mailto:contact@omnivirt.com).


## Add the OmniVirt SDK to your app
 
Add the following lines to `build.gradle` of your application module.
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

## Usage: OmniVirt Player

**OmniVirt Player** provides you a really easy way to embed 360° content on your Android application with just few lines of code.

### Get Started

1. Sign up for an account at [OmniVirt](www.omnivirt.com)
2. Upload your VR / 360° photo or video on [OmniVirt](https://www.omnivirt.com/).
3. Get the **Content ID** assigned to your content.

### Method 1: Fullscreen VRPlayer

Add the following to `AndroidManifest.xml`
```xml
<activity android:name="com.omnivirt.vrkit.FullscreenVRPlayer"
    android:configChanges="orientation|screenSize"></activity>
```

To open fullscreen player with adspace, use the following code:
<pre>
FullscreenVRPlayer.launch(YOUR_ACTIVITY, CONTENT_ID, AUTOPLAY, CARDBOARD_MODE, ADSPACE_ID)
</pre>

To open fullscreen player **without** adspace, use the following code instead:
<pre>
FullscreenVRPlayer.launch(YOUR_ACTIVITY, CONTENT_ID, AUTOPLAY, CARDBOARD_MODE);
</pre>

Implement interface OnVRPlayerInteractionListener in the caller activity and add the following functions:
<pre>
void onVRPlayerFragmentCreated();

void onVRPlayerLoaded(Integer maximumQuality, Quality currentQuality, Mode cardboardMode);

void onVRPlayerStarted();

void onVRPlayerPaused();

void onVRPlayerEnded();

void onVRPlayerSkipped();

void onVRPlayerDurationChanged(Double value);

void onVRPlayerProgressChanged(Double value);

void onVRPlayerBufferChanged(Double value);

void onVRPlayerSeekChanged(Double value);

void onVRPlayerCardboardChanged(Mode value);

void onVRPlayerAudioChanged(Double value);

void onVRPlayerQualityChanged(Quality value);

void onVRPlayerExpanded();

void onVRPlayerCollapsed();

void onVRPlayerLatitudeChanged(Double value);

void onVRPlayerLongitudeChanged(Double value);

void onVRPlayerSwitched(String sceneName, Array history);
</pre>


### Method 2: Embed a VRPlayer into an activity
 
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
void onVRPlayerFragmentCreated();

void onVRPlayerLoaded(Integer maximumQuality, Quality currentQuality, Mode cardboardMode);

void onVRPlayerStarted();

void onVRPlayerPaused();

void onVRPlayerEnded();

void onVRPlayerSkipped();

void onVRPlayerDurationChanged(Double value);

void onVRPlayerProgressChanged(Double value);

void onVRPlayerBufferChanged(Double value);

void onVRPlayerSeekChanged(Double value);

void onVRPlayerCardboardChanged(Mode value);

void onVRPlayerAudioChanged(Double value);

void onVRPlayerQualityChanged(Quality value);

void onVRPlayerExpanded();

void onVRPlayerCollapsed();

void onVRPlayerLatitudeChanged(Double value);

void onVRPlayerLongitudeChanged(Double value);

void onVRPlayerSwitched(String sceneName, Array history);
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

## Monetize your app with sponsored VR content

### Get Started

1. Sign up for an account at [OmniVirt](www.omnivirt.com)
2. Create one or more Ad Spaces for your app (for each Ad Space you can select different content and will get separate reporting)
3. Select what content to run in each Ad Space (e.g. OmniVirt's network ads)
4. Add one or more instances of the OmniVirt VRPlayer to your app (one for each Ad Space)



# Questions?

Please email us at [contact@omnivirt.com](mailto:contact@omnivirt.com)
