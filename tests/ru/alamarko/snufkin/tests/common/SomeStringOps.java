package ru.alamarko.snufkin.tests.common;

import java.util.Comparator;

public class SomeStringOps
{
    public static void main(String[] args)
    {
        String test1 = "abcdefal";
        test1=sortInString(test1,Comparator.reverseOrder());
        System.out.println(test1);
    }

    private static String sortInString(String pArg,
                                       Comparator<? super Character> pComparator)
    {
        return  pArg.chars()
                        .mapToObj(x -> (char) x)
                        .sorted(pComparator)
                        .map(Object::toString)
                        .reduce("", (x, y) -> x + y);
    }
}
