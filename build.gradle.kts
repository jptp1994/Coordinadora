buildscript {

    repositories {
        google()
        mavenCentral()
        maven(url = Config.ClassPaths.pluginGradle)
    }

    dependencies {
        classpath(Config.ClassPaths.googleServices)
        classpath(Config.ClassPaths.androidGradle)
        classpath(Config.ClassPaths.kotlinGradle)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
        classpath(Config.ClassPaths.daggerHiltGradle)
        classpath(Config.ClassPaths.navigationSafArgsGradle)
        // KtLint
        classpath(Config.ClassPaths.ktLint)
    }
}