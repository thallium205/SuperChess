package russell.john;
import com.jme3.app.AndroidHarness;
import android.content.pm.ActivityInfo;
import com.jme3.system.android.AndroidConfigChooser.ConfigType;
public class MainActivity extends AndroidHarness{
    public MainActivity(){
        // Set the application class to run
        appClass = "russell.john.SuperChess";
        //eglConfigType = ConfigType.FASTEST;  // Default
        eglConfigType = ConfigType.BEST;
        // Exit Dialog title & message
        exitDialogTitle = "Exit?";
        exitDialogMessage = "Press Yes";
        // Enable verbose logging
        eglConfigVerboseLogging = false;
        // Choose screen orientation
        screenOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        // Invert the MouseEvents X (default = true)
        mouseEventsInvertX = false;
        // Invert the MouseEvents Y (default = true)
        mouseEventsInvertY = false;
        
        // Mouse events enabled
        mouseEventsEnabled = true;
    }
}
