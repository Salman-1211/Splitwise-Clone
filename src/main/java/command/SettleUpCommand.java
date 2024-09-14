package main.java.command;

import main.java.controller.UserController;
import main.java.dtos.Transaction;

import java.util.List;


// similarly we can add other commands also.
public class SettleUpCommand implements Command {



    private UserController userController;

    public static final String SETTLEUP_USER = "settleuser";

    public SettleUpCommand(UserController userController) {
        this.userController = userController;
    }
    @Override
    public void execute(String input) {
        // input -> salman goaTrip settleUser
       List<String> words = List.of( input.split(" "));
       List<Transaction> transactions = userController.settleUser(words.get(0),words.get(1));


       // print the transaction here and verify
    }

    @Override
    public boolean matches(String input) {
        // input -> salman goaTrip settleUser
        List<String> words = List.of( input.split(" "));

        return  words.get(2).equals(SETTLEUP_USER);

    }
}
