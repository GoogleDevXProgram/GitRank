plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")

}


android {
    compileSdkVersion(buildConfigVersions.compileSdkVersion)
    buildToolsVersion(buildConfigVersions.buildToolsVersion)

    defaultConfig {
        applicationId = "com.example.gitrank"
        minSdkVersion(buildConfigVersions.minSdkVersion)
        targetSdkVersion(buildConfigVersions.targetSdkVersion)
        versionCode = buildConfigVersions.versionCode
        versionName = buildConfigVersions.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {

    implementation(kotlinDependencies.legacySupportV4)
    implementation(kotlinDependencies.lifecycleExtention)
    implementation(kotlinDependencies.lifecycleViewmodel)
    implementation(kotlinDependencies.kotlin)
    implementation(kotlinDependencies.coroutines)

    testImplementation("junit:junit:4.+")
    androidTestImplementation ("androidx.test.ext:junit:1.1.2")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.3.0")


    implementation(materialDesignDependencies.materialDesign)

    implementation(navigationDependencies.navigationFragmentKtx)
    implementation(navigationDependencies.navigationUi)
    implementation(navigationDependencies.fragment)


    //cardview
    implementation(AndroidXDependencies.cardview)

    //Glide
    implementation(glideDepencies.glide)
    implementation(glideDepencies.glideCompiler)


    //hilt
    implementation(hiltDependencies.hiltAdroidImpl)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    kapt(hiltDependencies.hiltKaptCompiler)
    implementation(hiltDependencies.hiltLifecycleViewmodel)
    kapt(hiltDependencies.hiltCompilerKapt)


    //Room Db
    implementation(roomDependencies.runtime)

    kapt(roomDependencies.kapt)
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation(roomDependencies.impl)


    //Coroutines
    implementation(coroutineDependencies.coroutineAndroid)
    implementation(coroutineDependencies.lifecycleLivedata)


    //ViewPager Indicator
    implementation(IndicatorDependency.indicator)


    implementation(AndroidXDependencies.viewpager2)

    implementation(AndroidXDependencies.appCompat)
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation(AndroidXDependencies.constraintLayout)

    implementation(RetrofitDependencies.retrofit)
    implementation(RetrofitDependencies.retrofitGsonConverter)
    implementation(RetrofitDependencies.retrofitLoggingInterceptor)


    implementation(TimberDependency.timber)

}