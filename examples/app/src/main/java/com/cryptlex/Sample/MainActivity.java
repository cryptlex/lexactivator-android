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
        LexActivator.SetDataDirectory(getApplicationContext().getFilesDir().getAbsolutePath());
        LexActivator.SetProductData("QjVFOUU4ODkyRDZDQ0E5OEUzMTkxRjI1MDU2QTI0NEQ=.QZId4v+XnZXqm8lKHBJi4R3Q2iCYC6L0ZPi6/MZEr7Oj5GeIx3Ze1ii/Zf7vztFYnXwtYLcyY+qu18VZ5IxcxOzrtJZvv21hwocytvxDyJajt9FW6MdmamMNsXVf2XC1gG2xa6pq0qIXiv0VxWsuxAmgKWK0g8DKDRQgJSx3B5yITaeg3u4urFWsF10jZsmFeA/xwMG8vs9/tw9XHgzUbnt5eWZdWhScT+yVoEPx4OnUJ74joD2awHUzCeW3KjGlJOhTM/AIGKE3eeFPHSWcvKBliLcHT7QO2NHYPWs6OJ+K5PMuGurFFafb4S3TpJLLYmx1G4hBxBbmsz0IA/i1OQKO97pmwYYPcYbQV1jOuzksuaFZlvsNtNnRv/QxmJgLR1reXKmJtC29cYI6OErO/sJIpd95Nbwj4Ww4F469qANI3fw0FqUABDvd5VfBLnMJaYyxIuOt4PZslWoE8lfY72HJUIcFuSMao8MXeRMp/aFRPVXca7qcfO66mW5yHHn5rRtsMjx/A/xyC++7LQr6ytY3OIM2GK6nzy0fYlMu6wOY3w5r8YpKnlWVXIXyKC0RW3EgtrIPiPwf5bftr+IDh9Np6l8IQzfd/J2MgluGebgCQK6zMhn+BmwUorixhkrdFEAImLcMYwlaMk0pdiSUCZjireJ5Q+jTRCWSMPPeneBGRpObzcCNVSlDOku9K8v/CikJqybhGd95nZJc7dtvwR/OPnQn6GxaPFNlkRKLAkc=");
        LexActivator.SetProductId("74a87f2e-1fdf-46e2-a205-4af3357f553b", LexActivator.LA_USER);
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
        }
    }
}
