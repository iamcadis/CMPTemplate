plugins {
    alias(libs.plugins.appLibrary)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.koin.core)
            api(libs.bundles.kotlinx)
            api(libs.bundles.lifecycle)
        }
    }
}

android {
    namespace = "com.core"
}