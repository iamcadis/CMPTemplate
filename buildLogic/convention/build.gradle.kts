plugins {
    `kotlin-dsl`
}

group = "btk.digital.convention"

dependencies {
    compileOnly(libs.gradle.android)
    compileOnly(libs.gradle.kotlin)
    compileOnly(libs.gradle.compose)
}

gradlePlugin {
    plugins {
        create("application") {
            id = "base.application"
            implementationClass = "btk.digital.convention.KMPApplicationPlugin"
        }
        create("compose") {
            id = "base.compose"
            implementationClass = "btk.digital.convention.ComposePlugin"
        }
        create("library") {
            id = "base.library"
            implementationClass = "btk.digital.convention.KMPLibraryPlugin"
        }
        create("feature") {
            id = "base.feature"
            implementationClass = "btk.digital.convention.KMPFeaturePlugin"
        }
    }
}