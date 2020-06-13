package com.example.powerreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {
    private static final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();
        String text = "unknown";
        assert intentAction != null;
        switch (intentAction){
            case Intent.ACTION_POWER_CONNECTED:
                text = "connected";
                break;
            case Intent.ACTION_POWER_DISCONNECTED:
                text = "disconnected";
                break;
            case ACTION_CUSTOM_BROADCAST:
                text = context.getString(R.string.custom);
                break;
        }
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }
}
