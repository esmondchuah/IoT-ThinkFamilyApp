package com.mycompany.thinkfamily;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;


/**
 * Service to push notifications in the background.
 */
public class NotificationService extends IntentService {

    NotificationCompat.Builder mBuilder;

    public NotificationService() {
        super("NotificationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        while (true) {
            mBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.thinkfamily_icon)
                    .setContentTitle("ThinkFamily")
                    .setContentText("ALERT!!! Your mom fell!!!");
            if (MainActivity.falling == 1){
                // Sets an ID for the notification
                int mNotificationId = 001;
                // Gets an instance of the NotificationManager service
                NotificationManager mNotifyMgr =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                // Builds the notification and issues it.
                mNotifyMgr.notify(mNotificationId, mBuilder.build());
                MainActivity.falling = 0;
            }
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
