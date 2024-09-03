plugins {
    id("application")
    kotlin("jvm")
}

group = "org.example"
version = "unspecified"



repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("org.example.MainKt")
}