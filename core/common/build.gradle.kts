plugins {
    alias(libs.plugins.appLibrary)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.bundles.kotlinx)
        }
    }
}

android {
    namespace = "com.core.common"
}