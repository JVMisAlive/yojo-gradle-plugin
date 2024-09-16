# yojo-gradle-plugin
This is yojo generator starts when spring-boot application is started.
Generates Java classes before compilation started


Configuration example:

```
plugins {
    id 'ru.yojo.codegen.gradle-plugin' version '0.5.0'
}

yojo {
    configurations {
        main {
            yojoConfiguration {
                lombokEnabled = true
                allArgsConstructor = true
                accessors {
                    enable = true
                    fluent = false
                    chain = true
                }
                packageLocation = "ru.yojo.codegen"
                outputDirectory = "./generated-sources/org/example"
                contractDirectory = "./api_specification/"
                
                springBootVersion = "3.x.x" //default false
                }
            }
        }
    }
}

sourceSets {
    main {
        java {
            srcDir "${buildDir}/generated-sources"
        }
    }
}

tasks.compileJava.dependsOn(generateClasses)
```
