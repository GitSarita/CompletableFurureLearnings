package futureexamples;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureCombineExample {

    static CompletableFuture<Integer> registerEmployee = CompletableFuture.supplyAsync(() -> {
        System.out.println("Registering employee in " + Thread.currentThread());
        return 1;
    });

    static CompletableFuture<Boolean> documentVerification = CompletableFuture.supplyAsync(() -> {
        System.out.println("Verifying documents of employee" + Thread.currentThread());
        return true;
    });

    static CompletableFuture<String> cardGeneration = CompletableFuture.supplyAsync(() -> {
        System.out.println("generating card no for employee" + Thread.currentThread());
        return "10128177";
    });

    public static void main(String rgs[]) throws ExecutionException, InterruptedException {
        CompletableFuture combinedFuture = CompletableFuture.allOf(registerEmployee,documentVerification,cardGeneration);


        registerEmployee.thenCombine(cardGeneration,
                (id,cardNo)->{
                    System.out.println("Card no for empId : "+id+" is :"+ cardNo);
                    return "ProjectId";
                })
        .thenCombine(documentVerification,(projectId,verified)-> {
            System.out.println("Document Verified t/f " + verified + " projectId :" + projectId);
            return "Employee onboarded successfully";
        });

    }
}
