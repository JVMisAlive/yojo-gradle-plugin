package ru.yojo.codegen.meta;

import groovy.lang.Closure;
import org.gradle.api.file.ProjectLayout;
import org.gradle.api.tasks.Input;
import ru.yojo.codegen.YojoConfig;

import javax.inject.Inject;

public class Configuration {

    private ProjectLayout layout;
    protected Boolean lombokEnabled;
    protected Boolean allArgsConstructor;
    protected Accessors accessors;
    protected String packageLocation;
    protected String outputDirectory;
    protected String contractDirectory;
    protected String springBootVersion;

    @Inject
    public Configuration(ProjectLayout layout) {
        this.layout = layout;
    }
    @SuppressWarnings("unused")
    public void accessors(Closure<?> closure) {
        YojoConfig.applyClosureToDelegate(closure, accessors);
    }

    public Boolean getLombokEnabled() {
        return lombokEnabled;
    }

    public void setLombokEnabled(Boolean lombokEnabled) {
        this.lombokEnabled = lombokEnabled;
    }

    public Boolean getAllArgsConstructor() {
        return allArgsConstructor;
    }

    public void setAllArgsConstructor(Boolean allArgsConstructor) {
        this.allArgsConstructor = allArgsConstructor;
    }

    public Accessors getAccessors() {
        return accessors;
    }

    public void setAccessors(Accessors accessors) {
        this.accessors = accessors;
    }

    public String getPackageLocation() {
        return packageLocation;
    }

    public void setPackageLocation(String packageLocation) {
        this.packageLocation = packageLocation;
    }

    public String getOutputDirectory() {
        return outputDirectory;
    }

    public void setOutputDirectory(String outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    public String getContractDirectory() {
        return contractDirectory;
    }

    public void setContractDirectory(String contractDirectory) {
        this.contractDirectory = contractDirectory;
    }

    public String getSpringBootVersion() {
        return springBootVersion;
    }

    public void setSpringBootVersion(String springBootVersion) {
        this.springBootVersion = springBootVersion;
    }

    public Configuration withAccessors() {
        this.accessors = new Accessors();
        return this;
    }

}
