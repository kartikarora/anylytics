import org.jetbrains.dokka.base.DokkaBase
import org.jetbrains.dokka.base.DokkaBaseConfiguration
import org.jetbrains.dokka.gradle.DokkaTask

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
}

buildscript {
    dependencies {
        classpath(libs.dokka.base)
    }
}

dependencies {
    dokkaPlugin(libs.android.documentation.plugin)
}

tasks.dokkaHtmlMultiModule.configure {
    pluginConfiguration<DokkaBase, DokkaBaseConfiguration> {
        footerMessage = "&copy; 2025 Kartik Arora"
    }
}