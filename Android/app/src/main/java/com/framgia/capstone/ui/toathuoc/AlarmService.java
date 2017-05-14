package com.framgia.capstone.ui.toathuoc;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Ringtone;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;
import com.framgia.capstone.R;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by tri on 08/05/2017.
 */

public class AlarmService extends IntentService {
    private NotificationManager alarmNotificationManager;
    private Ringtone ringtone;

    public AlarmService() {
        super("AlarmServiceDL");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        AlarmReceiver.completeWakefulIntent(intent);
        sendNotification("Đã tới giờ uống thuốc");
    }

    private void sendNotification(String msg) {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);

        Toast.makeText(getApplicationContext(), "thong bao", Toast.LENGTH_SHORT).show();

        alarmNotificationManager =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent =
                PendingIntent.getActivity(this, 0, new Intent(this, ChiTietToaThuocFragment.class),
                        0);

        NotificationCompat.Builder alamNotificationBuilder =
                new NotificationCompat.Builder(this).setContentTitle("Thông báo!")
                        .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                        .setAutoCancel(true)
                        .setLights(Color.BLUE, 1000, 5000)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                        .setContentText(msg);

        alamNotificationBuilder.setContentIntent(contentIntent);
        alarmNotificationManager.notify(1, alamNotificationBuilder.build());
    }
}
