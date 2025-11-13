plugins {
    alias(libs.plugins.appLibrary)
    alias(libs.plugins.appCompose)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.collections)
        }
    }
}

android {
    namespace = "com.design.system"
}