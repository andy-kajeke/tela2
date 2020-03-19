package com.planetsystems.tela.activities.fingerprintactivity;

import android.graphics.Bitmap;

import com.suprema.CaptureResponder;
import com.suprema.IBioMiniDevice;

public class CustomCaptureResponder extends CaptureResponder {
    public CustomCaptureResponder() {
        super();
    }

    @Override
    public void onCapture(Object contest, IBioMiniDevice.FingerState fingerState) {
        super.onCapture(contest, fingerState);
    }

    @Override
    public boolean onCaptureEx(
            Object contest,
            Bitmap bitmap,
            IBioMiniDevice.TemplateData templateData,
            IBioMiniDevice.FingerState fingerState) {
        return super.onCaptureEx(contest, bitmap, templateData, fingerState);
    }

    @Override
    public void onCaptureError(Object contest, int errorCode, String errorMessage) {
        super.onCaptureError(contest, errorCode, errorMessage);
    }

    public interface OnCustomCaptureResponderResponseListener {
        void onCapture(Object contest, IBioMiniDevice.FingerState fingerState);
        boolean onCaptureEx(Object contest, Bitmap bitmap, IBioMiniDevice.TemplateData templateData, IBioMiniDevice.FingerState fingerState);
        void onCaptureError(Object contest, int errorCode, String errorMessage);
    }
}
