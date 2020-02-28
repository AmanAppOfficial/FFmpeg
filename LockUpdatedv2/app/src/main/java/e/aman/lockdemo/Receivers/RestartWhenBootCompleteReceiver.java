package e.aman.lockdemo.Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

import e.aman.lockdemo.Services.LockScreenService;

public class RestartWhenBootCompleteReceiver extends BroadcastReceiver               //When phone reboots service dies , this reciever restarts the service.
{
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startForegroundService(new Intent(context, LockScreenService.class));
    }
}
