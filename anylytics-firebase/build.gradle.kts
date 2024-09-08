import me.kartikarora.anylytics.BuildMetadata
import me.kartikarora.anylytics.LibraryMetadata
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.maven.publish)
}

android {
    namespace = LibraryMetadata.anylyticsFirebaseArtifactNamespace
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
    mavenPublishing {
        coordinates(
            LibraryMetadata.artifactGroup,
            LibraryMetadata.anylyticsFirebaseArtifactId,
            LibraryMetadata.versionName
        )
        pom {
            version = LibraryMetadata.versionName
            group = LibraryMetadata.artifactGroup
            name = LibraryMetadata.anylyticsFirebaseArtifactName
            description.set(LibraryMetadata.anylyticsFirebaseDescription)
        }
    }
}

dependencies {
    implementation(projects.anylytics)

    implementation(libs.androidx.core.ktx)
    implementation(libs.firebase.analytics)
    implementation(libs.javax.inject)
}