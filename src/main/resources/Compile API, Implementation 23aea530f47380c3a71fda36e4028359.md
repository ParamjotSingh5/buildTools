# Compile/API, Implementation

Created by: Paramjot Singh
Created time: July 24, 2025 10:36 PM
Tags: Product

# Compile/API:

A configuration initially defined in Gradle, this configuration marks/forces mentioned dependency by your module to be compile and runtime dependent on your dependent. 

In recent upgrades, Gradle renamed `compile` configuration with `api` keyword, just to trigger discussions, motivation about migrating from `compile` configuration and migrating not transitively required dependencies to `implimentation` configuration.

```groovy
compile('org.apache.commons:commons-lang3:3.12.0') // Exposed to consumers, transitive
```

```groovy
// Module A
dependencies {
    compile 'org.springframework.boot:spring-boot-starter-web'
    compile 'mysql:mysql-connector-java'
    compile 'redis.clients:jedis'
}

// Module B depends on Module A
dependencies {
    compile project(':moduleA')
    // Module B automatically gets access to:
    // - Spring Boot classes
    // - MySQL driver classes  
    // - Redis client classes
    // Even if Module B doesn't need them!
}
```

# Implementation:

This was the real change and improvement introduced with recent upgrades in Gradle configurations. It does not exposes your module dependencies to dependent modules.

```groovy
// Module A - Better encapsulation
dependencies {
    api 'com.fasterxml.jackson.core:jackson-core'        // Exposed (part of public API)
    implementation 'mysql:mysql-connector-java'          // Hidden (internal detail)
    implementation 'redis.clients:jedis'                 // Hidden (internal detail)
}

// Module B depends on Module A
dependencies {
    implementation project(':moduleA')
    // Module B only gets access to:
    // - Jackson classes (because Module A uses 'api')
    // - Module A's own classes
    // MySQL and Redis are hidden!
}
```

Practical example:

```groovy
// UserService module
dependencies {
    // Part of your public API - consumers need these
    api 'org.springframework:spring-web'              // You return ResponseEntity
    api 'com.fasterxml.jackson.core:jackson-core'    // You return Jackson objects
    
    // Internal implementation details - consumers don't need these
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'mysql:mysql-connector-java'
    implementation 'org.springframework.boot:spring-boot-starter-security'
}
```