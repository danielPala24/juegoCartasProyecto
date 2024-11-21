/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CommandPattern;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PALA
 */
public class CommandManager {
    // singleton
    private static CommandManager commandManager;
    private final List<iCommand> commands;
    
    private CommandManager(){
        commands = new ArrayList<>();
    }
    
    public static synchronized CommandManager getInstance(){
        if(commandManager == null){
            commandManager = new CommandManager();
        }
        return commandManager;
    }
    
    // Registra un comando a la lista
    public void reigsterCommand(iCommand command){
        commands.add(command);
    }
    
    // Buscar un comando
    public iCommand getCommand(String commandName){
        for(iCommand command : commands){
            if(command.getCommandName().equalsIgnoreCase(commandName)){
                return command;
            }
        }
        return new NotFoundCommand();
    }
}
