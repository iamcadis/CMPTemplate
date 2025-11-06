plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
}

tasks.register<GenerateIosConfigTask>("generateIosConfig") {
    // Set the output directory for this task
    outputDir.set("${projectDir}/iosApp/Configuration")

    // Use the `providers.gradleProperty` API to lazily read from gradle.properties
    // This is cache-safe and will fail at configuration time if the property
    // is missing, which is much better than failing at execution time.
    teamId.set(providers.gradleProperty("ios.team.id"))
    productName.set(providers.gradleProperty("ios.product.name"))
    bundleId.set(providers.gradleProperty("app.id"))
    displayName.set(providers.gradleProperty("app.display.name"))
    versionCode.set(providers.gradleProperty("version.code"))
    versionName.set(providers.gradleProperty("version.name"))
}

// Automatically run before all iOS build-related tasks
tasks.matching {
    it.name.contains("build", ignoreCase = true) ||
            it.name.contains("assemble", ignoreCase = true) ||
            it.name.contains("syncFramework", ignoreCase = true)
}.configureEach {
    dependsOn("generateIosConfig")
}