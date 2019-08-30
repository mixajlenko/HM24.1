
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.String.format;

public class ExecutorDemo {
    static long start = System.nanoTime();
    public static void main(String[] args) throws InterruptedException {

        ExecutorService es = Executors.newFixedThreadPool(8);
        es.submit(new MyRunnable());
        es.submit(new Myrunnable2());
        es.submit(new Myrunnable3());
        es.submit(new Myrunnable4());
        es.shutdown();

    }

   private static int counter1 = 0;

    public void searchPrime(int from, int to, int counter) {
        int numb;
        boolean simpl = false;

        for(numb = from; numb <= to; numb++) {

            if (numb % 2 == 0) continue;

            for (int j = 2; j < numb; j++) {

                if (numb % j != 0) {
                    simpl = true;
                } else {
                    simpl = false;
                    break;
                }
            }
            if (simpl) {
                System.out.println(numb);
                counter1++;
            }
        }
        System.out.println(format("Finished by %d s, value : %d",
                (System.nanoTime() - start) / (1000_000_000),
                counter1));
    }

    static class MyRunnable implements Runnable {
        ExecutorDemo ed = new ExecutorDemo();

        @Override
        public void run() {
            ed.searchPrime(2, 250000, counter1);
        }
    }

    static class Myrunnable2 implements Runnable {
        ExecutorDemo ed = new ExecutorDemo();

        @Override
        public void run() {
            ed.searchPrime(250001, 500000, counter1);
        }
    }

    static class Myrunnable3 implements Runnable {
        ExecutorDemo ed = new ExecutorDemo();

        @Override
        public void run() {
            ed.searchPrime(500001, 750000, counter1);
        }
    }

    static class Myrunnable4 implements Runnable {
        ExecutorDemo ed = new ExecutorDemo();

        @Override
        public void run() {
            ed.searchPrime(750001, 1000000, counter1);
        }
    }
}

