plugins {
    alias(libs.plugins.appLibrary)
    alias(libs.plugins.appCompose)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.koin.core)
            api(libs.navigation)
            api(libs.bundles.kotlinx)
            api(libs.bundles.lifecycle)
        }
    }
}

android {
    namespace = "com.core"
}