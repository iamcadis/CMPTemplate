package btk.digital.convention

import btk.digital.convention.extension.addAndroidTarget
import btk.digital.convention.extension.addIosTarget
import btk.digital.convention.extension.configureAndroid
import btk.digital.convention.extension.getPluginId
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KMPApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target.pluginManager) {
            apply(target.getPluginId(alias = "kotlinMultiplatform"))
            apply(target.getPluginId(alias = "androidApplication"))
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

                sourceSets {
                    commonMain.dependencies {
                        implementation(project(":design-system"))
                    }
                }
            }
        }
    }
}