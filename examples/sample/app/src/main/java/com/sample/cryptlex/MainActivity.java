package com.sample.cryptlex;
import androidx.appcompat.app.AppCompatActivity;
import com.cryptlex.android.lexactivator.LexActivator;
import com.cryptlex.android.lexactivator.LexActivatorException;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private void initLexActivator() throws LexActivatorException {
        LexActivator.SetProductData("PASTE_PRODUCT_DATA");
        LexActivator.SetProductId("PASTE_PRODUCT_ID", LexActivator.LA_USER);
    }

    public void activateLicense() {
        try {
            LexActivator.SetLicenseKey("PASTE_LICENSE_KEY");
            LexActivator.SetActivationMetadata("key1", "value1");
            LexActivator.SetActivationMetadata("key2", "value2");
            int status = LexActivator.ActivateLicense();
            if (LexActivator.LA_OK == status || LexActivator.LA_EXPIRED == status || LexActivator.LA_SUSPENDED == status) {
                Log.i("I","License activated successfully: " + status);
            } else {
                Log.i("I","License activation failed: " + status);
            }
        } catch (LexActivatorException ex) {
            Log.i("I","License activation failed! Error code: " + ex.getCode() + " Error message: " + ex.getMessage());
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            initLexActivator();
            int status = LexActivator.IsLicenseGenuine();
            if (LexActivator.LA_OK == status) {
                Log.i("I","License is genuinely activated!");
            } else if (LexActivator.LA_EXPIRED == status) {
                Log.i("I","License is genuinely activated but has expired!");
            } else if (LexActivator.LA_GRACE_PERIOD_OVER == status) {
                Log.i("I","License is genuinely activated but grace period is over!");
            } else if (LexActivator.LA_SUSPENDED == status) {
                Log.i("I","License is genuinely activated but has been suspended!");
            } else {
                Log.i("I","License is not activated. Error code: " + status);
            }
        } catch (LexActivatorException ex) {
            Log.e("E","Error code: " + ex.getCode() + " Error message: " + ex.getMessage());
        }

    }
}