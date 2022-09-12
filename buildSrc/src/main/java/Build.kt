object Build {
    private const val androidBuildToolsVersion = "7.2.2"
    const val androidBuildTools = "com.android.tools.build:gradle:$androidBuildToolsVersion"

    private const val kotlinVersion = "1.7.10"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"

    private const val kotlinMetaDataVersion = "0.4.2"
    const val kotlinMetaData = "org.jetbrains.kotlinx:kotlinx-metadata-jvm:$kotlinMetaDataVersion"

    private const val hiltAndroidGradlePluginVersion = "2.38.1"
    const val hiltAndroidGradlePlugin =
        "com.google.dagger:hilt-android-gradle-plugin:$hiltAndroidGradlePluginVersion"

    private const val nav_version = "2.5.1"
    const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version"
}