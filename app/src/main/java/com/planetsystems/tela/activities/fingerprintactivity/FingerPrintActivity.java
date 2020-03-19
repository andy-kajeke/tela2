package com.planetsystems.tela.activities.fingerprintactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.planetsystems.tela.R;
import com.suprema.BioMiniFactory;
import com.suprema.IBioMiniDevice;

public class FingerPrintActivity extends AppCompatActivity {
    public static final String ACTION_USB_PERMISSION = "com.planetsystems.tela.activities.fingerprintactivity.ACTION_USB_PERMISSION";
    public static final boolean mUsbExternalUSBManager = false;
    private UsbManager usbManager;
    private PendingIntent pendingIntent;

    private static BioMiniFactory bioMiniFactory;
    public static final int REQUEST_WRITE_PERMISSION = 3456;
    public IBioMiniDevice iBioMiniDevice;

    private TextView logView;
    private TextView statusView;
    private ImageView imageView;
    private Button captureButton, actionButton;

    private IBioMiniDevice.CaptureOption captureOption = new IBioMiniDevice.CaptureOption();
    private CustomCaptureResponder customCaptureResponder;
    private DefaultCaptureResponder defaultCaptureResponder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_print);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_finger_print, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if ( item.getItemId() == R.id.itemSettingId) {
            Toast.makeText(this, "Setting clicked", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    synchronized public void printState(final CharSequence charSequence) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (statusView != null) {
                    statusView.setText(charSequence);
                }
            }
        });
    }
}
