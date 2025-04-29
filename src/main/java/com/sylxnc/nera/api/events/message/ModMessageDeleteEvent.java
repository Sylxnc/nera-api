package com.sylxnc.nera.api.events.message;

import lombok.Getter;
import net.dv8tion.jda.api.audit.ActionType;
import net.dv8tion.jda.api.audit.AuditLogEntry;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;


import java.util.EventObject;

public class ModMessageDeleteEvent extends EventObject {

    @Getter
    private final Guild guild;
    private final MessageDeleteEvent event;
    @Getter
    private Member member;
    @Getter
    private Channel channel;


    public ModMessageDeleteEvent(Object source, MessageDeleteEvent event) {
        super(source);

        this.guild = event.getGuild();
        this.event = event;
        guild.retrieveAuditLogs().type(ActionType.MESSAGE_DELETE).queue(logs -> {
            for (AuditLogEntry entry : logs) {
                if (entry.getTargetId().equals(event.getMessageId())) {
                    member = guild.getMemberById(entry.getUserId());
                    break;
                }
            }
        });
        this.channel = event.getChannel();
    }




}
