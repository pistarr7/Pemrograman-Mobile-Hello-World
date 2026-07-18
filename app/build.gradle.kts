/**
 * MINGGU 1 — build.gradle.kts (App Module)
 *
 * File ini mengkonfigurasi:
 * - SDK versions
 * - Plugin Kotlin & Compose
 * - Dependencies library
 */
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.helloworld"

    // Versi SDK yang digunakan untuk compile
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.helloworld"

        // minSdk = batas minimum Android yang didukung
        // API 26 = Android 8.0 (Oreo) — sesuai silabus
        minSdk = 26

        // targetSdk = versi Android target (selalu gunakan versi terbaru)
        targetSdk = 35

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        // Aktifkan Jetpack Compose
        compose = true
    }
}

dependencies {
    // ==========================================
    // CORE ANDROID
    // ==========================================
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Activity Compose — untuk setContent { }
    implementation(libs.androidx.activity.compose)

    // ==========================================
    // JETPACK COMPOSE BOM (Bill of Materials)
    // BOM memastikan semua library Compose menggunakan versi yang kompatibel
    // ==========================================
    implementation(platform(libs.androidx.compose.bom))

    // Core Compose UI
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)  // Untuk @Preview

    // Material Design 3
    implementation(libs.androidx.material3)
    implementation(libs.google.material)

    // Material Icons
    implementation(libs.androidx.material.icons.extended)

    // ==========================================
    // TESTING
    // ==========================================
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    // Debug only — untuk preview dan inspection tools
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
