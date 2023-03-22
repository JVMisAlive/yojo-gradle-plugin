package ru.yojo.codegen;

import org.gradle.api.Project;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class YojoPluginTest {
    @Test
    public void testThePlugin(){
        Project project = ProjectBuilder.builder().build();
        project.getPluginManager().apply("ru.yojo.yojo-gradle-plugin");
        Assertions.assertNotNull(project.getTasks().getByName("generateClasses"));
    }
}
