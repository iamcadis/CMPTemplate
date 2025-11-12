plugins {
    alias(libs.plugins.appLibrary)
    alias(libs.plugins.appCompose)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.navigation)
            api(libs.bundles.koin)
            api(libs.bundles.kotlinx)
            api(libs.bundles.lifecycle)
        }
    }
}

android {
    namespace = "com.core"
}