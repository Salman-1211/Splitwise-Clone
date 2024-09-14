package main.java.command;

import main.java.controller.UserController;
import main.java.repositories.GroupRepository;
import main.java.repositories.UserExpenseRepository;
import main.java.services.UserService;
import main.java.strategy.HeapSettleupStrategy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CommandExecutor ce =new CommandExecutor(
                new SettleUpCommand(
                    new UserController(new UserService(
                                new GroupRepository(),
                                new UserExpenseRepository(),
                                new HeapSettleupStrategy()
                        ))));
        while (true){
           String input = scanner.next() ;
           ce.execute(input);
        }
    }
}
