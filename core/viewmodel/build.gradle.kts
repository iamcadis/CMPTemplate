plugins {
    alias(libs.plugins.appLibrary)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.bundles.lifecycle)
        }
    }
}

android {
    namespace = "com.core.viewmodel"
}