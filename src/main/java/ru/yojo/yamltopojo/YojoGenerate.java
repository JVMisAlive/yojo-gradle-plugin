package ru.yojo.yamltopojo;

import org.gradle.api.DefaultTask;
import org.gradle.api.file.Directory;
import org.gradle.api.file.ProjectLayout;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.Provider;
import org.gradle.api.tasks.CacheableTask;
import org.gradle.api.tasks.TaskAction;
import org.gradle.process.ExecOperations;
import ru.yojo.yamltopojo.meta.Configuration;

import javax.inject.Inject;
import java.io.File;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CacheableTask
public abstract class YojoGenerate extends DefaultTask {

    private final Configuration yojoConfiguration;
    private final Provider<Directory> outputDir;
    private final ExecOperations execOperations;
    private final ProjectLayout projectLayout;


    @Inject
    public YojoGenerate(YojoConfig config, ObjectFactory objects, ExecOperations execOperations, ProjectLayout projectLayout) {
        this.yojoConfiguration = config.getJooqConfiguration();
        this.outputDir = objects.directoryProperty().value(config.getOutputDir());
        this.execOperations = execOperations;
        this.projectLayout = projectLayout;
    }

    @TaskAction
    public void generate() {
        Set<String> fileNames = listFilesUsingJavaIO(yojoConfiguration.getGenerate().getContractDirectory());
        fileNames.forEach(fileName -> executeYojo(yojoConfiguration, fileName));
    }

    private void executeYojo(final Configuration configFile, String fileName) {
        execOperations.javaexec(spec -> {
            spec.getMainClass().set("ru.yojo.yamltopojo.YojoGenerator");
            spec.setWorkingDir(projectLayout.getProjectDirectory());
            spec.args(configFile.getGenerate().getDirectory(),
                    configFile.getGenerate().getDirectory() + "/" + fileName,
                    configFile.getLombokEnabled(),
                    configFile.getAllArgsConstructor(),
                    configFile.getAccessors());
        });
    }

    @SuppressWarnings("all")
    public Set<String> listFilesUsingJavaIO(String dir) {
        return Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }

}
