val googleJavaFormatVersion = "1.18.1"

plugins {
	java
	id("org.springframework.boot") version "3.3.5"
	id("io.spring.dependency-management") version "1.1.6"
	id("com.diffplug.spotless") version "6.21.0"
}

group = "com.sookmyung"
version = "0.0.1-SNAPSHOT"

repositories {
	mavenCentral()
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("com.auth0:java-jwt:4.2.1")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.springframework.boot:spring-boot-starter-mail")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("com.h2database:h2")
	runtimeOnly("com.mysql:mysql-connector-j")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

spotless {
	java {
		target("**/*.java")
		googleJavaFormat(googleJavaFormatVersion)
		importOrder("sookmyung", "java", "javax", "jakarta", "org", "com")
		endWithNewline()
		removeUnusedImports()
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.register<Copy>("updateGitHooks") {
	from(".github/script/pre-commit")
	into(".git/hooks")
}

tasks.register<Exec>("makeGitHooksExecutable") {
	commandLine("chmod", "+x", ".git/hooks/pre-commit")
	dependsOn("updateGitHooks")
}

tasks.named("compileJava") {
	dependsOn("makeGitHooksExecutable")
}