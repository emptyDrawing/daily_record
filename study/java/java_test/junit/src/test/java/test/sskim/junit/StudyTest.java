package test.sskim.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;


@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class StudyTest {

	// 5 버젼부터는 굳이 public 을 안달아도 됩니다.
	@Test
	@DisplayName("스터디 만들기")
	void create_study() {
		Study study = new Study();

		// all 로 묶으면 묶인 거를 독립적-병렬적? 으로 실행 가능함.
		assertAll(
			() -> { assertNotNull(study);} 
			// 기대값 먼저, 실제 나오는 값, 메세지
			,() -> { assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디를 처음 만들면 Status 는 DRAFT"); }
			,() -> { assertTrue( study.getLimit() >= 1 , () -> "스터디 최대인원은 1명 이상이다. "); }
		);


	};

	@Test
	void create_new_sysout() {
		System.out.println("create1");
	};

	@Test
	@Disabled // @Test에 @Disabled 이 있으면 실행되지 않음
	void crash() {
		System.out.println("crash");
	}


	//All은 private 안되며 void 이외를 리턴하면 안됨, 반드시 static 형이어야함.
	@BeforeAll
	static void beforeAll() {
		System.out.println("beforeAll");
	}

	@AfterAll
	static void afterAll() {
		System.out.println("afterAll");
	}

	// Each 는 굳이 static 일필요는 없음음 
	@BeforeEach
	void beforeEach() {
		System.out.println("beforeEach");
	}

	@AfterEach
	void afterEach() {
		System.out.println("afterEach");
	}




}
