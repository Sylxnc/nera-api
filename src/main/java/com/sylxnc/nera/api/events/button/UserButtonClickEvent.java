package com.sylxnc.nera.api.events.button;

import lombok.Getter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.Component;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.util.EventObject;

public class UserButtonClickEvent extends EventObject {

    @Getter
    private final Member member;
    @Getter
    private final Guild guild;
    @Getter
    private final Button button;
    private final ButtonInteractionEvent event;

    public UserButtonClickEvent(Object source, ButtonInteractionEvent event) {
        super(source);
        this.member = event.getMember();
        this.guild = event.getGuild();
        this.button = event.getButton();
        this.event = event;


    }

    public String getButtonID(){
        return button.getId();
    }
    public String getLabel(){
        return button.getLabel();
    }
    public Component.Type getType(){
        return button.getType();
    }

    public String getUrl() {
        return button.getUrl();
    }

    public void reply(String message, boolean empharel){
        event.reply(message).setEphemeral(empharel).queue();
    }
    public void reply(EmbedBuilder embedBuilder, boolean empharel){
        event.replyEmbeds(embedBuilder.build()).setEphemeral(empharel).queue();
    }
    public void reply(String message,EmbedBuilder embedBuilder, boolean empharel){
        event.reply(message).addEmbeds(embedBuilder.build()).setEphemeral(empharel).queue();
    }


}
