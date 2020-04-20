import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id ("groovy")
    id ("org.jetbrains.intellij") version "0.4.16"
    kotlin("jvm") version "1.3.70"
}

group = "org.jjongz"
version = "0.0.1-SNAPSHOT"

tasks.withType<JavaCompile> {
    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.register<Copy>("copyGroovyFile") {
    from("src/main/groovy")
    include("*.groovy")
    into("/Users/jjongz/Library/ApplicationSupport/JetBrains/DataGrip2020.1/extensions/com.intellij.database/schema")
}

repositories {
    mavenCentral()
}

dependencies {
    compile ("org.codehaus.groovy:groovy-all:2.3.11")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
//    testImplementation("org.junit.jupiter:junit-jupiter-api")
//    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

