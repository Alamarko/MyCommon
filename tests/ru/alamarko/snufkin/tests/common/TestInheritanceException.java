package ru.alamarko.snufkin.tests.common;

public class TestInheritanceException
{


    public static void main(String[] args)
    {
        Train tr = new Train();
        Camel cml = new Camel();
        Moveable mvbl = cml;
        mvbl = (Moveable)tr;
        mvbl.move();


    }

    interface Moveable
    {

        void move();
    }

    interface Turnable
    {
        void turn();

    }

    public static class Camel implements Moveable, Turnable
    {

        @Override
        public void move()
        {
            System.out.println(this.getClass().toString() + " moves");
        }


        @Override
        public void turn()
        {
            System.out.println(this.getClass().toString() + " turns");

        }
    }

    public static class Train implements Turnable
    {

        @Override
        public void turn()
        {
            System.out.println(this.getClass().toString() + " turns");
        }
    }

}
