package ru.yojo.yamltopojo.meta;

public class Configuration {

    protected Boolean lombokEnabled;
    protected Boolean allArgsConstructor;
    protected Boolean accessors;
    protected Generate generate;


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

    public Boolean getAccessors() {
        return accessors;
    }

    public void setAccessors(Boolean accessors) {
        this.accessors = accessors;
    }

    public Generate getGenerate() {
        return generate;
    }

    public void setGenerate(Generate generate) {
        this.generate = generate;
    }

    public Configuration withGenerate(Generate value) {
        setGenerate(value);
        return this;
    }
}
