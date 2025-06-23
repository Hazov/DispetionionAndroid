import org.gradle.api.JavaVersion.VERSION_1_8

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    packaging {
        resources.excludes.add("META-INF/**")
    }
    namespace = "com.example.domain"
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
    implementation ("androidx.work:work-runtime-ktx:2.7.1")
    implementation ("com.google.android.gms:play-services-location:21.0.1")
    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}