object BuildVersion {
    const val compileSdk = 31
    const val buildTools = "31.0.0"
    const val minSdk = 21
    const val targetSdk = 31
    const val versionCode = 1
    const val versionName = "1.0.0"
    const val appId = "com.base.rxhttp_mvvm"
}

object Versions {
    const val kotlin = "1.6.0"
    const val core_ktx = "1.3.2"
    const val appcompat = "1.2.0"
    const val material = "1.3.0"
    const val constraintlayout = "2.0.4"

    const val okhttp = "4.9.1"
    const val rxhttp = "2.8.5"

    const val rxhttp_compiler = "2.8.5"
    const val coroutines_core = "1.5.2"
    const val coroutines_android = "1.5.2"

    const val lifecycle_ktx = "2.3.1"
    const val lifecycle_common = "2.3.1"
    const val lifecycle_extensions = "2.2.0"

    const val viewmodel_ktx = "2.3.0"
    const val livedata_ktx = "2.2.0"
}

object Libs {
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val rxhttp = "com.github.liujingxing.rxhttp:rxhttp:${Versions.rxhttp}"
    const val rxhttp_compiler = "com.github.liujingxing.rxhttp:rxhttp-compiler:${Versions.rxhttp_compiler}"
    //协程基础库
    const val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_core}"
    const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_android}"
    //lifecycle
    const val lifecycle_ktx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_ktx}"
    const val lifecycle_common = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle_common}"
    const val lifecycle_extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle_extensions}"
    // viewModel
    const val viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewmodel_ktx}"
    // liveData
    const val livedata_ktx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.livedata_ktx}"
}
