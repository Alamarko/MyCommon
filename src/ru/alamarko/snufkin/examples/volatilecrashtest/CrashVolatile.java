package ru.alamarko.snufkin.examples.volatilecrashtest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class CrashVolatile
{
    public static final String TEST_PASSED_ON_TRANSACTIONS_COUNT = "Test passed on transactions count:";
    public static final String TEST_FAILED_ON_TRANSACTIONS_COUNT="Test failed on transactions count:";
    public static final String CURRENT_TOTAL_AMOUNT = "Current total amount:";
    public static final String CREATING_TRANSACTIONS = "Creating Transactions...";
    public static final String AFTER_TRANSACTIONS_TOTAL_AMOUNT = "After transactions total amount:";
    private static int m_minRange = 10_000;
    private static final int m_maxRange = 100_000;
    private static int m_numOfUsers = 3;
    private static Random m_random = new Random();

    public static void main(String[] args)
    {
        int[] testThreadsNumber =
                new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16, 18, 20, 40, 50, 55, 58, 60, 80, 100};
        for (int ii = 0; ii < testThreadsNumber.length; ii++)
        {
            testTransactions(testThreadsNumber[ii]);
            System.out.println(
                    "---------------------------------######################---------------------------------");
        }
    }

    private static void testTransactions(int pNumOfTransactions)
    {
        MassUserStorage userStorage = new MassUserStorage(m_minRange, m_maxRange, m_numOfUsers);
        final double refAmount = userStorage.getTotalAmount();
        System.out.println(CURRENT_TOTAL_AMOUNT + userStorage);
        System.out.println(CREATING_TRANSACTIONS);
        List<Transaction> transactions = createTransactions(userStorage, pNumOfTransactions);
        ExecutorService tps = newFixedThreadPool(pNumOfTransactions);
        transactions.stream().parallel().forEach(x -> tps.execute(x));
        tps.shutdown();
        try
        {
            final boolean done = tps.awaitTermination(1, TimeUnit.MINUTES);
            //  System.out.println("Done?:" + done);
            System.out.println(AFTER_TRANSACTIONS_TOTAL_AMOUNT + userStorage);
            if (!userStorage.getTotalAmount().equals(refAmount))
                System.out.println(TEST_FAILED_ON_TRANSACTIONS_COUNT + pNumOfTransactions);
            else
                System.out.println(TEST_PASSED_ON_TRANSACTIONS_COUNT + pNumOfTransactions);
        }
        catch (InterruptedException pE)
        {
            pE.printStackTrace();
        }


    }

    public static List<Transaction> createTransactions(MassUserStorage pMassUserStorage, int pNumOfTransactions)
    {
        List<Transaction> transactions = new ArrayList<>(pNumOfTransactions);
        for (int ii = 0; ii < pNumOfTransactions; ii++)
        {
            User srcUser = pMassUserStorage.getUserById(GeneratorUtils.getNotEqualUser(-1, m_numOfUsers, m_random));

            User dstUser = pMassUserStorage.getUserById(
                    GeneratorUtils.getNotEqualUser(srcUser.getId(), m_numOfUsers, m_random));
            Transaction trans = new Transaction(ii,
                                                GeneratorUtils.getRandomAmountInRange(m_minRange, m_maxRange, m_random),
                                                srcUser, dstUser);
            transactions.add(trans);
        }
        return transactions;
    }


}
