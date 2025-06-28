import org.gradle.api.JavaVersion.VERSION_1_8

plugins {
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.gms.google-services")
}

android {
    packaging {
        resources.excludes.add("META-INF/**")
    }
    namespace = "com.example.dispidition"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.dispidition"
        minSdk = 34
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

dependencies{
    implementation("io.coil-kt.coil3:coil-compose:3.2.0")
    implementation ("androidx.work:work-runtime-ktx:2.10.0")
    implementation("com.google.firebase:firebase-messaging:24.1.1")
    implementation("com.google.firebase:firebase-bom:33.14.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0") // Retrofit
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0") // конвертер Gson
    implementation("com.google.dagger:hilt-android:2.51.1")
    implementation(libs.androidx.room.runtime)
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    kapt ("androidx.room:room-compiler:2.4.3")
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":UI"))
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.56.1")
    implementation("androidx.compose.runtime:runtime-livedata:1.7.8")
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

kapt {
    correctErrorTypes = true
}

