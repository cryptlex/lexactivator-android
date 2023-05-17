#!/bin/bash 
set -e
VERSION="v3.21.2";
FILE_NAME="LexActivator-Static-Android.zip"
curl -O -L https://dl.cryptlex.com/downloads/${VERSION}/${FILE_NAME}
unzip ${FILE_NAME} -d ./android
LIB_DEST_FOLDER="app/src/main/cpp/lexactivator"
cp ./android/libs/clang/arm64-v8a/libLexActivator.a ${LIB_DEST_FOLDER}/libs/arm64-v8a
cp ./android/libs/clang/armeabi-v7a/libLexActivator.a ${LIB_DEST_FOLDER}/libs/armeabi-v7a
cp ./android/headers/*.h ${LIB_DEST_FOLDER}/include

rm ${FILE_NAME}
rm -r android



