package de.nera.api.events;


import net.dv8tion.jda.api.entities.Member;

import java.util.EventObject;

public class BotJoinEvent extends EventObject {
    private final Member member;

    public BotJoinEvent(Object source, Member member) {
        super(source);
        this.member = member;
    }
    public Member getMember() {
        return member;
    }
}
