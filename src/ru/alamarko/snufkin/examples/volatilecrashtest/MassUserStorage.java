package ru.alamarko.snufkin.examples.volatilecrashtest;

import java.util.ArrayList;
import java.util.List;

public class MassUserStorage
{
    public static final String SHOULD_BE_AT_LEAST_1_USER = "Should be at least 1 user";
    private List<User> users;
    private final static String C_NAMEPREFIX = "UserName_";

    public MassUserStorage(int pMinRange, int pMaxRange, int pNumOfUsers)
    {
        assert pNumOfUsers > 0 : SHOULD_BE_AT_LEAST_1_USER;
        users = new ArrayList<>(pNumOfUsers);
        for (int ii = 0; ii < pNumOfUsers; ii++)
        {
            users.add(new User(pMinRange, pMaxRange, ii, C_NAMEPREFIX + ii));
        }
    }

    public Double getTotalAmount()
    {
       return users.stream().map(x -> x.getAmmount_remainder()).reduce((x, y) -> x + y).get();
    }

    public User getUserById(int pId)
    {
        int kk=0;
        return this.users.stream().filter(x->x.getId()==pId).findFirst().get();
    }

    @Override
    public String toString()
    {
        return String.format("%.10f", getTotalAmount());
    }


}
