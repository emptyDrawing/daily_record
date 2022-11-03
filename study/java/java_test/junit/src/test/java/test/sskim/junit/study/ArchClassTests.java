package test.sskim.junit.study;

import javax.persistence.Entity;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

import test.sskim.junit.App;

@AnalyzeClasses(packagesOf = App.class)
public class ArchClassTests {
   
    // 결과 확인은 ctl + ; + cont + o

    @ArchTest
    // 1) ..Controller는 ..Service와 ..Repository를 사용할 수 있다.
    ArchRule controllerClassRule = ArchRuleDefinition.classes().that()
        .haveSimpleNameEndingWith("Controller")
        .should().accessClassesThat().haveSimpleNameEndingWith("Service")
        .orShould().accessClassesThat().haveSimpleNameEndingWith("Repository");


    @ArchTest
    // 2) ..Repository는 ...Service와 ...Controller를 사용할 수 없다.
    ArchRule repositoryClassRule = ArchRuleDefinition.noClasses().that()
        .haveSimpleNameEndingWith("Repository")
        .should().accessClassesThat().haveSimpleNameEndingWith("Service")
        .orShould().accessClassesThat().haveSimpleNameEndingWith("Controller");


    @ArchTest
    // 3) Study* 로 시작하는 클래스는 ..study.. 패키지에 있어야 한다.
    // 그냥하니까 domain에 있는 Study, StudyStatus 때문에 안됨.
    ArchRule studyNameRule = ArchRuleDefinition.classes().that()
        .haveSimpleNameStartingWith("Study")
        .and().areNotAnnotatedWith(Entity.class)     // domain.study 제외
        .and().areNotEnums()                                        // StudyStatus 제외
        .should().resideInAPackage("..study..");
}
