package test.sskim;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException
    {
        // Class<Book> bookclass = Book.class;
        // Book book = new Book();
        // // public fields 만 리턴
        // //Arrays.stream(bookclass.getFields()).forEach(System.out::println);
        // // System.out.println("================================================================");
        // // 선언된 모든
        // Arrays.stream(bookclass.getDeclaredFields()).forEach( f -> {

        //     try {
        //         // 접근 가능하게 바꿔줄려면
        //         f.setAccessible(true);
        //         System.out.printf("\n %s %s \n" , f, f.get(book) );
        //     } catch (IllegalArgumentException | IllegalAccessException e) {
        //         e.printStackTrace();
        //     }
        // });

        // // 부모꺼 까지도 가져오기
        // Arrays.stream(MyBook.class.getAnnotations()).forEach(System.out::println );
        // // 내꺼만 가져오기
        // Arrays.stream(MyBook.class.getDeclaredAnnotations()).forEach(System.out::println );
            
        Class<?> bookClass = Class.forName("test.sskim.Book");
        Constructor<?> bookConstrutor = bookClass.getConstructor(String.class);
        Book book = (Book) bookConstrutor.newInstance("myFirst");

        System.out.println(book);
        Field field = Book.class.getDeclaredField("title");
        System.out.println(field.get(book));
    }
}