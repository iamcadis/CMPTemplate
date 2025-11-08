import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.TaskProvider

fun Project.dependsIfExists(name: String, task: TaskProvider<out Task>) {
    tasks.matching { it.name.contains(name, ignoreCase = true) }.configureEach {
        dependsOn(task)
    }
}