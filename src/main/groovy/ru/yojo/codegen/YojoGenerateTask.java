package ru.yojo.codegen;

import org.gradle.api.DefaultTask;
import org.gradle.api.file.ConfigurableFileCollection;
import org.gradle.api.plugins.ExtensionContainer;
import org.gradle.api.tasks.CacheableTask;
import org.gradle.api.tasks.TaskAction;
import ru.yojo.codegen.domain.lombok.LombokProperties;
import ru.yojo.codegen.generator.YojoGenerator;
import ru.yojo.codegen.mapper.MessageMapper;
import ru.yojo.codegen.mapper.SchemaMapper;
import ru.yojo.codegen.meta.Accessors;
import ru.yojo.codegen.meta.Configuration;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;

import static ru.yojo.codegen.util.LogUtils.ANSI_RESET;

@CacheableTask
public abstract class YojoGenerateTask extends DefaultTask {

    public static final String ANSI_RED = "\u001B[31m";

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
        if (yojoConfig.getOutputDirectory() == null) {
            yojoConfig.setOutputDirectory("/generated-src/yojo/main/");
        }

        if (yojoConfig.getContractDirectory() == null) {
            yojoConfig.setContractDirectory(getProject().getLayout().getProjectDirectory().getAsFile().getPath() + "/api_specification/");
        }

//        ConfigurableFileCollection inputFileCollection =
//                getProject().getObjects().fileCollection().from(
//                        getProject().getLayout().getProjectDirectory().dir(yojoConfig.getContractDirectory()).getAsFileTree());

        YojoGenerator yojoGenerator = new YojoGenerator(new SchemaMapper(), new MessageMapper());
        long millis = System.currentTimeMillis();
//        inputFileCollection.forEach(file -> {
//
//            String outputDirectory = getProject().getBuildDir() + "/generated-src/yojo/main/" + yojoConfig.getPackageLocation().replace(".", "/") + "/";
//            System.out.println(ANSI_RED + "YOJO START GENERATE FROM THE FILE: " + file.getName() + ANSI_RESET);
//            if (!new File(outputDirectory).exists()) {
//                new File(outputDirectory).mkdirs();
//            }
//            Accessors accessors = yojoConfig.getAccessors();
//            ru.yojo.codegen.domain.lombok.Accessors lombokAccessors = new ru.yojo.codegen.domain.lombok.Accessors(accessors.isEnable(), accessors.isFluent(), accessors.isChain());
//            yojoGenerator.generate(
//                    file.getPath(),
//                    outputDirectory,
//                    yojoConfig.getPackageLocation(),
//                    new LombokProperties(
//                            yojoConfig.getLombokEnabled(),
//                            yojoConfig.getAllArgsConstructor(),
//                            lombokAccessors)
//            );
//        });

        String outputDirectory = getProject().getBuildDir() + yojoConfig.getOutputDirectory() + yojoConfig.getPackageLocation().replace(".", "/") + "/";
        System.out.println(ANSI_RED + "YOJO START GENERATE FROM DIRECTORY: " + yojoConfig.getContractDirectory() + ANSI_RESET);
        if (!new File(outputDirectory).exists()) {
            new File(outputDirectory).mkdirs();
        }
        Accessors accessors = yojoConfig.getAccessors();
        ru.yojo.codegen.domain.lombok.Accessors lombokAccessors = new ru.yojo.codegen.domain.lombok.Accessors(accessors.isEnable(), accessors.isFluent(), accessors.isChain());
        try {
            yojoGenerator.generateAll(
                    yojoConfig.getContractDirectory(),
                    outputDirectory,
                    yojoConfig.getPackageLocation(),
                    new LombokProperties(
                            yojoConfig.getLombokEnabled(),
                            yojoConfig.getAllArgsConstructor(),
                            lombokAccessors)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        long l = System.currentTimeMillis() - millis;
        System.out.println("TIME FOR GENERATION: " + l);
        System.out.println("TASK IS COMPLETED");
    }

}