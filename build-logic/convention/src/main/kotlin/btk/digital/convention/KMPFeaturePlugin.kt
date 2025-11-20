package btk.digital.convention

import btk.digital.convention.extension.getBundle
import btk.digital.convention.extension.getPluginId
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KMPFeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target.pluginManager) {
            apply("base.library")
            apply("base.compose")
            apply(target.getPluginId(alias = "kotlinSerialization"))
        }

        with(target.extensions) {
            configure<KotlinMultiplatformExtension> {
                sourceSets {
                    commonMain.dependencies {
                        implementation(target.getBundle("koin"))
                        implementation(project(":core:common"))
                        implementation(project(":core:local"))
                        implementation(project(":core:viewmodel"))
                        implementation(project(":core:ui"))
                        implementation(project(":design-system"))
                    }
                }
            }
        }
    }
}