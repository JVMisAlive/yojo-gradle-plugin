package ru.yojo.yamltopojo;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskProvider;

@SuppressWarnings("unused")
public class YojoPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        String taskName = "generateYojo";
        YojoExtension yojoExtension = project.getExtensions().create("yojo", YojoExtension.class);

        yojoExtension.getConfigurations().configureEach(config -> {
            TaskProvider<YojoGenerate> yojo =
                    project.getTasks().register(
                            taskName,
                            YojoGenerate.class,
                            config,
                            project.getExtensions());
            yojo.configure(task -> {
                task.setDescription(String.format("Generates the POJOs from the %s YOJO configuration.", config.name));
                task.setGroup("Yojo");
            });
        });
    }

    public static String capitalize(String s) {
        return s == null || s.isEmpty() ? s : Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

}
