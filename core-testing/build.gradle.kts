plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk

        testInstrumentationRunner = ProjectConfig.testInstrumentationRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(Dependencies.DaggerHilt.hiltAndroid)
    kapt(Dependencies.DaggerHilt.hiltCompiler)

    api(Dependencies.Timber.timber)

    api(Testing.junit4)
    api(Testing.junitAndroidExt)
    api(Testing.truth)
    api(Testing.coroutines)
    api(Testing.turbine)
    api(Testing.espressoCore)
    api(Testing.testRunner)
    api(Testing.mockkAndroid)
    api(Testing.mockWebServer)
    api(Testing.hiltTesting)
    kaptAndroidTest(Dependencies.DaggerHilt.hiltCompiler)

//    testImplementation(Testing.junit4)
//    testImplementation(Testing.junitAndroidExt)
//    testImplementation(Testing.truth)
//    testImplementation(Testing.coroutines)
//    testImplementation(Testing.turbine)
//    testImplementation(Testing.mockk)
//    testImplementation(Testing.mockWebServer)
//
//    androidTestImplementation(Testing.junit4)
//    androidTestImplementation(Testing.junitAndroidExt)
//    androidTestImplementation(Testing.truth)
//    androidTestImplementation(Testing.coroutines)
//    androidTestImplementation(Testing.turbine)
//    androidTestImplementation(Testing.espressoCore)
//    androidTestImplementation(Testing.mockkAndroid)
//    androidTestImplementation(Testing.mockWebServer)
//    androidTestImplementation(Testing.hiltTesting)
//    kaptAndroidTest(Dependencies.DaggerHilt.hiltCompiler)
//    androidTestImplementation(Testing.testRunner)
}