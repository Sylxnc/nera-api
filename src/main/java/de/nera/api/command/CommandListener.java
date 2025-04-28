package de.nera.api.command;


import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter {

    private final CommandManager commandManager;
    private final String prefix;

    public CommandListener(CommandManager commandManager, String prefix) {
        this.commandManager = commandManager;
        this.prefix = prefix;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        String message = event.getMessage().getContentRaw();

        if (message.startsWith(prefix)) {
            String[] parts = message.substring(prefix.length()).split(" ");
            String commandName = parts[0];
            String[] args = new String[parts.length - 1];
            System.arraycopy(parts, 1, args, 0, args.length);

            boolean executed = commandManager.executeCommand(event.getAuthor().getName(), commandName, args);

            if (!executed) {
                event.getChannel().sendMessage("Unbekannter command" + commandName + "`").queue();
            }
        }
    }
}
