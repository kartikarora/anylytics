package me.kartikarora.anylytics

import me.kartikarora.anylytics.BuildMetadata.majorVersion
import me.kartikarora.anylytics.BuildMetadata.minorVersion
import me.kartikarora.anylytics.BuildMetadata.patchVersion
import me.kartikarora.anylytics.BuildMetadata.versionName

object LibraryMetadata {
    // General
    const val artifactGroup = "me.kartikarora.anylytics"
    val versionName get() = BuildMetadata.versionName

    // Anylytics
    const val anylyticsArtifactNamespace = "me.kartikarora.anylytics"
    const val anylyticsArtifactName = "anylytics"
    const val anylyticsArtifactId = "anylytics"
    const val anylyticsDescription =
        "Provider agnostic analytics library for Android with Compose support"

    // Adobe
    const val anylyticsAdobeArtifactNamespace = "me.kartikarora.anylytics.adobe"
    const val anylyticsAdobeArtifactName = "Anylytics Adobe"
    const val anylyticsAdobeArtifactId = "anylytics-adobe"
    const val anylyticsAdobeDescription =
        "Adobe Experience Cloud Analytics implementation of Anylytics"

    // Firebase
    const val anylyticsFirebaseArtifactNamespace = "me.kartikarora.anylytics.firebase"
    const val anylyticsFirebaseArtifactName = "Anylytics Firebase"
    const val anylyticsFirebaseArtifactId = "anylytics-firebase"
    const val anylyticsFirebaseDescription = "Firebase Analytics implementation of Anylytics"
}