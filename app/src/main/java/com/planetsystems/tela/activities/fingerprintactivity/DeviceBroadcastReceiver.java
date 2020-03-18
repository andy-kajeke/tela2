package com.planetsystems.tela.activities.fingerprintactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;

public class DeviceBroadcastReceiver extends BroadcastReceiver {
    private Context context;

    public DeviceBroadcastReceiver(Context context) {
        super();
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action =  intent.getAction();
        if ( FingerPrintActivity.ACTION_USB_PERMISSION.equals(action)) {
            UsbDevice device = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
            if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                if ( device != null ) {
                }
            } else {
                // permission denied
            }
        }

    }

    public interface OnDeviceConnectionListener {
        public void onDeviceConnectionSuccess();
        public void onDeviceConnectionError();
    }
}
