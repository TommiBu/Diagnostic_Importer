plugins {
    id("java")
    id("application")
}

group = "cz.diagnostic"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.poi:poi-ooxml:5.2.5")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

application {
    mainClass.set("cz.diagnostic.Main")
}

tasks.test {
    useJUnitPlatform()
}
