package org.sample.definition;

public class ArgDefinition {
    private String argName;
    private String argKey;
    private boolean resource; // デフォルト
    public ArgDefinition(String argName, String argKey, boolean resource) {
        this.argName = argName;
        this.argKey = argKey;
        this.resource = resource;
    }
    public String getArgName() {
        return argName;
    }
    public String getArgKey() {
        return argKey;
    }
    public boolean isResource() {
        return resource;
    }

}
