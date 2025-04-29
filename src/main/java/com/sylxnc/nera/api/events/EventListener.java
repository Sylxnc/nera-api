package com.sylxnc.nera.api.events;

import com.sylxnc.nera.api.events.join.BotJoinEvent;
import com.sylxnc.nera.api.events.join.GenericJoinEvent;
import com.sylxnc.nera.api.events.join.UserJoinEvent;

public interface EventListener {

    default void onGenericJoin(GenericJoinEvent event){

    }
     default void onBotJoin(BotJoinEvent event){

    }
    default void onUserJoin(UserJoinEvent event){

    }
}
