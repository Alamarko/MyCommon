package ru.alamarko.snufkin.examples.volatilecrashtest;

import java.util.Random;

public class User
{
    private int id;

    private String m_name;

    private Double m_ammount_remainder;
    private final static Random m_random = new Random();

    public User(int pRangeMin, int pRangeMax, int pId, String pName)
    {
        this.id=pId;
        this.m_name = pName;
        this.m_ammount_remainder = GeneratorUtils.getRandomAmountInRange(pRangeMin, pRangeMax, m_random);
    }

    public void changeAmountRemainder(Double pDelta)
    {
        this.m_ammount_remainder += pDelta;
    }

    public int getId()
    {
        return id;
    }

    public Double getAmmount_remainder()
    {
        return m_ammount_remainder;
    }

    public String getName()
    {
        return m_name;
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString()
    {
        return String.format("{Id: %d, remainder: %.2f}",this.id,this.getAmmount_remainder());
    }
}
