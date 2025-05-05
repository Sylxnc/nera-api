package com.sylxnc.nera.api.events;

import com.sylxnc.nera.api.events.button.UserButtonClickEvent;

import com.sylxnc.nera.api.events.join.*;
import com.sylxnc.nera.api.events.leave.*;
import com.sylxnc.nera.api.events.message.*;
import net.dv8tion.jda.api.events.channel.ChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.ChannelDeleteEvent;
import net.dv8tion.jda.api.events.guild.GuildBanEvent;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.guild.GuildUnbanEvent;
import net.dv8tion.jda.api.events.message.MessageBulkDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.events.role.RoleCreateEvent;
import net.dv8tion.jda.api.events.role.RoleDeleteEvent;


public interface EventListener {

    // ─────────────────────────────────────────────────────────────
    //  1) General Join/Leave Events (bereits vorhanden)
    default void onGenericJoin(GenericJoinEvent e) {}
    default void onBotJoin    (BotJoinEvent e) {}
    default void onUserJoin   (UserJoinEvent e) {}
    default void onUserLeave  (UserLeaveEvent e) {}
    default void onBotLeave   (BotLeaveEvent e) {}
    default void onGenericLeave(GenericLeaveEvent e) {}

    // ─────────────────────────────────────────────────────────────
    //  2) Message Events
    default void onUserMessageSend    (UserMessageSendEvent e) {}
    default void onModMessageDelete   (ModMessageDeleteEvent e) {}
    default void onMessageUpdate      (MessageUpdateEvent e) {}
    default void onMessageBulkDelete  (MessageBulkDeleteEvent e) {}

    // ─────────────────────────────────────────────────────────────
    //  3) Reaction Events
    default void onReactionAdd    (MessageReactionAddEvent e) {}
    default void onReactionRemove (MessageReactionRemoveEvent e) {}

    // ─────────────────────────────────────────────────────────────
    //  4) Channel Events
    default void onTextChannelCreate  (ChannelCreateEvent e) {}
    default void onTextChannelDelete  (ChannelDeleteEvent e) {}

    // ─────────────────────────────────────────────────────────────
    //  5) Guild / Server Events
    default void onGuildJoin      (GuildJoinEvent e) {}
    default void onGuildLeaveBot  (GuildLeaveEvent e) {}
    default void onGuildBan       (GuildBanEvent e) {}
    default void onGuildUnban     (GuildUnbanEvent e) {}
    default void onRoleCreate     (RoleCreateEvent e) {}
    default void onRoleDelete     (RoleDeleteEvent e) {}

    // ─────────────────────────────────────────────────────────────
    /*  6) Voice State Events
    default void onVoiceJoin    VoiceJoinEvent e) {}
    default void onVoiceLeave   (VoiceLeaveEvent e) {}
    default void onVoiceMove    (VoiceMoveEvent e) {}
    default void onVoiceMute    (VoiceMuteEvent e) {}
    default void onVoiceDeafen  (VoiceDeafenEvent e) {}
    default void onVoiceStateUpdate (VoiceStateUpdateEvent e) {}

    // ─────────────────────────────────────────────────────────────
    //  7) Presence / Typing
    default void onPresenceUpdate (PresenceUpdateEvent e) {}
    default void onTypingStart    (TypingStartEvent e) {}

    // ─────────────────────────────────────────────────────────────
    //  8) Interactions (Slash, Select, Modal, Buttons)
    default void onSlashCommand     (SlashCommandEvent e) {}
    default void onSelectMenu       (SelectMenuEvent e) {}
    default void onModalSubmit      (ModalSubmitEvent e) {}
    default void onUserButtonClick  (UserButtonClickEvent e) {}

    // ─────────────────────────────────────────────────────────────
    //  9) Scheduler / Task Events (optional)
    default void onScheduledTask    (ScheduledTaskEvent e) {}

     */
}
