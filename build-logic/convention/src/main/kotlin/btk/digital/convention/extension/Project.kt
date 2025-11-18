package btk.digital.convention.extension

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.ExternalModuleDependencyBundle
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.getByType

internal val Project.catalogs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun Project.getPluginId(alias: String): String {
    return catalogs.findPlugin(alias).get().get().pluginId
}

internal fun Project.getBundle(alias: String): Provider<ExternalModuleDependencyBundle?> {
    return catalogs.findBundle(alias).get()
}

internal fun Project.getLibrary(alias: String): Provider<MinimalExternalModuleDependency?> {
    return catalogs.findLibrary(alias).get()
}

internal fun Project.configureAndroid(extension: CommonExtension<*, *, *, *, *, *>) {
    with(extension) {
        compileSdk = findProperty("android.sdk.compile").toString().toInt()

        defaultConfig {
            minSdk = findProperty("android.sdk.minimal").toString().toInt()
        }
        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
    }
}