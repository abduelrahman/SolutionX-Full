plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-parcelize")
}

android {
    namespace = "am.leon.solutionx"
    flavorDimensions += "logging"
    compileSdk = 34

    defaultConfig {
        applicationId = "am.leon.solutionx"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    productFlavors {
        create("logCat") {
            dimension = "logging"
        }

        create("logWriter") {
            dimension = "logging"
        }

        create("production") {
            dimension = "logging"
        }
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    androidComponents {
        beforeVariants { variant ->
            val isReleaseWithLogCatOrLogWriterFlavor = variant.buildType == "release" &&
                    variant.productFlavors.any { it.second in listOf("logCat", "logWriter") }

            val isDebugWithProductionFlavor =
                variant.buildType == "debug" && variant.productFlavors.any { it.second == "production" }

            if (isReleaseWithLogCatOrLogWriterFlavor || isDebugWithProductionFlavor) {
                variant.enable = false
            }
        }
    }
}

dependencies {
    // Unit Test
    testImplementation(libs.junit)

    // Android Test
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.constraintlayout)

    // Material
    implementation(libs.material)

    // Kotlin Reflect
    implementation(libs.kotlin.reflect)

    // SDP && SSP
    implementation(libs.intuit.sdp)
    implementation(libs.intuit.ssp)

    // GSON
    implementation(libs.google.gson)
}