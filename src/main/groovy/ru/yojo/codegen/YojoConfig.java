package ru.yojo.codegen;

import groovy.lang.Closure;
import org.gradle.api.file.ProjectLayout;
import org.gradle.api.provider.ProviderFactory;
import ru.yojo.codegen.meta.Configuration;

import javax.inject.Inject;


public class YojoConfig {

    final String name;
    private ProjectLayout layout;

    private final Configuration yojoConfiguration;

    @Inject
    public YojoConfig(String name, ProviderFactory providers, ProjectLayout layout) {
        this.name = name;
        this.layout = layout;
        System.out.println("YOJO READ CONFIGURATIONS");
        this.yojoConfiguration = yojoDefaultConfiguration();
    }

    @SuppressWarnings("unused")
    public void yojoConfiguration(Closure<?> closure) {
        applyClosureToDelegate(closure, yojoConfiguration);
    }

    private Configuration yojoDefaultConfiguration() {
        return new Configuration(layout).withDirectories();
    }

    public String getName() {
        return name;
    }

    public Configuration getYojoConfiguration() {
        return yojoConfiguration;
    }

    /**
     * Applies the given closure to the given delegate.
     *
     * @param closure  the closure to apply
     * @param delegate the delegate that the closure is applied to
     */
    public static void applyClosureToDelegate(Closure<?> closure, Object delegate) {
        Closure<?> copy = (Closure<?>) closure.clone();
        copy.setResolveStrategy(Closure.DELEGATE_FIRST);
        copy.setDelegate(delegate);
        if (copy.getMaximumNumberOfParameters() == 0) {
            copy.call();
        } else {
            copy.call(delegate);
        }
    }

}
