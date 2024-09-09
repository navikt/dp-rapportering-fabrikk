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
    maven { setUrl("https://github-package-registry-mirror.gc.nav.no/cached/maven-release") }

}

dependencies {
    implementation(project(":modell"))

    implementation(libs.rapids.and.rivers)

    testImplementation(kotlin("test"))
    testImplementation(libs.bundles.kotest.assertions)
    testImplementation(libs.mockk)
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.11.0")

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
