plugins {
    id("java")
}

group = "br.com.iterasys"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.testng:testng:7.7.1")
    testImplementation("io.rest-assured:rest-assured:5.3.0")
    implementation("org.seleniumhq.selenium:selenium-java:4.10.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation ("org.slf4j:slf4j-api:1.7.33")
    testImplementation("junit:junit:4.13.1")
    // https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager
    implementation("io.github.bonigarcia:webdrivermanager:5.4.0")


}

tasks.test {
    useTestNG()
}