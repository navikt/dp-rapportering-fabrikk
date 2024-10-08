import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar


plugins {
    id("application")
    id("com.github.johnrengelman.shadow") version "8.1.1"
    kotlin("jvm")
}

group = "no.nav"

repositories {
    mavenCentral()
    maven { setUrl("https://github-package-registry-mirror.gc.nav.no/cached/maven-release") }
}

dependencies {
    implementation(project(":modell"))
    implementation(project(":common"))

    implementation(libs.rapids.and.rivers)
    implementation(libs.konfig)
    implementation(libs.kotlin.logging)
    implementation(libs.bundles.ktor.server)
    implementation(libs.bundles.jackson)
    implementation("io.ktor:ktor-server-netty:${libs.versions.ktor.get()}")
    implementation("io.ktor:ktor-server-config-yaml:${libs.versions.ktor.get()}")

    testImplementation(kotlin("test"))
    testImplementation(libs.rapids.and.rivers.test)
    testImplementation(libs.bundles.kotest.assertions)
    testImplementation(libs.mockk)
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.11.0")
    testImplementation("io.ktor:ktor-server-test-host-jvm:${libs.versions.ktor.get()}")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("no.nav.dagpenger.rapportering.fabrikk.mediator.ApplicationKt")
}

tasks.withType<ShadowJar> {
    mergeServiceFiles()
}
