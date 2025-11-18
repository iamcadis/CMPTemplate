plugins {
    alias(libs.plugins.appLibrary)
    alias(libs.plugins.appCompose)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.navigation)
            implementation(libs.backhandler)
            implementation(project(":core:viewmodel"))
        }
    }
}

android {
    namespace = "com.core.ui"
}