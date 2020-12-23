//
// Created by 徐奇 on 2020/12/22.
//

#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_androiddemo_MainActivity_testNdk(JNIEnv* env, jobject, jstring) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
