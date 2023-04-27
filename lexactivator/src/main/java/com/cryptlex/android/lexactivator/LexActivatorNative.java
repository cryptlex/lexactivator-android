package com.cryptlex.android.lexactivator;

import com.sun.jna.Library;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Native;
import com.sun.jna.JNIEnv;
import java.nio.ByteBuffer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.Callback;

public class LexActivatorNative implements Library {

    static{
        Native.register(LexActivatorNative.class,NativeLibrary.getInstance("LexActivator"));
    }
    
    public interface CallbackType extends Callback {

        void invoke(int status);
    }

    public interface ReleaseUpdateCallbackType extends Callback {
        
        void invoke(int status, ByteBuffer releaseJson, Object unused);
    }

    public static native int SetJniEnv(JNIEnv jniEnv);

    public static native int SetProductFile(String filePath);

    public static native int SetProductData(String productData);

    public static native int SetProductId(String productId, int flags);

    public static native int SetDataDirectory(String directoryPath);

    public static native int SetCustomDeviceFingerprint(String fingerprint);

    public static native int SetLicenseKey(String licenseKey);

    public static native int SetLicenseUserCredential(String email, String password);

    public static native int SetLicenseCallback(CallbackType callback);

    public static native int SetActivationMetadata(String key, String value);

    public static native int SetTrialActivationMetadata(String key, String value);

    public static native int SetAppVersion(String appVersion);

    public static native int SetReleaseVersion(String releaseVersion);

    public static native int SetReleasePublishedDate(int releasePublishedDate);

    public static native int SetReleasePlatform(String releasePlatform);

    public static native int SetReleaseChannel(String releaseChannel);

    public static native int SetActivationLeaseDuration(int leaseDuration);

    public static native int SetOfflineActivationRequestMeterAttributeUses(String name, int uses);

    public static native int SetNetworkProxy(String proxy);

    public static native int SetCryptlexHost(String host);

    public static native int GetProductMetadata(String key, ByteBuffer value, int length);

    public static native int GetProductVersionName(ByteBuffer name, int length);
    
    public static native int GetProductVersionDisplayName(ByteBuffer name, int length);
    
    public static native int GetProductVersionFeatureFlag(String name, IntByReference enabled, ByteBuffer data, int length);

    public static native int GetLicenseMetadata(String key, ByteBuffer value, int length);

    public static native int GetLicenseMeterAttribute(String name, IntByReference allowedUses, IntByReference totalUses, IntByReference grossUses);

    public static native int GetLicenseKey(ByteBuffer licenseKey, int length);

    public static native int GetLicenseExpiryDate(IntByReference expiryDate);

    public static native int GetLicenseMaintenanceExpiryDate(IntByReference maintenanceExpiryDate);

    public static native int GetLicenseMaxAllowedReleaseVersion(ByteBuffer maxAllowedReleaseVersion, int length);

    public static native int GetLicenseAllowedActivations(IntByReference allowedActivations);

    public static native int GetLicenseTotalActivations(IntByReference totalActivations);

    public static native int GetLicenseUserEmail(ByteBuffer email, int length);

    public static native int GetLicenseUserName(ByteBuffer name, int length);

    public static native int GetLicenseUserCompany(ByteBuffer company, int length);

    public static native int GetLicenseUserMetadata(String key, ByteBuffer value, int length);

    public static native int GetLicenseOrganizationName(ByteBuffer name, int length);

    public static native int GetLicenseOrganizationAddressInternal(ByteBuffer address, int length);

    public static native int GetLicenseType(ByteBuffer licenseType, int length);

    public static native int GetActivationMetadata(String key, ByteBuffer value, int length);

    public static native int GetActivationMode(ByteBuffer initialMode, int initialModeLength, ByteBuffer currentMode, int currentModeLength);

    public static native int GetActivationMeterAttributeUses(String name, IntByReference uses);

    public static native int GetServerSyncGracePeriodExpiryDate(IntByReference expiryDate);

    public static native int GetTrialActivationMetadata(String key, ByteBuffer value, int length);

    public static native int GetTrialExpiryDate(IntByReference trialExpiryDate);

    public static native int GetTrialId(ByteBuffer trialId, int length);

    public static native int GetLocalTrialExpiryDate(IntByReference trialExpiryDate);

    public static native int GetLibraryVersion(ByteBuffer libraryVersion, int length);

    public static native int CheckForReleaseUpdate(String platform, String version, String channel, CallbackType callback);

    public static native int CheckReleaseUpdateInternal(ReleaseUpdateCallbackType releaseUpdateCallback, int releaseFlags, Object userData);

    public static native int ActivateLicense();

    public static native int ActivateLicenseOffline(String filePath);

    public static native int GenerateOfflineActivationRequest(String filePath);

    public static native int DeactivateLicense();

    public static native int GenerateOfflineDeactivationRequest(String filePath);

    public static native int IsLicenseGenuine();

    public static native int IsLicenseValid();

    public static native int ActivateTrial();

    public static native int ActivateTrialOffline(String filePath);

    public static native int GenerateOfflineTrialActivationRequest(String filePath);

    public static native int IsTrialGenuine();

    public static native int ActivateLocalTrial(int trialLength);

    public static native int IsLocalTrialGenuine();

    public static native int ExtendLocalTrial(int trialExtensionLength);

    public static native int IncrementActivationMeterAttributeUses(String name, int increment);

    public static native int DecrementActivationMeterAttributeUses(String name, int decrement);

    public static native int ResetActivationMeterAttributeUses(String name);

    public static native int Reset();
}
