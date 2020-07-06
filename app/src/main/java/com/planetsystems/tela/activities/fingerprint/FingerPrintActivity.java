package com.planetsystems.tela.activities.fingerprint;

import android.annotation.SuppressLint;
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

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProviders;

import com.planetsystems.tela.MainRepository;
import com.planetsystems.tela.R;
import com.planetsystems.tela.data.ClockIn.ClockInRepository;
import com.planetsystems.tela.data.ClockIn.SyncClockIn;
import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.Teacher.TeacherRepository;
import com.planetsystems.tela.data.TelaRoomDatabase;
import com.planetsystems.tela.data.clockOut.ClockOutRepository;
import com.planetsystems.tela.data.clockOut.SyncClockOut;
import com.planetsystems.tela.data.logs.ExecutionLog;
import com.planetsystems.tela.data.logs.ExecutionLogRepository;
import com.planetsystems.tela.utils.BitmapConverter;
import com.planetsystems.tela.utils.DynamicData;
import com.suprema.BioMiniFactory;
import com.suprema.CaptureResponder;
import com.suprema.IBioMiniDevice;
import com.suprema.IUsbEventHandler;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class FingerPrintActivity extends Activity {
    public static final String FINGER_PRINT_DATA = "com.planetsystems.tela.activities.fingerprint.FingerPrintActivity.TEMPLATE_DATA";
    public static final String FINGER_PRINT_IMAGE = "com.planetsystems.tela.activities.fingerprint.FingerPrintActivity.FINGER_PRINT_IMAGE";
    public static final String ACTION_ENROLL = "com.planetsystems.tela.activities.fingerprint.FingerPrintActivity.ACTION_ENROLL";
    public static final String ACTION_CLOCK_OUT = "com.planetsystems.tela.activities.fingerprint.FingerPrintActivity.ACTION_CLOCK_OUT";
    public static final String ACTION_CLOCK_IN = "com.planetsystems.tela.activities.fingerprint.FingerPrintActivity.ACTION_CLOCK_IN";
    public static final String TEACHER_FIRST_NAME = "com.planetsystems.tela.activities.fingerprint.FingerPrintActivity.TEACHER_FIRST_NAME";
    public static final String TEACHER_LAST_NAME = "com.planetsystems.tela.activities.fingerprint.FingerPrintActivity.TEACHER_LAST_NAME";
    public static final String TEACHER_EMAIL = "com.planetsystems.tela.activities.fingerprint.FingerPrintActivity.TEACHER_EMAIL";
    public static final String TEACHER_GENDER = "com.planetsystems.tela.activities.fingerprint.FingerPrintActivity.TEACHER_GENDER";
    public static final String TEACHER_PHONE_NUMBER = "com.planetsystems.tela.activities.fingerprint.FingerPrintActivity.TEACHER_PHONE_NUMBER";
    public static final String TEACHER_NATIONAL_ID = "com.planetsystems.tela.activities.fingerprint.FingerPrintActivity.TEACHER_NATION_ID";
    public static final String TEACHER_LICENSED = "com.planetsystems.tela.activities.fingerprint.FingerPrintActivity.TEACHER_LICENSED";
    public static final String TEACHER_INITIALS = "com.planetsystems.tela.activities.fingerprint.FingerPrintActivity.INITIALS";
    public static final String TEACHER_ROLE = "com.planetsystems.tela.activities.fingerprint.FingerPrintActivity.TEACHER_ROLE";
    public static final String TEACHER_COMMENT = "com.planetsystems.tela.activities.fingerprint.FingerPrintActivity.COMMENT";
    public static final String EMPLOYEEE_NUMBER = "com.planetsystems.tela.activities.fingerprint.FingerPrintActivity.EMPLOYEE_NUMBER";

    public static final boolean mbUsbExternalUSBManager = false;
    private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";
    private UsbManager mUsbManager = null;
    private PendingIntent mPermissionIntent = null;

    private static BioMiniFactory mBioMiniFactory = null;
    public static final int REQUEST_WRITE_PERMISSION = 786;
    public IBioMiniDevice mCurrentDevice = null;
    private FingerPrintActivity mainContext;
    private IBioMiniDevice.TemplateData capturedTemplateData = null;
    private Bitmap captureBitmapImage = null;
    private CardView cardViewCapture;
    private TextView textViewCapture;
    private CardView cardViewEnroll;
    private TextView textViewEnroll;


    public final String className = getClass().getSimpleName();

    private IBioMiniDevice.CaptureOption mCaptureOptionDefault = new IBioMiniDevice.CaptureOption();
    private CaptureResponder mCaptureResponseDefault = new CaptureResponder() {
        @Override
        public void onCapture(Object o, IBioMiniDevice.FingerState fingerState) {
            super.onCapture(o, fingerState);
        }

        @Override
        public boolean onCaptureEx(Object context, final Bitmap capturedImage, final IBioMiniDevice.TemplateData templateData, IBioMiniDevice.FingerState fingerState) {
            logExecutionMessage("Captured Fingerprint Successfully: " + Arrays.toString(templateData.data) + " Length: " + String.valueOf(templateData.data.length),
                    "line 34", "onCapureEx", "No data");
            printState(getResources().getText(R.string.capture_single_ok));
            logExecutionMessage(((IBioMiniDevice)context).popPerformanceLog(), null, null, null);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (capturedImage != null) {
                        capturedTemplateData = templateData;
                        captureBitmapImage = capturedImage;


                        ImageView imageView = findViewById(R.id.imageViewFingerPrint);
                        if (imageView != null) {
                            imageView.setImageBitmap(capturedImage);
                        }
                    }
                }
            });
            return true;
        }

        @Override
        public void onCaptureError(Object context, int errorCode, String errorMessage) {
            logExecutionMessage("Error occurred: " + errorMessage + " Error Code" + errorCode, null, null, null);
            if (errorCode != IBioMiniDevice.ErrorCode.OK.value()) {
                printState(getResources().getText(R.string.capture_single_fail) + " ( " + errorMessage + " ) ");
            }
        }
    };

    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ACTION_USB_PERMISSION.equals(action)) {
                synchronized (this) {
                    UsbDevice device = (UsbDevice)intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        if (device != null) {
                            mBioMiniFactory.addDevice(device);
                            logExecutionMessage(String.format(Locale.ENGLISH, "Initialized device count - BioMiniFactory (%)",
                                    mBioMiniFactory.getDeviceCount()), null, null, null);
                        }
                    } else {
                        logExecutionMessage("Permission Denied for Device: " + device, null, null, null);
                    }
                }
            }
        }
    };

    public void checkDevice() {
        if (mUsbManager == null ) return;
        logExecutionMessage("Checking Device", null, null, null);
        HashMap<String, UsbDevice> deviceList = mUsbManager.getDeviceList();
        for (UsbDevice _device : deviceList.values()) {
            if (_device.getVendorId() == 0x16d1) {
                mUsbManager.requestPermission(_device, mPermissionIntent);
                logExecutionMessage("Device Found And Permission Requested", null, null, null);
            }
        }
    }

    private void printState(final CharSequence text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((TextView) findViewById(R.id.textViewStatus)).setText(text);
            }
        });
    }

    synchronized private void logExecutionMessage(String message, String lineNumber, String methodName, String data) {
        ExecutionLog executionLog = new ExecutionLog(
                message,
                new Date().toString(),
                DynamicData.getTime(),
                getClass().getSimpleName(),
                DynamicData.getSchoolID(),
                DynamicData.getSchoolName(),
                data,
                lineNumber,
                methodName
        );
        ExecutionLogRepository.getInstance(TelaRoomDatabase.getInstance(this)).logMessage(executionLog);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_print);
        mainContext = this;
        mCaptureOptionDefault.frameRate = IBioMiniDevice.FrameRate.SHIGH;

        cardViewCapture = findViewById(R.id.cardViewCapture);
        textViewCapture = findViewById(R.id.textViewCapture);
        cardViewEnroll = findViewById(R.id.cardViewEnroll);
        textViewEnroll = findViewById(R.id.textViewEnroll);

        if (Objects.equals(getIntent().getAction(), ACTION_ENROLL)) {
            textViewEnroll.setText("Enroll");
        }

        if (getIntent().getAction().equals(ACTION_CLOCK_IN)) {
            textViewEnroll.setText("Clock In");
        }

        if (getIntent().getAction().equals(ACTION_CLOCK_OUT)) {
            textViewEnroll.setText("Clock Ou");
        }

    }
}