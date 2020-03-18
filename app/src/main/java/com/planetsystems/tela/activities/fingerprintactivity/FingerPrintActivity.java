package com.planetsystems.tela.activities.fingerprintactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;

import com.planetsystems.tela.R;
import com.suprema.BioMiniFactory;
import com.suprema.IBioMiniDevice;

import java.util.HashMap;
import java.util.Iterator;

public class FingerPrintActivity extends AppCompatActivity implements DeviceBroadcastReceiver.OnDeviceConnectionListener{
    public static final String ACTION_USB_PERMISSION = "com.planetsystems.tela.activities.fingerprintactivity.FingerPrintActivity";
    public IBioMiniDevice.TemplateData teacherCapturedTemplate;
    private Bitmap teacherImage;
    //Flag.
    public static final boolean mbUsbExternalUSBManager = false;
    private UsbManager mUsbManager = null;
    private PendingIntent mPermissionIntent= null;
    //

    private static BioMiniFactory mBioMiniFactory = null;
    public static final int REQUEST_WRITE_PERMISSION = 786;
    public IBioMiniDevice mCurrentDevice = null;
    private FingerPrintActivity mainContext;

    private IBioMiniDevice.CaptureOption mCaptureOptionDefault = new IBioMiniDevice.CaptureOption();

    synchronized public void printState(final CharSequence str){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // print the message
            }
        });

    }

    synchronized public void log(final String msg) {
//        print the log
    }

    synchronized public void printRev(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                ((TextView) findViewById(R.id.revText)).setText(msg);
            }
        });
    }

    public void checkDevice() {
        if (mUsbManager == null ) {
            log("checkDevice");
            HashMap<String, UsbDevice> deviceList = mUsbManager.getDeviceList();
            for (UsbDevice _device : deviceList.values()) {
                if (_device.getDeviceId() == 0x16d1) {
                    // Suprema vendor ID
                    mUsbManager.requestPermission(_device, mPermissionIntent);
                }
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_print);
        mainContext = this;

        mCaptureOptionDefault.frameRate = IBioMiniDevice.FrameRate.SHIGH;

        if (mBioMiniFactory != null ) {
            mBioMiniFactory.close();
        }

        if( !mbUsbExternalUSBManager ){
//            Button btn_checkDevice = (Button)findViewById(R.id.buttonCheckDevice);
//            btn_checkDevice.setClickable(false);
//            btn_checkDevice.setEnabled(false);
        }else{
//            ((Button)findViewById(R.id.buttonCheckDevice)).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    checkDevice();
//                }
//            });
        }

        restartBioMini();

        printRev(""+mBioMiniFactory.getSDKInfo());
        bindComponents();

    }

    private void bindComponents() {

    }

    private void restartBioMini() {

    }

    @Override
    public void onDeviceConnectionSuccess() {

    }

    @Override
    public void onDeviceConnectionError() {

    }
}
