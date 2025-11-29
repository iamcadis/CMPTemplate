plugins {
    alias(libs.plugins.appLibrary)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.ktor.okhttp)
        }
        commonMain.dependencies {
            implementation(project(":core:local"))
            implementation(libs.koin.compose)
            implementation(libs.bundles.ktor)
        }
        iosMain.dependencies {
            implementation(libs.ktor.darwin)
        }
    }
}

android {
    namespace = "com.core.network"

    buildFeatures {
        buildConfig = true
    }
}