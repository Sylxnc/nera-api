package com.sylxnc.nera.api.events.join;


import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

public class BotJoinEvent extends GenericJoinEvent {
    public BotJoinEvent(Object source, Member self, Guild guild) {
        super(source, self, guild);
    }
}