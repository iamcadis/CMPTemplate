plugins {
    alias(libs.plugins.androidApp)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.activity)
        }
        commonMain.dependencies {
            implementation(project(":feature:home"))
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
