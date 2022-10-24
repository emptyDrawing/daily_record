package test.sskim.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class StudyTest {

	// 5 버젼부터는 굳이 public 을 안달아도 됩니다.
	@Test
	void create() {
		Study study = new Study();
		assertNotNull(study);
		System.out.println("create");
	};

	@Test
	void create1() {
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
