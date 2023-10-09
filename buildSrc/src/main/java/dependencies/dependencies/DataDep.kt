package dependencies.dependencies

object DataDep {
    const val kotlin = Dependencies.KotlinDep.kotlin
    const val javax = Dependencies.JavaDep.javax
    const val coroutineCore = Dependencies.CoroutinesDep.coroutineCore

    const val firestore = Dependencies.GoogleDep.googleServiceFirestore

    object Test {
        const val junit = Dependencies.TestDep.junit
        const val coroutines = Dependencies.TestDep.coroutinesTest
        const val mockitoKotlin = Dependencies.TestDep.mockitoKotlin
        const val mockitoInline = Dependencies.TestDep.mockitoInline
        const val assertJ = Dependencies.TestDep.assertJ
    }


    // Hilt
    val DaggerHilt = listOf(
        Dependencies.DaggerHiltDep.hiltAndroid
    )

    val DaggerHiltKapt = listOf(
        Dependencies.DaggerHiltDep.hiltAndroidKapt,
        Dependencies.DaggerHiltDep.hiltKapt
    )
}