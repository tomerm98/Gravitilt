package com.example.tomer.gravitilt;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class PhoneCallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if (GameplayActivity.screenView != null) {
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                GameplayActivity.screenView.stop();
            } else if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                GameplayActivity.screenView.run();
            }
        }
    }
}
