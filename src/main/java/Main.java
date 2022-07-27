import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class Main {
    public static int countFinishedFutures(List<Future> futures) {
        return (int) futures.stream().filter(f -> f.isDone()).count();
    }
    public static Callable<Double> getCallable(){
        return new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return Math.random();
            }
        };
    }
    public static void main (String[] args){
        final int THREAD_COUNT = 10;
        List<Future> futureList = new ArrayList<Future>();
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        IntStream.rangeClosed(1, THREAD_COUNT).forEach((i) -> {
            futureList.add(executor.submit(getCallable()));
        });
        System.out.println(countFinishedFutures(futureList));
        executor.shutdown();
    }
}