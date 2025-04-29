package com.sylxnc.nera.api.module;


public interface Module {
    void onLoad();
    void onEnable();
    void onDisable();

    String getName();
    String getVersion();
    String getDescription();
}
