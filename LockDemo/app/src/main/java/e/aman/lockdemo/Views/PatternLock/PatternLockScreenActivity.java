package e.aman.lockdemo.Views.PatternLock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import e.aman.lockdemo.R;
import e.aman.lockdemo.Services.WidgetService;
import e.aman.lockdemo.Services.LockScreenService;


public class PatternLockScreenActivity extends AppCompatActivity {

    Intent mServiceIntent;
    private LockScreenService mSensorService;
    Context ctx ;
    String status ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeFullScreen();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        status = preferences.getString("status", null);
        if(status.equals("lock_pattern"))
        {
            ctx = this ;
            startService(new Intent(this, WidgetService.class));
            mSensorService = new LockScreenService(ctx);
            mServiceIntent = new Intent(ctx, mSensorService.getClass());
            startService(mServiceIntent);


        }
        setContentView(R.layout.activity_pattern_lockscreen);
              finish();

    }

    private void makeFullScreen()
    {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|WindowManager.LayoutParams.FLAG_FULLSCREEN|
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

        int visibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        if (Build.VERSION.SDK_INT < 19) { //View.SYSTEM_UI_FLAG_IMMERSIVE is only on API 19+
            visibility |= View.SYSTEM_UI_FLAG_IMMERSIVE;
        }
        this.getWindow().getDecorView().setSystemUiVisibility(visibility);

    }

    @Override
    public void onBackPressed() {
        return;
    }

    @Override
    protected void onDestroy() {
           if(status.equals("lock_pattern"))
            stopService(mServiceIntent);
        super.onDestroy();
    }
//
//    @Override
//    protected void onStart() {
//        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("my_background", Context.MODE_PRIVATE);
//        int bg = sharedPref.getInt("background_resource", android.R.color.white); // the second parameter will be fallback if the preference is not found
//        getWindow().setBackgroundDrawableResource(bg);
//        super.onStart();
//    }
}
