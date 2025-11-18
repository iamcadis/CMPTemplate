plugins {
    alias(libs.plugins.androidApp)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.activity)
        }
        commonMain.dependencies {
            implementation(project(":feature:auth"))
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
