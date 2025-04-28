package de.nera.api.events.utils;

import de.nera.api.events.BotJoinEvent;
import de.nera.api.events.GenericJoinEvent;
import de.nera.api.events.UserJoinEvent;

public interface EventListener {

    default void onGenericJoin(GenericJoinEvent event){

    }
     default void onBotJoin(BotJoinEvent event){

    }
    default void onUserJoin(UserJoinEvent event){

    }
}
