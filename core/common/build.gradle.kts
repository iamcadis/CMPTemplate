plugins {
    alias(libs.plugins.appLibrary)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.bundles.kotlinx)
            implementation(libs.koin.core)
        }
    }
}

android {
    namespace = "com.core.common"
}