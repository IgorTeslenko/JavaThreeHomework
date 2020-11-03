package homework4;

public class Threads {

    static String str = "A";

    public static void main(String[] args) {
        Object lock = new Object();


        class MyTask implements Runnable {

            private String b;
            private String nextB;

            public MyTask(String b, String nextB) {
                this.b = b;
                this.nextB = nextB;
            }

            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    synchronized (lock) {
                        try {
                            while (!str.equals(b)) {
                                lock.wait();
                            }
                            System.out.println(Thread.currentThread().getName());
                            System.out.println(b);
                            str = nextB;
                            lock.notifyAll();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        new Thread(new MyTask("A", "B")).start();
        new Thread(new MyTask("B", "C")).start();
        new Thread(new MyTask("C", "A")).start();
    }

}
