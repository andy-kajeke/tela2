package com.planetsystems.tela.activities.fingerprintactivity;

import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.hardware.usb.UsbManager;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.planetsystems.tela.R;
import com.suprema.BioMiniFactory;
import com.suprema.CaptureResponder;
import com.suprema.IBioMiniDevice;

public class FingerPrintActivity extends AppCompatActivity {
    //Flag.
    public static final boolean mbUsbExternalUSBManager = false;
    private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";
    private UsbManager mUsbManager = null;
    private PendingIntent mPermissionIntent= null;
    //
    private static BioMiniFactory mBioMiniFactory = null;
    public static final int REQUEST_WRITE_PERMISSION = 786;
    public IBioMiniDevice mCurrentDevice = null;
    private FingerPrintActivity mainContext;

    public final static String TAG = "BioMini Sample";
    private TextView mLogView;
    private ScrollView mScrollLog = null;

    private IBioMiniDevice.CaptureOption mCaptureOptionDefault = new IBioMiniDevice.CaptureOption();
    private CaptureResponder mCaptureResponseDefault = new CaptureResponder() {
        @Override
        public boolean onCaptureEx(final Object context, final Bitmap capturedImage,
                                   final IBioMiniDevice.TemplateData capturedTemplate,
                                   final IBioMiniDevice.FingerState fingerState) {
            log("onCapture : Capture successful!");
            printState(getResources().getText(R.string.capture_single_ok));

            log(((IBioMiniDevice) context).popPerformanceLog());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (capturedImage != null) {
                        ImageView iv = (ImageView) findViewById(R.id.imageView_fingerprint);
                        if (iv != null) {
                            iv.setImageBitmap(capturedImage);
                        }
                    }
                }
            });
            return true;
        }

    };

    private void printState(CharSequence message) {

    }

    private void log(String message) {

    }
}