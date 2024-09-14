package main.java.runner;

import main.java.controller.UserController;
import main.java.dtos.Transaction;
import main.java.models.*;
import main.java.repositories.ExpenseRepository;
import main.java.repositories.GroupRepository;
import main.java.repositories.UserExpenseRepository;
import main.java.repositories.UserRespository;
import main.java.services.UserService;
import main.java.strategy.HeapSettleupStrategy;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        // 1. Create users
        User salman = new User("salman", "1234", "4321");
        User amit = new User("amit", "1234", "4321");
        User sundar = new User("sundar", "1234", "4321");
        User ramesh = new User("ramesh", "1234", "4321");

        List<User> goGuys = new ArrayList<>();

        goGuys.add(salman);
        goGuys.add(amit);
        goGuys.add(sundar);
        goGuys.add(ramesh);

        // 2. create the group and add these users to the group
        Group goaTrip = new Group("GOA-TRIP");
        goaTrip.setUsers(goGuys);

        // They went for goa. for Dinner
        // 3. create expenses(it is just bill)
        Expense dinnerExpense = new Expense("Dinner", 5000, ExpenseType.REGULAR);

        // 4. add the expense share of everyone(How much each person had to pay)
        UserExpense salmanShare = new UserExpense(salman, dinnerExpense, 1000, UserExpenseType.HAD_TO_PAY);

        UserExpense amitShare = new UserExpense(amit, dinnerExpense, 1000, UserExpenseType.HAD_TO_PAY);

        UserExpense sundarShare = new UserExpense(sundar, dinnerExpense, 1000, UserExpenseType.HAD_TO_PAY);

        UserExpense rameshShare = new UserExpense(ramesh, dinnerExpense, 2000, UserExpenseType.HAD_TO_PAY);

        // 5. Create who actually paid what
        UserExpense paidBySalman = new UserExpense(salman, dinnerExpense, 5000, UserExpenseType.PAID_BY);

        // 6. Manually add all these details to the database/repositories.
        UserRespository userRespository = new UserRespository();
        GroupRepository groupRepository = new GroupRepository();
        UserExpenseRepository userExpenseRepository = new UserExpenseRepository();
        ExpenseRepository expenseRespository = new ExpenseRepository();

        userRespository.setUsers(goGuys);
        goaTrip.getExpenses().add(dinnerExpense);
        groupRepository.getGroups().add(goaTrip);
        expenseRespository.getExpenses().add(dinnerExpense);

        userExpenseRepository.getUserExpenses().add(salmanShare);
        userExpenseRepository.getUserExpenses().add(amitShare);
        userExpenseRepository.getUserExpenses().add(sundarShare);
        userExpenseRepository.getUserExpenses().add(rameshShare);

        userExpenseRepository.getUserExpenses().add(paidBySalman);

        //
        UserController userController= new UserController(
                new UserService(groupRepository,userExpenseRepository, new HeapSettleupStrategy()));

        List<Transaction>userTransactions =userController.settleUser("salman","GOA-TRIP");

        for (Transaction transaction: userTransactions){
           System.out.println(transaction.getFrom()+"->" + transaction.getTo()+" : "+transaction.getAmount());
        }
        /*
            Excepted :
                Amit -> Salman : 1000
                Sundar -> Salman : 1000
                Ramesh -> Salman : 2000

        */

    }
}
