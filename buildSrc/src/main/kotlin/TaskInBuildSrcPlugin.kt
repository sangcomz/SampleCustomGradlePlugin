import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

class TaskInBuildSrcPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val extension = project.extensions.create<TaskInBuildSrcPluginExtension>("buildSrc")
        project.task("taskInBuildSrc") {
            group = "custom plugin task group"
            doLast {
                println("${extension.name}, Hello from the taskInBuildScript")
            }
        }
    }
}