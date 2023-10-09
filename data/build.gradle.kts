import dependencies.dependencies.DataDep
plugins {
    id(Config.Plugins.androidLibrary)
    id(Config.Plugins.kotlinAndroid)
    id(Config.Plugins.kotlinKapt)
    id(Config.Plugins.hilt)
    id(Config.Plugins.parcelize)
    id(Config.Plugins.googleServices)
}

android {
    namespace = Environments.Release.appData
    compileSdk = Versions.compileAndTargetSdk

    defaultConfig {
        minSdk = Versions.minSdk

        testInstrumentationRunner = Config.testRunner
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = Versions.jvmTarget
    }
}

dependencies {
    // Dagger-Hilt
    DataDep.DaggerHilt.forEach {
        implementation(it)
    }
    DataDep.DaggerHiltKapt.forEach {
        kapt(it)
    }

    //Firestore
    implementation(DataDep.firestore)
    // Modules
    implementation(project(Modules.domain))
    // Kotlin
    implementation(DataDep.kotlin)
    // Coroutines
    implementation(DataDep.coroutineCore)
    // JavaX
    implementation(DataDep.javax)
    // Test Dependencies
    testImplementation(DataDep.Test.junit)
    testImplementation(DataDep.Test.assertJ)
    testImplementation(DataDep.Test.mockitoKotlin)
    testImplementation(DataDep.Test.mockitoInline)
    testImplementation(DataDep.Test.coroutines)

}