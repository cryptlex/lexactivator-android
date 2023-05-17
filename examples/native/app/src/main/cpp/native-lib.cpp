#include <jni.h>
#include <string>
#include "lexactivator/include/LexActivator.h"
#include <android/log.h>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_anative_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    __android_log_print(ANDROID_LOG_INFO, "CTX", "%s", hello.c_str());
    int ctxJni = SetJniEnv(env);
    __android_log_print(ANDROID_LOG_INFO, "CTX", "JNIEnv: %d", ctxJni);
    return env->NewStringUTF(hello.c_str());
}