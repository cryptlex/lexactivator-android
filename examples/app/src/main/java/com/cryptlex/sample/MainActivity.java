package com.cryptlex.sample;

import androidx.appcompat.app.AppCompatActivity;

import com.cryptlex.lexactivator.LexActivator;
import com.cryptlex.lexactivator.LexActivatorException;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.Instant;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize handles to UI elements
        TextView tvStatus = (TextView) findViewById(R.id.textView);
        Button button = (Button) findViewById(R.id.buttonActivate);
        EditText licenseEntry = findViewById(R.id.editLicense);

        //Set stdout to display on TextView tvStatus
        System.setOut(new PrintStream(new OutputStream() {

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            @Override public void write(int oneByte) throws IOException {
                outputStream.write(oneByte);

                tvStatus.setText(new String(outputStream.toByteArray()));
            }
        }));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int status;
                    String license = String.valueOf(licenseEntry.getText());
                    LexActivator.SetProductData("PRODUCT_DATA");
                    LexActivator.SetProductId(license, LexActivator.LA_USER);

                    status = LexActivator.IsLicenseGenuine();

                    if (LexActivator.LA_OK == status) {
                        System.out.println("License is genuinely activated!");

                    } else if (LexActivator.LA_EXPIRED == status) {
                        System.out.println("License is genuinely activated but has expired!");
                    } else if (LexActivator.LA_GRACE_PERIOD_OVER == status) {
                        System.out.println("License is genuinely activated but grace period is over!");
                    } else if (LexActivator.LA_SUSPENDED == status) {
                        System.out.println("License is genuinely activated but has been suspended!");
                    } else {
                        int trialStatus;
                        trialStatus = LexActivator.IsTrialGenuine();
                        if (LexActivator.LA_OK == trialStatus) {
                            int trialExpiryDate = LexActivator.GetTrialExpiryDate();
                            long daysLeft = 0;
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                daysLeft = (trialExpiryDate - Instant.now().getEpochSecond()) / 86400;
                            }
                            System.out.println("Trial days left: " + daysLeft);
                        } else if (LexActivator.LA_TRIAL_EXPIRED == trialStatus) {
                            System.out.println("Trial has expired!");

                            LexActivator.SetLicenseKey("PASTE_LICENSE_KEY");
                            LexActivator.SetActivationMetadata("key1", "value1");

                            status = LexActivator.ActivateLicense(); // Ideally on a button click inside a dialog
                            if (LexActivator.LA_OK == status || LexActivator.LA_EXPIRED == status
                                    || LexActivator.LA_SUSPENDED == status) {
                                System.out.println("License activated successfully: " + status);
                            } else {
                                System.out.println("License activation failed: " + status);
                            }
                        } else {
                            System.out.println("Either trial has not started or has been tampered!");

                            trialStatus = LexActivator.ActivateTrial(); // Ideally on a button click inside a dialog
                            if (LexActivator.LA_OK == trialStatus) {
                                int trialExpiryDate = LexActivator.GetTrialExpiryDate();
                                long daysLeft = 0;
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    daysLeft = (trialExpiryDate - Instant.now().getEpochSecond()) / 86400;
                                }
                                System.out.println("Trial days left: " + daysLeft);
                            } else {
                                // Trial was tampered or has expired
                                System.out.println("Trial activation failed: " + trialStatus);
                            }
                        }
                    }
                } catch (LexActivatorException ex) {
                    System.out.println(ex.getCode() + ": " + ex.getMessage());
                }
            }
        });
    }
}