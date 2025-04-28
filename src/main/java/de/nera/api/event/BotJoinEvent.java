package de.nera.api.event;

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
