package ru.yojo.codegen.meta;

import org.gradle.api.file.DirectoryProperty;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.Input;

public interface Directory {
    @Input
    Property<DirectoryProperty> getOutputDirectory();
    @Input
    Property<DirectoryProperty> getContractDirectory();
}
