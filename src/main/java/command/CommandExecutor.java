package main.java.command;

import java.util.List;
import java.util.ArrayList;

public class CommandExecutor {
    private  List<Command> commands = new ArrayList<>();

    public CommandExecutor(SettleUpCommand settleUpCommand) {
        this.commands.add(settleUpCommand);
    }
    public void addCommand(Command command){
        commands.remove(command);
    }
    public void execute(String input){
        for (Command command : commands){
            if(command.matches(input)){
                command.execute(input);
                break;
            }
        }
    }
}
