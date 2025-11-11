package btk.digital.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class KMPFeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target.pluginManager) {
            apply("base.library")
            apply("base.compose")
        }

        with(target.extensions) {
            val compose = getByType<ComposeExtension>().dependencies

            configure<KotlinMultiplatformExtension> {
                sourceSets {
                    commonMain.dependencies {
                        implementation(compose.components.resources)
                        implementation(compose.components.uiToolingPreview)
                        implementation(project(":core"))
                        implementation(project(":design-system"))
                    }
                }
            }
        }
    }
}