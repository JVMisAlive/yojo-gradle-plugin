package ru.yojo.codegen.meta;

import org.gradle.api.file.ProjectLayout;
import org.gradle.api.tasks.Input;

import java.io.File;

public class Directories {
    @Input
    protected String outputDirectory;
    @Input
    protected String contractDirectory;

    public Directories(ProjectLayout layout) {

        if (getOutputDirectory() == null) {
            new File(layout.getBuildDirectory() + "generated-src/yojo/").mkdirs();
            setOutputDirectory(layout.getBuildDirectory().dir("generated-src/yojo/").toString());
        }

        if (getContractDirectory() == null) {
            setContractDirectory(layout.getProjectDirectory().dir("api_specification/").toString());
        }
    }

    public String getOutputDirectory() {
        return outputDirectory;
    }

    public String getContractDirectory() {
        return contractDirectory;
    }

    public void setOutputDirectory(String outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    public void setContractDirectory(String contractDirectory) {
        this.contractDirectory = contractDirectory;
    }
}
