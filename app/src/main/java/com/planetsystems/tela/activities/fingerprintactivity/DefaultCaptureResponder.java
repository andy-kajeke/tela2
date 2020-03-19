package com.planetsystems.tela.activities.fingerprintactivity;

import android.content.Context;
import android.graphics.Bitmap;

import com.suprema.CaptureResponder;
import com.suprema.IBioMiniDevice;

public class DefaultCaptureResponder extends CaptureResponder {


    DefaultCaptureResponder(Context context) {

    }
    public DefaultCaptureResponder() {
        super();
    }

    @Override
    public void onCapture(Object o, IBioMiniDevice.FingerState fingerState) {
        super.onCapture(o, fingerState);
    }

    @Override
    public boolean onCaptureEx(
            Object o,
            Bitmap bitmap,
            IBioMiniDevice.TemplateData templateData,
            IBioMiniDevice.FingerState fingerState) {

        // return results to the activity
        return true;
    }

    @Override
    public void onCaptureError(Object contest, int errorCode, String errorMessage) {
        super.onCaptureError(contest, errorCode, errorMessage);
    }


}
