package com.sylxnc.nera.api.events;

import com.sylxnc.nera.api.events.button.UserButtonClickEvent;
import com.sylxnc.nera.api.events.join.BotJoinEvent;
import com.sylxnc.nera.api.events.join.GenericJoinEvent;
import com.sylxnc.nera.api.events.join.UserJoinEvent;
import com.sylxnc.nera.api.events.leave.BotLeaveEvent;
import com.sylxnc.nera.api.events.leave.GenericLeaveEvent;
import com.sylxnc.nera.api.events.leave.UserLeaveEvent;
import com.sylxnc.nera.api.events.message.ModMessageDeleteEvent;
import com.sylxnc.nera.api.events.message.UserMessageSendEvent;

public interface EventListener {

    default void onGenericJoin(GenericJoinEvent event){}
    default void onBotJoin(BotJoinEvent event){}
    default void onUserJoin(UserJoinEvent event){}
    default void onUserLeave(UserLeaveEvent event){}
    default void onBotLeave(BotLeaveEvent event){}
    default void onGenericLeave(GenericLeaveEvent event){}
    default void onUserButtonClick(UserButtonClickEvent event){}
    default void onModMessageDelete(ModMessageDeleteEvent event){}
    default void onUserMessageSend(UserMessageSendEvent event){}

}
