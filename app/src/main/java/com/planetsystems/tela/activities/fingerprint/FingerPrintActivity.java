package com.planetsystems.tela.activities.fingerprint;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.PendingIntent;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import com.planetsystems.tela.R;
import com.suprema.BioMiniFactory;
import com.suprema.IBioMiniDevice;

public class FingerPrintActivity extends Activity {
    //Flag.
    public static final boolean mbUsbExternalUSBManager = false;
    private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";
    private UsbManager mUsbManager = null;
    private PendingIntent mPermissionIntent= null;
    //
    private static BioMiniFactory mBioMiniFactory = null;
    public static final int REQUEST_WRITE_PERMISSION = 786;
    public IBioMiniDevice mCurrentDevice = null;
    private com.planetsystems.tela.activities.fingerprintactivity.FingerPrintActivity mainContext;

    public final static String TAG = "BioMini Sample";
    private TextView mLogView;
    private ScrollView mScrollLog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_finger_print);
        setTitle(R.string.capture);
    }
}
