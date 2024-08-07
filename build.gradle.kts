plugins {
    java
    application
    eclipse
    alias(libs.plugins.versions)
    alias(libs.plugins.version.catalog.update)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

group = "com.kousenit"
version = "1.0"

application {
    mainClass.set("com.kousenit.fileio.ProcessDictionary")
}

repositories {
    mavenCentral()
}

dependencies {
    // JSON parsers
    implementation(libs.gson)
    implementation(libs.bundles.jackson)

    // Logging
    implementation(libs.slf4j.nop)

    // Testing
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation(libs.assertj)
    testImplementation(libs.bundles.mockito)
    testImplementation(libs.wiremock)
    testImplementation("com.jayway.jsonpath:json-path:2.9.0")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    jvmArgs("-XX:+EnableDynamicAgentLoading", "-Xshare:off")
}

tasks.withType<JavaExec>().configureEach {
    jvmArgs("-XX:+EnableDynamicAgentLoading", "-Xshare:off")
}
