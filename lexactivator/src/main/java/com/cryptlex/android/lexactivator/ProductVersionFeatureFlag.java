package com.cryptlex.android.lexactivator;

public class ProductVersionFeatureFlag {
    public String name;

    public Boolean enabled;

    public String data;

    public ProductVersionFeatureFlag(String name, Boolean enabled, String data) {
        this.name = name;
        this.enabled = enabled;
        this.data = data;
    }
}
