plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "com.example.twitterapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.twitterapp"
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

    buildFeatures{
        dataBinding = true
        buildConfig = true
    }
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(projects.twitter)
    implementation(projects.core.ui)

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    //Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.52")
    kapt("com.google.dagger:hilt-android-compiler:2.52")

    // ViewModel
//    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")

    // LiveData
//    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.4")

    implementation ("androidx.activity:activity-ktx:1.9.1")

    debugImplementation ("com.plutolib:pluto:2.2.2")
    releaseImplementation ("com.plutolib:pluto-no-op:2.2.2")

    debugImplementation("com.plutolib.plugins:network:2.2.2")
    releaseImplementation("com.plutolib.plugins:network-no-op:2.2.2")

    implementation ("com.jakewharton.timber:timber:5.0.1")

}