package ru.alamarko.snufkin.tests.common;


public class TestSeq
{

    Integer val = new Integer(123);

    public static void main(String[] args)
    {
        int[] seq = {1, 2, 5, 4, 5, 6, 3, 8, 7};
        System.out.println(getSubSeqNumber(seq));
        TestSeq seq1 = new TestSeq(100);
        seq1.exampleMethod(20);
    }

    TestSeq(int pA)
    {
        val = pA;
    }

    void proxyMethod()
    {
    }


    public void exampleMethod(Integer pVal)
    {
        TestSeq seq = new TestSeq(pVal)
        {
            private void performSomeOper()
            {
                System.out.println(this.val);
            }

            @Override
            void proxyMethod()
            {
                performSomeOper();
            }
        };
        seq.proxyMethod();
    }


    private static int getSubSeqNumber(int[] pSeq)
    {
        int length = pSeq.length;
        int count = 0;
        int accumDiff = 0;
        for (int ii = 0; ii < length; ii++)
        {
            int diff = pSeq[ii] - (ii + 1);
            accumDiff += diff;
            if (accumDiff == 0)
                count++;
        }
        return count;
    }


}
