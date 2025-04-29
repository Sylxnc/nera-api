package com.sylxnc.nera.api.events.leave;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

import java.util.EventObject;

public class GenericLeaveEvent extends EventObject {
    private final Member member;
    private final Guild guild;

    public GenericLeaveEvent(Object source, Member member, Guild guild) {
        super(source);
        this.member = member;
        this.guild = guild;
    }
    public Member getMember() {
        return member;
    }

    public Guild getGuild() {
        JDA jda = guild.getJDA();
        return guild;


    }
}
