package ru.yojo.codegen;

import org.gradle.api.DefaultTask;
import org.gradle.api.file.ConfigurableFileCollection;
import org.gradle.api.plugins.ExtensionContainer;
import org.gradle.api.tasks.CacheableTask;
import org.gradle.api.tasks.TaskAction;
import ru.yojo.codegen.context.YojoContext;
import ru.yojo.codegen.domain.lombok.LombokProperties;
import ru.yojo.codegen.generator.YojoGenerator;
import ru.yojo.codegen.mapper.Helper;
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


        Helper helper = new Helper();
        SchemaMapper schemaMapper = new SchemaMapper(helper);
        YojoGenerator yojoGenerator = new YojoGenerator(schemaMapper, new MessageMapper(helper, schemaMapper));
        long millis = System.currentTimeMillis();

        String outputDirectory = getProject().getBuildDir() + yojoConfig.getOutputDirectory() + yojoConfig.getPackageLocation().replace(".", "/") + "/";
        System.out.println(ANSI_RED + "YOJO START GENERATE FROM DIRECTORY: " + yojoConfig.getContractDirectory() + ANSI_RESET);
        if (!new File(outputDirectory).exists()) {
            new File(outputDirectory).mkdirs();
        }

        Accessors accessors = yojoConfig.getAccessors();
        ru.yojo.codegen.domain.lombok.Accessors lombokAccessors = new ru.yojo.codegen.domain.lombok.Accessors(accessors.isEnable(), accessors.isFluent(), accessors.isChain());
        try {
            yojoGenerator.generateAll(getYojoContext(outputDirectory, lombokAccessors));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        long l = System.currentTimeMillis() - millis;
        System.out.println("TIME FOR GENERATION: " + l);
        System.out.println("TASK IS COMPLETED");
    }

    private YojoContext getYojoContext(String outputDirectory, ru.yojo.codegen.domain.lombok.Accessors lombokSccessors) {
        YojoContext yojoContext = new YojoContext();
        yojoContext.setDirectory(yojoConfig.getContractDirectory());
        yojoContext.setOutputDirectory(outputDirectory);
        yojoContext.setPackageLocation(yojoConfig.getPackageLocation());
        yojoContext.setLombokProperties(new LombokProperties(
                yojoConfig.getLombokEnabled(),
                yojoConfig.getAllArgsConstructor(),
                lombokSccessors));
        yojoContext.setSpringBootVersion(yojoConfig.getSpringBootVersion());
        return yojoContext;
    }

}