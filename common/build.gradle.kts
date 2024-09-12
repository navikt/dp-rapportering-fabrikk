plugins {
    kotlin("jvm")
    `java-library`
}

group = "no.nav.dagpenger.rapportering.fabrikk"

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
    jvmToolchain(19)
}
