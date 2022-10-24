package test.sskim;

import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

@AutoService(Processor.class)
public class MagicMojaProcessor extends AbstractProcessor{

    // 이 프로세서가 어떤 어노테이션을 처리할 건지
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Set.of(Magic.class.getName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        
        // 부모꺼를 하던가
        // return super.getSupportedSourceVersion();
    
        // 최신으로 하던가
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // RoundEnvironment : 필터 체인이랑 비슷하게 여러 과정에서 처리할수 있다고 보면 됨.

        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Magic.class);


        elements.stream()
            .forEach( e -> {
                Name simpleName = e.getSimpleName();

                if(e.getKind() != ElementKind.INTERFACE) {
                    //import javax.tools.Diagnostic;
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Magic Annotation can not be used on "+ simpleName);
                } else {
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Magic Processing ->" + simpleName);
                }
                    
                // 새로운 소스코드 생성과정
                TypeElement typeElement = (TypeElement) e;
                ClassName className =  ClassName.get(typeElement);


                MethodSpec pullOut = MethodSpec.methodBuilder("pullOut")
                // import javax.lang.model.element.Modifier;
                    .addModifiers(Modifier.PUBLIC)
                    .returns(String.class)
                    .addStatement("return $S", className.simpleName())
                    .build();
                    
                TypeSpec magicMoja = TypeSpec.classBuilder("Magic"+className.simpleName())
                    .addModifiers(Modifier.PUBLIC)
                    .addMethod(pullOut)
                    .addSuperinterface(className) // 인터페이스 추가
                    .build();
                // 이렇게 하면 메모리-객체 가지만 정의
                
                // 소스파일을 만들자. Filer 이용 
                // Filer 인터페이스 : 소스 코드, 클래스 코드 및 리소스를 생성할 수 있는 인터페이스
                // https://docs.oracle.com/en/java/javase/11/docs/api/java.compiler/javax/annotation/processing/Filer.html
                
                Filer filer = processingEnv.getFiler();             
                // javapoet 과 결합되면 쉽게 만듬.
                try {
                    JavaFile.builder(className.packageName(), magicMoja)
                        .build()
                        .writeTo(filer);
                } catch (IOException ex) {
                    processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "FATAL ERROR-Bulid Source : " + ex);
                }

            });


        // true 를 리턴하면 이 애노테이션이 처리됬다고 보면 됨. 
        // (앞으로 더 다른 프로세서에서 처리 안한다는 뜻)
        return true;
    }

}
