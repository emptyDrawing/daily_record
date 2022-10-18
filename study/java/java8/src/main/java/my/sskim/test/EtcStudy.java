package my.sskim.test;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

@MyTestMark("test")
@MyTestMark("test2")
@MyTestMark("test3")

public class EtcStudy {
    
    // public static void main(String[] args) {
    //     MyTestMark[] marks = EtcStudy.class.getAnnotationsByType(MyTestMark.class); 
    //     Arrays.stream(marks).forEach( m -> System.out.println(m.value()));
    //     System.out.println("=================== 위 -기존 / 아래 컨테이터 ===================");
    //     Arrays.stream( EtcStudy.class.getAnnotation(MyTestMarkContainer.class).value())
    //         .forEach( m -> System.out.println(m.value()));
    // }


    // public static void main(String[] args) {
    //     int size = 15000;
    //     int[] numbers = new int[size];
    //     Random random = new Random();
    //     IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());

    //     long start = System.nanoTime();
    //     Arrays.sort(numbers);
    //     System.out.println("serial sorting took " + (System.nanoTime() - start));

    //     IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
    //     start = System.nanoTime();
    //     Arrays.parallelSort(numbers);
    //     System.out.println("parallel sorting took " + (System.nanoTime() - start));

    // }
}
