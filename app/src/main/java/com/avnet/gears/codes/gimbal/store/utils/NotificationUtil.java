package com.avnet.gears.codes.gimbal.store.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.avnet.gears.codes.gimbal.store.bean.NotificationActionBean;

import java.util.List;

/**
 * Created by 914889 on 2/27/15.
 */
public class NotificationUtil {

    public static void notify(Context context, Intent targetIntent,
                              String contentText, String notificationTitle, int resourceIcon,
                              boolean autoCancel, List<NotificationActionBean> actionsBeanList)  {
    // prepare intent which is triggered if the
    // notification is selected

    PendingIntent pIntent = PendingIntent.getActivity(context, 0, targetIntent, 0);

        // build notification
        // the addAction re-use the same intent to keep the example short
        Notification.Builder builder = new Notification.Builder(context)
                .setContentTitle(notificationTitle)
                .setContentText(contentText)
                .setSmallIcon(resourceIcon)
                .setContentIntent(pIntent)
                .setAutoCancel(autoCancel);
        // dynamically add sub actions, if any
        if(actionsBeanList != null) {
            for(NotificationActionBean actionBean : actionsBeanList){
                builder.addAction(actionBean.getDrawableIcon(),
                        actionBean.getActionTitle(),
                        actionBean.getTargetActionPendingIntent());
            }
        }

        // build to notifications once we set all teh parameters
        Notification n  = builder.build();

        // create a notification manager from the calling context
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // send notification
        notificationManager.notify(0, n);
    }
}
