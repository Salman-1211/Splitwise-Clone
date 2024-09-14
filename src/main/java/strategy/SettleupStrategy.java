package main.java.strategy;

import main.java.dtos.Transaction;
import main.java.models.User;

import java.util.List;
import java.util.Map;

public interface SettleupStrategy {
    public List<Transaction> settleUpUser(Map<User, Integer> map);
}
