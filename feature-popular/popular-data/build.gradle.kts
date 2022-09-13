plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk

        testInstrumentationRunner = ProjectConfig.testInstrumentationRunner
        consumerProguardFiles("consumer-rules.pro")
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

    implementation(project(Modules.coreDatabase))
    implementation(project(Modules.coreNetwork))
    testImplementation(project(Modules.coreTesting))

    kapt(Build.kotlinMetaData)


    implementation(Dependencies.DaggerHilt.hiltAndroid)
    implementation(Dependencies.Paging.paging3Common)
    kapt(Dependencies.DaggerHilt.hiltCompiler)

    implementation(Dependencies.Coroutines.coroutines)
    implementation(Testing.junitAndroidExt)
}