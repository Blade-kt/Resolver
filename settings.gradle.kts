plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
rootProject.name = "Resolver"
include("main", "client", "server")

rootProject.children.forEach { project ->
    project.projectDir = file("modules/${project.name}")
}