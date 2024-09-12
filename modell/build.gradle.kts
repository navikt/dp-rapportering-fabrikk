plugins {
    kotlin("jvm")
    `java-library`
}

group = "no.nav.dapenger.rapportering.fabrikk"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":common"))

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
