package btk.digital.convention

import btk.digital.convention.extension.addAndroidTarget
import btk.digital.convention.extension.addIosTarget
import btk.digital.convention.extension.configureAndroid
import btk.digital.convention.extension.getPluginId
import btk.digital.convention.extension.suppressExpectClass
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KMPLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target.pluginManager) {
            apply(target.getPluginId(alias = "kotlinMultiplatform"))
            apply(target.getPluginId(alias = "androidLibrary"))
        }

        with(target.extensions) {
            configure<LibraryExtension> {
                target.configureAndroid(this)

                defaultConfig {
                    consumerProguardFiles("consumer-rules.pro")
                }
            }
            configure<KotlinMultiplatformExtension> {
                addAndroidTarget()
                addIosTarget()
                suppressExpectClass()
            }
        }
    }
}