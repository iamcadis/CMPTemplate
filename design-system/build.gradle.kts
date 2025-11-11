plugins {
    alias(libs.plugins.appLibrary)
    alias(libs.plugins.appCompose)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(compose.material3)
        }
    }
}

android {
    namespace = "com.design.system"
}