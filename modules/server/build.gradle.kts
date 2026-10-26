plugins {
    kotlin("jvm") version "2.4.21"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":main"))
}

application {
    mainClass.set("me.blade.resolver.server.ServerMainKt")
}