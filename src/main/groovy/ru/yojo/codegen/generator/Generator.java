package ru.yojo.codegen.generator;

import ru.yojo.codegen.domain.LombokProperties;
import ru.yojo.codegen.domain.MessageImplementationProperties;

/**
 * Generate pojos from Yaml File
 */
public interface Generator {

    /**
     * Core method of generator
     * Must generate pojos using Yaml File
     *
     * @param filePath         - path to yaml file
     * @param outputDirectory  - output directory for pojos
     * @param lombokProperties - lombok annotation properties
     * @param packageLocation  - location of generated pojo
     * @param messageImplementationProperties - which class needs to implements
     */
    void generate(String filePath, String outputDirectory, String packageLocation, LombokProperties lombokProperties, MessageImplementationProperties messageImplementationProperties);
}
