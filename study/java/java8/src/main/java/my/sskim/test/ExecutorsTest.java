package my.sskim.test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class ExecutorsTest {

    // public static void main(String[] args) throws InterruptedException {

    //     ScheduledExecutorService exSvc = Executors.newScheduledThreadPool(2);
    //     exSvc.scheduleAtFixedRate(getRunnable("now "),1,2,TimeUnit.SECONDS);
    //     // 프로세스가 살아있어서 shutdown 을 해야함.
        
        
    //     Thread.sleep(10000);
    //     exSvc.shutdown(); // graceful shutdown - 다 끝내고 죽어라

    // }

    // private static Runnable getRunnable(String message) {
    //     return () -> System.out.println(message + " : " + Thread.currentThread().getName() );
    // }


    // public static void main(String[] args) throws InterruptedException, ExecutionException {
        
    //     ExecutorService exSvc = Executors.newSingleThreadExecutor();

    //     List<Future<String>> futures = exSvc.invokeAll(Arrays.asList(
    //         getCallable("1s",1000)
    //         ,getCallable("4s",1000)
    //         ,getCallable("2s",1000)
    //     ));


    //     System.out.println("start");

    //     futures.forEach(
    //         (f) -> {
    //             try {
    //                 System.out.println( f.get());
    //             } catch (InterruptedException | ExecutionException e) {
    //                 e.printStackTrace();
    //             }
    //         }
    //     );

    //     System.out.println("end!");
    //     exSvc.shutdown();
    // }

    // private static Callable<String> getCallable(String message, int time) {  
    //     return (() -> {
    //         Thread.sleep(time);
    //         return message + " : " + Thread.currentThread().getName();
    //     });
    // }
    
    // public static void main(String[] args) throws InterruptedException, ExecutionException {

    //     ExecutorService exSvc = Executors.newFixedThreadPool(4);

    //     CompletableFuture<Void> simpleFutrue = CompletableFuture.supplyAsync( () -> {
    //         System.out.println("supplyAsync : "  + Thread.currentThread().getName());
    //         return "simple run ok!";
    //     }, exSvc).thenAccept( (s) -> {
    //         System.out.println("thenApply : " + Thread.currentThread().getName());
    //         System.out.println(s.toUpperCase());
    //     }).thenRun( ()->{
    //         System.out.println("Runnable : " + Thread.currentThread().getName());
    //     });

    //     simpleFutrue.get();

    // }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        
        CompletableFuture<String> hello = CompletableFuture.supplyAsync( () -> {
            System.out.println("hello : "  + Thread.currentThread().getName());
            return "hello";
        });

        boolean throwsError = true;
        
        CompletableFuture<String> world = CompletableFuture.supplyAsync( () -> {

            if(throwsError){
                throw new IllegalStateException("Test");
            }

            System.out.println("world : "  + Thread.currentThread().getName());
            return "world";
        }).handle( (result, ex) -> {
            if(ex != null) {
                return "Error!";
            }
            return result;
        });
        
        // System.out.println( CompletableFuture.allOf(hello, world).thenAccept(System.out::println).get() );

        // List<CompletableFuture<String>> futures = Arrays.asList(hello, world);
        // CompletableFuture[] futuresArray = futures.toArray(new CompletableFuture[futures.size()]);
        // CompletableFuture<List<String>> results = 
        //     CompletableFuture.allOf(futuresArray)
        //         .thenApply( (v) -> futures.stream()
        //                 .map(CompletableFuture::join) // join 은 unCheckedExecption
        //                 .collect(Collectors.toList()));
        // results.get().forEach(System.out::println);

        CompletableFuture.anyOf(world, hello).thenAccept( System.out::println );
    }

    private static CompletableFuture<String> getMessageWorld(String message) {
        return CompletableFuture.supplyAsync( () -> {
            System.out.println("add World : "  + Thread.currentThread().getName());
            return message + "world";
        });
    }

    
}


