package test;

import static test.Test.TestDerived.getFixedIndex;

public class Test {
    static public class TestBase {
        static {
            int x = 0;
        }

        static int y = 1;

        int base = 0;

        public TestBase(int index) {
            base = index;
        }
    }

    static public class TestDerived extends TestBase {
        public int derived = 0;

        public TestDerived() {
            super(0);
            this.derived = 1000;

            TestBase.y = 2;
        }

        public void testMethod() {
            try {
                byte[] test = null;
                test[1] = 0x33;
            } catch (Exception ex) {
            }
        }

        static int getFixedIndex() {
            return 1000;
        }
    }

    public static void main(String args[]) {
        System.out.println("Test begin...");
        TestDerived derived = new TestDerived();
        derived.testMethod();
        derived.base = 1;
        System.out.println("Test end...");

        getFixedIndex();
    }
}
