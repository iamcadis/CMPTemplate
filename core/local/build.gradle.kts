plugins {
    alias(libs.plugins.appLibrary)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.google.tink.android)
        }
        commonMain.dependencies {
            implementation(libs.koin.compose)
            implementation(libs.bundles.datastore)
        }
    }
}

android {
    namespace = "com.core.local"
}