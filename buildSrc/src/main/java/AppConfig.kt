 object buildConfigVersions {

    val compileSdkVersion = 30
    val buildToolsVersion = "30.0.2"
    val minSdkVersion = 21
    val targetSdkVersion = 30
    val versionCode = 1
    val versionName = "1.0"
}


object kotlinDependencies {

    val legacySupportV4 =
        "androidx.legacy:legacy-support-v4:${DependencyVersions.legacysupportv4Version}"
    val lifecycleExtention =
        "androidx.lifecycle:lifecycle-extensions:${DependencyVersions.lifecycleextentionVersion}"
    val lifecycleViewmodel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${DependencyVersions.lifecycleViewmodel}"

    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${DependencyVersions.kotlin_version}"
    val coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${DependencyVersions.coroutinesAdroidVersion}"
}


object materialDesignDependencies {
    val materialDesign =
        "com.google.android.material:material:${DependencyVersions.materialDesignVersion}"
}

object coroutineDependencies {
    val coroutineAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${DependencyVersions.coroutineAndroid}"
    val lifecycleLivedata =
        "androidx.lifecycle:lifecycle-livedata-ktx:${DependencyVersions.lifecycleLivedata}"
}


object RetrofitDependencies {
    val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
    val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:2.9.0"
    val retrofitLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.9.0"

}


object AndroidXDependencies {

    val appCompat = "androidx.appcompat:appcompat:${DependencyVersions.appCompatVersion}"

    val cardview = "androidx.cardview:cardview:${DependencyVersions.cardviewVersion}"
    val viewpager2 =
        "androidx.viewpager2:viewpager2:${DependencyVersions.viewpager2Version}"
    val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${DependencyVersions.constraintLayoutVersion}"

}

object hiltDependencies {

    val hiltAdroidImpl = "com.google.dagger:hilt-android:${DependencyVersions.hiltVersion}"
    val hiltKaptCompiler =
        "com.google.dagger:hilt-android-compiler:${DependencyVersions.hiltVersion}"
    val hiltLifecycleViewmodel =
        "androidx.hilt:hilt-lifecycle-viewmodel:${DependencyVersions.daggerHiltLifecycleViewmodelVersion}"
    val hiltCompilerKapt =
        "androidx.hilt:hilt-compiler:${DependencyVersions.daggerHiltLifecycleViewmodelVersion}"

}


object navigationDependencies {

    val navigationFragmentKtx =
        "androidx.navigation:navigation-fragment-ktx:${DependencyVersions.navVersion}"
    val navigationUi = "androidx.navigation:navigation-ui-ktx:${DependencyVersions.navVersion}"
    val fragment = "androidx.fragment:fragment-ktx:${DependencyVersions.fragmentVersion}"
}


object roomDependencies {

    val runtime = "androidx.room:room-runtime:${DependencyVersions.androidRoomVersion}"
    val kapt = "androidx.room:room-compiler:${DependencyVersions.androidRoomVersion}"
    val impl = "androidx.room:room-ktx:${DependencyVersions.androidRoomVersion}"
}

object glideDepencies {

    val glide = "com.github.bumptech.glide:glide:${DependencyVersions.glideVersion}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${DependencyVersions.glideVersion}"
}

object IndicatorDependency {
    val indicator = "com.github.zhpanvip:viewpagerindicator:${DependencyVersions.indicatorVersion}"
}

object TimberDependency {
    val timber = "com.jakewharton.timber:timber:${DependencyVersions.timberVersion}"
}