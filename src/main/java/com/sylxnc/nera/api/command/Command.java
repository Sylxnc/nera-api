package com.sylxnc.nera.api.command;

public interface Command {
    String getName();
    void execute(String sender, String[] args);
}
