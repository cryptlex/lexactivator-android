package com.cryptlex.sample;

import com.cryptlex.android.lexactivator.LexActivator;
import com.cryptlex.android.lexactivator.LexActivatorException;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private TextView statusTextView;
    private TextInputEditText licenseKeyEditBox;

    private void initLexActivator() throws LexActivatorException {
        LexActivator.SetProductData("PASTE_PRODUCT_DATA_HERE");
        LexActivator.SetProductId("PASTE_PRODUCT_ID_HERE", LexActivator.LA_USER);
        LexActivator.SetReleaseVersion("1.0.0");  // Set this to the release version of your app
    }

    public void activateLicense(View view) {
        try {
            LexActivator.SetLicenseKey(licenseKeyEditBox.getText().toString().trim());
            LexActivator.SetActivationMetadata("key1", "value1");
            LexActivator.SetActivationMetadata("key2", "value2");
            int status = LexActivator.ActivateLicense();
            if (LexActivator.LA_OK == status || LexActivator.LA_EXPIRED == status || LexActivator.LA_SUSPENDED == status) {
                statusTextView.setText("License activated successfully: " + status);
            } else {
                statusTextView.setText("License activation failed: " + status);
            }
        } catch (LexActivatorException ex) {
            statusTextView.setText("License activation failed! Error code: " + ex.getCode() + " Error message: " + ex.getMessage());
        } catch (Exception ex) {
            statusTextView.setText("License activation failed! Error message: " + ex.getMessage());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        statusTextView = (TextView) findViewById(R.id.statusTextView);
        licenseKeyEditBox = (TextInputEditText) findViewById(R.id.licenseInput);
        try {
            initLexActivator();
            int status = LexActivator.IsLicenseGenuine();
            if (LexActivator.LA_OK == status) {
                statusTextView.setText("License is genuinely activated!");
            } else if (LexActivator.LA_EXPIRED == status) {
                statusTextView.setText("License is genuinely activated but has expired!");
            } else if (LexActivator.LA_GRACE_PERIOD_OVER == status) {
                statusTextView.setText("License is genuinely activated but grace period is over!");
            } else if (LexActivator.LA_SUSPENDED == status) {
                statusTextView.setText("License is genuinely activated but has been suspended!");
            } else {
                statusTextView.setText("License is not activated. Error code: " + status);
            }
        } catch (LexActivatorException ex) {
            statusTextView.setText("Error code: " + ex.getCode() + " Error message: " + ex.getMessage());
        } catch (Exception ex) {
            statusTextView.setText("Error message: " + ex.getMessage());
        }
    }
}
