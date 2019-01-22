package futureexamples;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.function.Supplier;

public class CompletableFutureWithSuppilerExample {

    public CompletableFuture<String> calculateAsync() {
        return CompletableFuture.supplyAsync(() ->{
            System.out.println("calculateAsync executing in "+Thread.currentThread());
            return  "Hello";
        });
    }
    public static void main(String[] args){
        CompletableFutureWithSuppilerExample obj = new CompletableFutureWithSuppilerExample();

        Supplier<Function<String, CompletionStage<String>>> anotherCompletableFuture =
                () -> s -> CompletableFuture.supplyAsync(() ->{
                    System.out.println("anotherCompletableFuture executing in "+Thread.currentThread());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "Hi World " + s;} );


                 obj.calculateAsync()
                .thenApply(s->s+" World") //mapping operation
                //.thenAccept(System.out::println)// terminal operation with consumer
               // .thenRun(()->System.out.println("Compulation completed"))//terminal operation without any value from above
                .thenCompose(anotherCompletableFuture.get())
                .thenAccept(System.out::println)
                .thenRun(()->System.out.println("composed result"));

                 //asynch main execution continue
        System.out.println("execution continues in main thread");

    }
}
