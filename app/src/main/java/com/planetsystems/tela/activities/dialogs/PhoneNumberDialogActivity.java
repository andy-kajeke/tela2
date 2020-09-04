package com.planetsystems.tela.activities.dialogs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.planetsystems.tela.R;

public class PhoneNumberDialogActivity extends AppCompatActivity {
    private final String PHONE_NUMBER_RESULT = "com.planetsystems.tela.activities.dialogs.PhoneNumberDialogActivity.PHONE_NUMBER_RESULT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number_dialog);
    }

    public void saveResult(View view) {
        EditText phone = findViewById(R.id.phoneNumber);
        String phoneNumber = phone.toString();
        if (phoneNumber.length() != 10) {
            phone.setError("Invalid Phone Number");
            Toast.makeText(this, String.valueOf(phoneNumber.length()) + phoneNumber, Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent();
            intent.putExtra(PHONE_NUMBER_RESULT, phoneNumber);
            setResult(RESULT_OK, intent);
            finish();
        }

    }
}