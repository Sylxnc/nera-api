package com.sylxnc.nera.api.events.join;


import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

public class UserJoinEvent extends GenericJoinEvent {
    public UserJoinEvent(Object source, Member member, Guild guild) {
        super(source, member, guild);
    }
}