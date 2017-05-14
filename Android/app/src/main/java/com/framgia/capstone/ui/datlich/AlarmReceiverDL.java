package com.framgia.capstone.ui.datlich;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.widget.Toast;
import com.framgia.capstone.ui.toathuoc.WakeLocker;

/**
 * Created by tri on 08/05/2017.
 */

public class AlarmReceiverDL extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        final Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();

        WakeLocker.acquire(context);

        //this will send a notification message
        ComponentName comp =
                new ComponentName(context.getPackageName(), AlarmServiceDL.class.getName());
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);

    /*    final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                    ringtone.stop();
            }
        }, 10 * 1000);*/
    }
}
