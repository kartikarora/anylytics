import me.kartikarora.anylytics.BuildMetadata
import me.kartikarora.anylytics.LibraryMetadata
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.maven.publish)
}

android {
    namespace = LibraryMetadata.anylyticsArtifactNamespace
    compileSdk = BuildMetadata.compileSdk

    defaultConfig {
        minSdk = BuildMetadata.minSdk
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JvmTarget.JVM_11.target
    }
    buildFeatures {
        compose = true
    }
    mavenPublishing {
        coordinates(
            LibraryMetadata.artifactGroup,
            LibraryMetadata.anylyticsArtifactId,
            LibraryMetadata.versionName
        )
        pom {
            version = LibraryMetadata.versionName
            group = LibraryMetadata.artifactGroup
            name = LibraryMetadata.anylyticsArtifactName
            description.set(LibraryMetadata.anylyticsDescription)
        }
    }
}

dependencies {
    implementation(libs.androidx.compose.foundation.nonBom)
    implementation(libs.androidx.core.ktx)
    implementation(libs.javax.inject)
    implementation(libs.kotlinx.serialization.json)
}