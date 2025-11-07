package btk.digital.convention.extension

import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

fun KotlinMultiplatformExtension.addAndroidTarget() {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
}

fun KotlinMultiplatformExtension.addIosTarget() {
    val frameworkName = project.name.split("-", "_")
        .joinToString("") {
            it.replaceFirstChar(Char::uppercaseChar)
        }

    listOf(iosArm64(), iosSimulatorArm64()).forEach {
        it.binaries.framework {
            baseName = frameworkName
            isStatic = true
        }
    }
}