package com.planetsystems.tela.activities.fingerprintactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.planetsystems.tela.R;

public class FingerPrintActivity extends AppCompatActivity {
    public static final String ACTION_USB_PERMISSION = "com.planetsystems.tela.activities.fingerprintactivity.ACTION_USB_PERMISSION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_print);
    }
}