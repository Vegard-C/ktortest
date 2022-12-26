buildscript {
  repositories {
    mavenCentral()
  }
}
val kotlinVersion: String by project

plugins {
  kotlin("jvm")
}

group = "com." + project.name
version = "0.0.1"

java.sourceCompatibility = JavaVersion.VERSION_17
val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget="17"
val compileTestKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileTestKotlin.kotlinOptions.jvmTarget="17"

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
  implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
}
