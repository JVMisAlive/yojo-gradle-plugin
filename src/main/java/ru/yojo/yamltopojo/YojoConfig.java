package ru.yojo.yamltopojo;

import org.gradle.api.file.Directory;
import org.gradle.api.file.ProjectLayout;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.Provider;
import org.gradle.api.provider.ProviderFactory;
import ru.yojo.yamltopojo.meta.Configuration;
import ru.yojo.yamltopojo.meta.Generate;

import javax.inject.Inject;

public class YojoConfig {

    final String name;
    private final Configuration yojoConfiguration;
    private final Provider<Directory> outputDir;

    @Inject
    public YojoConfig(String name, ObjectFactory objects, ProviderFactory providers, ProjectLayout layout) {
        this.name = name;
        this.yojoConfiguration = yojoDefaultConfiguration();
        this.outputDir = layout.getProjectDirectory()
                .dir(providers.<CharSequence>provider(() -> yojoConfiguration.getGenerate().getDirectory()))
                .orElse(layout.getBuildDirectory().dir("generated-src/yojo/" + name));
    }

    public Provider<Directory> getOutputDir() {
        return outputDir;
    }

    private Configuration yojoDefaultConfiguration() {
        return new Configuration()
                .withGenerate(new Generate());
    }

    public Configuration getJooqConfiguration() {
        return yojoConfiguration;
    }
}
