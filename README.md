# OmniVirt VR Player: 360° Video Player for Android
![API](https://img.shields.io/badge/API-17%2B-blue.svg?style=flat)

![Screenshot](https://github.com/OmniVirt/OmniVirtVRPlayer-Android-Example/blob/master/screenshot4.jpg?raw=true)

**OmniVirt** makes the leading player for 360° video experiences across mobile and desktop. Upload your 360° content to OmniVirt and serve it into your app with few easy steps.


Visit [omnivirt.com](https://omnivirt.com/) to upload your VR content. Contact us for more info at [contact@omnivirt.com](mailto:contact@omnivirt.com).

## Add the OmniVirt SDK to your app
 
Add the following lines to `build.gradle` of your application module.
```
dependencies {
    compile 'com.omnivirt:omnivirt-android-sdk:0.11.4'
} 
 
repositories {
    maven {
        url 'https://dl.bintray.com/omnivirt/OmniVirtSDK'
    }
}
```

## Usage

**OmniVirt VR Player** for Android provides you a really easy way to embed 360° content on your Android application with just few lines of code.

### Get Started

1. **Sign up** for an account at [OmniVirt](www.omnivirt.com)
2. **Upload** your VR / 360° photo or video on [OmniVirt](https://www.omnivirt.com/).
3. Keep the **Content ID** assigned to your content for further use.

Now the content is ready. There are two methods that you can use OmniVirt VR Player to play your VR content on your application.

### Method 1: Launch a Fullscreen VR Player

This method lets you play a VR content with just a single line of code !

#### Usage

First, add the following code to `AndroidManifest.xml`.
```xml
<activity android:name="com.omnivirt.vrkit.FullscreenVRPlayer"
    android:configChanges="orientation|screenSize"></activity>
```

To launch a fullscreen player, simply call:
```java
FullscreenVRPlayer.launch(MainActivity.this,
                          CONTENT_ID, // Replace with your Content ID
                          true,       // Autoplay
                          false       // Run in Cardboard mode
                          );
```

And ... done ! That's all !

#### Extra: Earn Money
Would like to earn money from your 360° content? You can create an **Ad Space** on [OmniVirt](www.omnivirt.com) and pass the **Ad Space ID** acquired to the command like shown below to enable ad on the player.

```java
FullscreenVRPlayer.launch(MainActivity.this,
                          CONTENT_ID, // Replace with your Content ID
                          true,       // Autoplay
                          false,      // Run in Cardboard mode
                          ADSPACE_ID  // Replace with your Ad Space ID
                          );
```

Once you set it up correctly, user will sometime see an ad among the player and that will turn into your revenue !

#### Player Callback

Any change on the player could be detected by implementing `OnVRPlayerInteractionListener` interface inside the **caller Activity**. Here is the example.

```java
public class PlayerActivity extends AppCompatActivity implements OnVRPlayerInteractionListener {

    ...
    
    private void play() {
        FullscreenVRPlayer.launch(MainActivity.this,
                                  24,
                                  true,       // Autoplay
                                  false       // Run in Cardboard mode
                                  );
    }

    /**
     * Called when VR Player Fragment embedded inside the Activity is created
     *************************************************************************/
    void onVRPlayerFragmentCreated() {    
    }
    
    /**
     * Called when VR Player is loaded
     **********************************/
    void onVRPlayerLoaded(Integer maximumQuality, Quality currentQuality, Mode cardboardMode) {    
    }
    
    /**
     * Called when VR Player has started playing
     ********************************************/
    void onVRPlayerStarted() {
    }
    
    /**
     * Called when VR Player is paused
     **********************************/
    void onVRPlayerPaused() {
    }
    
    /**
     * Called when VR Player has finished playing
     *********************************************/
    void onVRPlayerEnded() {
    }
    
    /**
     * Called when video has been skipped for the next one
     ******************************************************/
    void onVRPlayerSkipped() {
    }
    
    /**
     * Called when video duration has been changed
     *
     *   value - new duration in seconds
     **********************************************/
    void onVRPlayerDurationChanged(Double value) {
    }
    
    /**
     * Called when video progress has been changed
     *
     *   value - current progress in seconds
     **********************************************/
    void onVRPlayerProgressChanged(Double value) {
    }
    
    /**
     * Called when video has been buffered
     *
     *   bufferLength - buffer length in seconds
     ********************************************/
    void onVRPlayerBufferChanged(Double bufferLength) {
    }
    
    /**
     * Called when video has been seeked
     *
     *   value - seeked position in second
     *****************************************/
    void onVRPlayerSeekChanged(Double value) {
    }
    
    /**
     * Called when Cardboard mode has been changed
     *
     *   mode - new Cardboard mode (ON, OFF)
     **********************************************/
    void onVRPlayerCardboardChanged(Mode mode) {
    }
    
    /**
     * Called when volume level has been changed
     *
     *   volume - new volume level
     ********************************************/
    void onVRPlayerVolumeChanged(Double volume) {
    }
    
    /**
     * Called when video quality has been changed
     *
     *  quality - new video quality (QualitySD, QualityHD, QualitySHD and Quality4K)
     ******************************************************************************/
    void onVRPlayerQualityChanged(Quality quality) {
    }
    
    /**
     * Called when VR player has been expanded fullscreen
     *****************************************************/
    void onVRPlayerExpanded() {
    }    

    /**
     * Called when VR player has been restored to embedded size
     ***********************************************************/
    void onVRPlayerCollapsed() {
    }
    
    /**
     * Called when video angle in y-axis has been changed
     *
     *   latitude - new angle in degree
     *****************************************************/
    void onVRPlayerLatitudeChanged(Double latitude) {
    }    

    /**
     * Called when video angle in x-axis has been changed
     *
     *   longitude - new angle in degree
     *****************************************************/
    void onVRPlayerLongitudeChanged(Double longitude) {
    }
    
    /**
     * Called when video scene has been switched
     *
     *   scaneName - the name of new scene
     *   history - list of scenes navigated
     *****************************************/
    void onVRPlayerSwitched(String sceneName, Array history) {
    }

}
```


### Method 2: Embed a VR Player on an Activity with `VRPlayerFragment`
 
OmniVirt VR Player also provides you a Fragment that allows you to embed a VR Player on your Activity.

#### Usage
 
Add this `fragment` tag to your activity layout xml file.
```xml
<fragment
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:name="com.omnivirt.vrkit.VRPlayerFragment"
    android:id="@+id/vrplayer_fragment" />
```

To start playing, add the following snippet to your activity and replace `CONTENT_ID` with your VR Content's.
```java
VRPlayerFragment player = (VRPlayerFragment) getFragmentManager()
    .findFragmentById(R.id.vrplayer_fragment);
player.load(CONTENT_ID);
player.setCardboard(Mode.OFF);
```
 
Player callback could also be retrieved by the same approach as sample above.

#### Support Library v4 Fragment

Support Library v4 Fragment is also available in `com.omnivirt.vrkit.VRPlayerSupportFragment` class. Usage is still be the same as stock Fragment one.

```xml
<fragment
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:name="com.omnivirt.vrkit.VRPlayerSupportFragment"
    android:id="@+id/vrplayer_fragment" />
```


## QR Code for Cardboard Alignment
 
To launch QR Code scanner to scan a QR Code comes along with the Cardboard, call this following function.
```java
// Stock Fragment
QRReaderFragment.launchCardboardQRScanner(MainActivity.this);

// Support Fragment v4
QRReaderSupportFragment.launchCardboardQRScanner(MainActivity.this);
```


# Questions?

Please feel free to email us at [contact@omnivirt.com](mailto:contact@omnivirt.com) !
