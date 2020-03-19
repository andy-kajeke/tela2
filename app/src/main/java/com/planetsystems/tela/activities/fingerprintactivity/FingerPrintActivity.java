package com.planetsystems.tela.activities.fingerprintactivity;

import android.app.PendingIntent;
import android.hardware.usb.UsbManager;

import androidx.appcompat.app.AppCompatActivity;

public class FingerPrintActivity extends AppCompatActivity {
    //Flag.
    public static final boolean mbUsbExternalUSBManager = false;
    private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";
    private UsbManager mUsbManager = null;
    private PendingIntent mPermissionIntent= null;
    //


}