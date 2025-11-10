plugins {
    alias(libs.plugins.appLibrary)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.lifecycle.runtime)
            api(libs.lifecycle.viewmodel)
        }
    }
}

android {
    namespace = "com.core"
}