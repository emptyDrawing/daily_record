package my.sskim.test;

public class ConcurrentTest1 {
    

    // /**
    //  * @param args
    //  */
    // public static void main(String[] args) {

    //     final MyThread myThread = new MyThread();
    //     myThread.start();


    //     System.out.println("Hello :" +Thread.currentThread().getName());

    // }

    // static class MyThread extends Thread {
    //     public void run() {
    //         System.out.println("Thread : "+ Thread.currentThread().getName());
    //     }
    // };


    // public static void main(String[] args) throws InterruptedException {
    //     Thread thread = new Thread( () -> {
    //             while(true){
    //                 System.out.println("lamda-awalk : "+ Thread.currentThread().getName());
    //                 try {
    //                     Thread.sleep(1000);
    //                 } catch (InterruptedException e) {
    //                     System.out.println("exit");
    //                     return ; //이게 실제로 끝나는거, Interrupted 되었다고 끝나진 않음.
    //                 }
    //             }
    //         }
    //     );
    //     thread.start();

    //     System.out.println("Hello :" +Thread.currentThread().getName());
    //     Thread.sleep(3000);
    //     thread.interrupt();
    // }


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread( () -> {
            System.out.println("lamda-awalk : "+ Thread.currentThread().getName());

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }    
        });
        thread.start();

        System.out.println("Hello :" +Thread.currentThread().getName());
        thread.join();
        System.out.println(thread + " is stop");

    }
}
