package main.java.repositories;

import main.java.models.Expense;
import main.java.models.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupRepository {
    private List<Group> groups = new ArrayList<>();

    public List<Group> getGroups() {

        return groups;
    }

    public void setGroups() {

        this.groups = groups;
    }
    public List<Expense> findExpensesByGroup(String groupName){
        for(Group group: groups){
            if(groupName.equals(group.getName())){
                return  group.getExpenses();
            }
        }
        return new ArrayList<>();
    }
}
