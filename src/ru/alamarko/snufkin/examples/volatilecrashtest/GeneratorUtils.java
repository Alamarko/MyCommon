package ru.alamarko.snufkin.examples.volatilecrashtest;

import java.util.Random;

public class GeneratorUtils
{
    public static double getRandomAmountInRange(int pRangeMin, int pRangeMax, Random pRandom)
    {
        return pRangeMin + (pRangeMax - pRangeMin) * pRandom.nextDouble();
    }

    public static final int getNotEqualUser(int pUserId, int pNumOfUsers, Random pRandom)
    {
        assert pRandom != null : "Random shouldn't be null";
        assert pNumOfUsers > 0 : "Should be at least 1 user";
        int userId = pRandom.nextInt(pNumOfUsers);
        return userId == pUserId ? userId < pNumOfUsers - 1 ? userId + 1 : userId - 1 : userId;
    }

}
