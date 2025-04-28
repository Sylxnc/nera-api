package de.nera.api.events.genericjoin;


import java.util.EventListener;

public interface GenericJoinListener extends EventListener {
    void onGenericJoin(GenericJoinEvent event);
}
