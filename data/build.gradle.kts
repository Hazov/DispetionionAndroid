import org.gradle.api.JavaVersion.VERSION_1_8

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("com.google.gms.google-services")
    id("kotlin-kapt")
}

android {
    packaging {
        resources.excludes.add("META-INF/**")
    }

    namespace = "com.example.data"
    compileSdk = 35

    defaultConfig {

        minSdk = 34
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = VERSION_1_8
        targetCompatibility = VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation ("com.google.android.gms:play-services-location:21.0.1")
    implementation(project(":domain"))
    implementation ("androidx.room:room-runtime:2.4.3")
    kapt ("androidx.room:room-compiler:2.4.3")
    implementation("com.google.firebase:firebase-messaging:24.1.1")
    implementation("com.google.firebase:firebase-bom:33.14.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0") // Retrofit
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0") // конвертер Gson
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
