plugins {
    alias(libs.plugins.appLibrary)
    alias(libs.plugins.appCompose)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(compose.ui)
            api(compose.runtime)
            api(compose.material3)
            api(compose.foundation)
        }
    }
}

android {
    namespace = "com.design.system"
}