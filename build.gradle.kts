buildscript {
  repositories {
    mavenCentral()
  }
}
val kotlinVersion: String by project
val kotorVersion: String by project
val logbackVersion: String by project

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
  implementation("io.ktor:ktor-server-core-jvm:$kotorVersion")
  implementation("io.ktor:ktor-server-netty-jvm:$kotorVersion")
  implementation("ch.qos.logback:logback-classic:$logbackVersion")
}

tasks.create("fatJar", Jar::class) {
  group = "build"
  description = "Creates a self-contained jar of the app"
  manifest.attributes["Main-Class"] = "ktortest.StartUpKt"
  archiveClassifier.set("fat")
  duplicatesStrategy = DuplicatesStrategy.EXCLUDE
  val dependencies = configurations
    .runtimeClasspath
    .get()
    .map(::zipTree)
  from(dependencies)
  with(tasks.jar.get())
}

tasks.named("build") {
  dependsOn("fatJar")
}