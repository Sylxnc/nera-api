package com.sylxnc.nera.api.events.join;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import java.util.EventObject;

/** Wird für *jede* Join-Interaktion gefeuert. */
public class GenericJoinEvent extends EventObject {
    private final Member member;
    private final Guild guild;

    public GenericJoinEvent(Object source, Member member, Guild guild) {
        super(source);
        this.member = member;
        this.guild  = guild;
    }

    public Member getMember() {
        return member;
    }

    public Guild getGuild() {
        return guild;
    }
}

