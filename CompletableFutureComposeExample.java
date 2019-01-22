package futureexamples;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureComposeExample {

    static CompletableFuture<Integer> employeeIdFuture = CompletableFuture.supplyAsync(() -> generateEmployeeId());

    private static CompletableFuture<Double> generateJoiningBonusFuture(Integer empId) {
        //run some logic
        System.out.println("Generating joining bonus for employee " + empId);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.supplyAsync(() -> 12500.00);
    }

    private static Integer generateEmployeeId() {
        try {
            System.out.println("Creating Employee Record in DB");
            Thread.sleep(2000); //analogous to a DB call

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return 123;
    }

    public static void main(String[] args) {
        System.out.println("Main execution started");

        employeeIdFuture
                .thenCompose(empId -> generateJoiningBonusFuture(empId))
                .thenRun(()->System.out.println("Joining bonus generated"));

        System.out.println("Main execution continued");

    }
}
