import java.util.Date;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class PrimeNum extends RecursiveTask<Long> {

    private long[] data;
    private int from, to;
    private int maxDataSize = 10000;
    private static int numOfCores = Runtime.getRuntime().availableProcessors();
    ExecutorDemo ed = new ExecutorDemo();

    public PrimeNum(long[] data) {
        this.data = data;
        from = 2;
        to = data.length;
    }

    public PrimeNum(long[] data, int from, int to) {
        this.data = data;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Long compute() {
        int length = to - from;
        long counter = 0;

        if (length <= maxDataSize) {
            ed.searchPrime(from, to, (int) counter);
        } else {
            PrimeNum task1 = new PrimeNum(data, from, from + length / 2);
            PrimeNum task2 = new PrimeNum(data, from + length / 2, to);

            task1.fork();
            long part2 = task2.compute();
            long part1 = task1.join();
            counter = (int) (part1 + part2);
        }
        return counter;
    }

    public static void main(String[] args) {
        System.out.println(new Date());
        long data[] = LongStream.rangeClosed(1, 1_000_000).toArray();
        new ForkJoinPool(numOfCores).invoke(new PrimeNum(data));
        System.out.println(new Date());
    }
}
