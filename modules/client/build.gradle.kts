plugins {
    kotlin("jvm") version "2.4.21"
    application
}

val lwjglVersion = project.property("lwjglVersion") as String
val lwjglNatives = "natives-windows"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":main"))
    implementation(platform("org.lwjgl:lwjgl-bom:$lwjglVersion"))

    implementation("org.lwjgl:lwjgl")
    implementation("org.lwjgl:lwjgl-glfw")
    implementation("org.lwjgl:lwjgl-opengl")

    implementation("org.lwjgl:lwjgl::$lwjglNatives")
    implementation("org.lwjgl:lwjgl-glfw::$lwjglNatives")
    implementation("org.lwjgl:lwjgl-opengl::$lwjglNatives")
}

application {
    mainClass.set("me.blade.resolver.client.ResolverClient")
}