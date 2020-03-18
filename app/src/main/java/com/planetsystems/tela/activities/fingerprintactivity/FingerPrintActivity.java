package com.planetsystems.tela.activities.fingerprintactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.planetsystems.tela.R;
import com.suprema.IBioMiniDevice;

public class FingerPrintActivity extends AppCompatActivity implements DeviceBroadcastReceiver.OnDeviceConnectionListener{
    public static final String ACTION_USB_PERMISSION = "com.planetsystems.tela.activities.fingerprintactivity.FingerPrintActivity";
    public IBioMiniDevice.TemplateData teacherCapturedTemplate;
    private Bitmap teacherImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_print);
    }

    @Override
    public void onDeviceConnectionSuccess() {

    }

    @Override
    public void onDeviceConnectionError() {

    }
}
