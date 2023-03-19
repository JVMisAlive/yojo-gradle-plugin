package ru.yojo.yamltopojo;

import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.model.ObjectFactory;

import javax.inject.Inject;

public class YojoExtension {

    private final NamedDomainObjectContainer<YojoConfig> configurations;

    @Inject
    public YojoExtension(ObjectFactory objects) {
        this.configurations = objects.domainObjectContainer(YojoConfig.class, name -> objects.newInstance(YojoConfig.class, name));
    }

    public NamedDomainObjectContainer<YojoConfig> getConfigurations() {
        return configurations;
    }
}
