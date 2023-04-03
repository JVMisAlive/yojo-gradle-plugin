package ru.yojo.codegen.meta;

import org.gradle.api.tasks.Input;

public class Directories {
    @Input
    protected String outputDirectory;
    @Input
    protected String contractDirectory;

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
