package ru.yojo.codegen.meta;

import org.gradle.api.tasks.Input;

public class MessageImplementation {

    @Input
    protected String implementationPackage;
    @Input
    protected String implementationClass;

    public String getImplementationPackage() {
        return implementationPackage;
    }

    public void setImplementationPackage(String implementationPackage) {
        this.implementationPackage = implementationPackage;
    }

    public String getImplementationClass() {
        return implementationClass;
    }

    public void setImplementationClass(String implementationClass) {
        this.implementationClass = implementationClass;
    }
}
