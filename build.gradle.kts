plugins {
    id("java")
    id("io.spring.dependency-management") version ("1.0.13.RELEASE")
    id("org.springframework.boot") version ("3.1.0")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation ("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client:3.0.6")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}