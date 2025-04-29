package com.sylxnc.nera.api.events.message;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.EventObject;

public class UserMessageSendEvent extends EventObject {

    private final MessageReceivedEvent event;
    private final String message;
    private final Member member;
    private final MessageChannel channel;


    public UserMessageSendEvent(Object source, MessageReceivedEvent event) {
        super(source);
        this.event = event;
        this.message = event.getMessage().getContentRaw();
        this.member = event.getMember();
        this.channel = event.getChannel();
    }





}
