plugins {
    id("java")
    id("war")
    id("org.springframework.boot") version "3.4.1"
}

group = "com.github.dnsmoly"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    val springBootVersion: String by project
    implementation(platform("org.springframework.boot:spring-boot-dependencies:$springBootVersion"))
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("jakarta.mail:jakarta.mail-api:2.1.3")
    implementation("org.eclipse.angus:jakarta.mail:2.0.3")
    compileOnly("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    compileOnly("jakarta.servlet:jakarta.servlet-api:6.1.0")
}

tasks.test {
    useJUnitPlatform()
}