package test.sskim.junit;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;


@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class StudyTest {


	@Test
	@DisplayName("조건별 테스트")
	void conditionalTest() {
		// import static org.junit.jupiter.api.Assumptions.*;
		// 그런데 환경변수는 vscode 가 들고가고 있어서 잘 안됨.
		System.out.println(System.getenv("TEST_ENV"));
		assumeTrue("LOCAL".equalsIgnoreCase(System.getenv("TEST_ENV")));
		// 위조건이 만족해야지 나머지 조건이 도는 구조이다.
	}


	// 5 버젼부터는 굳이 public 을 안달아도 됩니다.
	@Test
	@DisplayName("스터디 만들기")
	@EnabledOnOs({OS.LINUX,OS.MAC})
	@EnabledOnJre(JRE.JAVA_11)
	void create_study() {
		Study study = new Study(0);

		assertTimeoutPreemptively(Duration.ofMillis(10),() -> {
			new Study(0);
			Thread.sleep(100);
		});

		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()-> new Study(-10) ); 
		assertEquals("limit 는 0보다 커야된다.", ex.getMessage());

		// all 로 묶으면 묶인 거를 독립적-병렬적? 으로 실행 가능함.
		// 이렇게 하면 2,3번이 한번에 틀린걸 알 수 있음음
		assertAll(
			() -> { assertNotNull(study);} 
			,() -> { assertTrue( study.getLimit() >= 1 , () -> "스터디 최대인원은 1명 이상이다. "); }
			// 기대값 먼저, 실제 나오는 값, 메세지
			,() -> { assertEquals(StudyStatus.DRAFT, study.getStatus(), "스터디를 처음 만들면 Status 는 DRAFT"); }

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
