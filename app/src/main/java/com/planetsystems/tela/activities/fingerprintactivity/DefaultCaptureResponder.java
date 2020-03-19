package com.planetsystems.tela.activities.fingerprintactivity;

import android.content.Context;
import android.graphics.Bitmap;

import com.suprema.CaptureResponder;
import com.suprema.IBioMiniDevice;

public class DefaultCaptureResponder extends CaptureResponder {
    private OnDefaultCaptureResponderResponseListener responseListener;


    public DefaultCaptureResponder(Context context) {
        responseListener = (OnDefaultCaptureResponderResponseListener) context;
    }

    @Override
    public void onCapture(Object o, IBioMiniDevice.FingerState fingerState) {
        super.onCapture(o, fingerState);
        responseListener.onDefaultCaptureResponderCapture(o, fingerState);
    }

    @Override
    public boolean onCaptureEx(
            Object o,
            Bitmap bitmap,
            IBioMiniDevice.TemplateData templateData,
            IBioMiniDevice.FingerState fingerState) {

        return responseListener.onDefaultCaptureResponderCaptureEx(o, bitmap, templateData, fingerState);
    }

    @Override
    public void onCaptureError(Object contest, int errorCode, String errorMessage) {
        responseListener.onDefaultCaptureResponderCaptureError(contest, errorCode, errorMessage);
    }

    public interface OnDefaultCaptureResponderResponseListener {
        boolean onDefaultCaptureResponderCaptureEx(Object o, Bitmap bitmap, IBioMiniDevice.TemplateData templateData, IBioMiniDevice.FingerState fingerState);
        void onDefaultCaptureResponderCaptureError(Object contest, int errorCode, String errorMessage);
        void onDefaultCaptureResponderCapture(Object o, IBioMiniDevice.FingerState fingerState);
    }
}
