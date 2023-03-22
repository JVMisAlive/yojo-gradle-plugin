package ru.yojo.codegen;

import org.gradle.api.DefaultTask;
import org.gradle.api.file.ConfigurableFileCollection;
import org.gradle.api.tasks.TaskAction;

public class YojoGenerateTask extends DefaultTask {

    @TaskAction
    public void generateClasses() {
        System.out.println("TASK IS STARTED");
        YojoExtension yojoExtension = new YojoExtension(getProject().getObjects());
        yojoExtension.getConfigurations().forEach(System.out::println);
        ConfigurableFileCollection inputFileCollection =
                getProject().getObjects().fileCollection().from(
                        getProject().getLayout().getProjectDirectory().dir("api_specification").getAsFileTree());
        inputFileCollection.forEach(System.out::println);
        System.out.println("TASK IS COMPLETED");
    }

}