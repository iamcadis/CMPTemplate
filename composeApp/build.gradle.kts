plugins {
    alias(libs.plugins.androidApp)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.activity)
        }
        commonMain.dependencies {
            implementation(libs.lifecycle.runtime)
            implementation(libs.lifecycle.viewmodel)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.app"
}

dependencies {
    debugImplementation(compose.uiTooling)
}
