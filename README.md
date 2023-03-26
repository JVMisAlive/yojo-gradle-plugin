# yojo-gradle-plugin
This is yojo generator starts when spring-boot application is started.

Configuration example:

```
yojo {
    configurations {
        main {
            yojoConfiguration {
                lombokEnabled = true
                allArgsConstructor = true
                accessors = true
                directories {
                    outputDirectory = "./ru/yojo/pojos/"
                    contractDirectory = "./api_specification/"
                }
            }
        }
    }
}
```