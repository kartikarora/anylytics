import me.kartikarora.anylytics.BuildMetadata
import me.kartikarora.anylytics.DemoAppMetadata
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.ksp)
}

android {
    namespace = DemoAppMetadata.namespace
    compileSdk = BuildMetadata.compileSdk

    defaultConfig {
        applicationId = DemoAppMetadata.namespace
        minSdk = BuildMetadata.minSdk
        targetSdk = BuildMetadata.targetSdk
        versionCode = BuildMetadata.versionCode
        versionName = BuildMetadata.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JvmTarget.JVM_11.target
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))

    implementation(projects.anylytics)
    implementation(projects.anylyticsAdobe)
    implementation(projects.anylyticsFirebase)

    implementation(libs.androidx.activity)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.bundles.androidx.compose)
    implementation(libs.firebase.analytics)
    implementation(libs.hilt.android)
    implementation(libs.javax.inject)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.material)

    ksp(libs.hilt.android.compiler)

    debugImplementation(libs.bundles.androidx.compose.debug)
}