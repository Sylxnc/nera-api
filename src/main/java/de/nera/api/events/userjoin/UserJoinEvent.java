package de.nera.api.events.userjoin;

import net.dv8tion.jda.api.entities.Member;

import java.util.EventObject;

public class UserJoinEvent extends EventObject {
    private final Member member;

    public UserJoinEvent(Object source, Member member) {
        super(source);
        this.member = member;
    }
    public Member getMember() {
        return member;
    }
}
