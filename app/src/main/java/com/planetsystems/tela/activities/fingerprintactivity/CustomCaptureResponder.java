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
        responseListener.onCustomCaptureResponderResponseCapture(contest, fingerState);
    }

    @Override
    public boolean onCaptureEx(
            Object contest,
            Bitmap bitmap,
            IBioMiniDevice.TemplateData templateData,
            IBioMiniDevice.FingerState fingerState) {
        return responseListener.onCustomCaptureResponderResponseCaptureEx(contest, bitmap, templateData, fingerState);

    }

    @Override
    public void onCaptureError(Object contest, int errorCode, String errorMessage) {
        responseListener.onCustomCaptureResponderResponseCaptureError(contest, errorCode, errorMessage);
        super.onCaptureError(contest, errorCode, errorMessage);
    }

    public interface OnCustomCaptureResponderResponseListener {
        void onCustomCaptureResponderResponseCapture(Object contest, IBioMiniDevice.FingerState fingerState);
        boolean onCustomCaptureResponderResponseCaptureEx(Object contest, Bitmap bitmap, IBioMiniDevice.TemplateData templateData, IBioMiniDevice.FingerState fingerState);
        void onCustomCaptureResponderResponseCaptureError(Object contest, int errorCode, String errorMessage);
    }
}
