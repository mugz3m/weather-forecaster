plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.kotlin)
}

android {
    namespace = "ru.mugz3m.weatherforecaster"
    compileSdk = 33

    defaultConfig {
        applicationId = "ru.mugz3m.weatherforecaster"
        minSdk = 26
        targetSdk = 33
        versionName = "1.0"
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
}

dependencies {

    implementation(libs.androidx.coreKtx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.swiperefreshlayout)

    // Navigation
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.navigation.dynamicFeatures)

    // UI
    implementation(libs.material)

    // Coroutines
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    // REST
    implementation(libs.bundles.retrofit2)

    // Pictures
    implementation(libs.glide)

    // Location
    implementation(libs.location)
}
