object Config {

    object Android {
        // Android sdk and version
        const val androidMinSdkVersion = 26
        const val androidTargetSdkVersion = 34
        const val androidCompileSdkVersion = 34
        const val androidBuildToolsVersion = "30.0.2"
    }

    object ClassPaths {
        const val googleServices= "com.google.gms:google-services:${Versions.googleServices}"
        const val androidGradle = "com.android.tools.build:gradle:${Versions.androidVersion}"
        const val kotlinGradle =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
        const val daggerHiltGradle =
            "com.google.dagger:hilt-android-gradle-plugin:${Versions.daggerHilt}"
        const val navigationSafArgsGradle =
            "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.gradleNavigationArgVersion}"
        const val ktLint ="org.jlleitschuh.gradle:ktlint-gradle:${Versions.ktLintVersion}"
        const val pluginGradle = "https://plugins.gradle.org/m2/"
    }

    object Plugins {
        const val android = "com.android.application"
        const val kotlinAndroid = "kotlin-android"
        const val navigationSafArgs = "androidx.navigation.safeargs.kotlin"
        const val kotlinKapt = "kotlin-kapt"
        const val hilt = "dagger.hilt.android.plugin"
        const val parcelize= "kotlin-parcelize"
        const val googleServices="com.google.gms.google-services"
        const val androidLibrary = "com.android.library"
    }

    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
}