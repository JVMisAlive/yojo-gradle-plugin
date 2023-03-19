package ru.yojo.yamltopojo.meta;

public class Generate {
    protected String directory = "target/generated-sources/yojo";
    protected String contractDirectory = "api_specification";

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getContractDirectory() {
        return contractDirectory;
    }

    public void setContractDirectory(String contractDirectory) {
        this.contractDirectory = contractDirectory;
    }
}
