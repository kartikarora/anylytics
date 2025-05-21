import org.jetbrains.dokka.base.DokkaBase
import org.jetbrains.dokka.base.DokkaBaseConfiguration
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.dokka.versioning.VersioningConfiguration
import org.jetbrains.dokka.versioning.VersioningPlugin

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.dokka)
    alias(libs.plugins.google.gms.google.services) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlinx.serialization) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.jetbrains.compose) apply false
}

buildscript {
    dependencies {
        classpath(libs.dokka.android.documentation.plugin)
        classpath(libs.dokka.base)
        classpath(libs.dokka.versioning.plugin)
    }
}

tasks.dokkaHtmlMultiModule.configure {
    pluginConfiguration<DokkaBase, DokkaBaseConfiguration> {
        moduleName = "Anylytics"
        footerMessage = "&copy; 2025 Kartik Arora"
        outputDirectory = layout.buildDirectory.dir("dokkaOutput/libraries/anylytics")
    }

    pluginConfiguration<VersioningPlugin, VersioningConfiguration> {
        version = "0.0.1"
        renderVersionsNavigationOnAllPages = true
    }
}