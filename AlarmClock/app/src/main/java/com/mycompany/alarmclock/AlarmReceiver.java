package com.mycompany.alarmclock;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.content.WakefulBroadcastReceiver;

import java.net.URI;


public class AlarmReceiver extends WakefulBroadcastReceiver {

    private static Ringtone mRingtone=null;
    @Override
    public void onReceive(final Context context, Intent intent) {

        MainActivity inst=MainActivity.instance();
        inst.setAlarmText("\n                  Alarm!\n           Get Up! Get up!");
        Uri alarmUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
        mRingtone=RingtoneManager.getRingtone(context,alarmUri);
        mRingtone.play();
        //ringtone.play();

        //this will send a notification message
        ComponentName comp = new ComponentName(context.getPackageName(),
                AlarmService.class.getName());
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);
    }
    public  static void stopRingtone(){
        mRingtone.stop();
    }
}
