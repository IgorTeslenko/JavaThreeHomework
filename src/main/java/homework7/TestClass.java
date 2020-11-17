package homework7;


import homework7.testingAtbs.AfterSuite;
import homework7.testingAtbs.BeforeSuite;
import homework7.testingAtbs.MyTest;

public class TestClass {

    @BeforeSuite
    public void methodBefore() {
        System.out.println("TestCase.methodBefore");
    }
    @AfterSuite
    public void methodAfter() {
        System.out.println("TestCase.methodAfter");
    }

    @MyTest(priority = 1)
    public void methodTest1() {
        System.out.println("TestCase.methodTest1 priority 1 (highest)");
    }

    @MyTest(priority = 5)
    public void methodTest2() {
        System.out.println("TestCase.methodTest2 priority 5 (lowest)");
    }

    @MyTest(priority = 2)
    public void methodTest3() {
        System.out.println("TestCase.methodTest3 priority 2(middle)");
    }


    public void commonMethod1() {
        System.out.println("TestCase.commonMethod1");
    }
    public void commonMethod2() {
        System.out.println("TestCase.commonMethod2");
    }

}
