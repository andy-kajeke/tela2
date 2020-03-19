package com.planetsystems.tela.activities.fingerprintactivity;

import android.content.Context;
import android.hardware.usb.UsbManager;

import com.suprema.BioMiniFactory;

public class TelaBioMiniFactory extends BioMiniFactory {
    private OnTelaDeviceChangeListener changeListener;
    public TelaBioMiniFactory(Context context) {
        super(context);
        if ( changeListener != null ) {
            changeListener = (OnTelaDeviceChangeListener) context;
        }
    }

    public TelaBioMiniFactory(Context context, UsbManager usbManager) {
        super(context, usbManager);
        if ( changeListener != null ) {
            changeListener = (OnTelaDeviceChangeListener) context;
        }
    }

    @Override
    public void onDeviceChange(DeviceChangeEvent deviceChangeEvent, Object contest) {
        changeListener.onTelaDeviceChange(deviceChangeEvent, contest);
    }

    public interface OnTelaDeviceChangeListener {
        void onTelaDeviceChange(DeviceChangeEvent deviceChangeEvent, Object contest);
    }
}
