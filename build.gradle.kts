plugins {
    id("java")
    id("war")
}

group = "com.github.dnsmoly"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("jakarta.mail:jakarta.mail-api:2.1.3")
    implementation("org.eclipse.angus:jakarta.mail:2.0.3")
    compileOnly("jakarta.servlet:jakarta.servlet-api:6.1.0")
}

tasks.test {
    useJUnitPlatform()
}