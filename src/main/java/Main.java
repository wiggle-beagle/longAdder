import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int[] arr1 = {1, 3, 5, 7, 9, 11};
        int[] arr2 = {10, 20, 30, 40, 50};
        int[] arr3 = {11, 22, 33, 44, 55, 66};

        LongAdder longAdder = new LongAdder();

        ThreadGroup threadGroup = new ThreadGroup("main");


        IntStream.of(arr1).forEach(i -> new Thread(threadGroup, () -> longAdder.add(i)).start());
        IntStream.of(arr2).forEach(i -> new Thread(threadGroup, () -> longAdder.add(i)).start());
        IntStream.of(arr3).forEach(i -> new Thread(threadGroup, () -> longAdder.add(i)).start());


        Thread[] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
        for (Thread thread : threads)
            thread.join();

        System.out.println(longAdder.sum());
    }
}
