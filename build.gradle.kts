import kotlin.arrayOf

buildscript {
    repositories {
        maven {
            url = uri("/Users/sangcomz/projects/gradle-slack-upload-plugin/repo")
        }
    }

    dependencies {
        classpath("xyz.sangcomz:gradle-slack-upload-plugin:0.0.4")
    }
}

plugins {
    kotlin("jvm") version "1.3.61"
    //apply buildSrc plugin
    `task-in-buildsrc-plugin`
}

apply {
    plugin("xyz.sangcomz.gradle")
}

group = "xyz.sangcomz"
version = "1.0-SNAPSHOT"

repositories {
    maven {
        url = uri("/Users/sangcomz/projects/gradle-slack-upload-plugin/repo")
    }
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

open class TaskInBuildScriptPluginExtension {
    var name: String? = null
}

class TaskInBuildScriptPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val extension = project.extensions.create<TaskInBuildScriptPluginExtension>("buildScript")
        project.task("taskInBuildScript") {
            group = "custom plugin task group"
            doLast {
                println("${extension.name}, Hello from the taskInBuildScript")
            }
        }
    }
}

// Apply the plugin
apply<TaskInBuildScriptPlugin>()

// Configure the extension using a DSL block
configure<TaskInBuildScriptPluginExtension> {
    name = "sangcomz"
}

//buildSrc extension
buildSrc{
    name = "sangcomz"
}

configure<xyz.sangcomz.gradle.SlackUploadPluginExtension>{
    token = "your api token"
    channels = "wowchannel"
    title = "File Title"
    initialComment = "Upload Sample.txt"
    filePaths =  arrayOf("sample.txt")
    zipName = "zipFile"
    zipFilePath = "build/zip"
    deleteZipFileAfterUpload = false
}
