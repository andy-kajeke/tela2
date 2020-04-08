package com.planetsystems.tela.activities.fingerprint;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
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
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStoreOwner;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.FingerPrintCaptureResponder;
import com.planetsystems.tela.activityViewModel.MainActivityViewModel;
import com.suprema.BioMiniFactory;
import com.suprema.IBioMiniDevice;
import com.suprema.IUsbEventHandler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;

public class FingerPrintActivity extends Activity implements FingerPrintCaptureResponder.OnFingerPrintCaptureResponseListener{
    public static final String FIRST_NAME = "com.planetsystems.tela.activities.fingerprint.FingerPrintActivity.FIRST_NAME";
    public static final String LAST_NAME = "com.planetsystems.tela.activities.fingerprint.FingerPrintActivity.LAST_NAME";
    public static final String INITIALS = "com.planetsystems.tela.activities.fingerprint.FingerPrintActivity.INITIALS";
    public static final String PHONE_NUMBER = "com.planetsystems.tela.activities.fingerprint.FingerPrintActivity.PHONE_NUMBER";
    public static final String EMAIL_ADDRESS = "com.planetsystems.tela.activities.fingerprint.FingerPrintActivity.EMAIL_ADDRESS";
    public static final String NATIONAL_ID = "com.planetsystems.tela.activities.fingerprint.FingerPrintActivity.NATIONAL_ID";
    public static final String GENDER = "com.planetsystems.tela.activities.fingerprint.FingerPrintActivity.GENDER";
    public static final String ACTION_ENROLL = "com.planetsystems.tela.activities.fingerprint.FingerPrintActivity.ACTION_ENROLL";
    public static final String ACTION_CLOCK_IN = "com.planetsystems.tela.activities.fingerprint.FingerPrintActivity.ACTION_CLOCK_IN";
    public static final String ACTION_CLOCK_OUT = "com.planetsystems.tela.activities.fingerprint.FingerPrintActivity.ACTION_CLOCK_OUT";
    public static final String FINGER_PRINT_DATA = "com.planetsystems.tela.activities.fingerprint.FingerPrintActivity.FINGER_PRINT_DATA";
    public static final String FINGER_PRINT_IMAGE = "com.planetsystems.tela.activities.fingerprint.FingerPrintActivity.FINGER_PRINT_IMAGE";

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
    private TextView statusTextView;
    private ScrollView mScrollLog = null;
    private Intent startActivityIntent;
    private IBioMiniDevice.TemplateData capturedTemplateData;
    private Bitmap capturedImageData;
    private FingerPrintActivityViewModel printActivityViewModel;

    private IBioMiniDevice.CaptureOption mCaptureOptionDefault = new IBioMiniDevice.CaptureOption();

    synchronized public void printState(final CharSequence str){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                statusTextView.setText(str);
            }
        });
    }

    synchronized public void log(final String msg) {
    }

    synchronized public void printRev(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                ((TextView) findViewById(R.id.revText)).setText(msg);
            }
        });
    }

    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver(){
        public void onReceive(Context context, Intent intent){
            String action = intent.getAction();
            if(ACTION_USB_PERMISSION.equals(action)){
                synchronized(this){
                    UsbDevice device = (UsbDevice)intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if(intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)){
                        if(device != null){
                            if( mBioMiniFactory == null) return;
                            mBioMiniFactory.addDevice(device);
                            log(String.format(Locale.ENGLISH ,"Initialized device count- BioMiniFactory (%d)" , mBioMiniFactory.getDeviceCount() ));
                        }
                    }
                    else{
                        Log.d(TAG, "permission denied for device"+ device);
                    }
                }
            }
        }
    };

    public void checkDevice(){
        if(mUsbManager == null) return;
        log("checkDevice");
        HashMap<String , UsbDevice> deviceList = mUsbManager.getDeviceList();
        Iterator<UsbDevice> deviceIter = deviceList.values().iterator();
        while(deviceIter.hasNext()){
            UsbDevice _device = deviceIter.next();
            if( _device.getVendorId() ==0x16d1 ){
                //Suprema vendor ID
                Toast.makeText(this, "Requesting permission", Toast.LENGTH_LONG).show();
                mUsbManager.requestPermission(_device , mPermissionIntent);
            }else{
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_print);
        setTitle(R.string.capture);

        mainContext = this;
        statusTextView = findViewById(R.id.textViewStatus);
        mCaptureOptionDefault.frameRate = IBioMiniDevice.FrameRate.SHIGH;
        startActivityIntent = getIntent();


        if(mBioMiniFactory != null) {
            mBioMiniFactory.close();
        }

        restartBioMini();

        printRev(""+mBioMiniFactory.getSDKInfo());
        if (startActivityIntent.getAction() == ACTION_ENROLL) {
            findViewById(R.id.cardViewCapture).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((ImageView) findViewById(R.id.imageViewFingerPrint)).setImageBitmap(null);
                    if(mCurrentDevice != null) {
                        //mCaptureOptionDefault.captureTimeout = (int)mCurrentDevice.getParameter(IBioMiniDevice.ParameterType.TIMEOUT).value;
                        mCurrentDevice.captureSingle(
                                mCaptureOptionDefault,
                                new FingerPrintCaptureResponder(mainContext),
                                true);
                    }


                }
            });

            findViewById(R.id.cardViewAction).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (capturedTemplateData == null ) {
                        Toast.makeText(FingerPrintActivity.this, "Please take fingerprint to proceed", Toast.LENGTH_LONG).show();
                        setResult(RESULT_CANCELED);
                    } else {
                        Intent intent = new Intent();
                        intent.putExtra(FIRST_NAME, startActivityIntent.getStringExtra(FIRST_NAME));
                        intent.putExtra(LAST_NAME, startActivityIntent.getStringExtra(LAST_NAME));
                        intent.putExtra(INITIALS, startActivityIntent.getStringExtra(INITIALS));
                        intent.putExtra(EMAIL_ADDRESS, startActivityIntent.getStringExtra(EMAIL_ADDRESS));
                        intent.putExtra(PHONE_NUMBER, startActivityIntent.getStringExtra(PHONE_NUMBER));
                        intent.putExtra(NATIONAL_ID, startActivityIntent.getStringExtra(NATIONAL_ID));
                        intent.putExtra(GENDER, startActivityIntent.getStringExtra(GENDER));
                        intent.putExtra(FINGER_PRINT_DATA, capturedTemplateData.data);
                        intent.putExtra(FINGER_PRINT_IMAGE, capturedImageData);
                        setResult(RESULT_OK, intent);
                    }
                    finish();
                }
            });
        }

        if (Objects.equals(startActivityIntent.getAction(), ACTION_CLOCK_IN)) {
//            clock in
            if (mCurrentDevice != null ) {
                mCurrentDevice.captureSingle(
                        mCaptureOptionDefault,
                        new FingerPrintCaptureResponder(mainContext),
                        true);
            }
            Toast.makeText(FingerPrintActivity.this, "Device not connected", Toast.LENGTH_LONG).show();
            finish();
        }

        if (Objects.equals(startActivityIntent.getAction(), ACTION_CLOCK_OUT)) {
//            clock in
            Toast.makeText(FingerPrintActivity.this, "Action Clock out", Toast.LENGTH_LONG).show();
            mCurrentDevice.captureSingle(
                    mCaptureOptionDefault,
                    new FingerPrintCaptureResponder(mainContext),
                    true);
        }
    }

    void handleDevChange(IUsbEventHandler.DeviceChangeEvent event, Object dev) {
        if (event == IUsbEventHandler.DeviceChangeEvent.DEVICE_ATTACHED && mCurrentDevice == null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int cnt = 0;
                    while (mBioMiniFactory == null && cnt < 20) {
                        SystemClock.sleep(1000);
                        cnt++;
                    }
                    if (mBioMiniFactory != null) {
                        mCurrentDevice = mBioMiniFactory.getDevice(0);
                        printState(getResources().getText(R.string.device_attached));
                        Log.d(TAG, "mCurrentDevice attached : " + mCurrentDevice);
                        if (mCurrentDevice != null /*&& mCurrentDevice.getDeviceInfo() != null*/) {
                            log(" DeviceName : " + mCurrentDevice.getDeviceInfo().deviceName);
                            log("         SN : " + mCurrentDevice.getDeviceInfo().deviceSN);
                            log("SDK version : " + mCurrentDevice.getDeviceInfo().versionSDK);
                        }
                    }
                }
            }).start();
        } else if (mCurrentDevice != null && event == IUsbEventHandler.DeviceChangeEvent.DEVICE_DETACHED && mCurrentDevice.isEqual(dev)) {
            printState(getResources().getText(R.string.device_detached));
            Log.d(TAG, "mCurrentDevice removed : " + mCurrentDevice);
            mCurrentDevice = null;
        }
    }

    void restartBioMini() {
        if(mBioMiniFactory != null) {
            mBioMiniFactory.close();
        }
        if( mbUsbExternalUSBManager ){
            mUsbManager = (UsbManager)getSystemService(Context.USB_SERVICE);
            mBioMiniFactory = new BioMiniFactory(mainContext, mUsbManager){
                @Override
                public void onDeviceChange(DeviceChangeEvent event, Object dev) {
                    log("----------------------------------------");
                    log("onDeviceChange : " + event + " using external usb-manager");
                    log("----------------------------------------");
                    handleDevChange(event, dev);
                }
            };
            //
            mPermissionIntent = PendingIntent.getBroadcast(this,0,new Intent(ACTION_USB_PERMISSION),0);
            IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
            registerReceiver(mUsbReceiver, filter);
            checkDevice();
        }else {
            mBioMiniFactory = new BioMiniFactory(mainContext) {
                @Override
                public void onDeviceChange(DeviceChangeEvent event, Object dev) {
                    log("----------------------------------------");
                    log("onDeviceChange : " + event);
                    log("----------------------------------------");
                    handleDevChange(event, dev);
                }
            };
        }
    }

    @Override
    protected void onDestroy() {
        if (mBioMiniFactory != null) {
            mBioMiniFactory.close();
            mBioMiniFactory = null;
        }
        if( mbUsbExternalUSBManager ){
            unregisterReceiver(mUsbReceiver);
        }
        super.onDestroy();
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},  REQUEST_WRITE_PERMISSION);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            log("permission granted");
        }
    }
    @Override
    public void onPostCreate(Bundle savedInstanceState){
        requestPermission();
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onFingerPrintCaptureResponseCapture(Object contest, IBioMiniDevice.FingerState fingerState) {

    }

    @Override
    public boolean onFingerPrintCaptureResponseCaptureEx(Object contest, Bitmap bitmap, IBioMiniDevice.TemplateData templateData, IBioMiniDevice.FingerState fingerState) {
        if (Objects.equals(startActivityIntent.getAction(), ACTION_ENROLL)) {
            log("onCapture : Capture successful!");
            printState(getResources().getText(R.string.capture_single_ok));
            capturedTemplateData = templateData;
            capturedImageData = bitmap;

            log(((IBioMiniDevice) contest).popPerformanceLog());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (capturedImageData != null) {
                        ImageView iv = (ImageView) findViewById(R.id.imageViewFingerPrint);
                        if (iv != null) {
                            iv.setImageBitmap(capturedImageData);
                        }
                    }
                }
            });
        }

        if (Objects.equals(startActivityIntent.getAction(), ACTION_CLOCK_IN )){
            // clock in here
        }

        if (Objects.equals(startActivityIntent.getAction(), ACTION_CLOCK_OUT)) {
            // clock out
        }

        return true;
    }

    @Override
    public void onFingerPrintCaptureResponseCaptureError(Object contest, int errorCode, String errorMessage) {
        log("onCaptureError : " + errorCode + " ErrorCode :" + errorCode);
        if( errorCode != IBioMiniDevice.ErrorCode.OK.value())
            printState(getResources().getText(R.string.capture_single_fail) + "("+errorMessage+")");
    }
}
