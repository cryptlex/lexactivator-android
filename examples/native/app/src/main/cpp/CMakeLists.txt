cmake_minimum_required(VERSION 3.18.1)
project("anative")

add_library(
        anative
        SHARED
        native-lib.cpp)


find_library(log-lib log)

# Specify the library directories
set(LEX_ARMV7_DIR ${CMAKE_SOURCE_DIR}/lexactivator/libs/armeabi-v7a)
set(LEX_ARM64_DIR ${CMAKE_SOURCE_DIR}/lexactivator/libs/arm64-v8a)
# Specify the library file names
set(LEX_LIB_FILENAME libLexActivator.a)
# Specify the header directory
set(LEX_INCLUDE_DIR lexactivator/include)

# Set the path to the library headers
include_directories(${LEX_INCLUDE_DIR})

# Add the source file(s) for your application
add_library(LexActivator STATIC IMPORTED)

find_library(log-lib log)

if(${ANDROID_ABI} STREQUAL "armeabi-v7a")
    # Link the armv7 library
    set_target_properties(LexActivator PROPERTIES IMPORTED_LOCATION ${LEX_ARMV7_DIR}/${LEX_LIB_FILENAME})
elseif(${ANDROID_ABI} STREQUAL "arm64-v8a")
    # Link the arm64 library
    set_target_properties(LexActivator PROPERTIES IMPORTED_LOCATION ${LEX_ARM64_DIR}/${LEX_LIB_FILENAME})
else()
    message(FATAL_ERROR "Unsupported ABI: ${ANDROID_ABI}")
endif()

target_link_libraries(anative ${log-lib} LexActivator)