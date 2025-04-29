package com.sylxnc.nera.api;

import com.sylxnc.nera.api.events.EventListener;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    public static List<EventListener> listeners = new ArrayList<>();

    public static void registerListener(EventListener listener) {
        listeners.add(listener);
    }


}
