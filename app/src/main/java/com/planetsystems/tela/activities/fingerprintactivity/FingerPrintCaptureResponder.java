package com.planetsystems.tela.activities.fingerprintactivity;

import android.content.Context;
import android.graphics.Bitmap;

import com.suprema.CaptureResponder;
import com.suprema.IBioMiniDevice;

public class FingerPrintCaptureResponder extends CaptureResponder {


    FingerPrintCaptureResponder(Context context) {

    }
    public FingerPrintCaptureResponder() {
        super();
    }

    @Override
    public void onCapture(Object o, IBioMiniDevice.FingerState fingerState) {
        super.onCapture(o, fingerState);
    }

    @Override
    public boolean onCaptureEx(Object o, Bitmap bitmap, IBioMiniDevice.TemplateData templateData, IBioMiniDevice.FingerState fingerState) {
        return super.onCaptureEx(o, bitmap, templateData, fingerState);
    }

    @Override
    public void onCaptureError(Object o, int i, String s) {
        super.onCaptureError(o, i, s);
    }


}
