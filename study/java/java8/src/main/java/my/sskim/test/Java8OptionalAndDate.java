package my.sskim.test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Java8OptionalAndDate {
    
    public static void main(String[] args) {
        
        List<OnClass> springClass = new ArrayList<>();
        springClass.add(new OnClass(1,"spring boot",true));
        springClass.add(new OnClass(2,"spring data jpa",false));
        springClass.add(new OnClass(3,"spring mvc",false));
        springClass.add(new OnClass(4,"spring core",false));
        springClass.add(new OnClass(5,"API Spring boot",false));
 
        // 이상태로 바로 시작하면 null 포인트...
        // OnClass testProgress = springClass.get(0);
        // System.out.println(testProgress.getProgress().getStudyDuration());
        
        Optional<OnClass> checkClass = springClass.stream()
            .filter( c -> c.getTitle().startsWith("jpa"))
            .findFirst();
        
        System.out.println(checkClass.isPresent());


    }
}
