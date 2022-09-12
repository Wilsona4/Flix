object Dependencies {
    object AndroidX {
        private const val coreKtxVersion = "1.7.0"
        const val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"

        private const val appCompatVersion = "1.5.0"
        const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"

        private const val constraintLayoutVersion = "2.1.4"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
    }

    object CircleImageView {
        private const val version = "3.1.0"
        const val circleImageView = "de.hdodenhof:circleimageview:3.1.0"
    }

    object Coroutines {
        private const val version = "1.6.4"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
    }

    object Coil {
        private const val version = "2.1.0"
        const val coil = "io.coil-kt:coil:$version"
    }

    object DaggerHilt {
        const val version = "2.38.1"
        const val hiltAndroid = "com.google.dagger:hilt-android:$version"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:$version"
    }

    object Material {
        private const val version = "1.6.1"
        const val material = "com.google.android.material:material:$version"
    }

    object Navigation {
        private const val navVersion = "2.5.1"
        const val fragmentKtx =
            "androidx.navigation:navigation-fragment-ktx:$navVersion"
        const val uiKtx =
            "androidx.navigation:navigation-ui-ktx:$navVersion"
    }

    object OkHttp {
        private const val okHttpVersion = "5.0.0-alpha.3"
        const val okHttp = "com.squareup.okhttp3:okhttp:$okHttpVersion"
        const val okHttpLoggingInterceptor =
            "com.squareup.okhttp3:logging-interceptor:$okHttpVersion"
    }

    object Paging {
        private const val version = "3.1.1"
        private const val roomPagingVersion = "2.4.0-beta01"

        const val paging3 = "androidx.paging:paging-runtime:$version"
        const val paging3Common = "androidx.paging:paging-common-ktx:$version"

        //Paging artifact for Room
        const val roomPaging = "androidx.room:room-paging:$roomPagingVersion"
    }

    object Room {
        private const val roomVersion = "2.4.3"

        const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
        const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
        const val roomKtx = "androidx.room:room-ktx:$roomVersion"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val moshiConverter = "com.squareup.retrofit2:converter-moshi:$version"
        const val GsonConverter = "com.squareup.retrofit2:converter-gson:$version"
    }

    object Timber {
        private const val version = "5.0.1"
        const val timber = "com.jakewharton.timber:timber:$version"
    }
}
