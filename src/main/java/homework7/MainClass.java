package homework7;

import homework7.testingAtbs.AfterSuite;
import homework7.testingAtbs.BeforeSuite;
import homework7.testingAtbs.MyTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.TreeMap;

public class MainClass {

    public static void start(Class<?> className) {
        final int MIN_PRIORITY = 1;
        final int MAX_PRIORITY = 10;
        Map<Integer, Method> map = new TreeMap<>();

        for (Method method : className.getDeclaredMethods()) {
            if (method.getAnnotation(BeforeSuite.class) != null) {
                map.put(MIN_PRIORITY - 1, method);
            }
            if (method.getAnnotation(AfterSuite.class) != null) {
                map.put(MAX_PRIORITY + 1, method);
            }
            if (method.getAnnotation(MyTest.class) != null) {
                MyTest test = method.getAnnotation(MyTest.class);
                map.put(test.priority(), method);
            }
        }

        System.out.println("Map:");
        for (Integer key : map.keySet()) {
            System.out.println("priority:" + key + " " + map.get(key).getName());
        }

        try {
            TestClass testCase = new TestClass();
            for (Integer key : map.keySet()) {
                map.get(key).invoke(testCase);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        System.out.println("Annotations analyze:");
        start(TestClass.class);
    }
}