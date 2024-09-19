#!/bin/bash 
set -e
VERSION="v3.30.0";
curl -O -L https://dl.cryptlex.com/downloads/${VERSION}/LexActivator-Android.zip
unzip LexActivator-Android.zip -d ./android
cp ./android/libs/clang/arm64-v8a/libLexActivator.so lexactivator/src/main/jniLibs/arm64-v8a
cp ./android/libs/clang/armeabi-v7a/libLexActivator.so lexactivator/src/main/jniLibs/armeabi-v7a
rm LexActivator-Android.zip
rm -r android

if [ -z "$ANDROID_NDK_ROOT" ]
then
      echo "\$ANDROID_NDK_ROOT is empty"
fi

# LibC++ from NDK in bundle.
cp $ANDROID_NDK_ROOT/toolchains/llvm/prebuilt/linux-x86_64/sysroot/usr/lib/aarch64-linux-android/libc++_shared.so lexactivator/src/main/jniLibs/arm64-v8a
cp $ANDROID_NDK_ROOT/toolchains/llvm/prebuilt/linux-x86_64/sysroot/usr/lib/arm-linux-androideabi/libc++_shared.so lexactivator/src/main/jniLibs/armeabi-v7a


