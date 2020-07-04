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
import com.planetsystems.tela.utils.BitmapConverter;
import com.planetsystems.tela.utils.DynamicData;
import com.suprema.BioMiniFactory;
import com.suprema.IBioMiniDevice;
import com.suprema.IUsbEventHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class FingerPrintActivity extends Activity implements FingerPrintCaptureResponder.OnFingerPrintCaptureResponseListener{
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









    //Flag.
    public static final boolean mbUsbExternalUSBManager = false;
    private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";
    private UsbManager mUsbManager = null;
    private PendingIntent mPermissionIntent= null;
    //
    private BioMiniFactory mBioMiniFactory = null;
    public static final int REQUEST_WRITE_PERMISSION = 786;
    public IBioMiniDevice mCurrentDevice = null;
    private FingerPrintActivity mainContext;

    public final static String TAG = "BioMini Sample";
    private TextView statusTextView;
    private ScrollView mScrollLog = null;
    private Intent startActivityIntent;
    private Intent incomingIntent;
    private IBioMiniDevice.TemplateData capturedTemplateData;
    private Bitmap capturedImageData;
    private FingerPrintActivityViewModel printActivityViewModel;

    private CardView cardViewCapture, cardViewEnroll;
    private TextView textViewCapture, textViewEnroll;
    private ImageView fingerprintImageView;
    private TeacherRepository teacherRepository;
    private List<SyncTeacher> syncTeachers;
    private SyncTeacher syncTeacher;
    private List<SyncClockIn> syncClockIns;

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
        for (UsbDevice _device : deviceList.values()) {
            if (_device.getVendorId() == 0x16d1) {
                //Suprema vendor ID
                Toast.makeText(this, "Requesting permission", Toast.LENGTH_LONG).show();
                mUsbManager.requestPermission(_device, mPermissionIntent);
            }
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        incomingIntent = getIntent();
        teacherRepository = MainRepository.getInstance(getApplication()).getTeachersRepository();
        syncTeachers = teacherRepository.getTeachers();


        setContentView(R.layout.activity_finger_print);
        setTitle(R.string.capture);

        mainContext = this;
        statusTextView = findViewById(R.id.textViewStatus);
        mCaptureOptionDefault.frameRate = IBioMiniDevice.FrameRate.SHIGH;
        cardViewCapture = findViewById(R.id.cardViewCapture);
        cardViewEnroll = findViewById(R.id.cardViewEnroll);
        textViewCapture = findViewById(R.id.textViewCapture);
        textViewEnroll = findViewById(R.id.textViewEnroll);
        fingerprintImageView = findViewById(R.id.imageViewFingerPrint);
        cardViewCapture.setVisibility(View.GONE);



        if(mBioMiniFactory != null) {
            mBioMiniFactory.close();
        }

        restartBioMini();
        if (Objects.equals(getIntent().getAction(), ACTION_CLOCK_OUT)) {
            // removed enroll button and change the with or cap
            textViewEnroll.setText(R.string.clock_out);
        }

        if (Objects.equals(getIntent().getAction(), ACTION_CLOCK_IN)) {
            textViewEnroll.setText("Clock In");
        }

        cardViewCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentDevice != null) {
                    //mCaptureOptionDefault.captureTimeout = (int)mCurrentDevice.getParameter(IBioMiniDevice.ParameterType.TIMEOUT).value;
                    mCurrentDevice.captureSingle(
                            mCaptureOptionDefault,
                            new FingerPrintCaptureResponder(mainContext),
                            true
                    );
                }
            }
        });

        cardViewEnroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                syncTeachers = teacherRepository.getTeachers();
                //if (capturedTemplateData != null && capturedImageData != null ) {

                    if (Objects.equals(getIntent().getAction(), ACTION_ENROLL)) {
                        // TODO enroll
                        try {
                            SyncTeacher syncTeacher1 = teacherRepository.getTeacherWithFingerPrint(capturedTemplateData.data);
                            SyncTeacher syncTeacher2 = teacherRepository.getTeacherWithNationalID(incomingIntent.getStringExtra(TEACHER_NATIONAL_ID));
                            syncTeacher = (syncTeacher1 != null ) || (syncTeacher2 != null )? syncTeacher2: null;
                            if (syncTeacher == null ) {
                                SyncTeacher syncTeacher = new SyncTeacher.Builder()
                                        .setDOB(null)
                                        .setEmailAddress(incomingIntent.getStringExtra(TEACHER_EMAIL))
                                        .setLastName(incomingIntent.getStringExtra(TEACHER_LAST_NAME))
                                        .setFirstName(incomingIntent.getStringExtra(TEACHER_FIRST_NAME))
                                        //.setFingerImage(BitmapConverter.encodeBitmapToBase64(capturedImageData))
                                        .setFingerPrint(capturedTemplateData.data)
                                        .setGender(incomingIntent.getStringExtra(TEACHER_GENDER))
                                        .setPhoneNumber(incomingIntent.getStringExtra(TEACHER_PHONE_NUMBER))
                                        .setNationalID(incomingIntent.getStringExtra(TEACHER_NATIONAL_ID))
                                        .setLicensed(incomingIntent.getBooleanExtra(TEACHER_LICENSED, false))
                                        .setInitials(incomingIntent.getStringExtra(TEACHER_INITIALS))
                                        .setRole("Teacher")
                                        .setDOB(new Date().toString())
                                        .setSchoolID(DynamicData.getSchoolID())
                                        .build();
                                teacherRepository.insertSyncTeacher(syncTeacher);
                                Toast.makeText(FingerPrintActivity.this, "Teacher Enrolled Successfully ", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(FingerPrintActivity.this, "Teacher Already Enrolled", Toast.LENGTH_SHORT).show();
                            }
                        } catch (InterruptedException | ExecutionException e) {
                            Toast.makeText(FingerPrintActivity.this, "Error Enrolling Teacher", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    } else if (Objects.equals(getIntent().getAction(), ACTION_CLOCK_IN)) {
                        clockInTeacher();

                    } else if (Objects.equals(getIntent().getAction(), ACTION_CLOCK_OUT)) {
                        clockOutTeacher();
                    }

//                } else {
//                    Toast.makeText(FingerPrintActivity.this, "No Fingerprint was Captured", Toast.LENGTH_SHORT).show();
//                }
            }
        });

        printRev(""+mBioMiniFactory.getSDKInfo());
        // start capturing fingerprint on spot
        if(mCurrentDevice != null) {
            //mCaptureOptionDefault.captureTimeout = (int)mCurrentDevice.getParameter(IBioMiniDevice.ParameterType.TIMEOUT).value;
            mCurrentDevice.captureSingle(
                    mCaptureOptionDefault,
                    new FingerPrintCaptureResponder(mainContext),
                    true
            );
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
            log("onCapture : Capture successful!");
            printState(getResources().getText(R.string.capture_single_ok));
            capturedTemplateData = templateData;
            capturedImageData = bitmap;

            log(((IBioMiniDevice) contest).popPerformanceLog());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (capturedImageData != null) {
                        fingerprintImageView.setImageBitmap(capturedImageData);
                    }
                }
            });
        return true;
    }

    @Override
    public void onFingerPrintCaptureResponseCaptureError(Object contest, int errorCode, String errorMessage) {
        log("onCaptureError : " + errorCode + " ErrorCode :" + errorCode);
        if( errorCode != IBioMiniDevice.ErrorCode.OK.value())
            printState(getResources().getText(R.string.capture_single_fail) + "("+errorMessage+")");
        cardViewCapture.setVisibility(View.VISIBLE);
    }

    public void saveClockIn(SyncClockIn clockIn, byte[] finger, ClockInRepository clockInRepository, SyncTeacher syncTeacher) {
        if (clockIn == null) {
            clockInRepository.synClockInTeacher(new SyncClockIn(
                    syncTeacher.getEmployeeNumber(),
                    syncTeacher.getEmployeeNumber(),
                    syncTeacher.getFirstName(),
                    syncTeacher.getLastName(),
                    DynamicData.getLatitude(),
                    DynamicData.getLongitude(),
                    DynamicData.getDate(),
                    DynamicData.getDay(),
                    DynamicData.getTime(),
                    DynamicData.getSchoolID(),
                    finger
            ));
            Toast.makeText(FingerPrintActivity.this, "Clocked In SuccessFully", Toast.LENGTH_SHORT).show();
        } else  {
            Toast.makeText(FingerPrintActivity.this, "Teacher Already Clocked In", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent();
        intent.putExtra(TEACHER_ROLE, syncTeacher.getRole());
        setResult(RESULT_OK, intent);
        finish();
    }

    private void clockInTeacher() {
        final ClockInRepository clockInRepository = ClockInRepository.getInstance(TelaRoomDatabase.getInstance(getApplicationContext()));
        byte[] finger = new byte[30];
        int i = 0;
        while (i < 20) {
            finger[i] = Byte.parseByte(String.valueOf(i * 3 + 20));
            i++;
        }
        try {
            SyncTeacher syncTeacher = teacherRepository.getTeacherWithFingerPrint(finger);
            if (syncTeacher != null) {
                try {
                    if (syncTeacher.getEmployeeNumber() == null) {
                        SyncClockIn clockIn = clockInRepository.getSyncClockInByFingerPrintAndDate(syncTeacher.getFingerPrint(), DynamicData.getDate());
                        saveClockIn(clockIn, finger, clockInRepository, syncTeacher); // save clock in finger print
                    } else {
                        SyncClockIn clockIn = clockInRepository.getSyncClockInByEmployeeIDAndDate(syncTeacher.getEmployeeNumber(), DynamicData.getDate());
                        saveClockIn(clockIn, finger, clockInRepository, syncTeacher); // clock teacher since there is no clock in today
                    }
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                    Toast.makeText(FingerPrintActivity.this, "There was Errors", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(FingerPrintActivity.this, "No Records Found", Toast.LENGTH_SHORT).show();
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void clockOutTeacher() {
        final ClockOutRepository clockOutRepository = ClockOutRepository.getInstance(TelaRoomDatabase.getInstance(getApplicationContext()));
        byte[] finger = new byte[30];
        int i = 0;
        while (i < 20) {
            finger[i] = Byte.parseByte(String.valueOf(i * 3 + 20));
            i++;
        }
        try {
            SyncTeacher syncTeacher = teacherRepository.getTeacherWithFingerPrint(finger);
            if (syncTeacher != null) {
                try {
                    if (syncTeacher.getEmployeeNumber() == null) {
                        SyncClockOut clockOut = clockOutRepository.getSyncClockOutByFingerPrintAndDate(syncTeacher.getFingerPrint(), DynamicData.getDate());
                        saveClockOut(clockOut, finger, clockOutRepository, syncTeacher); // save clock in finger print
                    } else {
                        SyncClockOut clockOut = clockOutRepository.getSyncClockOutByEmployeeNumberAndDate(syncTeacher.getEmployeeNumber(), DynamicData.getDate());
                        saveClockOut(clockOut, finger, clockOutRepository, syncTeacher); // clock teacher since there is no clock in today
                    }
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                    Toast.makeText(FingerPrintActivity.this, "There was Errors", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(FingerPrintActivity.this, "No Records Found", Toast.LENGTH_SHORT).show();
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void saveClockOut(SyncClockOut clockOut, byte[] finger, ClockOutRepository clockOutRepository, SyncTeacher teacher) {
        if (clockOut == null) {
            clockOutRepository.insertSynClockOut(new SyncClockOut(
                    DynamicData.getDate(),
                    DynamicData.getDay(),
                    DynamicData.getTime(),
                    getIntent().getStringExtra(TEACHER_COMMENT),
                    teacher.getEmployeeNumber(),
                    teacher.getEmployeeNumber(),
                    DynamicData.getLatitude(),
                    DynamicData.getLongitude(),
                    DynamicData.getSchoolID(),
                    DynamicData.getSchoolName(),
                    teacher.getFirstName(),
                    teacher.getLastName()
            ));
            Toast.makeText(FingerPrintActivity.this, "Clocked Out SuccessFully", Toast.LENGTH_SHORT).show();
        } else  {
            Toast.makeText(FingerPrintActivity.this, "Teacher Already Clocked In", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent();
        intent.putExtra(TEACHER_ROLE, syncTeacher.getRole());
        setResult(RESULT_OK, intent);
        finish();
    }
}
