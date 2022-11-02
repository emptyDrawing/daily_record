package test.sskim.junit.study;

import org.junit.Test;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;

public class ArchTests {
    
    @Test
    public void packageDependencyTests() {
 
        // 이 프로젝트의 기본 패키지
        JavaClasses importedClass = new ClassFileImporter()
            .importPackages("test.sskim.junit"); 

        // 1) ..domain.. 패키지에 있는 클래스는 ..study.., ..member.., ..domain..에서 참조 가능.
        ArchRule domainPackageRule = ArchRuleDefinition.classes().that()
            .resideInAPackage("..domain..")
            .should().onlyBeAccessed().byClassesThat()
            .resideInAnyPackage("..study..","..member..","..domain..");
        
        domainPackageRule.check(importedClass);

        // 2) ..member.. 패키지에 있는 클래스는 ..study..와 ..member..에서만 참조 가능.
        ArchRule domainPackageRule2 = ArchRuleDefinition.classes().that()
            .resideInAPackage("..member..")
            .should().onlyBeAccessed().byClassesThat()
            .resideInAnyPackage("..study..","..member..");
        
        domainPackageRule2.check(importedClass);

        // 3) (반대로) ..domain.. 패키지는 ..member.. 패키지를 참조하지 못한다.
        ArchRule domainPackageRule3ReverseCheck = ArchRuleDefinition.noClasses().that()
            .resideInAPackage("..domain..")
            .should().accessClassesThat().resideInAPackage("..member..");
        
        domainPackageRule3ReverseCheck.check(importedClass);

        // 4) ..study.. 패키지에 있는 클래스는 ..study.. 에서만 참조 가능
        ArchRule studyPackageRule4 = ArchRuleDefinition.classes().that()
            .resideInAPackage("..study..")
            .should().onlyBeAccessed().byClassesThat()
            .resideInAPackage("..study..");

        studyPackageRule4.check(importedClass);

        // 5) 순환참조는 없어야된다.
        // slices 라는 뜻은 디폴트 패키지에서 'domain', 'member', 'study' 를 각각의 패키지로 만드는거
        ArchRule freeOfCycles = SlicesRuleDefinition.slices()
            .matching("..junit.(*)..")
            .should().beFreeOfCycles(); 
        freeOfCycles.check(importedClass);
    }
}
