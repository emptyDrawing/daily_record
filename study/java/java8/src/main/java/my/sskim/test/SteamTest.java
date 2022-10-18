package my.sskim.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class SteamTest {
    
    /**
     * @param args
     */
    public static void main(String[] args) {
       
        // List<String> list = new ArrayList<String>();

        // list.add("abds");
        // list.add("cvbzxcvas");
        // list.add("qweqweqwe");


        // // list.stream().map(
        // //     s -> {
        // //         System.out.println(s);
        // //         return s.toUpperCase();
        // //     } 
        // // );

        // // System.out.println("================================================================");
        // // list.forEach(System.out::println);

        
        // list.parallelStream().map( s -> {
        //     System.out.println(s + " " + Thread.currentThread().getName());
        //     return s.toUpperCase();
        // }).collect(Collectors.toList()).forEach(System.out::println);


        List<OnClass> java8Class = new ArrayList<>();
        java8Class.add(new OnClass(1,"stream",true));
        java8Class.add(new OnClass(2,"ramda",true));
        java8Class.add(new OnClass(3,"time",false));
        java8Class.add(new OnClass(4,"refactor",false));

        List<OnClass> springClass = new ArrayList<>();
        springClass.add(new OnClass(5,"spring boot",true));
        springClass.add(new OnClass(6,"spring data jpa",false));
        springClass.add(new OnClass(7,"spring mvc",false));
        springClass.add(new OnClass(8,"spring core",false));

        List<List<OnClass>> toListenClass = new ArrayList<>();
        toListenClass.add(java8Class);
        toListenClass.add(springClass);

        System.out.println("stpring 으로 시작하는 수업목록만..");
        springClass.stream()
            .filter( c -> c.getTitle().startsWith("spring") )
            .forEach( c -> System.out.println(c.getId()) );
        
        System.out.println("closed 되지 않는 수업은 ");
        springClass.stream()
            .filter(Predicate.not( OnClass::isClosed))
            .forEach( c -> System.out.println(c.getId()) );

        
        System.out.println("FlatMap - 두 수업목록에 들어가 있는 수업아이디 출력");
        toListenClass.stream()
            .flatMap( Collection::stream )
            .forEach( c -> System.out.println(c.getId()) );

        System.out.println("10부터 1씩 증가하는 무제한 스트림중 앞 10개 빼고 최대 10개까지 수집");
        Stream<Integer> justWord = Stream.iterate(10, i -> i+1 );  // 이렇게 만 하면 중계형이라서 일어나지 않는다.
        justWord
            .skip(10)
            .limit(10)
            .forEach(System.out::println);
    }
}
