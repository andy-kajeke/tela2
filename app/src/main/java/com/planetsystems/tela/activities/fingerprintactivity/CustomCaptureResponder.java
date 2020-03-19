package com.planetsystems.tela.activities.fingerprintactivity;

import android.content.Context;
import android.graphics.Bitmap;

import com.suprema.CaptureResponder;
import com.suprema.IBioMiniDevice;

public class CustomCaptureResponder extends CaptureResponder {
    private OnCustomCaptureResponderResponseListener responseListener;
    public CustomCaptureResponder(Context context) {
        super();
        responseListener = (OnCustomCaptureResponderResponseListener) context;
    }

    @Override
    public void onCapture(Object contest, IBioMiniDevice.FingerState fingerState) {
        super.onCapture(contest, fingerState);
        responseListener.onCapture(contest, fingerState);
    }

    @Override
    public boolean onCaptureEx(
            Object contest,
            Bitmap bitmap,
            IBioMiniDevice.TemplateData templateData,
            IBioMiniDevice.FingerState fingerState) {
        responseListener.onCaptureEx(contest, bitmap, templateData, fingerState);
        return super.onCaptureEx(contest, bitmap, templateData, fingerState);
    }

    @Override
    public void onCaptureError(Object contest, int errorCode, String errorMessage) {
        responseListener.onCaptureError(contest, errorCode, errorMessage);
        super.onCaptureError(contest, errorCode, errorMessage);
    }

    public interface OnCustomCaptureResponderResponseListener {
        void onCapture(Object contest, IBioMiniDevice.FingerState fingerState);
        boolean onCaptureEx(Object contest, Bitmap bitmap, IBioMiniDevice.TemplateData templateData, IBioMiniDevice.FingerState fingerState);
        void onCaptureError(Object contest, int errorCode, String errorMessage);
    }
}
