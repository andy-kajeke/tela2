package com.planetsystems.tela.activities.fingerprint;

import android.content.Context;
import android.graphics.Bitmap;

import com.suprema.CaptureResponder;
import com.suprema.IBioMiniDevice;

public class FingerPrintCaptureResponder extends CaptureResponder {
    private OnFingerPrintCaptureResponseListener listener;
    public FingerPrintCaptureResponder(Context context) {
        super();
        listener = (OnFingerPrintCaptureResponseListener) context;
    }

    @Override
    public void onCapture(Object o, IBioMiniDevice.FingerState fingerState) {
        super.onCapture(o, fingerState);
        listener.onFingerPrintCaptureResponseCapture(o, fingerState);
    }

    @Override
    public boolean onCaptureEx(Object o, Bitmap bitmap, IBioMiniDevice.TemplateData templateData, IBioMiniDevice.FingerState fingerState) {
        return listener.onFingerPrintCaptureResponseCaptureEx(o, bitmap, templateData, fingerState);
    }

    @Override
    public void onCaptureError(Object o, int i, String s) {
        listener.onFingerPrintCaptureResponseCaptureError(o, i, s);
    }

    public interface OnFingerPrintCaptureResponseListener {
        void onFingerPrintCaptureResponseCapture(Object contest, IBioMiniDevice.FingerState fingerState);
        boolean onFingerPrintCaptureResponseCaptureEx(Object o, Bitmap bitmap, IBioMiniDevice.TemplateData templateData, IBioMiniDevice.FingerState fingerState);
        void onFingerPrintCaptureResponseCaptureError(Object contest, int errorCode, String errorMessage);
    }
}
