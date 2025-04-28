package de.nera.api.events.userjoin;


import java.util.EventListener;

public interface UserJoinListener extends EventListener {
    void onUserJoin(UserJoinEvent event);
}
