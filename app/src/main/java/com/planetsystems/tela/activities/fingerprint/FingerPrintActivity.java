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
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.planetsystems.tela.MainRepository;
import com.planetsystems.tela.R;
import com.planetsystems.tela.constants.Role;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import static com.planetsystems.tela.activities.mainActivity.MainActivity.SchoolDeviceIMEINumber;

public class FingerPrintActivity extends Activity{
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
    private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";
    public static final String EMPLOYEEE_NUMBER = "com.planetsystems.tela.activities.fingerprint.FingerPrintActivity.EMPLOYEE_NUMBER";

    //Flag.
    public static final boolean mbUsbExternalUSBManager = false;
    private UsbManager mUsbManager = null;
    private PendingIntent mPermissionIntent= null;
    private ExecutionLogRepository executionLogRepository;
    private TeacherRepository teacherRepository;
    private ClockInRepository clockInRepository;
    private ClockOutRepository clockOutRepository;
    //

    private CardView cardViewEnroll = null;
    private TextView textViewEnroll = null;
    private CardView cardViewCapture = null;
    private TextView textViewCapture = null;
    private CardView cardViewImage = null;


    private static BioMiniFactory mBioMiniFactory = null;
    public static final int REQUEST_WRITE_PERMISSION = 786;
    public IBioMiniDevice mCurrentDevice = null;
    private FingerPrintActivity mainContext;

    public final static String TAG = "BioMini Sample";
    private ScrollView mScrollLog = null;

    synchronized public void printState(final CharSequence str){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((TextView)findViewById(R.id.textViewStatus)).setText(str);
            }
        });

    }

    synchronized public void printRev(final String msg) {

    }


    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver(){
        public void onReceive(Context context,Intent intent){
            String action = intent.getAction();
            if(ACTION_USB_PERMISSION.equals(action)){
                synchronized(this){
                    UsbDevice device = (UsbDevice)intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if(intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)){
                        if(device != null){
                            if( mBioMiniFactory == null) return;
                            mBioMiniFactory.addDevice(device);
                            String message = String.format(Locale.ENGLISH ,"Initialized device count- BioMiniFactory (%d)" , mBioMiniFactory.getDeviceCount() );
                            logMessage(message, String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
                            }.getClass().getEnclosingMethod()).getName());
                        }
                    }
                    else{
                        String message = "permission denied for device"+ device;
                        logMessage(message, String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
                        }.getClass().getEnclosingMethod()).getName());
                    }
                }
            }
        }
    };
    public void checkDevice(){
        if(mUsbManager == null) return;
        String message = "checkDevice";
        logMessage(message, String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
        }.getClass().getEnclosingMethod()).getName());
        HashMap<String , UsbDevice> deviceList = mUsbManager.getDeviceList();
        for (UsbDevice _device : deviceList.values()) {
            if (_device.getVendorId() == 0x16d1) {
                //Suprema vendor ID
                mUsbManager.requestPermission(_device, mPermissionIntent);
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_print);
        cardViewEnroll = findViewById(R.id.cardViewEnroll);
        cardViewCapture = findViewById(R.id.cardViewCapture);
        textViewEnroll = findViewById(R.id.textViewEnroll);
        textViewCapture = findViewById(R.id.textViewCapture);
        cardViewImage = findViewById(R.id.cardViewFingerPrint);
        executionLogRepository = ExecutionLogRepository.getInstance(TelaRoomDatabase.getInstance(this));
        teacherRepository = TeacherRepository.getInstance(TelaRoomDatabase.getInstance(this));

        if(Objects.equals(getIntent().getAction(), ACTION_CLOCK_IN)) {
            clockInRepository = ClockInRepository.getInstance(TelaRoomDatabase.getInstance(this));
        }

        if (Objects.equals(getIntent().getAction(), ACTION_CLOCK_OUT)) {
            clockOutRepository = ClockOutRepository.getInstance(TelaRoomDatabase.getInstance(this));
            clockInRepository = ClockInRepository.getInstance(TelaRoomDatabase.getInstance(this));
        }


        mainContext = this;
        cardViewEnroll.setVisibility(View.GONE);


        cardViewCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentDevice != null) {
                    ((ImageView) findViewById(R.id.imageViewFingerPrint)).setImageBitmap(null);
                    IBioMiniDevice.CaptureOption option = new IBioMiniDevice.CaptureOption();
                    option.extractParam.captureTemplate = true;
                    option.captureTemplate = true; //deprecated
                    //option.frameRate = IBioMiniDevice.FrameRate.ELOW;
                    // capture fingerprint image
                    mCurrentDevice.captureSingle(option,
                            new CaptureResponder() {
                                @Override
                                public boolean onCaptureEx(final Object context, final Bitmap capturedImage,
                                                           final IBioMiniDevice.TemplateData capturedTemplate,
                                                           final IBioMiniDevice.FingerState fingerState) {
                                    String message = "Captured fingerprint: " + Arrays.toString(capturedTemplate.data)
                                            + " of Size: " + String.valueOf(capturedTemplate.data.length);

                                    logMessage(message, String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
                                    }.getClass().getEnclosingMethod()).getName());

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            cardViewImage.setCardBackgroundColor(getColor(R.color.colorDeviceCapturedSuccess));
                                            if(capturedImage != null) {
                                                ImageView iv = (ImageView) findViewById(R.id.imageViewFingerPrint);
                                                if(iv != null) {
                                                    iv.setImageBitmap(capturedImage);
                                                    Toast.makeText(FingerPrintActivity.this, "Success image set", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }
                                    });
                                    message = "Enroll or Clock or Clock out";

                                    logMessage(message, String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
                                    }.getClass().getEnclosingMethod()).getName());
                                    message = ((IBioMiniDevice)context).popPerformanceLog();
                                    logMessage(message, String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
                                    }.getClass().getEnclosingMethod()).getName());

                                    if (Objects.equals(getIntent().getAction(), ACTION_ENROLL)) {
                                        enrollTeacher(capturedTemplate.data, capturedImage);
                                    }

                                    if (getIntent().getAction().equals(ACTION_CLOCK_IN)) {
                                        clockInTeacher(capturedTemplate.data);
                                    }

                                    if (getIntent().getAction().equals(ACTION_CLOCK_OUT)) {
                                        clockOutTeacher(capturedTemplate.data);
                                    }

                                    return true;
                                }

                                @Override
                                public void onCaptureError(Object context, int errorCode, String error) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            cardViewImage.setCardBackgroundColor(getColor(R.color.colorDeviceCapturedError));
                                        }
                                    });
                                    String message = "onCaptureError : " + error;
                                    logMessage(message, String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
                                    }.getClass().getEnclosingMethod()).getName());
                                    Toast.makeText(FingerPrintActivity.this, "There was error here", Toast.LENGTH_SHORT).show();
                                    printState(getResources().getText(R.string.enroll_fail));
                                }
                            }, true);
                }
            }
        });

        if(mBioMiniFactory != null) {
            mBioMiniFactory.close();
        }

        restartBioMini();

        printRev(""+mBioMiniFactory.getSDKInfo());
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
                        Log.d(TAG, "mCurrentDevice attached : " + mCurrentDevice);
                        if (mCurrentDevice != null /*&& mCurrentDevice.getDeviceInfo() != null*/) {
                            String message = " DeviceName : " + mCurrentDevice.getDeviceInfo().deviceName + "\n" +
                            "         SN : " + mCurrentDevice.getDeviceInfo().deviceSN + "\n" +
                            "SDK version : " + mCurrentDevice.getDeviceInfo().versionSDK;

                            logMessage(message, String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
                            }.getClass().getEnclosingMethod()).getName());
                            printState(getResources().getText(R.string.device_attached));
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    cardViewImage.setCardBackgroundColor(getColor(R.color.colorDeviceConnected));
                                }
                            });
                        }
                    }
                }
            }).start();
        } else if (mCurrentDevice != null && event == IUsbEventHandler.DeviceChangeEvent.DEVICE_DETACHED && mCurrentDevice.isEqual(dev)) {
            printState(getResources().getText(R.string.device_detached));
            Log.d(TAG, "mCurrentDevice removed : " + mCurrentDevice);
            mCurrentDevice = null;
            printState("Device Device Removed");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    cardViewImage.setCardBackgroundColor(getColor(R.color.colorDeviceNoteConnected));
                }
            });
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
                    String message = "onDeviceChange : " + event + " using external usb-manager";
                    logMessage(message, String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
                    }.getClass().getEnclosingMethod()).getName());
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
                    String message = "onDeviceChange : " + event;
                    logMessage(message, String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
                    }.getClass().getEnclosingMethod()).getName());
                    handleDevChange(event, dev);
                }
            };
        }
        //mBioMiniFactory.setTransferMode(IBioMiniDevice.TransferMode.MODE2);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
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
            String message = "permission granted";
            logMessage(message, String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingMethod()).getName());
        }
    }
    @Override
    public void onPostCreate(Bundle savedInstanceState){
        requestPermission();
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
    }

    public void logMessage(String message, String lineNumber, String methodName) {
        executionLogRepository.logMessage(
                new ExecutionLog(message,
                        DynamicData.getDate(),
                        new Date().toString(),
                        getClass().getSimpleName(),
                        DynamicData.getSchoolID(SchoolDeviceIMEINumber),
                        DynamicData.getSchoolName(),
                        null,
                        lineNumber,
                        methodName
                )
        );
    }

    private void enrollTeacher(byte[] fingerPrintData, Bitmap capturedImage) {
        SyncTeacher syncTeacher = getTeacherWithFingerPrint(fingerPrintData);
        if (syncTeacher == null ) {
            // teacher not enrolled with fingerprint
            try {
                SyncTeacher nationalIdTeacher =  teacherRepository.getTeacherWithNationalID(getIntent().getStringExtra(TEACHER_NATIONAL_ID));
                if (nationalIdTeacher == null) {
                    String message =  "This is a new Teacher Enrolling him/her in: " + getIntent().getStringExtra(TEACHER_NATIONAL_ID);
                    logMessage(message, String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
                    }.getClass().getEnclosingMethod()).getName());

                    // saving teacher information
                    Intent intent = getIntent();
                    SyncTeacher teacher = new SyncTeacher(
                            null,
                            null,
                            null,
                            null,
                            Role.HEAD_TEACHER_ROLE,
                            new Date().toString(),
                            intent.getStringExtra(TEACHER_EMAIL),
                            fingerPrintData,
                            saveBitmapToExternalStorage(capturedImage, intent.getStringExtra(TEACHER_NATIONAL_ID),
                                    intent.getStringExtra(TEACHER_FIRST_NAME) +
                                    intent.getStringExtra(TEACHER_LAST_NAME)),
                            intent.getStringExtra(TEACHER_FIRST_NAME),
                            intent.getStringExtra(TEACHER_LAST_NAME),
                            intent.getStringExtra(TEACHER_GENDER),
                            intent.getStringExtra(TEACHER_INITIALS),
                            false,
                            intent.getStringExtra(TEACHER_NATIONAL_ID),
                            intent.getStringExtra(TEACHER_PHONE_NUMBER),
                            DynamicData.getSchoolID(SchoolDeviceIMEINumber),
                            fingerPrintData.length,
                            true

                    );
                    teacherRepository.insertSyncTeacher(teacher);
                    message = "Teacher Enrolled Successfully With Fingerprint: " + Arrays.toString(fingerPrintData);
                    logMessage(message, String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
                    }.getClass().getEnclosingMethod()).getName());


                   runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           Toast.makeText(FingerPrintActivity.this, "Teacher Enrolled Successfully", Toast.LENGTH_LONG).show();
                       }
                   });
                } else {
                    String message =  "This Teacher is already Enrolled:  " + getIntent().getStringExtra(TEACHER_NATIONAL_ID) + " \n" +
                            "His/her National ID saved is: " + nationalIdTeacher.getNationalId() + "\n" +
                            "Compared Against National ID: " + getIntent().getStringExtra(TEACHER_NATIONAL_ID);
                    logMessage(message, String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
                    }.getClass().getEnclosingMethod()).getName());

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(FingerPrintActivity.this, "Teacher Already Enrolled", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
                String message =  "There was error getting Teacher with National ID " + getIntent().getStringExtra(TEACHER_NATIONAL_ID);
                logMessage(message, String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
                }.getClass().getEnclosingMethod()).getName());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(FingerPrintActivity.this, "Unknown Error", Toast.LENGTH_LONG).show();
                    }
                });
            }
        } else {

            String message =  "This Teacher is already Enrolled:  " + getIntent().getStringExtra(TEACHER_NATIONAL_ID) + " \n" +
                    "His/her Fingerprint saved is: " + Arrays.toString(syncTeacher.getFingerPrint()) + "\n" +
                    "Compared Against fingerprint: " + Arrays.toString(fingerPrintData);
            logMessage(message, String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingMethod()).getName());

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(FingerPrintActivity.this, "Teacher Already Enrolled", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private String saveBitmapToExternalStorage(Bitmap capturedImage, String file_name, String description) {
        String path = Environment.getExternalStorageState().toString();
        OutputStream fileOutPutStream = null;
        Integer count = 0;
        File file = new File(path, file_name + new Date().getYear() + "-" +  new Date().getMonth() + ".jpg");
        try {
            fileOutPutStream = new FileOutputStream(file);
            capturedImage.compress(Bitmap.CompressFormat.JPEG, 85, fileOutPutStream);
            fileOutPutStream.flush();
            fileOutPutStream.close();
            MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), file.getName(), "Finger print image for " + description);

            String message = "File saved at : " + file.getAbsolutePath();
            logMessage(message, String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingMethod()).getName());
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private SyncTeacher getTeacherWithFingerPrint(byte[] fingerPrintData) {
        SyncTeacher foundTeacher = null;
        List<SyncTeacher> syncTeachers = teacherRepository.getTeachers();
        for (SyncTeacher syncTeacher: syncTeachers) {
            if (syncTeacher.getFingerPrint() != null) {
                if (mCurrentDevice != null ) {
                    if (mCurrentDevice.verify(
                            fingerPrintData,
                            fingerPrintData.length,
                            syncTeacher.getFingerPrint(),
                            syncTeacher.getFingerPrint().length)
                    ) {
                        String message =  "Teacher with Fingerprint Found: " + syncTeacher.getNationalId()
                                + " has Fingerprint: " + Arrays.toString(syncTeacher.getFingerPrint()) + " Tested against Fingerprint " + Arrays.toString(fingerPrintData);
                        logMessage(message, String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
                        }.getClass().getEnclosingMethod()).getName());
                        foundTeacher = syncTeacher;
                        break;
                    }

                    String message =  "Searching for Teacher: " + syncTeacher.getNationalId()
                            + " has Fingerprint: " + Arrays.toString(syncTeacher.getFingerPrint()) + " Tested against Fingerprint " + Arrays.toString(fingerPrintData);
                    logMessage(message, String.valueOf(new Throwable().getStackTrace() [0].getLineNumber()), Objects.requireNonNull(new Object() {
                    }.getClass().getEnclosingMethod()).getName());
                }
            }

        }
        return foundTeacher;
    }

    private void clockInTeacher(byte[] fingerPrintData) {
        SyncTeacher syncTeacher = getTeacherWithFingerPrint(fingerPrintData);
        if (syncTeacher != null) {
            SyncClockIn clockIn = getClockInWithFingerPrintOrEmployeeNumber(fingerPrintData, syncTeacher.getEmployeeNumber());
            if (clockIn == null ) {
                String message = "Teacher Not Clocked Today, Clocking them in ";
                logMessage(message, String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
                }.getClass().getEnclosingMethod()).getName());

                SyncClockIn clock = new SyncClockIn(
                        syncTeacher.getEmployeeNumber(),
                        syncTeacher.getEmployeeId(),
                        syncTeacher.getFirstName(),
                        syncTeacher.getLastName(),
                        DynamicData.getLatitude(),
                        DynamicData.getLongitude(),
                        DynamicData.getDate(),
                        DynamicData.getDay(),
                        DynamicData.getTime(),
                        DynamicData.getSchoolID(SchoolDeviceIMEINumber),
                        fingerPrintData
                );
                clockInRepository.synClockInTeacher(clock);
                message = "Teacher clocked in Successfully: " + new Date().toString();
                logMessage(message, String.valueOf(new Throwable().getStackTrace() [0].getLineNumber()), Objects.requireNonNull(new Object() {
                }.getClass().getEnclosingMethod()).getName());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(FingerPrintActivity.this, "Clocked In Successfully" , Toast.LENGTH_SHORT).show();
                    }
                });

                Intent intent = new Intent();
                intent.putExtra(EMPLOYEEE_NUMBER, syncTeacher.getEmployeeNumber());
                intent.putExtra(TEACHER_ROLE, syncTeacher.getRole());
                setResult(RESULT_OK, intent);
                finish();
            } else {
                String message = "Teacher already clocked loading home page";
                logMessage(message, String.valueOf(new Throwable().getStackTrace() [0].getLineNumber()), Objects.requireNonNull(new Object() {
                }.getClass().getEnclosingMethod()).getName());

                Intent intent = new Intent();
                intent.putExtra(EMPLOYEEE_NUMBER, syncTeacher.getEmployeeNumber());
                intent.putExtra(TEACHER_ROLE, syncTeacher.getRole());
                setResult(RESULT_OK, intent);
                finish();
            }
        } else {
            String message =  "No Teacher found with that fingerprint :- " + Arrays.toString(fingerPrintData);
            logMessage(message, String.valueOf(new Throwable().getStackTrace() [0].getLineNumber()), Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingMethod()).getName());

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(FingerPrintActivity.this, "No Record Found", Toast.LENGTH_SHORT).show();
                    printState("No Record Found");
                }
            });
        }

    }

    private SyncClockIn getClockInWithFingerPrintOrEmployeeNumber(byte[] fingerPrintData, String employeeNumber) {
        SyncClockIn clock = null;
        try {
            List<SyncClockIn> syncClockIns = clockInRepository.getClockedInTeachersByDateNoteLiveData(DynamicData.getDate());

            logMessage("============" + String.valueOf(syncClockIns.size()) + "=========", String.valueOf(new Throwable().getStackTrace() [0].getLineNumber()), Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingMethod()).getName());


            for (SyncClockIn clockIn: syncClockIns) {
                if (mCurrentDevice != null) {
                    if (clockIn.getFingerPrint() != null) {
                        String message = "Current Clock had fingerprint: " + Arrays.toString(clockIn.getFingerPrint());
                        logMessage(message, String.valueOf(new Throwable().getStackTrace() [0].getLineNumber()), Objects.requireNonNull(new Object() {
                        }.getClass().getEnclosingMethod()).getName());

                        if (mCurrentDevice.verify( //3991
                                fingerPrintData,
                                fingerPrintData.length,
                                clockIn.getFingerPrint(),
                                clockIn.getFingerPrint().length)
                        ) {

                            message = "Clock In Found with Fingerprint: " + Arrays.toString(clockIn.getFingerPrint()) + "\n"
                                    + " Compared against " + Arrays.toString(fingerPrintData) + " EmployeeNumber: " + clockIn.getEmployeeNo();
                            logMessage(message, String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
                            }.getClass().getEnclosingMethod()).getName());
                            clock = clockIn;
                            break;
                        } else {

                            message = "Clock In Has No Fingerprint";
                            logMessage(message, String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
                            }.getClass().getEnclosingMethod()).getName());
                        }
                    } else {
                        if (clockIn.getEmployeeNo().equals(employeeNumber)) {
                            String message = "Teacher has no fingerprint but has employeeNumber clocked In";
                            logMessage(message, String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
                            }.getClass().getEnclosingMethod()).getName());
                            clock = clockIn;
                            break;
                        }
                    }
                } else {
                    String message = "Device was disconnected:- " + new Date().toString();
                    logMessage(message, String.valueOf(new Throwable().getStackTrace() [0].getLineNumber()), Objects.requireNonNull(new Object() {
                    }.getClass().getEnclosingMethod()).getName());
                }
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            String message = "There was error getting clock ins for today:- " + new Date().toString();
            logMessage(message, String.valueOf(new Throwable().getStackTrace() [0].getLineNumber()), Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingMethod()).getName());
        }
        return clock;
    }


    private void clockOutTeacher(byte[] fingerPrintData) {
        SyncTeacher syncTeacher = getTeacherWithFingerPrint(fingerPrintData);
        if (syncTeacher != null) {
            String message = "Teacher with Fingerprint Found :=+ " + Arrays.toString(fingerPrintData);

            logMessage(message + " <= ", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingMethod()).getName());

            SyncClockIn clockIn = getClockInWithFingerPrintOrEmployeeNumber(fingerPrintData, syncTeacher.getEmployeeNumber());
            if (clockIn != null ) {
                message = "Teacher Clocked In Today: ";
                logMessage(message, String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
                }.getClass().getEnclosingMethod()).getName());


                SyncClockOut clockOut = getClockOutWithFingerPrintOrEmployeeNumber(fingerPrintData, syncTeacher.getEmployeeNumber());
                if (clockOut == null) {
                    message = "Teacher Not Clocked Out Today yet , Clocking them Out ";
                    logMessage(message, String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
                    }.getClass().getEnclosingMethod()).getName());

                    SyncClockOut syncClockOut = new SyncClockOut(
                            DynamicData.getDate(),
                            DynamicData.getDay(),
                            DynamicData.getTime(),
                            null,
                            syncTeacher.getEmployeeNumber(),
                            syncTeacher.getEmployeeId(),
                            DynamicData.getLatitude(),
                            DynamicData.getLongitude(),
                            DynamicData.getSchoolID(SchoolDeviceIMEINumber),
                            DynamicData.getSchoolName(),
                            syncTeacher.getFirstName(),
                            syncTeacher.getLastName(),
                            fingerPrintData
                    );
                    clockOutRepository.insertSynClockOut(syncClockOut);

                    message = "Teacher clocked Out Successfully: " + new Date().toString();
                    logMessage(message, String.valueOf(new Throwable().getStackTrace() [0].getLineNumber()), Objects.requireNonNull(new Object() {
                    }.getClass().getEnclosingMethod()).getName());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(FingerPrintActivity.this, "Clocked Out Successfully", Toast.LENGTH_SHORT).show();
                        }
                    });

                    Intent intent = new Intent();
                    intent.putExtra(EMPLOYEEE_NUMBER, syncTeacher.getEmployeeNumber());
                    intent.putExtra(TEACHER_ROLE, syncTeacher.getRole());
                    setResult(RESULT_OK, intent);
                    finish();


                } else {
                    message = "Teacher already clocked Out";
                    logMessage(message, String.valueOf(new Throwable().getStackTrace() [0].getLineNumber()), Objects.requireNonNull(new Object() {
                    }.getClass().getEnclosingMethod()).getName());

                    Intent intent = new Intent();
                    intent.putExtra(EMPLOYEEE_NUMBER, syncTeacher.getEmployeeNumber());
                    intent.putExtra(TEACHER_ROLE, syncTeacher.getRole());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            } else {
                message =  "No Clock In found with that fingerprint :- " + Arrays.toString(fingerPrintData);
                logMessage(message, String.valueOf(new Throwable().getStackTrace() [0].getLineNumber()), Objects.requireNonNull(new Object() {
                }.getClass().getEnclosingMethod()).getName());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(FingerPrintActivity.this, "No Record Found", Toast.LENGTH_SHORT).show();
                        printState("No Record Found");
                    }
                });
            }

        } else {
            String message =  "No Teacher found with that fingerprint :- " + Arrays.toString(fingerPrintData);
            logMessage(message, String.valueOf(new Throwable().getStackTrace() [0].getLineNumber()), Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingMethod()).getName());

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(FingerPrintActivity.this, "No Record Found", Toast.LENGTH_SHORT).show();
                    printState("No Record Found");
                }
            });
        }
    }

    private SyncClockOut getClockOutWithFingerPrintOrEmployeeNumber(byte[] fingerPrintData, String employeeNumber) {
        SyncClockOut clockOut = null;
        try {
            List<SyncClockOut> syncClockOuts = clockOutRepository.getSyncClockOutsByDateNotLiveData(DynamicData.getDate());
            logMessage("============" + String.valueOf(syncClockOuts.size()) + "=========", String.valueOf(new Throwable().getStackTrace() [0].getLineNumber()), Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingMethod()).getName());
            for (SyncClockOut syncClockOut: syncClockOuts) {
                if (mCurrentDevice != null) {
                    if (syncClockOut.getFingerPrint() != null) {
                        String message = "Current Clock Out had fingerprint: " + Arrays.toString(syncClockOut.getFingerPrint());
                        logMessage(message, String.valueOf(new Throwable().getStackTrace() [0].getLineNumber()), Objects.requireNonNull(new Object() {
                        }.getClass().getEnclosingMethod()).getName());

                        if (mCurrentDevice.verify(
                                fingerPrintData,
                                fingerPrintData.length,
                                syncClockOut.getFingerPrint(),
                                syncClockOut.getFingerPrint().length)
                        ) {

                            message = "Clock Out Found with Fingerprint: " + Arrays.toString(syncClockOut.getFingerPrint()) + "\n"
                                    + " Compared against " + Arrays.toString(fingerPrintData) + " EmployeeNumber: " + syncClockOut.getEmployeeNo();
                            logMessage(message, String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
                            }.getClass().getEnclosingMethod()).getName());
                            clockOut = syncClockOut;
                            break;
                        } else {

                            message = "No Clock Out Record Found, Teacher Not Enrolled or Didn't clocked in";
                            logMessage(message, String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
                            }.getClass().getEnclosingMethod()).getName());
                        }
                    }
                    else {
                        if (syncClockOut.getEmployeeNo().equals(employeeNumber)) {
                            String message = "Clock Out Teacher has no fingerprint but has employeeNumber clocked Out";
                            logMessage(message, String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
                            }.getClass().getEnclosingMethod()).getName());
                            clockOut = syncClockOut;
                            break;
                        }
                    }
                } else {
                    String message = "Device was disconnected:- " + new Date().toString();
                    logMessage(message, String.valueOf(new Throwable().getStackTrace() [0].getLineNumber()), Objects.requireNonNull(new Object() {
                    }.getClass().getEnclosingMethod()).getName());
                }
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            String message = "There was error getting clock Out for today:- " + new Date().toString();
            logMessage(message, String.valueOf(new Throwable().getStackTrace() [0].getLineNumber()), Objects.requireNonNull(new Object() {
            }.getClass().getEnclosingMethod()).getName());
        }
        return clockOut;
    }

//    // OnClick Event .
//    View.OnClickListener ClickEvent = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            switch (view.getId() ){
//                case R.id.buttonEnroll:
//                    if (mCurrentDevice != null) {
//                        final String userName = ((EditText) findViewById(R.id.editUsername)).getText().toString();
//                        Toast.makeText(FingerPrintActivity.this, " Template existing " + userName, Toast.LENGTH_SHORT).show();
//                        if (userName.equals("")) {
//                            log("<<ERROR>> There is no user name");
//                            return;
//                        }
//                        ((ImageView) findViewById(R.id.imagePreview)).setImageBitmap(null);
//                        IBioMiniDevice.CaptureOption option = new IBioMiniDevice.CaptureOption();
//                        option.extractParam.captureTemplate = true;
//                        option.captureTemplate = true; //deprecated
//                        //option.frameRate = IBioMiniDevice.FrameRate.ELOW;
//                        // capture fingerprint image
//                        mCurrentDevice.captureSingle(option,
//                                new CaptureResponder() {
//                                    @Override
//                                    public boolean onCaptureEx(final Object context, final Bitmap capturedImage,
//                                                               final IBioMiniDevice.TemplateData capturedTemplate,
//                                                               final IBioMiniDevice.FingerState fingerState) {
//                                        runOnUiThread(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                if(capturedImage != null) {
//                                                    ImageView iv = (ImageView) findViewById(R.id.imagePreview);
//                                                    if(iv != null) {
//                                                        iv.setImageBitmap(capturedImage);
//                                                        Toast.makeText(FingerPrintActivity.this, "Success image set", Toast.LENGTH_SHORT).show();
//                                                    }
//                                                }
//                                            }
//                                        });
//                                        if(capturedTemplate != null) {
//                                            Toast.makeText(FingerPrintActivity.this, " Template existing ", Toast.LENGTH_SHORT).show();
//                                            mUsers.add(new UserData(userName, capturedTemplate.data, capturedTemplate.data.length));
//                                            log("User data added : " + userName);
//                                            printState(getResources().getText(R.string.enroll_ok));
//                                        }
//                                        else {
//                                            log("<<ERROR>> Template is not extracted...");
//                                            printState(getResources().getText(R.string.enroll_fail));
//                                        }
//                                        log(((IBioMiniDevice)context).popPerformanceLog());
//
//                                        return true;
//                                    }
//
//                                    @Override
//                                    public void onCaptureError(Object context, int errorCode, String error) {
//                                        log("onCaptureError : " + error);
//                                        Toast.makeText(FingerPrintActivity.this, "There was error here", Toast.LENGTH_SHORT).show();
//                                        printState(getResources().getText(R.string.enroll_fail));
//                                    }
//                                }, true);
//                    }
//                    break;
//                case R.id.buttonVerify:
//                    if (mCurrentDevice != null) {
//                        if (mUsers.size() == 0) {
//                            log("There is no enrolled data");
//                            return;
//                        }
//                        // capture fingerprint image
//                        IBioMiniDevice.CaptureOption option = new IBioMiniDevice.CaptureOption();
//                        option.extractParam.captureTemplate = true;
//                        option.captureTemplate = true; //deprecated
//
//                        if (mCurrentDevice.getDeviceInfo().scannerType.getDeviceClass() == IBioMiniDevice.ScannerClass.UNIVERSIAL_DEVICE) {
//                            // capture fingerprint image
//                            mCurrentDevice.captureSingle(option,
//                                    new CaptureResponder() {
//                                        @Override
//                                        public boolean onCaptureEx(final Object context, final Bitmap capturedImage,
//                                                                   final IBioMiniDevice.TemplateData capturedTemplate,
//                                                                   final IBioMiniDevice.FingerState fingerState) {
//                                            runOnUiThread(new Runnable() {
//                                                @Override
//                                                public void run() {
//                                                    if (capturedImage != null) {
//                                                        ImageView iv = (ImageView) findViewById(R.id.imagePreview);
//                                                        if (iv != null) {
//                                                            iv.setImageBitmap(capturedImage);
//                                                        }
//                                                    }
//                                                }
//                                            });
//                                            if (capturedTemplate != null) {
//                                                boolean isMatched = false;
//                                                String matchedName = "";
//                                                for (UserData ud : mUsers) {
//                                                    if (mCurrentDevice.verify(
//                                                            capturedTemplate.data, capturedTemplate.data.length,
//                                                            ud.template, ud.template.length)) {
//                                                        isMatched = true;
//                                                        matchedName = ud.name;
//                                                        break;
//                                                    }
//                                                }
//                                                if (isMatched) {
//                                                    log("Match found : " + matchedName);
//                                                    printState(getResources().getText(R.string.verify_ok));
//                                                } else {
//                                                    log("No match found : ");
//                                                    printState(getResources().getText(R.string.verify_not_match));
//                                                }
//                                            } else {
//                                                log("<<ERROR>> Template is not extracted...");
//                                                printState(getResources().getText(R.string.verify_fail));
//                                            }
//                                            return true;
//                                        }
//
//                                        @Override
//                                        public void onCaptureError(Object context, int errorCode, String error) {
//                                            log("onCaptureError : " + error);
//                                            printState(getResources().getText(R.string.capture_fail));
//                                        }
//                                    }, true);
//                        }else if (mCurrentDevice.getDeviceInfo().scannerType.getDeviceClass() == IBioMiniDevice.ScannerClass.HID_DEVICE) {
//
//                            UserData _user = mUsers.get(mUsers.size()-1);
//                            if(mCurrentDevice.verify(_user.template , null)) { // HID Device does not supported verify with two templates.
//                                log("Match found : " + _user.name);
//                                printState(getResources().getText(R.string.verify_ok));
//                            }else {
//                                IBioMiniDevice.ErrorCode _ecode =  mCurrentDevice.getLastError();
//                                log(_ecode.toString()  + "("+_ecode.value()  +")");
//                                printState(getResources().getText(R.string.verify_not_match));
//                            }
//                        }
//                    }
//                    break;
//                case R.id.buttonDeleteAll: {
//                    mUsers.clear();
//                    printState(getResources().getText(R.string.delete_user));
//                }
//                break;
//                case R.id.buttonExportBmp:
//                {
//                    if (mCurrentDevice != null) {
//                        final String userName = ((EditText) findViewById(R.id.editUsername)).getText().toString();
//                        if (userName.equals("")) {
//                            log("<<ERROR>> There is no user name");
//                            printState(getResources().getText(R.string.no_user_name));
//                            return;
//                        }
//                        byte[] bmp = mCurrentDevice.getCaptureImageAsBmp();
//                        if (bmp == null) {
//                            log("<<ERROR>> Cannot get BMP buffer");
//                            printState(getResources().getText(R.string.export_bmp_fail));
//                            return;
//                        }
//                        try {
//                            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/" + userName + "_capturedImage.bmp");
//                            FileOutputStream fos = new FileOutputStream(file);
//                            fos.write(bmp);
//                            fos.close();
//
//                            printState(getResources().getText(R.string.export_bmp_ok));
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//                break;
//                case R.id.buttonExportWsq: {
//                    if (mCurrentDevice != null) {
//                        final String userName = ((EditText) findViewById(R.id.editUsername)).getText().toString();
//                        if (userName.equals("")) {
//                            log("<<ERROR>> There is no user name");
//                            printState(getResources().getText(R.string.no_user_name));
//                            return;
//                        }
//
//                        byte[] wsq = mCurrentDevice.getCaptureImageAsWsq(-1, -1, 3.5f, 0);
//                        if (wsq == null) {
//                            log("<<ERROR>> Cannot get WSQ buffer");
//                            printState(getResources().getText(R.string.export_wsq_fail));
//                            return;
//                        }
//                        try {
//                            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/" + userName + "_capturedImage.wsq");
//                            FileOutputStream fos = new FileOutputStream(file);
//                            fos.write(wsq);
//                            fos.close();
//                            printState(getResources().getText(R.string.export_wsq_ok));
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//                break;
//                case R.id.buttonTemplate: {
//                    if (mCurrentDevice != null) {
//                        final String userName = ((EditText) findViewById(R.id.editUsername)).getText().toString();
//                        if (userName.equals("")) {
//                            log("<<ERROR>> There is no user name");
//                            printState(getResources().getText(R.string.no_user_name));
//                            return;
//                        }
//
//                        int tmp_type_idx = (int) ((Spinner) findViewById(R.id.spinnerTemplateType)).getSelectedItemId();
//                        int tmp_type;
//                        IBioMiniDevice.TemplateType _type;
//                        String encKey = ((EditText) findViewById(R.id.editEncryptKey)).getText().toString();
//                        if (encKey.equals("")) {
//                            mCurrentDevice.setEncryptionKey(null);
//                        } else {
//                            try {
//                                mCurrentDevice.setEncryptionKey(encKey.getBytes("UTF-8"));
//                            } catch (UnsupportedEncodingException e) {
//                                mCurrentDevice.setEncryptionKey(null);
//                                log("<<ERROR>> cannot set encryption key unsupported character sets assigned...");
//                                printState(getResources().getText(R.string.export_template_fail));
//                                e.printStackTrace();
//                            }
//                        }
//                        switch (tmp_type_idx) {
//                            case 0:
//                                tmp_type = IBioMiniDevice.TemplateType.SUPREMA.value();
//                                _type = IBioMiniDevice.TemplateType.SUPREMA;
//                                break;
//                            case 1:
//                                tmp_type = IBioMiniDevice.TemplateType.ISO19794_2.value();
//                                _type = IBioMiniDevice.TemplateType.ISO19794_2;
//                                break;
//                            case 2:
//                                tmp_type = IBioMiniDevice.TemplateType.ANSI378.value();
//                                _type = IBioMiniDevice.TemplateType.ANSI378;
//                                break;
//                            default:
//                                tmp_type = IBioMiniDevice.TemplateType.SUPREMA.value();
//                                _type = IBioMiniDevice.TemplateType.SUPREMA;
//                                break;
//                        }
//                        mCurrentDevice.setParameter(
//                                new IBioMiniDevice.Parameter(IBioMiniDevice.ParameterType.TEMPLATE_TYPE,
//                                        tmp_type));
//                        IBioMiniDevice.TemplateData tmp = mCurrentDevice.extractTemplate();
//                        if (tmp == null) {
//                            log("<<ERROR>> Cannot get Template buffer");
//                            printState(getResources().getText(R.string.export_template_fail));
//                            return;
//                        }
//                        if (tmp.data != null) {
//                            try {
//
//                                File file = new File(
//                                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/" +
//                                                userName + "_(" + _type.toString() + ")_capturedTemplate.tmp");
//                                FileOutputStream fos = new FileOutputStream(file);
//                                fos.write(tmp.data);
//                                fos.close();
//                                printState(getResources().getText(R.string.export_template_ok));
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            } finally {
//                                try {
//                                    if (!encKey.equals("")) {
//                                        File file = new File(
//                                                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/" +
//                                                        userName + "_(" + _type.toString() + " _capturedTemplate_dec.tmp");
//                                        FileOutputStream fos = new FileOutputStream(file);
//                                        fos.write(mCurrentDevice.decrypt(tmp.data));
//                                        fos.close();
//                                        printState(getResources().getText(R.string.export_template_ok));
//
//                                    }
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }
//                    }
//                }
//                break;
//                case R.id.button19794_4: {
//                    if (mCurrentDevice != null) {
//                        final String userName = ((EditText) findViewById(R.id.editUsername)).getText().toString();
//                        if (userName.equals("")) {
//                            log("<<ERROR>> There is no user name");
//                            printState(getResources().getText(R.string.no_user_name));
//                            return;
//                        }
//                        byte[] format19794_4 = mCurrentDevice.getCaptureImageAs19794_4();
//                        if (format19794_4 == null) {
//                            log("<<ERROR>> Cannot get 19794_4 buffer");
//                            printState(getResources().getText(R.string.export_19794_4_fail));
//                            return;
//                        }
//                        try {
//                            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/" + userName + "_capturedImage.dat");
//                            FileOutputStream fos = new FileOutputStream(file);
//                            fos.write(format19794_4);
//                            fos.close();
//                            printState(getResources().getText(R.string.export_19794_4_ok));
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//                break;
//                case R.id.buttonReadCaptureParam:
//                    if(mCurrentDevice != null) {
//                        int security_level = (int) mCurrentDevice.getParameter(IBioMiniDevice.ParameterType.SECURITY_LEVEL).value;
//                        int sensitivity_level = (int) mCurrentDevice.getParameter(IBioMiniDevice.ParameterType.SENSITIVITY).value;
//                        int timeout = (int) mCurrentDevice.getParameter(IBioMiniDevice.ParameterType.TIMEOUT).value;
//                        int lfd_level = (int) mCurrentDevice.getParameter(IBioMiniDevice.ParameterType.DETECT_FAKE).value;
//                        boolean fast_mode = mCurrentDevice.getParameter(IBioMiniDevice.ParameterType.FAST_MODE).value == 1;
//                        boolean crop_mode = mCurrentDevice.getParameter(IBioMiniDevice.ParameterType.SCANNING_MODE).value == 1;
//                        boolean ext_trigger = mCurrentDevice.getParameter(IBioMiniDevice.ParameterType.EXT_TRIGGER).value == 1;
//                        boolean auto_sleep = mCurrentDevice.getParameter(IBioMiniDevice.ParameterType.ENABLE_AUTOSLEEP).value == 1;
//                        boolean extract_mode = mCurrentDevice.getParameter(IBioMiniDevice.ParameterType.EXTRACT_MODE_BIOSTAR).value == 1;
//                        ((SeekBar) findViewById(R.id.seekBarSecurityLevel)).setProgress(security_level);
//                        ((SeekBar) findViewById(R.id.seekBarSensitivity)).setProgress(sensitivity_level);
//                        ((SeekBar) findViewById(R.id.seekBarTimeout)).setProgress(timeout/1000);
//                        ((SeekBar) findViewById(R.id.seekBarLfdLevel)).setProgress(lfd_level);
//                        ((CheckBox) findViewById(R.id.checkBoxFastMode)).setChecked(fast_mode);
//                        ((CheckBox) findViewById(R.id.checkBoxCropMode)).setChecked(crop_mode);
//                        ((CheckBox) findViewById(R.id.checkBoxExtTrigger)).setChecked(ext_trigger);
//                        ((CheckBox) findViewById(R.id.checkBoxAutoSleep)).setChecked(auto_sleep);
//                        printState(getResources().getText(R.string.read_params_ok));
//                    }
//
//                    break;
//                case R.id.buttonWriteCaptureParam:
//
//                    if(mCurrentDevice != null) {
//                        int security_level = ((SeekBar) findViewById(R.id.seekBarSecurityLevel)).getProgress();
//                        int sensitivity_level = ((SeekBar) findViewById(R.id.seekBarSensitivity)).getProgress();
//                        int timeout = ((SeekBar) findViewById(R.id.seekBarTimeout)).getProgress();
//                        int lfd_level = ((SeekBar) findViewById(R.id.seekBarLfdLevel)).getProgress();
//                        boolean fast_mode = ((CheckBox) findViewById(R.id.checkBoxFastMode)).isChecked();
//                        boolean crop_mode = ((CheckBox) findViewById(R.id.checkBoxCropMode)).isChecked();
//                        boolean ext_trigger = ((CheckBox) findViewById(R.id.checkBoxExtTrigger)).isChecked();
//                        boolean auto_sleep = ((CheckBox) findViewById(R.id.checkBoxAutoSleep)).isChecked();
//                        boolean extract_mode = false;
//                        mCurrentDevice.setParameter(new IBioMiniDevice.Parameter(IBioMiniDevice.ParameterType.SECURITY_LEVEL, security_level));
//                        mCurrentDevice.setParameter(new IBioMiniDevice.Parameter(IBioMiniDevice.ParameterType.SENSITIVITY, sensitivity_level));
//                        mCurrentDevice.setParameter(new IBioMiniDevice.Parameter(IBioMiniDevice.ParameterType.TIMEOUT, timeout));
//                        mCurrentDevice.setParameter(new IBioMiniDevice.Parameter(IBioMiniDevice.ParameterType.DETECT_FAKE, lfd_level));
//                        mCurrentDevice.setParameter(new IBioMiniDevice.Parameter(IBioMiniDevice.ParameterType.FAST_MODE, fast_mode?1:0));
//                        mCurrentDevice.setParameter(new IBioMiniDevice.Parameter(IBioMiniDevice.ParameterType.SCANNING_MODE, crop_mode?1:0));
//                        mCurrentDevice.setParameter(new IBioMiniDevice.Parameter(IBioMiniDevice.ParameterType.EXT_TRIGGER, ext_trigger?1:0));
//                        mCurrentDevice.setParameter(new IBioMiniDevice.Parameter(IBioMiniDevice.ParameterType.ENABLE_AUTOSLEEP, auto_sleep?1:0));
//                        mCurrentDevice.setParameter(new IBioMiniDevice.Parameter(IBioMiniDevice.ParameterType.EXTRACT_MODE_BIOSTAR, extract_mode?1:0));
//                        printState(getResources().getText(R.string.write_params_ok));
//                    }
//                    break;
//            }
//        }
//    };




//    private UsbManager mUsbManager = null;
//    private PendingIntent mPermissionIntent= null;
//    //
//    private BioMiniFactory mBioMiniFactory = null;
//    public static final int REQUEST_WRITE_PERMISSION = 786;
//    public IBioMiniDevice mCurrentDevice = null;
//    private FingerPrintActivity mainContext;
//
//    public final static String TAG = "BioMini Sample";
//    private TextView statusTextView;
//    private ScrollView mScrollLog = null;
//    private Intent startActivityIntent;
//    private Intent incomingIntent;
//    private IBioMiniDevice.TemplateData capturedTemplateData;
//    private Bitmap capturedImageData;
//    private FingerPrintActivityViewModel printActivityViewModel;
//
//    private CardView cardViewCapture, cardViewEnroll;
//    private TextView textViewCapture, textViewEnroll;
//    private ImageView fingerprintImageView;
//    private TeacherRepository teacherRepository;
//    private ExecutionLogRepository executionLogRepository;
//    private SyncTeacher syncTeacher;
//    private List<SyncClockIn> syncClockIns;
//
//    private IBioMiniDevice.CaptureOption mCaptureOptionDefault = new IBioMiniDevice.CaptureOption();
//
//    synchronized public void printState(final CharSequence str){
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                statusTextView.setText(str);
//            }
//        });
//    }
//
//    synchronized public void log(final String msg) {
//    }
//
//    synchronized public void printRev(final String msg) {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
////                ((TextView) findViewById(R.id.revText)).setText(msg);
//                Log_Message(msg, String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                }.getClass().getEnclosingMethod()).getName());
//            }
//        });
//    }
//
//    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver(){
//        public void onReceive(Context context, Intent intent){
//            String action = intent.getAction();
//            if(ACTION_USB_PERMISSION.equals(action)){
//                synchronized(this){
//                    UsbDevice device = (UsbDevice)intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
//                    if(intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)){
//                        if(device != null){
//                            if( mBioMiniFactory == null) return;
//                            mBioMiniFactory.addDevice(device);
//                            log(String.format(Locale.ENGLISH ,"Initialized device count- BioMiniFactory (%d)" , mBioMiniFactory.getDeviceCount() ));
//                            Log_Message(String.format(Locale.ENGLISH ,"Initialized device count- BioMiniFactory (%d)" , mBioMiniFactory.getDeviceCount() ), String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                            }.getClass().getEnclosingMethod()).getName());
//                        }
//                    }
//                    else{
//                        Log.d(TAG, "permission denied for device"+ device);
//                        Log_Message("permission denied for device"+ device, String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                        }.getClass().getEnclosingMethod()).getName());
//                    }
//                }
//            }
//        }
//    };
//
//    public void checkDevice(){
//        Log_Message("Checking the device", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//        }.getClass().getEnclosingMethod()).getName());
//        if(mUsbManager == null) return;
//        log("checkDevice");
//        HashMap<String , UsbDevice> deviceList = mUsbManager.getDeviceList();
//        for (UsbDevice _device : deviceList.values()) {
//            if (_device.getVendorId() == 0x16d1) {
//                //Suprema vendor ID
//                Log_Message("Requesting Permission", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                }.getClass().getEnclosingMethod()).getName());
//                Toast.makeText(this, "Requesting permission", Toast.LENGTH_LONG).show();
//                mUsbManager.requestPermission(_device, mPermissionIntent);
//            }
//        }
//    }
//
//    @SuppressLint("SetTextI18n")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        incomingIntent = getIntent();
//        teacherRepository = MainRepository.getInstance(getApplication()).getTeachersRepository();
//        executionLogRepository = MainRepository.getInstance(getApplication()).getExecutionLogRepository();
//
//
//        setContentView(R.layout.activity_finger_print);
//        setTitle(R.string.capture);
//
//        mainContext = this;
//        statusTextView = findViewById(R.id.textViewStatus);
//        mCaptureOptionDefault.frameRate = IBioMiniDevice.FrameRate.SHIGH;
//        cardViewCapture = findViewById(R.id.cardViewCapture);
//        cardViewEnroll = findViewById(R.id.cardViewEnroll);
//        textViewCapture = findViewById(R.id.textViewCapture);
//        textViewEnroll = findViewById(R.id.textViewEnroll);
//        fingerprintImageView = findViewById(R.id.imageViewFingerPrint);
//
//
//
//        if(mBioMiniFactory != null) {
//            mBioMiniFactory.close();
//        }
//
//        restartBioMini();
//        if (Objects.equals(getIntent().getAction(), ACTION_CLOCK_OUT)) {
//            // removed enroll button and change the with or cap
//            textViewEnroll.setText(R.string.clock_out);
//        }
//
//        if (Objects.equals(getIntent().getAction(), ACTION_CLOCK_IN)) {
//            textViewEnroll.setText("Clock In");
//        }
//
//        cardViewCapture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(mCurrentDevice != null) {
//
//                    Log_Message("Bio Mini device available capturing fingerprint", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                    }.getClass().getEnclosingMethod()).getName());
//
//                    mCurrentDevice.captureSingle(
//                            mCaptureOptionDefault,
//                            new FingerPrintCaptureResponder(mainContext),
//                            true
//                    );
//                }
//            }
//        });
//
//        cardViewEnroll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (capturedTemplateData != null && capturedImageData != null ) {
//                    Log_Message("Captured template exist and image", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                    }.getClass().getEnclosingMethod()).getName());
//
//
//                    if (Objects.equals(getIntent().getAction(), ACTION_ENROLL)) {
//                        Log_Message("Enrollment action", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                        }.getClass().getEnclosingMethod()).getName());
//
//                        enrollTeacher(capturedTemplateData.data, teacherRepository, incomingIntent.getStringExtra(TEACHER_NATIONAL_ID));
//                    } else if (Objects.equals(getIntent().getAction(), ACTION_CLOCK_IN)) {
//
//                        Log_Message("Clock In action", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                        }.getClass().getEnclosingMethod()).getName());
//
//                        clockInTeacher(capturedTemplateData.data);
//                    } else if (Objects.equals(getIntent().getAction(), ACTION_CLOCK_OUT)) {
//
//                        Log_Message("Action Clock Out", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                        }.getClass().getEnclosingMethod()).getName());
//
//                        clockOutTeacher(capturedTemplateData.data);
//                    }
//                } else {
//                    Log_Message("No Fingerprint was Captured", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                    }.getClass().getEnclosingMethod()).getName());
//                    printState("No Fingerprint was Captured");
//                }
//            }
//        });
//
//        printRev(""+mBioMiniFactory.getSDKInfo());
//    }
//
//    private void enrollTeacher(byte[] fingerprint, TeacherRepository teacherRepository, String nationalID) {
//
//        Log_Message("Enrolling Teacher", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//        }.getClass().getEnclosingMethod()).getName());
//        try {
//            SyncTeacher syncTeacher1 = getTeacherWithFingerPrint(fingerprint);
//            SyncTeacher syncTeacher2 = teacherRepository.getTeacherWithNationalID(nationalID);
//            if (syncTeacher1 == null ) {
//                Log_Message("Not Found Teacher with finger print of size - " + String.valueOf(fingerprint.length), String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                }.getClass().getEnclosingMethod()).getName());
//                // already enrolled
//                if (syncTeacher2 == null ) {
//                    Log_Message("Not Found Teacher with ID and Enrolling", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                    }.getClass().getEnclosingMethod()).getName());
//                    SyncTeacher syncTeacher = new SyncTeacher.Builder()
//                            .setDOB(null)
//                            .setEmailAddress(incomingIntent.getStringExtra(TEACHER_EMAIL))
//                            .setLastName(incomingIntent.getStringExtra(TEACHER_LAST_NAME))
//                            .setFirstName(incomingIntent.getStringExtra(TEACHER_FIRST_NAME))
//                            //.setFingerImage(BitmapConverter.encodeBitmapToBase64(capturedImageData))
//                            .setFingerPrint(fakeFingerPrint)
//                            .setGender(incomingIntent.getStringExtra(TEACHER_GENDER))
//                            .setPhoneNumber(incomingIntent.getStringExtra(TEACHER_PHONE_NUMBER))
//                            .setNationalID(incomingIntent.getStringExtra(TEACHER_NATIONAL_ID))
//                            .setLicensed(incomingIntent.getBooleanExtra(TEACHER_LICENSED, false))
//                            .setInitials(incomingIntent.getStringExtra(TEACHER_INITIALS))
//                            .setRole("Teacher")
//                            .setDOB(new Date().toString())
//                            .setSchoolID(DynamicData.getSchoolID())
//                            .build();
//                    teacherRepository.insertSyncTeacher(syncTeacher);
//                    Toast.makeText(FingerPrintActivity.this, "Teacher Enrolled Successfully ", Toast.LENGTH_SHORT).show();
//                    Log_Message("Teacher Enrolled Successfully", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                    }.getClass().getEnclosingMethod()).getName());
//                } else {
//                    Log_Message("Found Teacher with National ID -- " + syncTeacher2.getNationalId(), String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                    }.getClass().getEnclosingMethod()).getName());
//                }
//            } else {
//                Log_Message("Found Teacher of Fingerprint", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                }.getClass().getEnclosingMethod()).getName());
//            }
//        } catch (InterruptedException | ExecutionException e) {
//            Toast.makeText(FingerPrintActivity.this, "Error Enrolling Teacher", Toast.LENGTH_LONG).show();
//            Log_Message("Error have occurred :- " + e.toString(), String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//            }.getClass().getEnclosingMethod()).getName());
//            e.printStackTrace();
//        }
//    }
//
//    void handleDevChange(IUsbEventHandler.DeviceChangeEvent event, Object dev) {
//        Log_Message("Device Changing and handling it", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//        }.getClass().getEnclosingMethod()).getName());
//        if (event == IUsbEventHandler.DeviceChangeEvent.DEVICE_ATTACHED && mCurrentDevice == null) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    int cnt = 0;
//                    while (mBioMiniFactory == null && cnt < 20) {
//                        SystemClock.sleep(1000);
//                        cnt++;
//                    }
//                    if (mBioMiniFactory != null) {
//                        mCurrentDevice = mBioMiniFactory.getDevice(0);
//                        printState(getResources().getText(R.string.device_attached));
//                        Log_Message("mCurrentDevice attached : " + mCurrentDevice, String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                        }.getClass().getEnclosingMethod()).getName());
//                        Log.d(TAG, "mCurrentDevice attached : " + mCurrentDevice);
//                        if (mCurrentDevice != null /*&& mCurrentDevice.getDeviceInfo() != null*/) {
//                            log(" DeviceName : " + mCurrentDevice.getDeviceInfo().deviceName);
//                            log("         SN : " + mCurrentDevice.getDeviceInfo().deviceSN);
//                            log("SDK version : " + mCurrentDevice.getDeviceInfo().versionSDK);
//                        }
//                    }
//                }
//            }).start();
//        } else if (mCurrentDevice != null && event == IUsbEventHandler.DeviceChangeEvent.DEVICE_DETACHED && mCurrentDevice.isEqual(dev)) {
//            printState(getResources().getText(R.string.device_detached));
//            Log.d(TAG, "mCurrentDevice removed : " + mCurrentDevice);
//            mCurrentDevice = null;
//        }
//    }
//
//    void restartBioMini() {
//        Log_Message("Restarting Bio Mini", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//        }.getClass().getEnclosingMethod()).getName());
//        if(mBioMiniFactory != null) {
//            Log_Message("Closing Bio Mini Factory", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//            }.getClass().getEnclosingMethod()).getName());
//            mBioMiniFactory.close();
//        }
//        if( mbUsbExternalUSBManager ){
//            mUsbManager = (UsbManager)getSystemService(Context.USB_SERVICE);
//            mBioMiniFactory = new BioMiniFactory(mainContext, mUsbManager){
//                @Override
//                public void onDeviceChange(DeviceChangeEvent event, Object dev) {
//                    log("----------------------------------------");
//                    Log_Message("onDeviceChange : " + event + " using external usb-manager", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                    }.getClass().getEnclosingMethod()).getName());
//                    log("onDeviceChange : " + event + " using external usb-manager");
//                    log("----------------------------------------");
//                    handleDevChange(event, dev);
//                }
//            };
//            //
//            mPermissionIntent = PendingIntent.getBroadcast(this,0,new Intent(ACTION_USB_PERMISSION),0);
//            IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
//            registerReceiver(mUsbReceiver, filter);
//            checkDevice();
//        }else {
//            mBioMiniFactory = new BioMiniFactory(mainContext) {
//                @Override
//                public void onDeviceChange(DeviceChangeEvent event, Object dev) {
//                    Log_Message("Device Changing : " + event, String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                    }.getClass().getEnclosingMethod()).getName());
//                    log("----------------------------------------");
//                    log("onDeviceChange : " + event);
//                    log("----------------------------------------");
//                    handleDevChange(event, dev);
//                }
//            };
//        }
//    }
//
//    @Override
//    protected void onDestroy() {
//        if (mBioMiniFactory != null) {
//            Log_Message("Un Registering the device and nulling factory", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//            }.getClass().getEnclosingMethod()).getName());
//            mBioMiniFactory.close();
//            mBioMiniFactory = null;
//        }
//        if( mbUsbExternalUSBManager ){
//            Log_Message("Un registering the receiver", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//            }.getClass().getEnclosingMethod()).getName());
//            unregisterReceiver(mUsbReceiver);
//        }
//        super.onDestroy();
//    }
//
//    private void requestPermission() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            Log_Message("Requesting Permission", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//            }.getClass().getEnclosingMethod()).getName());
//            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},  REQUEST_WRITE_PERMISSION);
//        }
//    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        if (requestCode == REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            log("permission granted");
//            Log_Message("Permission Granted", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//            }.getClass().getEnclosingMethod()).getName());
//        }
//    }
//    @Override
//    public void onPostCreate(Bundle savedInstanceState){
//        Log_Message("Post Created asking for permission", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//        }.getClass().getEnclosingMethod()).getName());
//        requestPermission();
//        super.onPostCreate(savedInstanceState);
//    }
//
//    @Override
//    public void onFingerPrintCaptureResponseCapture(Object contest, IBioMiniDevice.FingerState fingerState) {
//
//    }
//
//    @Override
//    public boolean onFingerPrintCaptureResponseCaptureEx(Object contest, Bitmap bitmap, IBioMiniDevice.TemplateData templateData, IBioMiniDevice.FingerState fingerState) {
//        Log_Message("Captured Fingerprint successfully", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//        }.getClass().getEnclosingMethod()).getName());
//        log("onCapture : Capture successful!");
//        printState(getResources().getText(R.string.capture_single_ok));
//        capturedTemplateData = templateData;
//        capturedImageData = bitmap;
//
//        log(((IBioMiniDevice) contest).popPerformanceLog());
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                if (capturedImageData != null) {
//                    Log_Message("Captured Image is displayed", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                    }.getClass().getEnclosingMethod()).getName());
//                    fingerprintImageView.setImageBitmap(capturedImageData);
//                }
//            }
//        });
//        return true;
//    }
//
//    @Override
//    public void onFingerPrintCaptureResponseCaptureError(Object contest, int errorCode, String errorMessage) {
//        log("onCaptureError : " + errorCode + " ErrorCode :" + errorCode);
//        Log_Message("Error During Capturing fingerprint - " + errorMessage, String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//        }.getClass().getEnclosingMethod()).getName());
//        if( errorCode != IBioMiniDevice.ErrorCode.OK.value())
//            Log_Message(getResources().getText(R.string.capture_single_fail) + "("+errorMessage+")", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//            }.getClass().getEnclosingMethod()).getName());
//        printState(getResources().getText(R.string.capture_single_fail) + "("+errorMessage+")");
//    }
//
//    public void saveClockIn(SyncClockIn clockIn, byte[] finger, ClockInRepository clockInRepository, SyncTeacher syncTeacher) {
//        Log_Message("Saving Clock In", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//        }.getClass().getEnclosingMethod()).getName());
//
//
//        if (clockIn == null) {
//            Log_Message("Clock In not Null", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//            }.getClass().getEnclosingMethod()).getName());
//            clockInRepository.synClockInTeacher(new SyncClockIn(
//                    syncTeacher.getEmployeeNumber(),
//                    syncTeacher.getEmployeeNumber(),
//                    syncTeacher.getFirstName(),
//                    syncTeacher.getLastName(),
//                    DynamicData.getLatitude(),
//                    DynamicData.getLongitude(),
//                    DynamicData.getDate(),
//                    DynamicData.getDay(),
//                    DynamicData.getTime(),
//                    DynamicData.getSchoolID(),
//                    finger
//            ));
//
//            Log_Message("teacher clocked in successfully", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//            }.getClass().getEnclosingMethod()).getName());
//
//
//            Toast.makeText(FingerPrintActivity.this, "Clocked In SuccessFully", Toast.LENGTH_SHORT).show();
//        } else  {
//
//
//            Log_Message("Teacher already clocked in finishing activity", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//            }.getClass().getEnclosingMethod()).getName());
//
//
//            Toast.makeText(FingerPrintActivity.this, "Teacher Already Clocked In", Toast.LENGTH_SHORT).show();
//        }
//        Intent intent = new Intent();
//        intent.putExtra(TEACHER_ROLE, syncTeacher.getRole());
//        intent.putExtra(EMPLOYEEE_NUMBER, syncTeacher.getEmployeeNumber());
//        setResult(RESULT_OK, intent);
//        finish();
//    }
//
//    private void clockInTeacher(byte[] finger) {
//        Log_Message("Clocking teacher with fingerprint", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//        }.getClass().getEnclosingMethod()).getName());
//
//
//        final ClockInRepository clockInRepository = ClockInRepository.getInstance(TelaRoomDatabase.getInstance(getApplicationContext()));
//        SyncTeacher syncTeacher = getTeacherWithFingerPrint(finger);
//        if (syncTeacher != null) {
//
//
//            Log_Message("Teacher With Finger print found", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//            }.getClass().getEnclosingMethod()).getName());
//
//
//            try {
//                if (syncTeacher.getEmployeeNumber() == null) {
//                    Log_Message("Teacher have not Employee number", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                    }.getClass().getEnclosingMethod()).getName());
//                    SyncClockIn clockIn = getSyncClockInByFingerPrintAndDate(syncTeacher.getFingerPrint(), DynamicData.getDate());
//                    saveClockIn(clockIn, finger, clockInRepository, syncTeacher); // save clock in finger print
//                } else {
//                    Log_Message("Teacher have employee Number", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                    }.getClass().getEnclosingMethod()).getName());
//                    SyncClockIn clockIn = clockInRepository.getSyncClockInByEmployeeIDAndDate(syncTeacher.getEmployeeNumber(), DynamicData.getDate());
//                    saveClockIn(clockIn, finger, clockInRepository, syncTeacher); // clock teacher since there is no clock in today
//                }
//            } catch (ExecutionException | InterruptedException e) {
//                e.printStackTrace();
//                Log_Message("There was error - " + e.toString(), String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                }.getClass().getEnclosingMethod()).getName());
//                Toast.makeText(FingerPrintActivity.this, "There was Errors", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            Log_Message("No record Found for fingerprint", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//            }.getClass().getEnclosingMethod()).getName());
//            Toast.makeText(FingerPrintActivity.this, "No Records Found", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private SyncClockIn getSyncClockInByFingerPrintAndDate(byte[] fingerPrint, String date) {
//        Log_Message("Find clock in By Fingerprint and Date", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//        }.getClass().getEnclosingMethod()).getName());
//        SyncClockIn clockIn = null;
//        List<SyncClockIn> syncClockIns = null;
//        try {
//            syncClockIns = ClockInRepository.getInstance(TelaRoomDatabase.getInstance(this))
//                    .getClockInByDate(date);
//            if (mCurrentDevice != null) {
//                Log_Message("BioMini device Not Null", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                }.getClass().getEnclosingMethod()).getName());
//                for (SyncClockIn clock: syncClockIns) {
//                    if (mCurrentDevice.verify(fingerPrint, fingerPrint.length, clock.getFingerPrint(), clock.getFingerPrint().length)) {
//                        Log_Message("Teacher found", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                        }.getClass().getEnclosingMethod()).getName());
//                        clockIn = clock;
//                        break;
//                    }
//                }
//            } else {
//                Log_Message("Device Not Connected", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                }.getClass().getEnclosingMethod()).getName());
//                Toast.makeText(this, "Device Not Connected", Toast.LENGTH_SHORT).show();
//            }
//        } catch (ExecutionException | InterruptedException e) {
//            e.printStackTrace();
//            Log_Message("There was error, app crushed", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//            }.getClass().getEnclosingMethod()).getName());
//        }
//        Log_Message("Clock In - " + String.valueOf(clockIn != null), String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//        }.getClass().getEnclosingMethod()).getName());
//        return clockIn;
//    }
//
//    private SyncTeacher getTeacherWithFingerPrint(byte[] finger) {
//        Log_Message("Getting Teacher With Fingerprint", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//        }.getClass().getEnclosingMethod()).getName());
//        SyncTeacher teacher = null;
//        List<SyncTeacher> syncTeachers = teacherRepository.getTeachers();
//        if (mCurrentDevice != null) {
//            Log_Message("BioMini Device Not Null", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//            }.getClass().getEnclosingMethod()).getName());
//            for (SyncTeacher syncTeacher: syncTeachers) {
//                if (syncTeacher.getFingerPrint() != null) {
//                    Log_Message("Current Teacher National ID  => " + syncTeacher.getNationalId(), String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                    }.getClass().getEnclosingMethod()).getName());
//                    Log_Message("Unknown Teacher National ID  => " + incomingIntent.getStringExtra(TEACHER_NATIONAL_ID), String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                    }.getClass().getEnclosingMethod()).getName());
//
//
//                    Log_Message("Current Teacher has Fingerprint => " + Arrays.toString(syncTeacher.getFingerPrint())
//                            + " OF SIZE  { " + String.valueOf(syncTeacher.getFingerPrint().length) + " } ", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                    }.getClass().getEnclosingMethod()).getName());
//
//                    Log_Message("Unknown Teacher has Fingerprint => " + Arrays.toString(finger)
//                            + " OF SIZE { " + String.valueOf(finger.length) + " }", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                    }.getClass().getEnclosingMethod()).getName());
//                    if (mCurrentDevice.verify(finger, finger.length, syncTeacher.getFingerPrint(), syncTeacher.getFingerPrint().length)) {
//                        //TODO Error is here and it needs to fixed
//                        //if (Arrays.equals(finger, syncTeacher.getFingerPrint())) {
//                        Log_Message("Found Teacher with a given Fingerprint and Returning it ---", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                        }.getClass().getEnclosingMethod()).getName());
//                        return  syncTeacher;
//                    } else {
//                        Log_Message("Teacher Not The same", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                        }.getClass().getEnclosingMethod()).getName());
//                    }
//                }
//            }
//        } else {
//
//            Log_Message("Device Removed", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//            }.getClass().getEnclosingMethod()).getName());
//
//
//            Toast.makeText(this, "Device Removed", Toast.LENGTH_SHORT).show();
//        }
//        Log_Message("Teacher of Fingerprint  found? - " + String.valueOf(teacher != null ), String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//        }.getClass().getEnclosingMethod()).getName());
//        return teacher;
//    }
//
//    private void clockOutTeacher(byte[] finger) {
//        Log_Message("Clocking Out Teacher", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//        }.getClass().getEnclosingMethod()).getName());
//
//        final ClockOutRepository clockOutRepository = ClockOutRepository.getInstance(TelaRoomDatabase.getInstance(getApplicationContext()));
//        SyncTeacher syncTeacher = getTeacherWithFingerPrint(finger);
//        if (syncTeacher != null) {
//            Log_Message("Teacher With Fingerprint Found", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//            }.getClass().getEnclosingMethod()).getName());
//
//            try {
//                if (syncTeacher.getEmployeeNumber() == null) {
//                    Log_Message("Teacher has No Employee Number", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                    }.getClass().getEnclosingMethod()).getName());
//                    SyncClockOut clockOut = getSyncClockOutByFingerPrintAndDate(syncTeacher.getFingerPrint(), DynamicData.getDate());
//                    saveClockOut(clockOut, finger, clockOutRepository, syncTeacher); // save clock in finger print
//                } else {
//                    Log_Message("Teacher has Employee Number", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                    }.getClass().getEnclosingMethod()).getName());
//                    SyncClockOut clockOut = clockOutRepository.getSyncClockOutByEmployeeNumberAndDate(syncTeacher.getEmployeeNumber(), DynamicData.getDate());
//                    saveClockOut(clockOut, finger, clockOutRepository, syncTeacher); // clock teacher since there is no clock in today
//                }
//            } catch (ExecutionException | InterruptedException e) {
//                e.printStackTrace();
//                Log_Message("There was error", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                }.getClass().getEnclosingMethod()).getName());
//                Toast.makeText(FingerPrintActivity.this, "There was Errors", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            Log_Message("No Teacher Found with Finger Print", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//            }.getClass().getEnclosingMethod()).getName());
//            Toast.makeText(FingerPrintActivity.this, "No Records Found", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private SyncClockOut getSyncClockOutByFingerPrintAndDate(byte[] fingerPrint, String date) {
//        Log_Message("Finding ClockOut By Fingerprint for Today", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//        }.getClass().getEnclosingMethod()).getName());
//        List<SyncClockOut> syncClockOuts = null;
//        SyncClockOut clockOut = null;
//        try {
//            syncClockOuts = ClockOutRepository.getInstance(TelaRoomDatabase.getInstance(this)).getClockOutsByDate(date);
//            if ( mCurrentDevice != null) {
//                Log_Message("BioMini device is Available", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                }.getClass().getEnclosingMethod()).getName());
//                for (SyncClockOut clock: syncClockOuts) {
//                    if (mCurrentDevice.verify(clock.getFingerPrint(), clock.getFingerPrint().length, fingerPrint, fingerPrint.length)) {
//                        Log_Message("Clocked Out Found breaking the loop", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                        }.getClass().getEnclosingMethod()).getName());
//                        clockOut = clock;
//                        break;
//                    }
//                }
//            } else  {
//                Toast.makeText(FingerPrintActivity.this, "Device Disconnected", Toast.LENGTH_SHORT).show();
//                Log_Message("BioMini Device Not Available", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//                }.getClass().getEnclosingMethod()).getName());
//            }
//        } catch (ExecutionException | InterruptedException e) {
//            e.printStackTrace();
//        }
//        Log_Message("Clocked Out -" + String.valueOf(clockOut == null), String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//        }.getClass().getEnclosingMethod()).getName());
//        return clockOut;
//    }
//
//    private void saveClockOut(SyncClockOut clockOut, byte[] fingerPrint, ClockOutRepository clockOutRepository, SyncTeacher teacher) {
//        Log_Message("About to Clock Out", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//        }.getClass().getEnclosingMethod()).getName());
//
//        if (clockOut == null) {
//            clockOutRepository.insertSynClockOut(new SyncClockOut(
//                    DynamicData.getDate(),
//                    DynamicData.getDay(),
//                    DynamicData.getTime(),
//                    getIntent().getStringExtra(TEACHER_COMMENT),
//                    teacher.getEmployeeNumber(),
//                    teacher.getEmployeeNumber(),
//                    DynamicData.getLatitude(),
//                    DynamicData.getLongitude(),
//                    DynamicData.getSchoolID(),
//                    DynamicData.getSchoolName(),
//                    teacher.getFirstName(),
//                    teacher.getLastName(),
//                    fingerPrint
//            ));
//            Toast.makeText(FingerPrintActivity.this, "Clocked Out SuccessFully", Toast.LENGTH_SHORT).show();

//        } else  {
//            Toast.makeText(FingerPrintActivity.this, "Teacher Already Clocked In", Toast.LENGTH_SHORT).show();
//            Log_Message("Teacher Already Clocked In", String.valueOf(new Throwable().getStackTrace()[0].getLineNumber()), Objects.requireNonNull(new Object() {
//            }.getClass().getEnclosingMethod()).getName());
//        }
//        Intent intent = new Intent();
//        intent.putExtra(TEACHER_ROLE, teacher.getRole());
//        setResult(RESULT_OK, intent);
//        intent.putExtra(EMPLOYEEE_NUMBER, teacher.getEmployeeNumber());
//        finish();
//    }
}


//result = {byte[627]@6069}
//        0 = 69
//        1 = 32
//        2 = -111
//        3 = 27
//        4 = -125
//        5 = 0
//        6 = 83
//        7 = 66
//        8 = 26
//        9 = 65
//        10 = -32
//        11 = 7
//        12 = 3
//        13 = 67
//        14 = 4
//        15 = 96
//        16 = -78
//        17 = -118
//        18 = 49
//        19 = -58
//        20 = 48
//        21 = 118
//        22 = 9
//        23 = 42
//        24 = 70
//        25 = 112
//        26 = 29
//        27 = -123
//        28 = 55
//        29 = -58
//        30 = -32
//        31 = 30
//        32 = -123
//        33 = 20
//        34 = -121
//        35 = 48
//        36 = -118
//        37 = 24
//        38 = 27
//        39 = 71
//        40 = -80
//        41 = 125
//        42 = -117
//        43 = 19
//        44 = 7
//        45 = -47
//        46 = -109
//        47 = -83
//        48 = 20
//        49 = -120
//        50 = 80
//        51 = 126
//        52 = -56
//        53 = 18
//        54 = 8
//        55 = -128
//        56 = -101
//        57 = 72
//        58 = 59
//        59 = -56
//        60 = -64
//        61 = 29
//        62 = -127
//        63 = 15
//        64 = -120
//        65 = -47
//        66 = -99
//        67 = 19
//        68 = 28
//        69 = -119
//        70 = -127
//        71 = 118
//        72 = -117
//        73 = 22
//        74 = 74
//        75 = 32
//        76 = 112
//        77 = -92
//        78 = 60
//        79 = -118
//        80 = 96
//        81 = 118
//        82 = 9
//        83 = 25
//        84 = -117
//        85 = -80
//        86 = 105
//        87 = -100
//        88 = 27
//        89 = -52
//        90 = -47
//        91 = 100
//        92 = -109
//        93 = 12
//        94 = -115
//        95 = 32
//        96 = 76
//        97 = -120
//        98 = 29
//        99 = 13

//result = {byte[493]@6202}
//        0 = 69
//        1 = 22
//        2 = -113
//        3 = 25
//        4 = -112
//        5 = 0
//        6 = 83
//        7 = 66
//        8 = 33
//        9 = 65
//        10 = -80
//        11 = 114
//        12 = 6
//        13 = 26
//        14 = 1
//        15 = -48
//        16 = 26
//        17 = -126
//        18 = 37
//        19 = -126
//        20 = -64
//        21 = 24
//        22 = 7
//        23 = 11
//        24 = -126
//        25 = -32
//        26 = 123
//        27 = 11
//        28 = 11
//        29 = -124
//        30 = -79
//        31 = 115
//        32 = -120
//        33 = 39
//        34 = -58
//        35 = 32
//        36 = 114
//        37 = 6
//        38 = 8
//        39 = -120
//        40 = 33
//        41 = 101
//        42 = -113
//        43 = 8
//        44 = 73
//        45 = 64
//        46 = 4
//        47 = -115
//        48 = 30
//        49 = -53
//        50 = 80
//        51 = 21
//        52 = 7
//        53 = 14
//        54 = -53
//        55 = -48
//        56 = 102
//        57 = 3
//        58 = 52
//        59 = 11
//        60 = -47
//        61 = 23
//        62 = -120
//        63 = 17
//        64 = 76
//        65 = 0
//        66 = 12
//        67 = 5
//        68 = 37
//        69 = 76
//        70 = 49
//        71 = 114
//        72 = 5
//        73 = 45
//        74 = 13
//        75 = 97
//        76 = 24
//        77 = -120
//        78 = 34
//        79 = 14
//        80 = 33
//        81 = 22
//        82 = -122
//        83 = 47
//        84 = -50
//        85 = -112
//        86 = 29
//        87 = -123
//        88 = 54
//        89 = -114
//        90 = -63
//        91 = 117
//        92 = 10
//        93 = 14
//        94 = 79
//        95 = 48
//        96 = 16
//        97 = 11
//        98 = 23
//        99 = 15

//result = { 69, 22, -113 , 25, -112, 0,83,66,33,65,-80, 114,6,26,1,-48,26,-126 ,37,-126,-64 ,24 ,7,
//        11,-126,-32, 123, 11, 11, -124, -79, 115, -120, 39, -58, 32, 114, 6, 8, -120, 33, 101, -113,
//        8, 73, 64, 4, -115, 30, -53, 80, 21, 7, 14, -53, -48, 102, 3, 52, 11, -47, 23, -120, 17, 76,
//        0, 12, 5, 37, 76, 49, 114, 5, 45, 13, 97, 24, -120, 34, 14, 33, 22, -122, 47, -50, -112, 29,
//        -123, 54, -114, -63, 117, 10, 14, 79, 48, 16, 11, 23, 15 }