import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar


plugins {
    id("application")
    id("com.github.johnrengelman.shadow") version "8.1.1"
    kotlin("jvm")
}

group = "no.nav"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":modell"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("no.nav.dagpenger.rapportering.fabrikk.mediator.MainKt")
}

tasks.withType<ShadowJar> {
    mergeServiceFiles()
}
