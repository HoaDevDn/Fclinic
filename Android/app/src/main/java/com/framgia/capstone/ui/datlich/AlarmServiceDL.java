package com.framgia.capstone.ui.datlich;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;
import com.framgia.capstone.R;
import com.framgia.capstone.ui.toathuoc.AlarmReceiver;
import com.framgia.capstone.ui.toathuoc.ChiTietToaThuocFragment;

/**
 * Created by tri on 08/05/2017.
 */

public class AlarmServiceDL extends IntentService {
    private NotificationManager alarmNotificationManager;
    private Ringtone ringtone;

    public AlarmServiceDL() {
        super("AlarmServiceDL");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        AlarmReceiver.completeWakefulIntent(intent);
        sendNotification("Còn một tiếng nữa tới giờ đi khám");
    }

    private void sendNotification(String msg) {

        alarmNotificationManager =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent =
                PendingIntent.getActivity(this, 0, new Intent(this, LichTrongFragment.class),
                        0);

        NotificationCompat.Builder alamNotificationBuilder =
                new NotificationCompat.Builder(this).setContentTitle("Thông báo!")
                        .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                        .setContentText(msg);

        alamNotificationBuilder.setContentIntent(contentIntent);
        alarmNotificationManager.notify(1, alamNotificationBuilder.build());
    }

}
