package com.planetsystems.tela.activities.fingerprintactivity;

import android.content.Context;
import android.hardware.usb.UsbManager;

import com.suprema.BioMiniFactory;

public class TelaBioMiniFactory extends BioMiniFactory {
    public TelaBioMiniFactory(Context context) {
        super(context);
    }

    public TelaBioMiniFactory(Context context, UsbManager usbManager) {
        super(context, usbManager);
    }

    @Override
    public void onDeviceChange(DeviceChangeEvent deviceChangeEvent, Object o) {

    }
}
