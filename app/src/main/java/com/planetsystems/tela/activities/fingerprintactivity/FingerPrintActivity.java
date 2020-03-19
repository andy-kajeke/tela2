package com.planetsystems.tela.activities.fingerprintactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.planetsystems.tela.R;
import com.suprema.BioMiniFactory;
import com.suprema.IBioMiniDevice;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

public class FingerPrintActivity extends AppCompatActivity implements
        DeviceBroadcastReceiver.OnDeviceConnectionListener,
        DefaultCaptureResponder.OnDefaultCaptureResponderResponseListener,
        CustomCaptureResponder.OnCustomCaptureResponderResponseListener {
    public static final String ACTION_USB_PERMISSION = "com.planetsystems.tela.activities.fingerprintactivity.ACTION_USB_PERMISSION";
    public static final boolean mUsbExternalUSBManager = false;
    private UsbManager usbManager;
    private PendingIntent pendingIntent;

    private static BioMiniFactory bioMiniFactory;
    public static final int REQUEST_WRITE_PERMISSION = 3456;
    public IBioMiniDevice iBioMiniDevice;

    private TextView logView;
    private TextView statusView;
    private ImageView imageView;
    private Button captureButton, actionButton;

    private IBioMiniDevice.CaptureOption captureOption = new IBioMiniDevice.CaptureOption();
    private CustomCaptureResponder customCaptureResponder;
    private DefaultCaptureResponder defaultCaptureResponder;
    private DeviceBroadcastReceiver deviceBroadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_print);
        captureOption.frameRate = IBioMiniDevice.FrameRate.SHIGH;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_finger_print, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if ( item.getItemId() == R.id.itemSettingId) {
            Toast.makeText(this, "Setting clicked", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    synchronized public void printState(final CharSequence charSequence) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (statusView != null) {
                    statusView.setText(charSequence);
                }
            }
        });
    }

    synchronized public void log(final String logMessage) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if ( logView == null ) {
                    logView = findViewById(R.id.textViewLog);
                }

                if ( logView != null ) {
                    logView.append( logMessage + "\n");
                }
            }
        });
    }

    @Override
    public void onDeviceConnectionSuccess(UsbDevice usbDevice) {
        if ( bioMiniFactory == null ) return;
        bioMiniFactory.addDevice(usbDevice);
        log(String.format(Locale.ENGLISH, "Initialized device count- BioMiniFactory (%d)", bioMiniFactory.getDeviceCount()));
    }

    @Override
    public void onDeviceConnectionError() {
    }

    public void checkDevice() {
        if (usbManager == null ) return;
        log("Checking Device...");
        HashMap<String, UsbDevice> usbDeviceHashMap = usbManager.getDeviceList();
        Iterator<UsbDevice> usbDeviceIterator = usbDeviceHashMap.values().iterator();
        while (usbDeviceIterator.hasNext()) {
            UsbDevice usbDevice = usbDeviceIterator.next();
            if (usbDevice.getVendorId() == 0x16d1) {
                // Suprema
                usbManager.requestPermission(usbDevice, pendingIntent);
            }
        }
    }

    @Override
    public boolean onDefaultCaptureResponderCaptureEx(Object o, Bitmap bitmap, IBioMiniDevice.TemplateData templateData, IBioMiniDevice.FingerState fingerState) {
        log("Capturing: Capture successful");
        return true;
    }

    @Override
    public void onDefaultCaptureResponderCaptureError(Object contest, int errorCode, String errorMessage) {
        log(("Capture: Capture Error"));
    }

    @Override
    public void onDefaultCaptureResponderCapture(Object o, IBioMiniDevice.FingerState fingerState) {

    }

    @Override
    public void onCustomCaptureResponderResponseCapture(Object contest, IBioMiniDevice.FingerState fingerState) {

    }

    @Override
    public boolean onCustomCaptureResponderResponseCaptureEx(Object contest, Bitmap bitmap, IBioMiniDevice.TemplateData templateData, IBioMiniDevice.FingerState fingerState) {
        return false;
    }

    @Override
    public void onCustomCaptureResponderResponseCaptureError(Object contest, int errorCode, String errorMessage) {

    }
}
