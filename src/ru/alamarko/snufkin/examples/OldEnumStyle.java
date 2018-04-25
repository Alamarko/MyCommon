package ru.alamarko.snufkin.examples;

import java.io.ObjectStreamException;
import java.io.Serializable;

public final class OldEnumStyle implements Serializable
{
    private static final int VAL1_VALUE = 1;
    static final OldEnumStyle VAL1 = new OldEnumStyle(VAL1_VALUE);
    private static final int VAL2_VALUE = 2;
    static final OldEnumStyle VAL2 = new OldEnumStyle(VAL2_VALUE);
    private static final int VAL3_VALUE = 3;
    static final OldEnumStyle VAL3 = new OldEnumStyle(VAL3_VALUE);
    private static final int VAL4_VALUE = 4;
    static final OldEnumStyle VAL4 = new OldEnumStyle(VAL4_VALUE);
    private int value;

    private OldEnumStyle(int newVal)
    {
        value = newVal;
    }

    private Object readResolve() throws ObjectStreamException
    {
        switch (value)
        {
            case VAL1_VALUE:
                return VAL1;
            case VAL2_VALUE:
                return VAL2;
            case VAL3_VALUE:
                return VAL3;
            case VAL4_VALUE:
                return VAL4;
        }
        return null;
    }

    @Override
    public String toString()
    {
        return "OldEnumStyle{" + "value=" + value + '}';
    }

    private interface ICore
    {
        int getVersion();

        String getCoreName();

        boolean incrementVersion();

        boolean changeName(String pName);
    }

    private interface ICoreWrapper
    {
        boolean tryIncrementVersion();

        boolean tryChangeName(String pName);

        ICore getCoreInstance();
    }

    private static class CoreWrapper implements ICoreWrapper
    {
        private ICore m_core;

        private CoreWrapper(String pName, int pVersion)
        {
            this.m_core = new Core(pName, pVersion);
        }


        @Override
        public boolean tryIncrementVersion()
        {
            return this.m_core.incrementVersion();
        }

        @Override
        public boolean tryChangeName(String pName)
        {
            return this.m_core.changeName(pName);
        }

        @Override
        public ICore getCoreInstance()
        {
            return this.m_core;
        }

        private class Core implements ICore
        {
            volatile int m_version;
            volatile String m_name;

            private Core(String pName, int pVersion)
            {
                this.m_name = pName;
                this.m_version = pVersion;
            }

            @Override
            public int getVersion()
            {
                return this.m_version;
            }

            @Override
            public String getCoreName()
            {
                return this.m_name;
            }

            @Override
            public boolean incrementVersion()
            {
                m_version++;
                return true;
            }

            @Override
            public boolean changeName(String pName)
            {
                this.m_name=pName;
                return true;
            }


        }


    }

}
