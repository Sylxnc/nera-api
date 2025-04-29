package com.sylxnc.nera.api.command;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {

    private final Map<String, Command> commands = new HashMap<>();

    public void registerCommand(Command command) {
        commands.put(command.getName().toLowerCase(), command);
        System.out.println("Command " + command.getName() + "' registered");
    }

    public boolean executeCommand(String sender, String commandName, String[] args) {
        Command command = commands.get(commandName.toLowerCase());
        if (command != null) {
            command.execute(sender, args);
            return true;
        }
        return false;
    }

    public boolean isCommand(String commandName) {
        return commands.containsKey(commandName.toLowerCase());
    }
}
