package com.sylxnc.nera.api.module;

public class ModuleContext {
    private final Object jda;
    private final Object eventManager;
    private final Object moduleManager;

    public ModuleContext(Object jda, Object eventManager, Object moduleManager) {
        this.jda = jda;
        this.eventManager = eventManager;
        this.moduleManager = moduleManager;
    }

    public Object getJda() {
        return jda;
    }

    public Object getEventManager() {
        return eventManager;
    }

    public Object getModuleManager() {
        return moduleManager;
    }
}
