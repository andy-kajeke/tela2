package com.planetsystems.tela.activities.fingerprintactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.planetsystems.tela.R;
import com.suprema.BioMiniFactory;
import com.suprema.IBioMiniDevice;
import com.suprema.IUsbEventHandler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

public class FingerPrintActivity extends AppCompatActivity implements
        DeviceBroadcastReceiver.OnDeviceConnectionListener,
        DefaultCaptureResponder.OnDefaultCaptureResponderResponseListener,
        CustomCaptureResponder.OnCustomCaptureResponderResponseListener,
        TelaBioMiniFactory.OnTelaDeviceChangeListener{
    public static final String ACTION_USB_PERMISSION = "com.planetsystems.tela.activities.fingerprintactivity.ACTION_USB_PERMISSION";
    public static final boolean mUsbExternalUSBManager = false;
    private UsbManager usbManager;
    private PendingIntent pendingIntent;

    private TelaBioMiniFactory telaBioMiniFactory;
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

        if (telaBioMiniFactory != null ) {
            telaBioMiniFactory.close();
        }

        restartBioMini();
        log("" + telaBioMiniFactory.getSDKInfo());
    }

    private void restartBioMini() {
        if (telaBioMiniFactory != null ) {
            telaBioMiniFactory.close();
        }

        if (mUsbExternalUSBManager) {
            usbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
            telaBioMiniFactory = new TelaBioMiniFactory(this, usbManager);
            pendingIntent = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0);
            IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
            registerReceiver(deviceBroadcastReceiver, filter);
            checkDevice();
        } else telaBioMiniFactory = new TelaBioMiniFactory(this);
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
        if ( telaBioMiniFactory == null ) return;
        telaBioMiniFactory.addDevice(usbDevice);
        log(String.format(Locale.ENGLISH, "Initialized device count- BioMiniFactory (%d)", telaBioMiniFactory.getDeviceCount()));
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
        log("Capturing: Capturing it");
    }

    @Override
    public void onCustomCaptureResponderResponseCapture(Object contest, IBioMiniDevice.FingerState fingerState) {
        log("Capturing: Capturing");
    }

    @Override
    public boolean onCustomCaptureResponderResponseCaptureEx(Object contest, Bitmap bitmap, IBioMiniDevice.TemplateData templateData, IBioMiniDevice.FingerState fingerState) {
        log("Capturing: Capture successful");
        return true;
    }

    @Override
    public void onCustomCaptureResponderResponseCaptureError(Object contest, int errorCode, String errorMessage) {
        log("Capturing: Capture error");
    }

    void handleDeviceChange(IUsbEventHandler.DeviceChangeEvent event, Object device) {
        if (event == IUsbEventHandler.DeviceChangeEvent.DEVICE_ATTACHED && iBioMiniDevice == null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int count = 0;
                    while (telaBioMiniFactory == null && count < 20) {
                        SystemClock.sleep(1000);
                        count++;
                    }

                    if (telaBioMiniFactory != null ) {
                        iBioMiniDevice = telaBioMiniFactory.getDevice(0);
                        printState("Device connected");
                        if ( iBioMiniDevice != null ) {
                            log("DeviceName: " + iBioMiniDevice.getDeviceInfo().deviceName);
                            log("        SN: " + iBioMiniDevice.getDeviceInfo().deviceSN);
                            log("SDK version: " + iBioMiniDevice.getDeviceInfo().versionSDK);
                        }
                    }
                }
            }).start();
        } else if (iBioMiniDevice != null && event == IUsbEventHandler.DeviceChangeEvent.DEVICE_ATTACHED && iBioMiniDevice.isEqual(device)) {
            printState("device attached");
            iBioMiniDevice = null;
        }
    }

    @Override
    public void onTelaDeviceChange(IUsbEventHandler.DeviceChangeEvent deviceChangeEvent, Object contest) {
        log("-----------------------------");
        log("Device Changed : " + deviceChangeEvent);
        log("-----------------------------");
        handleDeviceChange(deviceChangeEvent, contest);
    }

    @Override
    protected void onDestroy() {
        if ( telaBioMiniFactory != null ) {
            telaBioMiniFactory.close();
            telaBioMiniFactory = null;
        }

        if (mUsbExternalUSBManager) {
            unregisterReceiver(deviceBroadcastReceiver);
        }
        super.onDestroy();
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            log("permission granted");
        }
    }
}
