package btk.digital.convention

import btk.digital.convention.extension.addAndroidTarget
import btk.digital.convention.extension.addIosTarget
import btk.digital.convention.extension.configureAndroid
import btk.digital.convention.extension.getLibrary
import btk.digital.convention.extension.getPluginId
import btk.digital.convention.extension.suppressOptIn
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KMPApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target.pluginManager) {
            apply(target.getPluginId(alias = "androidApplication"))
            apply(target.getPluginId(alias = "kotlinMultiplatform"))
            apply("base.compose")
        }

        with(target.extensions) {
            configure<BaseAppModuleExtension> {
                target.configureAndroid(this)

                defaultConfig {
                    targetSdk = target.findProperty("android.sdk.compile").toString().toInt()
                }
                buildTypes {
                    getByName("release") {
                        multiDexEnabled = true
                        isMinifyEnabled = true
                        isShrinkResources = true
                        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                    }
                    getByName("debug") {
                        applicationIdSuffix = ".debug"
                    }
                }
            }

            configure<KotlinMultiplatformExtension> {
                addAndroidTarget()
                addIosTarget()
                suppressOptIn()

                sourceSets {
                    commonMain.dependencies {
                        implementation(target.getLibrary("koin-compose"))
                        implementation(project(":core:ui"))
                        implementation(project(":design-system"))
                    }
                }
            }
        }
    }
}