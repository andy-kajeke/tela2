package com.planetsystems.tela.activities.fingerprint;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.planetsystems.tela.R;

public class EnrollFingerPrint extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_finger_print);
        setTitle(R.string.capture);
    }
}
