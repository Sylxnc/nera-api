package com.sylxnc.nera.api.module;


import java.util.ArrayList;
import java.util.List;

public interface Module {
    void onLoad();
    void onEnable();
    void onDisable();

    String getName();
    String getVersion();
    String getDescription();

    String mainServerID();

    default List<String> lockedOnServerIDs() {
        return new ArrayList<>();
    }
}
