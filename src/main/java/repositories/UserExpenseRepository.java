package main.java.repositories;

import main.java.models.Expense;
import main.java.models.UserExpense;

import java.util.ArrayList;
import java.util.List;

public class UserExpenseRepository {
    private List<UserExpense> userExpenses = new ArrayList<>();

    public List<UserExpense> getUserExpenses() {
        return userExpenses;
    }

    public void setUserExpenses(List<UserExpense> userExpenses) {
        this.userExpenses = userExpenses;
    }
    public List<UserExpense> findUserExpensesByExpense(String desc) {

        List<UserExpense> userExpensesList = new ArrayList<>();
        for (UserExpense userExpense : userExpenses) {
            if (desc.equals(userExpense.getExpense().getDescription())){
                userExpensesList.add(userExpense);
            }
        }
        return userExpensesList;
        }
    }