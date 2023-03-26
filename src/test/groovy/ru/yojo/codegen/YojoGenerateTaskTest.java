package ru.yojo.codegen;

import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.tasks.TaskProvider;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class YojoGenerateTaskTest {

    @Test
    void generateClasses() {
        Project project = ProjectBuilder.builder().build();
        YojoExtension yojoExtension = project.getExtensions().create("yojo", YojoExtension.class);

        yojoExtension.getConfigurations().configureEach(config -> {
            String taskName = "generateClasses";
            project.getTasks().create(taskName, YojoGenerateTask.class, config, project.getExtensions());
            Assertions.assertNotNull(project.getTasks().getByName("generateClasses"));
        });
    }
}