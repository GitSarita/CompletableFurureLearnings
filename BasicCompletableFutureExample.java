package futureexamples;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.lang.Thread.sleep;

public class BasicCompletableFutureExample {
    public Future<String> calculateAsync(){
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(()-> {
            sleep(1000);
            completableFuture.complete("calculateAsync is complete");
            return null;
        });
        return completableFuture;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        BasicCompletableFutureExample obj = new BasicCompletableFutureExample();
        System.out.println("Calling get on calculateAsynch future "+System.currentTimeMillis()/1000);
        Future<String> result = obj.calculateAsync();

        System.out.println("result computed at :"+System.currentTimeMillis()/1000+" result is "+result);

    }
}
