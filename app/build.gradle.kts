plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.gms.google.services)
    alias(libs.plugins.android.hilt)
    id("kotlin-kapt")
    id("kotlin-android")

}

android {
    namespace = "com.aimbeat.taskManagement"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.aimbeat.taskManagement"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding =  true
        viewBinding =  true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Firebase
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.messaging)

    //Dagger-Hilt
    implementation(libs.dagger.hilt)
    kapt(libs.dagger.hilt.compiler)

    // Room database
    implementation (libs.androidx.room.ktx)
    implementation (libs.androidx.room.runtime)
    kapt (libs.androidx.room.compiler)

    // Navigation
    implementation (libs.androidx.navigation.fragment)
    implementation (libs.javapoet)

    //Coroutines

    implementation(libs.kotlinx.coroutines.android)
    implementation (libs.kotlinx.coroutines.core)




}