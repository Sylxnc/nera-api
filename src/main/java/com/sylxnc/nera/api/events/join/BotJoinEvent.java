package com.sylxnc.nera.api.events.join;


import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

import java.util.EventObject;

public class BotJoinEvent extends EventObject {
    private final Member member;
    private final Guild guild;

    public BotJoinEvent(Object source, Member member, Guild guild) {
        super(source);
        this.member = member;
        this.guild = guild;


    }
    public Member getMember() {
        return member;
    }
    public Guild getGuild() {
        return guild;
    }
}
