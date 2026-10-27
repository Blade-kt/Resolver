plugins {
    kotlin("jvm") version "2.4.21"
}

val jomlVersion = project.property("jomlVersion") as String

repositories {
    mavenCentral()
}

dependencies {
    api(kotlin("stdlib"))

    implementation("org.joml:joml:$jomlVersion")
}