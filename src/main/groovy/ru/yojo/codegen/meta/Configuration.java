package ru.yojo.codegen.meta;

import groovy.lang.Closure;
import org.gradle.api.file.ProjectLayout;
import ru.yojo.codegen.YojoConfig;
import ru.yojo.codegen.domain.LombokProperties;

import javax.inject.Inject;

public class Configuration {

    private ProjectLayout layout;
    protected Boolean lombokEnabled;
    protected Boolean allArgsConstructor;
    protected Accessors accessors;
    protected MessageImplementation messageImplementation;
    protected String packageLocation;
    protected Directories directories;

    @Inject
    public Configuration(ProjectLayout layout) {
        this.layout = layout;
    }

    @SuppressWarnings("unused")
    public void directories(Closure<?> closure) {
        YojoConfig.applyClosureToDelegate(closure, directories);
    }

    @SuppressWarnings("unused")
    public void accessors(Closure<?> closure) {
        YojoConfig.applyClosureToDelegate(closure, accessors);
    }

    @SuppressWarnings("unused")
    public void messageImplementation(Closure<?> closure) {
        YojoConfig.applyClosureToDelegate(closure, messageImplementation);
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

    public Directories getDirectories() {
        return directories;
    }

    public void setDirectories(Directories directoriesProperty) {
        this.directories = directoriesProperty;
    }

    public String getPackageLocation() {
        return packageLocation;
    }

    public void setPackageLocation(String packageLocation) {
        this.packageLocation = packageLocation;
    }

    public MessageImplementation getMessageImplementation() {
        return messageImplementation;
    }

    public void setMessageImplementation(MessageImplementation messageImplementation) {
        this.messageImplementation = messageImplementation;
    }

    public Configuration withDirectories() {
        this.directories = new Directories();
        return this;
    }

    public Configuration withAccessors() {
        this.accessors = new Accessors();
        return this;
    }

    public Configuration withMessageImplementation() {
        this.messageImplementation = new MessageImplementation();
        return this;
    }

}
