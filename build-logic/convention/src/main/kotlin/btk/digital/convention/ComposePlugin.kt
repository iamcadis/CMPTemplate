package btk.digital.convention

import btk.digital.convention.extension.getPluginId
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class ComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target.pluginManager) {
            apply(target.getPluginId(alias = "composeMultiplatform"))
            apply(target.getPluginId(alias = "composeCompiler"))
        }

        with(target.extensions) {
            val compose = getByType<ComposeExtension>().dependencies

            configure<KotlinMultiplatformExtension> {
                sourceSets {
                    androidMain.dependencies {
                        implementation(compose.preview)
                    }
                    commonMain.dependencies {
                        implementation(compose.components.resources)
                        implementation(compose.components.uiToolingPreview)
                    }
                }
            }
        }
    }
}