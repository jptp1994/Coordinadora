import dependencies.dependencies.DomainDep
plugins {
    id(Config.Plugins.androidLibrary)
    id(Config.Plugins.kotlinAndroid)
    id(Config.Plugins.kotlinKapt)
    id(Config.Plugins.hilt)
    id(Config.Plugins.parcelize)
}

android {
    namespace = Environments.Release.appDomain
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
    implementation(DomainDep.kotlin)
    implementation(DomainDep.coroutineCore)

    // JavaX
    implementation(DomainDep.javax)

    // Dagger-Hilt
    DomainDep.DaggerHilt.forEach {
        implementation(it)
    }
    DomainDep.DaggerHiltKapt.forEach {
        kapt(it)
    }

    // Test Dependencies
    testImplementation(DomainDep.Test.junit)
    testImplementation(DomainDep.Test.assertJ)
    testImplementation(DomainDep.Test.mockitoKotlin)
    testImplementation(DomainDep.Test.mockitoInline)
    testImplementation(DomainDep.Test.coroutines)

}