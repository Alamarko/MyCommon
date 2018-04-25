package ru.alamarko.snufkin.examples.volatilecrashtest;

public class Transaction implements Runnable
{
    private volatile double m_transAmount;
    private User m_userSource;
    private User m_userDest;
    private int m_id;

    public Transaction(int pTransId, double pTransAmount, User pUserSource, User pUserDest)
    {
        m_id = pTransId;
        m_transAmount = pTransAmount;
        m_userSource = pUserSource;
        m_userDest = pUserDest;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run()
    {
        //        System.out.println(
        //                "Thread id: " + Thread.currentThread().getId() + " Processing transaction: " + this.m_id +  " Amount: "+ m_transAmount +", User " + m_userSource + " ---> User: " + m_userDest );
        try
        {
            m_userSource.changeAmountRemainder(-m_transAmount);
            //  System.out.println(LocalDateTime.now()+" "+Thread.currentThread().getId() + " " + m_userSource.getId() + "--->" + m_userDest.getId());
            Thread.sleep(500);
            //  System.out.println(Thread.currentThread().getId()+" active threads: "+Thread.activeCount());
            m_userDest.changeAmountRemainder(m_transAmount);
            Thread.sleep(500);
        }
        catch (InterruptedException pE)
        {
            pE.printStackTrace();
        }

        //        System.out.println(
        //                "Thread id: " + Thread.currentThread().getId() + " After transaction: " + this.m_id + " User " + m_userSource + " ---> User: " + m_userDest);
    }
}
