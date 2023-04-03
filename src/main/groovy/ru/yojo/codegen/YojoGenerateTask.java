package ru.yojo.codegen;

import org.gradle.api.DefaultTask;
import org.gradle.api.file.ConfigurableFileCollection;
import org.gradle.api.plugins.ExtensionContainer;
import org.gradle.api.tasks.CacheableTask;
import org.gradle.api.tasks.TaskAction;
import ru.yojo.codegen.mapper.MessageMapper;
import ru.yojo.codegen.meta.Configuration;
import ru.yojo.codegen.domain.LombokProperties;
import ru.yojo.codegen.generator.YojoGenerator;
import ru.yojo.codegen.mapper.SchemaMapper;

import javax.inject.Inject;
import java.io.File;

@CacheableTask
public abstract class YojoGenerateTask extends DefaultTask {

    private final Configuration yojoConfig;
    private final ExtensionContainer extensionContainer;

    @Inject
    public YojoGenerateTask(YojoConfig yojoConfig, ExtensionContainer extensions) {
        this.yojoConfig = yojoConfig.getYojoConfiguration();
        this.extensionContainer = extensions;
    }

    @TaskAction
    public void generateClasses() {
        System.out.println("TASK IS STARTED");
        if (yojoConfig.getDirectories().getOutputDirectory() == null) {
            yojoConfig.getDirectories().setOutputDirectory("/generated-sources/yojo/");
        }

        if (yojoConfig.getDirectories().getContractDirectory() == null) {
            yojoConfig.getDirectories().setContractDirectory(getProject().getLayout().getProjectDirectory().getAsFile().getPath() + "/api_specification/");
        }

        ConfigurableFileCollection inputFileCollection =
                getProject().getObjects().fileCollection().from(
                        getProject().getLayout().getProjectDirectory().dir(yojoConfig.getDirectories().getContractDirectory()).getAsFileTree());

        YojoGenerator yojoGenerator = new YojoGenerator(new SchemaMapper(), new MessageMapper());
        inputFileCollection.forEach(file -> {
            String path = getProject().getBuildDir() + yojoConfig.getDirectories().getOutputDirectory();
            System.out.println("YOJO START GENERATE FROM THE FILE: " + file.getName());
            if (!new File(path).exists()) {
                new File(path).mkdirs();
            }
            yojoGenerator.generate(file.getPath(), path, yojoConfig.getPackageLocation(), new LombokProperties(yojoConfig.getLombokEnabled(), yojoConfig.getAllArgsConstructor(), yojoConfig.getAccessors()));
        });

        System.out.println("TASK IS COMPLETED");
    }

}