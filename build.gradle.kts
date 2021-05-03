// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    val kotlin_version by extra("1.3.72")
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.0.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${DependencyVersions.kotlinVersion}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${DependencyVersions.navVersion}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${DependencyVersions.hiltVersion}")
        classpath("com.google.gms:google-services:4.3.5")

    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven(
                url = "https://jitpack.io"
        )
        maven(url = "url 'https://maven.google.com")
    }
}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}