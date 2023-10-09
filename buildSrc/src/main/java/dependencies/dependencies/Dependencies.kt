package dependencies.dependencies

import Versions

//Contains the dependencies of the proyect
object Dependencies {



    object CoreDep {
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val material = "com.google.android.material:material:${Versions.materialDesign}"
        const val constraint =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        const val activityKtx = "androidx.activity:activity-ktx:${Versions.ktxActivity}"
    }

    object ImageDep{
        const val picasso= "com.squareup.picasso:picasso:${Versions.picasso}"
    }


    object CoroutinesDep {
        const val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    }


    object DaggerHiltDep {
        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.daggerHilt}"

        const val hiltKapt="androidx.hilt:hilt-compiler:${Versions.hiltCompiler}"

        const val hiltAndroidKapt = "com.google.dagger:hilt-compiler:${Versions.daggerHilt}"

    }

    object GoogleDep {
        const val googleServiceFirestore= "com.google.firebase:firebase-firestore-ktx:${Versions.googleFirestore}"
    }

    object KotlinDep {
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    }

    object JavaDep {
        const val javax = "javax.inject:javax.inject:${Versions.javaxInjectVersion}"
    }

    object LifeCycleDep {
        const val viewModelKtx =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifeCycleViewModel}"
        const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifeCycleViewModel}"
        const val lifeCycleExtension =
            "androidx.lifecycle:lifecycle-extensions:${Versions.lifeCycleExtensions}"
        const val lifeCycleRuntime = "androidx.lifecycle:lifecycle-runtime:${Versions.lifeCycleViewModel}"
        const val lifeCycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycleViewModel}"


    }



    object RetrofitDep {
        const val retrofit="com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofitConverter="com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

        const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.moshiConverterVersion}"
        const val loggingInterceptor= "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLogger}"
    }


    object TestDep {
        const val coroutinesTest="org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
        const val junit= "junit:junit:${Versions.junit}"

        const val assertJ = "org.assertj:assertj-core:${Versions.assertJVersion}"

        const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlinVersion}"

        const val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInlineVersion}"
        const val testCore = "androidx.test:core:${Versions.axTestCore}"
        const val testExtJunit= "androidx.test.ext:junit:${Versions.junitExt}"
        const val espressoCore="androidx.test.espresso:espresso-core:${Versions.espresso}"
        const val androidxArchCore = "androidx.arch.core:core-testing:${Versions.coreTesting}"
        const val robolectric = "org.robolectric:robolectric:${Versions.robolectricVersion}"
    }

}