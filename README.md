# yojo-gradle-plugin
This is yojo generator starts when spring-boot application is started.

Configuration example:

```
plugins {
    id 'ru.yojo.codegen.gradle-plugin' version '0.0.1'
}

yojo {
    configurations {
        main {
            yojoConfiguration {
                lombokEnabled = true
                allArgsConstructor = true
                accessors = true
                packageLocation = "ru.yojo.codegen"
                directories {
                    outputDirectory = "./generated-sources/ru/yojo/codegen/"
                    contractDirectory = "./api_specification/"
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