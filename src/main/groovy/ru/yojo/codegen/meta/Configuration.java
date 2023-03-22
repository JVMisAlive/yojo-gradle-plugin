package ru.yojo.codegen.meta;

import org.gradle.api.provider.Property;

public class Configuration {

    protected Property<Boolean> lombokEnabled;
    protected Property<Boolean> allArgsConstructor;
    protected Property<Boolean> accessors;
    protected Property<Directory> directoryProperty;
}
