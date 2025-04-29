package com.sylxnc.nera.api.events.join;

import com.sylxnc.nera.api.secured.SecuredMember;
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
