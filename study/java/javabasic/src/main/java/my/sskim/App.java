package my.sskim;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;

import static net.bytebuddy.matcher.ElementMatchers.named;

import java.io.File;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            new ByteBuddy().redefine(EmptyMoja.class)
                .method(named("pullout")).intercept(FixedValue.value("Rabbit"))
                .make().saveIn(new File("/home/ecsuser/study/daily_record/study/java/javabasic/target/classes"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println( new EmptyMoja().pullout() );
    }
}
