#include <jni.h>
#include <string>
#include "lexactivator/include/LexActivator.h"
#include <android/log.h>

void initLexActivator(JNIEnv* env)
{
    int status;

    status = SetJniEnv(env);
    if (LA_OK != status)
    {
        __android_log_print(ANDROID_LOG_ERROR, "CTX", "Error code: %d", status);
        exit(status);
    }

    //    SetProductData
    status = SetProductData("PRODUCT_DATA");
    if (LA_OK != status)
    {
        __android_log_print(ANDROID_LOG_ERROR, "CTX", "Error code: %d", status);
        exit(status);
    }

    //    SetProductId
    status = SetProductId("PRODUCT_ID", LA_USER);
    if (LA_OK != status)
    {
        __android_log_print(ANDROID_LOG_ERROR, "CTX", "Error code: %d", status);
        exit(status);
    }


}

int validateLicenseStatus()
{
    int status = IsLicenseGenuine();
    if (LA_OK == status) {
        unsigned int expiryDate = 0;
        GetLicenseExpiryDate(&expiryDate);
        int daysLeft = (expiryDate - time(NULL)) / 86400;
        __android_log_print(ANDROID_LOG_INFO, "CTX","Days left: %d\n", daysLeft);
        __android_log_print(ANDROID_LOG_INFO, "CTX","License is genuinely activated!");
    }
    else if (LA_EXPIRED == status)
    {
        __android_log_print(ANDROID_LOG_INFO, "CTX","License is genuinely activated but has expired!");
    }
    else if (LA_SUSPENDED == status)
    {
        __android_log_print(ANDROID_LOG_INFO, "CTX","License is genuinely activated but has been suspended!");
    }
    else if (LA_GRACE_PERIOD_OVER == status)
    {
        __android_log_print(ANDROID_LOG_INFO, "CTX","License is genuinely activated but grace period is over!");
    }
    else
    {
        int trialStatus;
        trialStatus = IsTrialGenuine();
        if (LA_OK == trialStatus)
        {
            unsigned int trialExpiryDate = 0;
            GetTrialExpiryDate(&trialExpiryDate);
            int daysLeft = (trialExpiryDate - time(NULL)) / 86400;
            __android_log_print(ANDROID_LOG_INFO, "CTX","Trial days left: %d", daysLeft);
        }
        else if (LA_TRIAL_EXPIRED == trialStatus)
        {
            __android_log_print(ANDROID_LOG_INFO, "CTX","Trial has expired!");

            // Time to buy the license and activate the app
        }
        else
        {
            __android_log_print(ANDROID_LOG_INFO, "CTX","Either trial has not started or has been tampered!");
            // Activating the trial
        }
    }
    return status;
}

extern "C" JNIEXPORT jint JNICALL
Java_com_example_anative_MainActivity_getActivationStatus(
        JNIEnv* env,
        jobject /* this */) {
    initLexActivator(env);

    return validateLicenseStatus();
}

