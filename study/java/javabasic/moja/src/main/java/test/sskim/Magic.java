package test.sskim;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 소스 레벨에서 읽어서 프로세서에서 처리해서 컴파일 하면 새로운 소스가 만들어지게 함
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE) // interface, class, enum 에 사용가능
public @interface Magic {
    
}
