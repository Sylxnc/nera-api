package de.nera.api.events.botjoin;


import java.util.EventListener;

public interface BotJoinListener extends EventListener {
    void onBotJoin(BotJoinEvent event);
}
