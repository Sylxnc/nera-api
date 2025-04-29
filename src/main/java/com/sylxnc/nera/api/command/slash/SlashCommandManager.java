package com.sylxnc.nera.api.command.slash;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlashCommandManager {

    private static final Map<String, SlashCommand> commands = new HashMap<>();

    public static void registerSlashCommand(SlashCommand command) {
        commands.put(command.getName().toLowerCase(), command);
    }

    public static void executeSlashCommand(SlashCommandInteractionEvent event) {
        String commandName = event.getName().toLowerCase();
        SlashCommand command = commands.get(commandName);

        if (command != null) {
            command.execute(event);
        } else {
            event.reply("Fehlerhafter Command: `" + commandName + "`").setEphemeral(true).queue();
        }
    }

    public static void registerCommandSystem(Guild guild) {

        List<CommandData> commandDataList = new ArrayList<>();
        for (SlashCommand cmd : commands.values()) {
            commandDataList.add(Commands.slash(cmd.getName(), cmd.getDescription()));
        }

        guild.updateCommands().addCommands(commandDataList).queue();


    }

}
