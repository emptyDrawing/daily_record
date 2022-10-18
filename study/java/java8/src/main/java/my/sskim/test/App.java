package my.sskim.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;

public class App {
    /**
     * @param args
     */
    public static void main(String[] args) {
        
        // RunSomtime runSomtime = () -> System.out.println("Hello World!");

        // // runSomtime.doIt();
        // UnaryOperator<Integer> plus10 = (i) -> i + 10;
        // UnaryOperator<Integer> multiply2 = (i) -> i * 2;

        // UnaryOperator<Integer> mul2plus10 = plus10.compose(multiply2);
        // System.out.println(mul2plus10.apply(3)); // 16


        // UnaryOperator<Integer> plus10mul2 = plus10.andThen(multiply2);
        // System.out.println(plus10mul2.apply(3)); // 26;


        // Supplier<Integer> get10 = () -> 10;
        // BinaryOperator<Integer> getPlusTwo = (a,b) -> a + b;

        // new App().run();


        // String[] testStr = {"hello", "world", "is", "test"};

        // Arrays.sort(testStr, String::compareToIgnoreCase);
        // System.out.println(Arrays.toString(testStr)); // [hello, is, test, world]


        List<String> names = new ArrayList();
        names.add("hello");
        names.add("test");
        names.add("todo");
        names.add("world");

        names.forEach( System.out::println );
        System.out.println("========================");

        Spliterator<String> splitor = names.spliterator();
        Spliterator<String> splitor2 = splitor.trySplit();

        while(splitor.tryAdvance(System.out::println));
        System.out.println("========================");

        while(splitor2.tryAdvance(System.out::println));

        Comparator<? super String> comparator = String::compareToIgnoreCase;
        names.sort(comparator.reversed());

    }

    // private void run() {
    //     int baseNum = 10;

    //     IntConsumer shading = new IntConsumer() {
    //         int baseNum = 11;
            
    //         public void accept(int i) {
    //             System.out.println(baseNum);
    //         };
    //     }

    //     IntConsumer printInt = (i) -> System.out.println(baseNum);

    //     shading.accept(10);
    //     printInt.accept(10);
    // }

}